package br.facens.provafinal.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
public class Servico implements Serializable{

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String tipo;
private double preco;

@ManyToMany
@JoinTable
(name="AgendamentosServicos",
uniqueConstraints = @UniqueConstraint(columnNames = {"id_servico","id_agendamento"}),
joinColumns = @JoinColumn(name="id_servico"),
inverseJoinColumns = @JoinColumn(name="id_agendamento")
) 
private List<Agendamento> agendamentos;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getTipo() {
    return tipo;
}

public void setTipo(String tipo) {
    this.tipo = tipo;
}

public double getPreco() {
    return preco;
}

public void setPreco(double preco) {
    this.preco = preco;
}

public List<Agendamento> getAgendamentos() {
    return agendamentos;
}

public void setAgendamentos(List<Agendamento> agendamentos) {
    this.agendamentos = agendamentos;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((agendamentos == null) ? 0 : agendamentos.hashCode());
    result = prime * result + id;
    long temp;
    temp = Double.doubleToLongBits(preco);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Servico other = (Servico) obj;
    if (agendamentos == null) {
        if (other.agendamentos != null)
            return false;
    } else if (!agendamentos.equals(other.agendamentos))
        return false;
    if (id != other.id)
        return false;
    if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
        return false;
    if (tipo == null) {
        if (other.tipo != null)
            return false;
    } else if (!tipo.equals(other.tipo))
        return false;
    return true;
}

@Override
public String toString() {
    return "Servico [id=" + id + ", preco=" + preco + ", tipo=" + tipo + "]";
}


}