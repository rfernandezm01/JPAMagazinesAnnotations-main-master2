package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Armas")
public class Armas implements Serializable {
  @Id
  @Column(name = "ArmaID")
  private int ArmaID;
  @Column(name = "Nombre", length = 30)
  private String NombreArma;
  @Column(name = "TipodeArma", length = 20)
  private String TipodeArmas;
  @Column(name = "Numerodeestrellas")
  private int NumerodeestrellasArma;
  @Column(name = "PuntosAtaque")
  private int PuntosAtaque;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ArmaID", referencedColumnName = "ArmaID")
  public Personaje personaje;

  public Armas(int ArmaID, String NombreArma, String TipodeArmas, int NumertodeestrellasArma, int PuntosAtaque) {
    super();
    this.NombreArma = NombreArma;
    this.TipodeArmas = TipodeArmas;
    this.ArmaID = ArmaID;
    this.NumerodeestrellasArma = NumertodeestrellasArma;
    this.PuntosAtaque = PuntosAtaque;
  }

  public Armas(int magazineId, String title, String publicationDate) {
    super();
  }

  public int getArmaID() {
    return ArmaID;
  }

  public void setArmaID(int armaID) {
    this.ArmaID = armaID;
  }

  public String getNombreArma() {
    return NombreArma;
  }

  public void setNombreArma(String nombredearma) {
    this.NombreArma = nombredearma;
  }

  public String getTipodeArmas() {
    return TipodeArmas;
  }

  public void setTipodeArmas(String tiposdearmas) {
    this.TipodeArmas = tiposdearmas;
  }

  public int getNumerodeestrellasArma() {
    return NumerodeestrellasArma;
  }

  public void setNumerodeestrellasArma(int numerodeestrellasArma) {
    this.NumerodeestrellasArma = numerodeestrellasArma;
  }

  public int getPuntosAtaque() {
    return PuntosAtaque;
  }

  public void setPuntosAtaque(int puntosAtaque) {
    this.PuntosAtaque = puntosAtaque;
  }

  public Personaje getPersonaje() {
    return personaje;
  }

  //public List<Personaje> getArticles() {
  //return articles;
  //}

  public void setPersonaje(Personaje personaje) {
    this.personaje = personaje;
  }


  @Override
  public String toString() {
    //String result =
    return "Armas [ArmaID=" + ArmaID + ",Nombre=" + NombreArma + ", TipodeArma="
            + TipodeArmas + ", NumerodeestrellasArma=" + NumerodeestrellasArma + ", PuntosAtaque=" + PuntosAtaque + ", Personaje=" + personaje.toString() + "]";
  }

  public void addPersonaje(Personaje personaje) {
  }
}

    /*result += "\n Llista de Armas: [ \n";

    /* for (Personaje a : Personaje) {
      result += "\t";
      result += a.toString();
      result += "\n";
    }

    result += "] \n";

    return result;
  }

}
     */
