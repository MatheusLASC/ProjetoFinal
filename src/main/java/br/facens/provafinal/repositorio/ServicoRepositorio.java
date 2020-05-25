package br.facens.provafinal.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.facens.provafinal.entidade.Servico;

public interface ServicoRepositorio extends JpaRepository <Servico,Integer>{
    
}