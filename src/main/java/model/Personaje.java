package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Personaje")
public class Personaje implements Serializable{
  @Id
  @Column(name = "PersonajeID")
  int PersonajeID;
  @Column(name = "Nombre", length = 30)
  String NombrePersonaje;
  @Column(name = "Numerodeestrellas")
  int Numerodeestrellas;
  @Column(name = "TipodeArma", length = 20)
  String TipodeArma;
  @Column(name = "Elemento", length = 20)
  String Elemento;
  @Column(name = "Sexo", length = 20)
  String Sexo;

  public Personaje(int PersonajeID, String NombrePersonaje, int TipodeArma,
                   String Elemento, String Numerodeestrellas, String Sexo, Region region) {
    super();
    this.PersonajeID = PersonajeID;
    this.NombrePersonaje = NombrePersonaje;
    this.Numerodeestrellas = Integer.parseInt(Numerodeestrellas);
    this.TipodeArma = String.valueOf(TipodeArma);
    this.Elemento = Elemento;
    this.Sexo = Sexo;
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "RegionID")
  Region region;

  public Personaje(int articleId, String title, Date creationDate, boolean publishable, Region region) {
    super();

  }

  public int getPersonajeID() {
    return PersonajeID;
  }

  public void setPersonajeID(int personajeID) {
    this.PersonajeID = personajeID;
  }

  public String getNombrePersonaje() {
    return NombrePersonaje;
  }

  public void setNombrePersonaje(String nombrepersonaje) {
    this.NombrePersonaje = nombrepersonaje;
  }

  public int getNumerodeestrellas(int i) {
    return Numerodeestrellas;
  }

  public void setNumerodeestrellas(int numerodeestrellas) {
    this.Numerodeestrellas = numerodeestrellas;
  }

  public String getTipodeArma() {
    return TipodeArma;
  }

  public void setTipodeArma(String tipodeArma) {
    this.TipodeArma = tipodeArma;
  }

  public String getElemento() {
    return Elemento;
  }

  public void setElemento(String elemento) {
    this.Elemento = elemento;
  }

  public String getSexo() {
    return Sexo;
  }

  public void setSexo(String sexo) {
    this.Sexo = sexo;
  }

  public Region getRegion() {
    return region;
  }

  //public List<Personaje> getArticles() {
  //return articles;
  //}

  public void setRegion(Region regions) {
    this.region = regions;
  }
  @Override
  public String toString() {
    return "Personaje{" +
            "PersonajeID=" + PersonajeID +
            ", Nombre='" + NombrePersonaje + '\'' +
            ", Numerodeestrellas=" + Numerodeestrellas +
            ", TipodeArma=" + TipodeArma +
            ", Elemento=" + Elemento +
            ", Sexo=" + Sexo +
            ", Region=" + region.toString() +
            '}';
  }

}
