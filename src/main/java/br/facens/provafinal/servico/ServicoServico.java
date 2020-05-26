package br.facens.provafinal.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.facens.provafinal.entidade.Agendamento;
import br.facens.provafinal.entidade.Servico;
import br.facens.provafinal.repositorio.ServicoRepositorio;

@Service
public class ServicoServico {
    
    @Autowired
    private ServicoRepositorio sr;

    public List<Servico> getServicos()
    {
        return sr.findAll();
    }

    public void salvar(Servico servico){
        sr.save(servico);
    }
    
    public void update (Servico servico)
    {
        // recupera dados
        List<Agendamento> aux = sr.findById(servico.getId()).get().getAgendamentos();
            servico.setAgendamentos(aux);
            sr.save(servico);
    }

    public Servico getServicobyID(int id)
    {
        return sr.findById(id).get();
    }

    public void remover(Servico servico) {
        sr.delete(servico);
	}
}