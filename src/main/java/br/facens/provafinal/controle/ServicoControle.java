package br.facens.provafinal.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.facens.provafinal.entidade.Agendamento;
import br.facens.provafinal.entidade.Servico;
import br.facens.provafinal.servico.AgendamentoServico;
import br.facens.provafinal.servico.ServicoServico;

@Controller
public class ServicoControle {
    
    @Autowired
    private ServicoServico ss;

    @Autowired
    private AgendamentoServico as;

    @GetMapping("/servicos")
    public ModelAndView getServicos()
    {
        ModelAndView mv = new ModelAndView("servicoTemplate");
        mv.addObject("servicos",ss.getServicos());
        return mv;
    }

    @PostMapping("/associarServicoAgendamento")
    public String associaragendamento (@ModelAttribute Agendamento agendamento, @RequestParam Integer idservico)
    {
        //requestparam - via requisição, conforme onde estiver o serviço em questão
        Servico servico = ss.getServicobyID(idservico);
        agendamento = as.getAgendamentobyID(agendamento.getId());

        servico.getAgendamentos().add(agendamento);
        ss.salvar(servico); // funciona como update no salvar

        return "redirect:/detalhesservico/" + idservico;
    }

    @GetMapping("/detalhesservico/{id}")
    public ModelAndView getServicoDetalhes (@PathVariable(name= "id") Integer id)
    {
             Servico servico = ss.getServicobyID(id);
             ModelAndView mv = new ModelAndView("detalhesservico");
             mv.addObject("servico",servico);
             List<Agendamento> agendamentoNOTassociados = as.getAgendamentos();
             //remove os agendamentos que não estão associados a algum serviço
             //com isso mostra somente os agendamentos não associados
             agendamentoNOTassociados.removeAll(servico.getAgendamentos());
             mv.addObject("agendamentos", agendamentoNOTassociados);
             return mv;
    }

    @GetMapping("/editarServico")
    public ModelAndView editarServico(@RequestParam Integer id){
        
        ModelAndView mv = new ModelAndView("servicoEdit");
    
        Servico servico = ss.getServicobyID(id);
        mv.addObject("servico",  servico);

        return mv;
   
    }
    @PostMapping("/updateservico")
    public String update(@ModelAttribute Servico servico)
        {
            if(servico.getTipo().trim().isEmpty())
            {
                return "redirect:/servicos";
            }
        else
            {
                ss.update(servico);
                return "redirect:/servicos";
            }
               
        }

    @PostMapping("/salvarservico")
    public String salvar(@ModelAttribute  Servico servico)
        {
            
            if(servico.getTipo().trim().isEmpty())
            {
                return "redirect:/servicos";
            }
        else
            {
                ss.salvar(servico);
                return "redirect:/servicos";
            }
           
        }
   
        @GetMapping("/removerServico")
        public String removerServico(@RequestParam Integer id){
            
            Servico servico = ss.getServicobyID(id);
            ss.remover(servico);

            return "redirect:/servicos";
        }
}