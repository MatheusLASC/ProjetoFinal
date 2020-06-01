package br.facens.provafinal.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
public class Agendamento implements Serializable {

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String data;


@ManyToMany
@JoinTable
(name="AgendamentosServicos",
uniqueConstraints = @UniqueConstraint(columnNames = {"id_servico","id_agendamento"}),
joinColumns = @JoinColumn(name="id_agendamento"),
inverseJoinColumns = @JoinColumn(name="id_servico")
) 
private List<Servico> servicos;

@ManyToOne 
@JoinColumn(name="ID_PROFISSIONAL")
private Profissional profissional;

@ManyToOne 
@JoinColumn(name="ID_CLIENTE")
private Cliente cliente;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getData() {
    return data;
}

public void setData(String data) {
    this.data = data;
}

public List<Servico> getServicos() {
    return servicos;
}

public void setServicos(List<Servico> servicos) {
    this.servicos = servicos;
}

public Profissional getProfissional() {
    return profissional;
}

public void setProfissional(Profissional profissional) {
    this.profissional = profissional;
}

public Cliente getCliente() {
    return cliente;
}

public void setCliente(Cliente cliente) {
    this.cliente = cliente;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    result = prime * result + ((data == null) ? 0 : data.hashCode());
    result = prime * result + id;
    result = prime * result + ((profissional == null) ? 0 : profissional.hashCode());
    result = prime * result + ((servicos == null) ? 0 : servicos.hashCode());
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
    Agendamento other = (Agendamento) obj;
    if (cliente == null) {
        if (other.cliente != null)
            return false;
    } else if (!cliente.equals(other.cliente))
        return false;
    if (data == null) {
        if (other.data != null)
            return false;
    } else if (!data.equals(other.data))
        return false;
    if (id != other.id)
        return false;
    if (profissional == null) {
        if (other.profissional != null)
            return false;
    } else if (!profissional.equals(other.profissional))
        return false;
    if (servicos == null) {
        if (other.servicos != null)
            return false;
    } else if (!servicos.equals(other.servicos))
        return false;
    return true;
}

@Override
public String toString() {
    return "Agendamento [data=" + data + ", id=" + id + "]";
}



}