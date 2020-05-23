package br.facens.provafinal.entidade;

import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Cliente implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String nome;
private String email;
private String endereco;
private int numero;

@OneToMany
@JoinColumn(name = "ID_CLIENTE")
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

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getEndereco() {
    return endereco;
}

public void setEndereco(String endereco) {
    this.endereco = endereco;
}

public int getNumero() {
    return numero;
}

public void setNumero(int numero) {
    this.numero = numero;
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
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
    result = prime * result + id;
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + numero;
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
    Cliente other = (Cliente) obj;
    if (agendamentos == null) {
        if (other.agendamentos != null)
            return false;
    } else if (!agendamentos.equals(other.agendamentos))
        return false;
    if (email == null) {
        if (other.email != null)
            return false;
    } else if (!email.equals(other.email))
        return false;
    if (endereco == null) {
        if (other.endereco != null)
            return false;
    } else if (!endereco.equals(other.endereco))
        return false;
    if (id != other.id)
        return false;
    if (nome == null) {
        if (other.nome != null)
            return false;
    } else if (!nome.equals(other.nome))
        return false;
    if (numero != other.numero)
        return false;
    return true;
}

@Override
public String toString() {
    return "Cliente [email=" + email + ", endereco=" + endereco + ", id=" + id + ", nome=" + nome + ", numero=" + numero
            + "]";
}



}