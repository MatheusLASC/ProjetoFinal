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

    public void adiciona (Agendamento agendamento)
    {
        ar.save(agendamento);
    }

    public boolean salvar(Agendamento agendamento){
        
        List<Agendamento> agendamentos = ar.findAll();
        boolean v = false;

        for (Agendamento auxAgendamento : agendamentos) {
            if(agendamento.getData().equals(auxAgendamento.getData()))
            {
                if(agendamento.getProfissional().getId()==auxAgendamento.getProfissional().getId() || agendamento.getCliente().getId()==auxAgendamento.getCliente().getId())
                {
                    v=true;
                }
            }
        }

        if(v==false)
        {
            ar.save(agendamento);
            return true;
        }
        else
        {
            return  false;
        }
        
    }
    
    public boolean update(Agendamento agendamento){

        List<Agendamento> agendamentos = ar.findAll();
        Agendamento aux = ar.findById(agendamento.getId()).get();
         agendamentos.remove(aux); //para não analisar o mesmo objeto
         boolean v = false;
        
        
         for (Agendamento auxAgendamento : agendamentos) {
            if(agendamento.getData().equals(auxAgendamento.getData()))
            {
                if(agendamento.getProfissional().getId()==auxAgendamento.getProfissional().getId() || agendamento.getCliente().getId()==auxAgendamento.getCliente().getId())
                {
                    v=true;
                }
            }
        }
        
        if(v==false)
        {
              // recupera dados da associação
        List<Servico> auxs = ar.findById(agendamento.getId()).get().getServicos();
        agendamento.setServicos(auxs);
            ar.save(agendamento);
            return true;
        }
        else
        {
            return  false;
        }
        
    }

    public Agendamento getAgendamentobyID(int id)
    {
        return ar.findById(id).get();
    }

    public void remover(Agendamento agendamento) {
        ar.delete(agendamento);
	}

}