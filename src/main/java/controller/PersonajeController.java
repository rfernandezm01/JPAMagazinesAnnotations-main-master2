package controller;

import model.Personaje;
import model.Region;
import model.Armas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class PersonajeController {
  //Article

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  private ArmasController armasController = new ArmasController(connection);
  private RegionController regionController = new RegionController(connection);
  private List<model.Personaje> Personaje;

  public PersonajeController(Connection connection) {this.connection = connection;}

  public PersonajeController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param personajesFile Aquest String correspon amb l'arxiu on s'emmagatzemen les
   *                     dades de les isntancies de Revista
   * @return ArrayList d'objectes Revista, amb els seus articles i la
   * informació de l'autor
   * @throws IOException <dt><b>Preconditions:</b>
   *                     <dd>
   *                     filename<>nil </br> llistaRevistes<>nil </br>
   *                     llistaRevistes.getRevista(i).getArticles()== nil</br>
   *                     <dt><b>Postconditions:</b>
   *                     <dd>
   *                     llistaRevistes.getRevistes()<>nil</br>
   *                     llistaRevistes.getRevista(i).getArticles()<>nil</br>
   *                     llistaRevistes.getRevista(i).getArticle(j)<>nil</br>
   *                     llistaRevistes
   *                     .getRevista(i).getArticle(j).getAutor()<>nil</br>
   */

  public List<Personaje> readPersonajesFile(String personajesFile, String regionesFile, String armasFile) throws IOException {

      int PersonajeID, ArmaID, Regionid, Numerodeestrellas;
      String NombrePersonaje, TipodeArma, Elemento, Sexo;




      BufferedReader br2 = new BufferedReader(new FileReader(personajesFile));
      String linea2 = "";

      List<Armas> armasList = armasController.readArmasFile(armasFile);
      List<Region> regionList = regionController.readRegionesFile(regionesFile);

      while ((linea2 = br2.readLine()) != null) {
        StringTokenizer str = new StringTokenizer(linea2, ",");
        PersonajeID = Integer.parseInt(str.nextToken());
        ArmaID = Integer.parseInt(str.nextToken());
        Regionid = Integer.parseInt(str.nextToken());
        NombrePersonaje = str.nextToken();
        Numerodeestrellas = Integer.parseInt(str.nextToken());
        TipodeArma = str.nextToken();
        Elemento = str.nextToken();
        Sexo = str.nextToken();



        armasList.get(ArmaID - 1).addPersonaje(new Personaje(PersonajeID, NombrePersonaje, Numerodeestrellas, TipodeArma, Elemento , Sexo, regionList.get(Regionid - 1)));

    }
    br2.close();
   return Personaje;
  }

  /* Method to CREATE an Article in the database */
  public void addPersonaje(Personaje personaje) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(personaje);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Articles */
  public void listPersonajes() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Personaje> result = em.createQuery("from Personaje", Personaje.class)
        .getResultList();
    for (Personaje personaje : result) {
      System.out.println(personaje.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Article */
  public void updatePersonaje(Integer personajeId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Personaje personaje = (Personaje) em.find(Personaje.class, personajeId);
    em.merge(personaje);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Article from the records */
  public void deletePersonaje(Integer personajeId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Personaje personaje = (Personaje) em.find(Personaje.class, personajeId);
    em.remove(personaje);
    em.getTransaction().commit();
    em.close();
  }

  public ArrayList<ArrayList<String>> readRegionesPersonajes (String regionesPersonajes) throws IOException {

    BufferedReader br2 = new BufferedReader(new FileReader(regionesPersonajes));
    String linea = "";

    ArrayList<ArrayList<String>> resultado = new ArrayList<ArrayList<String>>();

    String regionId;
    String personajeId;



    while ((linea = br2.readLine()) != null) {

      ArrayList<String> line = new ArrayList<String>();
      StringTokenizer str = new StringTokenizer(linea, ",");
      regionId = str.nextToken();
      personajeId = str.nextToken();

      line.add(regionId);
      line.add(personajeId);

      resultado.add(line);
    }
    br2.close();
    return resultado;
  }
}

