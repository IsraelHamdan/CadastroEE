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
@Table(name = "PessoaJuridicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaJuridicas.findAll", query = "SELECT p FROM PessoaJuridicas p"),
    @NamedQuery(name = "PessoaJuridicas.findByIdPJ", query = "SELECT p FROM PessoaJuridicas p WHERE p.idPJ = :idPJ"),
    @NamedQuery(name = "PessoaJuridicas.findByCnpj", query = "SELECT p FROM PessoaJuridicas p WHERE p.cnpj = :cnpj")})
public class PessoaJuridicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPJ")
    private Integer idPJ;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "cnpj")
    private String cnpj;
    @JoinColumn(name = "idPJ", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoas pessoas;

    public PessoaJuridicas() {
    }

    public PessoaJuridicas(Integer idPJ) {
        this.idPJ = idPJ;
    }

    public PessoaJuridicas(Integer idPJ, String cnpj) {
        this.idPJ = idPJ;
        this.cnpj = cnpj;
    }

    public Integer getIdPJ() {
        return idPJ;
    }

    public void setIdPJ(Integer idPJ) {
        this.idPJ = idPJ;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        hash += (idPJ != null ? idPJ.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaJuridicas)) {
            return false;
        }
        PessoaJuridicas other = (PessoaJuridicas) object;
        if ((this.idPJ == null && other.idPJ != null) || (this.idPJ != null && !this.idPJ.equals(other.idPJ))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroee.model.PessoaJuridicas[ idPJ=" + idPJ + " ]";
    }
    
}
