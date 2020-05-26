package br.facens.provafinal.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.facens.provafinal.entidade.Agendamento;
import br.facens.provafinal.entidade.Cliente;
import br.facens.provafinal.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {
    
    @Autowired
    private ClienteRepositorio cr;

    public List<Cliente> getClientes(){
        return cr.findAll();
    }

    public void salvar(Cliente cliente){
        cr.save(cliente);
    }
    
    public void update (Cliente cliente)
    {
        // recupera dados
        List<Agendamento> aux = cr.findById(cliente.getId()).get().getAgendamentos();
            cliente.setAgendamentos(aux);
            cr.save(cliente);
    }

    public Cliente getClientebyID(int id)
    {
        return cr.findById(id).get();
    }

    public void remover(Cliente cliente) {
        cr.delete(cliente);
	}
}