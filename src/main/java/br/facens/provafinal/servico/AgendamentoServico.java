package br.facens.provafinal.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.facens.provafinal.entidade.Agendamento;
import br.facens.provafinal.entidade.Servico;
import br.facens.provafinal.repositorio.AgendamentoRepositorio;

@Service
public class AgendamentoServico {

    @Autowired
    private AgendamentoRepositorio ar;

    public List<Agendamento> getAgendamentos(){
        return ar.findAll();
    }

    public void salvar(Agendamento agendamento){
        ar.save(agendamento);
    }
    
    public void update(Agendamento agendamento){
        // recupera dados da associação
        List<Servico> aux = ar.findById(agendamento.getId()).get().getServicos();
            agendamento.setServicos(aux);
            ar.save(agendamento);
    }

    public Agendamento getAgendamentobyID(int id)
    {
        return ar.findById(id).get();
    }

    public void remover(Agendamento agendamento) {
        ar.delete(agendamento);
	}

}