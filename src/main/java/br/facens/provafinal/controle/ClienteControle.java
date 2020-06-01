package br.facens.provafinal.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.facens.provafinal.entidade.Cliente;
import br.facens.provafinal.servico.ClienteServico;

@Controller
public class ClienteControle {
    @Autowired
    private ClienteServico cs;

    @GetMapping("/clientes")
    public ModelAndView getClientes()
    {
        ModelAndView mv = new ModelAndView("clientesTemplate");
        mv.addObject("clientes",cs.getClientes());
        return mv;
    }

    @GetMapping("/erroremoverC")
    public ModelAndView Erro()
    {
     
        ModelAndView mv = new ModelAndView("erroRemoverTemplate");
        return mv;
    }

    @GetMapping("/detalhescliente/{id}")
    public ModelAndView getClienteDetalhes(@PathVariable(name="id") Integer id) {
       Cliente cliente = cs.getClientebyID(id);
       ModelAndView mv = new ModelAndView("detalhescliente");
       mv.addObject("cliente", cliente);
       

        return mv;
    }

    @GetMapping("/editarCliente")
    public ModelAndView editarCliente(@RequestParam Integer id){
        
        ModelAndView mv = new ModelAndView("clienteEdit");
        Cliente cliente = cs.getClientebyID(id);
        mv.addObject("cliente",  cliente);

        return mv;
   
    }

    @PostMapping("/updatecliente")
    public String update(@ModelAttribute Cliente cliente)
        {
            if(cliente.getNome().trim().isEmpty() || cliente.getEmail().trim().isEmpty() || cliente.getNome().trim().isEmpty())    
            {
                return "redirect:/clientes";
            }
        else
            {
                cs.update(cliente); 
                return "redirect:/clientes";
            }    
       
        }

    @PostMapping("/salvarcliente")
    public String salvar(@ModelAttribute Cliente cliente)
        {
            if(cliente.getNome().trim().isEmpty() || cliente.getEmail().trim().isEmpty() || cliente.getNome().trim().isEmpty())    
            {
                return "redirect:/clientes";
            }
        else
            {
                cs.salvar(cliente);; 
                return "redirect:/clientes";
            }    
           
        }

        @GetMapping("/removerCliente")
        public String removerProfissional(@RequestParam Integer id)
         {
               boolean v; 
               Cliente cliente = cs.getClientebyID(id);
               
               v = cs.remover(cliente);
               if(v==false)
               {
                   return "redirect:/erroremoverC";
               }
               else
               {
                    return "redirect:/clientes";
               }
                
            }    
    
}