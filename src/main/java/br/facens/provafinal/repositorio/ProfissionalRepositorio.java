package br.facens.provafinal.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.facens.provafinal.entidade.Profissional;

public interface ProfissionalRepositorio extends JpaRepository <Profissional, Integer> {
    
}