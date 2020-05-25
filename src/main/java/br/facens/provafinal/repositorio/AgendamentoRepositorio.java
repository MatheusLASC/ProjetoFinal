package br.facens.provafinal.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.facens.provafinal.entidade.Agendamento;

public interface AgendamentoRepositorio  extends JpaRepository <Agendamento, Integer>{
    
}