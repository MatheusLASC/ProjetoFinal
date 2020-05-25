package br.facens.provafinal.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import br.facens.provafinal.entidade.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente,Integer>{
    
}