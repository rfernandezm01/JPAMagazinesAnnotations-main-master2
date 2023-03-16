package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Region")
public class Region implements Serializable {
  @Id
  @Column(name = "RegionID")
  int Regionid;
  @Column(name = "nom", length = 30)
  String Nomregion;
  @Column(name = "Habitantes")
  int Habitantes;
  @Column(name = "Elemento", length = 20)
  String Elemento;
  @Column(name = "Nombrearconte", length = 30)
  String Nombrearconte;
  @Column(name = "Mundo", length = 20)
  String Mundo;
  public Region(int Regionid, String Nomregion, int Habitantes, String Elemento,
                String Nombrearconte, String Mundo) {
    super();
    this.Regionid = Regionid;
    this.Nomregion = Nomregion;
    this.Habitantes = Habitantes;
    this.Elemento = Elemento;
    this.Nombrearconte = Nombrearconte;
    this.Mundo = Mundo;

  }
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "RegionID", referencedColumnName = "RegionID")
  List<Personaje> personajes;

  public Region(int id, String name, String country, String year, boolean active) {

  }

  public int getRegionid() {
    return Regionid;
  }

  public void setRegionid(int regionid) {
    this.Regionid = regionid;
  }

  public String getNomregion() {
    return Nomregion;
  }

  public void setNomregion(String nomregion) {
    this.Nomregion = nomregion;
  }

  public int getHabitantes() { return Habitantes; }

  public void setHabitantes(int habitantes) {
    this.Habitantes = habitantes;
  }

  public String getElemento() {
    return Elemento;
  }

  public void setElemento(String elemento) {
    this.Elemento = elemento;
  }

  public String getNombrearconte() {
    return Nombrearconte;
  }

  public void setNombrearconte(String nombrearconte) {
    this.Nombrearconte = nombrearconte;
  }

  public String getMundo() {
    return Mundo;
  }

  public void setMundo(String mundo) {
    this.Mundo = mundo;
  }
  public List<Personaje> getPersonajes() {
    return personajes;
  }

  //public List<Personaje> getArticles() {
  //return articles;
  //}

  public void setPersonajes(List<Personaje> personajes) {
    this.personajes = personajes;
  }


  @Override
  public String toString() {
    String result =  "Region [RegionID=" + Regionid + ", Nomregion=" + Nomregion + ", Habitantes=" + Habitantes
        + ", Elemento=" + Elemento + ", Nombrearconte=" + Nombrearconte + ", Mundo=" + Mundo +", Personajes= [ ";


    for (Personaje p : personajes)
    {
      result += p.toString();
    }

    result += " ]";
    return result;

  }


}
