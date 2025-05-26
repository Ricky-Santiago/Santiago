/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author RICKY SANTIAGO
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodiUsua", query = "SELECT u FROM Usuario u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuario.findByNdniUsua", query = "SELECT u FROM Usuario u WHERE u.ndniUsua = :ndniUsua"),
    @NamedQuery(name = "Usuario.findByLogiUsua", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua"),
    @NamedQuery(name = "Usuario.findByPassUsua", query = "SELECT u FROM Usuario u WHERE u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByNiveUsua", query = "SELECT u FROM Usuario u WHERE u.niveUsua = :niveUsua"),
    @NamedQuery(name = "Usuario.validar", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua and u.passUsua = :passUsua"),

    @NamedQuery(name = "Usuario.findByActiUsua", query = "SELECT u FROM Usuario u WHERE u.actiUsua = :actiUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codiUsua")
    private Integer codiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ndniUsua")
    private String ndniUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "logiUsua")
    private String logiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "passUsua")
    private String passUsua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveUsua")
    private int niveUsua;
    @Column(name = "actiUsua")
    private Boolean actiUsua;

    public Usuario() {
    }

    public Usuario(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuario(Integer codiUsua, String ndniUsua, String logiUsua, String passUsua, int niveUsua) {
        this.codiUsua = codiUsua;
        this.ndniUsua = ndniUsua;
        this.logiUsua = logiUsua;
        this.passUsua = passUsua;
        this.niveUsua = niveUsua;
    }

    public Integer getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(Integer codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getNdniUsua() {
        return ndniUsua;
    }

    public void setNdniUsua(String ndniUsua) {
        this.ndniUsua = ndniUsua;
    }

    public String getLogiUsua() {
        return logiUsua;
    }

    public void setLogiUsua(String logiUsua) {
        this.logiUsua = logiUsua;
    }

    public String getPassUsua() {
        return passUsua;
    }

    public void setPassUsua(String passUsua) {
        this.passUsua = passUsua;
    }

    public int getNiveUsua() {
        return niveUsua;
    }

    public void setNiveUsua(int niveUsua) {
        this.niveUsua = niveUsua;
    }

    public Boolean getActiUsua() {
        return actiUsua;
    }

    public void setActiUsua(Boolean actiUsua) {
        this.actiUsua = actiUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ codiUsua=" + codiUsua + " ]";
    }
    
}
