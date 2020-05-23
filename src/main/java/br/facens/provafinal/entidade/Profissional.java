package br.facens.provafinal.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Profissional implements Serializable{

private static final long serialVersionUID = 1L;
@GeneratedValue(strategy = GenerationType.AUTO)
@Id
private int id;
private String nome;
private double salario;
private double porcentagemlucro;

@OneToMany
@JoinColumn(name = "ID_PROFISSIONAL")
private List<Agendamento> agendamentos;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public double getSalario() {
    return salario;
}

public void setSalario(double salario) {
    this.salario = salario;
}

public double getPorcentagemlucro() {
    return porcentagemlucro;
}

public void setPorcentagemlucro(double porcentagemlucro) {
    this.porcentagemlucro = porcentagemlucro;
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
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    long temp;
    temp = Double.doubleToLongBits(porcentagemlucro);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(salario);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    Profissional other = (Profissional) obj;
    if (agendamentos == null) {
        if (other.agendamentos != null)
            return false;
    } else if (!agendamentos.equals(other.agendamentos))
        return false;
    if (id != other.id)
        return false;
    if (nome == null) {
        if (other.nome != null)
            return false;
    } else if (!nome.equals(other.nome))
        return false;
    if (Double.doubleToLongBits(porcentagemlucro) != Double.doubleToLongBits(other.porcentagemlucro))
        return false;
    if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
        return false;
    return true;
}

@Override
public String toString() {
    return "Profissional [id=" + id + ", nome=" + nome + ", porcentagemlucro=" + porcentagemlucro + ", salario="
            + salario + "]";
}


}