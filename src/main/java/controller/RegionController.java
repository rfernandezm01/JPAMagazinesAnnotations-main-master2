package controller;

import model.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RegionController {
//Author
  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public RegionController(Connection connection) {
    this.connection = connection;
  }

  public RegionController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  public List<Region> readAuthorsFile(String filename) throws IOException {
    int Regionid, Habitantes;
    String Nomregion, Elemento, Nombrearconte, Mundo;
    List<Region> authorsList = new ArrayList<Region>();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      Regionid = Integer.parseInt(str.nextToken());
      Nomregion = str.nextToken();
      Habitantes = Integer.parseInt(str.nextToken());
      Elemento = str.nextToken();
      Nombrearconte = str.nextToken();
      Mundo = str.nextToken();
      // System.out.println(id + name + country + year + active);
      authorsList.add(new Region(Regionid, Nomregion, Habitantes, Elemento, Nombrearconte, Mundo));

    }
    br.close();

    return authorsList;
  }

  public void printAutors(ArrayList<Region> authorsList) {
    for (int i = 0; i < authorsList.size(); i++) {
      System.out.println(authorsList.get(i).toString());
    }
  }


  /* Method to CREATE an Autor in the database */
  public void addAuthor(Region author) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region authotExists = (Region) em.find(Region.class, author.getRegionid());
    if (authotExists == null ){
      System.out.println("insert autor");
      em.persist(author);
    }
    em.getTransaction().commit();
    em.close();
  }


  /* Method to READ all Autors */
  public void listAuthors() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Region> result = em.createQuery("from Region", Region.class)
        .getResultList();
    for (Region author : result) {
      System.out.println(author.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an author */
  public void updateAutor(Integer authorId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region author = (Region) em.find(Region.class, authorId);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Author from the records */
  public void deleteAuthor(Integer authorId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region author = (Region) em.find(Region.class, authorId);
    em.remove(author);
    em.getTransaction().commit();
    em.close();
  }

}
