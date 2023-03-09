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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonajeController {
  //Article

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  private ArmasController magazineController = new ArmasController(connection);
  private RegionController authorController = new RegionController(connection);

  public PersonajeController(Connection connection) {
    this.connection = connection;
  }

  public PersonajeController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param articlesFile Aquest String correspon amb l'arxiu on s'emmagatzemen les
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
  public List<Armas> readArticlesFile(String articlesFile, String magazinesFile, String authorsFile)
      throws IOException {
    int PersonajeID, ArmaID, Regionid, Numerodeestrellas;
    String NombrePersonaje, TipodeArma, Elemento, Sexo;




    BufferedReader br = new BufferedReader(new FileReader(articlesFile));
    String linea = "";

    List<Armas> magazinesList = magazineController.readMagazinesFile(magazinesFile);
    List<Region> authorList = authorController.readAuthorsFile(authorsFile);

    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      PersonajeID = Integer.parseInt(str.nextToken());
      ArmaID = Integer.parseInt(str.nextToken());
      Regionid = Integer.parseInt(str.nextToken());
      NombrePersonaje = str.nextToken();
      Numerodeestrellas = Integer.parseInt(str.nextToken());
      TipodeArma = str.nextToken();
      Elemento = str.nextToken();
      Sexo = str.nextToken();


        magazinesList.get(ArmaID - 1).addPersonaje(new Personaje(PersonajeID, NombrePersonaje, Numerodeestrellas, TipodeArma, Elemento , Sexo, authorList.get(Regionid - 1)));

    br.close();

    return magazinesList;
  }

  public List<Personaje>  readArticlesFile(String articlesFile, String authorsFile) throws IOException {
    int articleId, magazineId, authorId;
    String title;
    Date creationDate;
    boolean publishable;
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

    BufferedReader br = new BufferedReader(new FileReader(articlesFile));
    String linea = "";
    List<Region> authorList = authorController.readAuthorsFile(authorsFile);
    List<Personaje> articlesList = new ArrayList<Personaje>();

    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      articleId = Integer.parseInt(str.nextToken());
      magazineId = Integer.parseInt(str.nextToken());
      authorId = Integer.parseInt(str.nextToken());
      title = str.nextToken();

      try {
        creationDate = dateFormat.parse(str.nextToken());
        publishable = Boolean.parseBoolean(str.nextToken());

        articlesList.add(new Personaje(articleId, title, creationDate, publishable, authorList.get(authorId - 1)));

      } catch (ParseException e) {

        e.printStackTrace();
      }

    }
    br.close();
    return articlesList;

  }

  /* Method to CREATE an Article in the database */
  public void addArticle(Personaje article) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(article);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Articles */
  public void listArticles() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Personaje> result = em.createQuery("from Personaje", Personaje.class)
        .getResultList();
    for (Personaje article : result) {
      System.out.println(article.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Article */
  public void updateArticle(Integer articleId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Personaje article = (Personaje) em.find(Personaje.class, articleId);
    em.merge(article);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Article from the records */
  public void deleteArticle(Integer articleId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Personaje article = (Personaje) em.find(Personaje.class, articleId);
    em.remove(article);
    em.getTransaction().commit();
    em.close();
  }


}

