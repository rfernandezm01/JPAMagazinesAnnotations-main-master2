package controller;

import model.Armas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

//Maginaze
public class ArmasController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public ArmasController(Connection connection) {this.connection = connection;}

  public ArmasController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param filearmas Aquest String correspon amb l'arxiu on s'emmagatzemen les
   *                 dades de les instancies de Revista
   * @throws IOException <dt><b>Preconditions:</b>
   *                     <dd>
   *                     filearmas<>nil </br> llistaRevistes == nil
   *                     <dt><b>Postconditions:</b>
   *                     <dd>
   *                     llistaRevistes<>nil
   */

  public List<Armas> readArmasFile(String filearmas) throws IOException {
    int ArmaID;
    String NombreArma;
    String TipodeArmas;
    int NumerodeestrellasArma;
    int PuntosAtaque;
    List<Armas> armasList = new ArrayList();

    BufferedReader br = new BufferedReader(new FileReader(filearmas));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      ArmaID = Integer.parseInt(str.nextToken());
      NombreArma = str.nextToken();
      TipodeArmas = str.nextToken();
      NumerodeestrellasArma = Integer.parseInt(str.nextToken());
      PuntosAtaque = Integer.parseInt(str.nextToken());

      armasList.add(new Armas(ArmaID, NombreArma, TipodeArmas, NumerodeestrellasArma, PuntosAtaque));

    }
    br.close();
    return armasList;
  }

  public void printArmas(ArrayList<Armas> arrayList) {
    for (int i = 0; i < arrayList.size(); i++) {
      System.out.println(arrayList.get(i).toString());
    }
  }

  /* Method to CREATE a Magazine  in the database */
  public void addArma(Armas armas) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(armas);

    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Magazines */
  public void listArmas() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Armas> result = em.createQuery("from Armas", Armas.class)
        .getResultList();



    for (Armas armas : result) {
      System.out.println(armas.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Magazine */
  public void updateArmas(Integer armasId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Armas armas = (Armas) em.find(Armas.class, armasId);
    em.merge(armas);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Magazine from the records */
  public void deleteArma(Integer armasId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Armas armas = (Armas) em.find(Armas.class, armasId);
    em.remove(armas);
    em.getTransaction().commit();
    em.close();
  }
}
