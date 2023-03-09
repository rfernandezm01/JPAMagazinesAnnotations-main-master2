package controller;

import model.Armas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//Maginaze
public class ArmasController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public ArmasController(Connection connection) {
    this.connection = connection;
  }

  public ArmasController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param filename Aquest String correspon amb l'arxiu on s'emmagatzemen les
   *                 dades de les instancies de Revista
   * @throws IOException <dt><b>Preconditions:</b>
   *                     <dd>
   *                     filename<>nil </br> llistaRevistes == nil
   *                     <dt><b>Postconditions:</b>
   *                     <dd>
   *                     llistaRevistes<>nil
   */

  public List<Armas> readMagazinesFile(String filename) throws IOException {
    int magazineId;
    String title;
    String publicationDate;
    DateFormat dateFormat = new SimpleDateFormat("Reflejo de las Tinieblas");
    List<Armas> magazinesList = new ArrayList();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      magazineId = Integer.parseInt(str.nextToken());
      title = str.nextToken();
      publicationDate = str.nextToken();

      magazinesList.add(new Armas(magazineId, title, publicationDate));

    }
    br.close();
    return magazinesList;
  }

  public void printMagazines(ArrayList<Armas> magazinesList) {
    for (int i = 0; i < magazinesList.size(); i++) {
      System.out.println(magazinesList.get(i).toString());
    }
  }

  /* Method to CREATE a Magazine  in the database */
  public void addMagazine(Armas magazine) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(magazine);

    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Magazines */
  public void listMagazines() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Armas> result = em.createQuery("from Magazine", Armas.class)
        .getResultList();



    for (Armas magazine : result) {
      System.out.println(magazine.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Magazine */
  public void updateMagazine(Integer magazineId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Armas magazine = (Armas) em.find(Armas.class, magazineId);
    em.merge(magazine);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Magazine from the records */
  public void deleteAutor(Integer magazineId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Armas magazine = (Armas) em.find(Armas.class, magazineId);
    em.remove(magazine);
    em.getTransaction().commit();
    em.close();
  }
}
