package br.facens.provafinal.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.facens.provafinal.entidade.Profissional;
import br.facens.provafinal.servico.ProfissionalServico;

@Controller
public class ProfissionalControle {
    
    @Autowired
    private ProfissionalServico ps;

    @GetMapping("/profissionais")
    public ModelAndView getProfissionais()
    {
        ModelAndView mv = new ModelAndView("profissionaisTemplate");
        mv.addObject("profissionais",ps.getProfissionais());
        return mv;
    }


    @GetMapping("/detalhesprofissional/{id}")
    public ModelAndView getProfissionalDetalhes(@PathVariable(name="id") Integer id) {
       Profissional profissional = ps.getProfissionalbyID(id);
       ModelAndView mv = new ModelAndView("detalhesprofissional");
       mv.addObject("profissional", profissional);
       

        return mv;
    }

    @GetMapping("/editarProfissional")
    public ModelAndView editarProfissional(@RequestParam Integer id){
        
        ModelAndView mv = new ModelAndView("profissionalEdit");
        Profissional profissional = ps.getProfissionalbyID(id);
        mv.addObject("profissional",  profissional);

        return mv;
   
    }

    @PostMapping("/updateprofissional")
    public String update(@ModelAttribute Profissional profissional)
        {
            if(profissional.getNome().trim().isEmpty())    
            {
                return "redirect:/profissionais";
            }
        else
            {
                ps.update(profissional); 
                return "redirect:/profissionais";
            }    
       
        }

    @PostMapping("/salvarprofissional")
    public String salvar(@ModelAttribute Profissional profissional)
        {
            if(profissional.getNome().trim().isEmpty())    
            {
                return "redirect:/profissionais";
            }
        else
            {
                ps.salvar(profissional); 
                return "redirect:/profissionais";
            }    
       
        }
}