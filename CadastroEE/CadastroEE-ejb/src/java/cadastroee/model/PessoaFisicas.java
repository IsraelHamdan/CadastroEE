/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroee.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Cleyton
 */
@Entity
@Table(name = "PessoaFisicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaFisicas.findAll", query = "SELECT p FROM PessoaFisicas p"),
    @NamedQuery(name = "PessoaFisicas.findByIdPessoaFisica", query = "SELECT p FROM PessoaFisicas p WHERE p.idPessoaFisica = :idPessoaFisica"),
    @NamedQuery(name = "PessoaFisicas.findByCpf", query = "SELECT p FROM PessoaFisicas p WHERE p.cpf = :cpf")})
public class PessoaFisicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPessoaFisica")
    private Integer idPessoaFisica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cpf")
    private String cpf;
    @JoinColumn(name = "idPessoaFisica", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoas pessoas;

    public PessoaFisicas() {
    }

    public PessoaFisicas(Integer idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public PessoaFisicas(Integer idPessoaFisica, String cpf) {
        this.idPessoaFisica = idPessoaFisica;
        this.cpf = cpf;
    }

    public Integer getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Integer idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoas getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoas pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoaFisica != null ? idPessoaFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaFisicas)) {
            return false;
        }
        PessoaFisicas other = (PessoaFisicas) object;
        if ((this.idPessoaFisica == null && other.idPessoaFisica != null) || (this.idPessoaFisica != null && !this.idPessoaFisica.equals(other.idPessoaFisica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroee.model.PessoaFisicas[ idPessoaFisica=" + idPessoaFisica + " ]";
    }
    
}
