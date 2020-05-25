package br.facens.provafinal.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.facens.provafinal.entidade.Agendamento;
import br.facens.provafinal.entidade.Profissional;
import br.facens.provafinal.repositorio.ProfissionalRepositorio;

@Service
public class ProfissionalServico {
 
    @Autowired
    private ProfissionalRepositorio pr;

    public List<Profissional> getProfissionais(){
        return pr.findAll();
    }

    public void salvar(Profissional profissional){
        pr.save(profissional);
    }
    
    public void update(Profissional profissional)
    {
        // recupera dados
        List<Agendamento> aux = pr.findById(profissional.getId()).get().getAgendamentos();
            profissional.setAgendamentos(aux);;
            pr.save(profissional);
    }

    public Profissional getProfissionalbyID(int id)
    {
        return pr.findById(id).get();
    }

    public void remover(Profissional profissional) {
        pr.delete(profissional);
	}

}