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
import br.facens.provafinal.servico.ClienteServico;
import br.facens.provafinal.servico.ProfissionalServico;
import br.facens.provafinal.servico.ServicoServico;

@Controller
public class AgendamentoControle {
    @Autowired
    private AgendamentoServico as;

    @Autowired
    private ClienteServico cs;

    @Autowired
    private ProfissionalServico ps;

    @Autowired
    private ServicoServico ss;

    @GetMapping("/agendamentos")
    public ModelAndView getAgendamentos()
    {
     
        ModelAndView mv = new ModelAndView("agendamentosTemplate");
        mv.addObject("agendamento", new Agendamento()); 
        mv.addObject("agendamentos",as.getAgendamentos());
        mv.addObject("clientes", cs.getClientes());
        mv.addObject("profissionais", ps.getProfissionais());
        return mv;
    }

    @GetMapping("/erroagendamento")
    public ModelAndView Erro()
    {
     
        ModelAndView mv = new ModelAndView("erroAgendamentoTemplate");
        return mv;
    }

    @PostMapping("/associarAgendamentoServico")
    public String associarservico (@ModelAttribute Servico servico, @RequestParam Integer idagendamento)
    {
        //requestparam - via requisição, conforme onde estiver o servico em questão
        Agendamento agendamento = as.getAgendamentobyID(idagendamento);
        servico = ss.getServicobyID(servico.getId());

        agendamento.getServicos().add(servico);
        as.adiciona(agendamento); // funciona como update

        return "redirect:/detalhesagendamento/" + idagendamento;
    }

    @GetMapping("/detalhesagendamento/{id}")
    public ModelAndView getAgendamentoDetalhes (@PathVariable(name= "id") Integer id)
    {
             Agendamento agendamento = as.getAgendamentobyID(id);
             ModelAndView mv = new ModelAndView("detalhesagendamento");
             mv.addObject("agendamento",agendamento);
             List<Servico> servicosNOTassociados = ss.getServicos();
             //remove os servicos que não estão associados a algum agendamento
             //com isso mostra somente os servicos não associados
             servicosNOTassociados.removeAll(agendamento.getServicos());
             mv.addObject("servicos", servicosNOTassociados);
             return mv;
    }

    @GetMapping("/editarAgendamento")
    public ModelAndView editarAgendamento(@RequestParam Integer id){
        
        ModelAndView mv = new ModelAndView("agendamentoEdit");
    
        Agendamento agendamento = as.getAgendamentobyID(id);
        mv.addObject("agendamento",  agendamento);
        mv.addObject("clientes", cs.getClientes());
        mv.addObject("profissionais", ps.getProfissionais());

        
        return mv;
   
    }
   
    @PostMapping("/updateagendamento")
    public String update(@ModelAttribute Agendamento agendamento)
        {
            boolean v;
         if(agendamento.getData().trim().isEmpty())
            {
                return "redirect:/agendamentos";
            } 
        else
            {
                v = as.update(agendamento);
                if(v==false)
                {
                    return "redirect:/erro";
                }
                else
                {
                    return "redirect:/agendamentos";
                }
                
            }                  
            
        }

    @PostMapping("/salvaragendamento")
    public String salvar(@ModelAttribute Agendamento agendamento)
        {
            boolean v;
            if(agendamento.getData().trim().isEmpty())
            {
                return "redirect:/agendamentos";
            } 
        else
            {
                v = as.salvar(agendamento);
                if(v==false)
                {
                    return "redirect:/erroagendamento";
                }
                else
                {
                    return "redirect:/agendamentos";
                }
                
            }                  
            
            
        }
        
        @GetMapping("/removerAgendamento")
        public String removerAgendamento(@RequestParam Integer id){
            
            Agendamento agendamento = as.getAgendamentobyID(id);
            as.remover(agendamento);

            return "redirect:/agendamentos";
        }
    
}