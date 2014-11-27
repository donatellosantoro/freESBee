/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.comune;


import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cittadino
{
  private Long id;
  private String codiceFiscale;
  private String nome;
  private String cognome;
  private Calendar dataNascita;
  private String comuneNascita;
  private String comuneResidenza;
  private String via;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodiceFiscale() {
    return this.codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public String getCognome() {
    return this.cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  @Temporal(TemporalType.DATE)
  public Calendar getDataNascita() {
    return this.dataNascita;
  }

  public void setDataNascita(Calendar dataNascita) {
    this.dataNascita = dataNascita;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
  
    
}
