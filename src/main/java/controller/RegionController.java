package controller;

import model.Region;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static java.lang.Integer.parseInt;

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

  public List<Region> readRegionesFile(String fileregion) throws IOException {
    int Regionid, Habitantes;
    String Nomregion, Elemento, Nombrearconte, Mundo;
    List<Region> regionesList = new ArrayList<Region>();

    BufferedReader br = new BufferedReader(new FileReader(fileregion));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      Regionid = parseInt(str.nextToken());
      Nomregion = str.nextToken();
      Habitantes = parseInt(str.nextToken());
      Elemento = str.nextToken();
      Nombrearconte = str.nextToken();
      Mundo = str.nextToken();
      // System.out.println(id + name + country + year + active);
      regionesList.add(new Region(Regionid, Nomregion, Habitantes, Elemento, Nombrearconte, Mundo));

    }
    br.close();

    return regionesList;
  }

  public void printRegiones(ArrayList<Region> regionesList) {
    for (int i = 0; i < regionesList.size(); i++) {
      System.out.println(regionesList.get(i).toString());
    }
  }


  /* Method to CREATE an Autor in the database */
  public void addRegion(Region region) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region regionExists = (Region) em.find(Region.class, region.getRegionid());
    if (regionExists == null ){
      System.out.println("insert region");
      em.persist(region);
    }
    em.getTransaction().commit();
    em.close();
  }


  /* Method to READ all Autors */
  public void listRegiones() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Region> result = em.createQuery("from Region", Region.class)
        .getResultList();
    for (Region region : result) {
      System.out.println(region.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an author */
  public void updateRegion(Integer regionId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region region = (Region) em.find(Region.class,regionId);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Author from the records */
  public void deleteRegion(Integer regionId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Region region = (Region) em.find(Region.class, regionId);
    em.remove(region);
    em.getTransaction().commit();
    em.close();
  }

}
