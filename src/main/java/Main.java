import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import controller.PersonajeController;
import controller.ArmasController;
import controller.RegionController;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

  static SessionFactory sessionFactoryObj;
/*
  private static SessionFactory buildSessionFactory() {
    // Creating Configuration Instance & Passing Hibernate Configuration File
    Configuration configObj = new Configuration();
    configObj.configure("hibernate.cfg.xml");

    // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
    ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

    // Creating Hibernate SessionFactory Instance
    sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
    return sessionFactoryObj;
  } */

  private static SessionFactory buildSessionFactory() {
    try {
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    } catch (HibernateException he) {
      System.out.println("Session Factory creation failure");
      throw he;
    }
  }

  public static EntityManagerFactory createEntityManagerFactory() throws ExceptionInInitializerError {
    EntityManagerFactory emf;
    try {
      emf = Persistence.createEntityManagerFactory("JPAMagazines");
    } catch (Throwable ex) {
      System.err.println("Failed to create EntityManagerFactory object."+ ex);
      throw new ExceptionInInitializerError(ex);
    }
    return emf;
  }

  public static void main(String[] args) {
    ArrayList<Armas> armitas = new ArrayList();

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    Connection c = connectionFactory.connect();

//    SessionFactory sessionFactory = buildSessionFactory();
    EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
    //sessionObj = buildSessionFactory().openSession();


    RegionController regionController = new RegionController(c, entityManagerFactory);
    PersonajeController personajeController = new PersonajeController(c, entityManagerFactory);
    ArmasController armasController = new ArmasController(c, entityManagerFactory);
    PersonajeController personajesController = new PersonajeController(c, entityManagerFactory);

    Menu menu = new Menu();
    int opcio;
    opcio = menu.mainMenu();

    switch (opcio) {

      case 1:

        System.out.println("1!!");
        try {

          // regionController.printAutors(regionController.readAuthorsFile("src/main/resources/autors.txt"));
        //

         // for (Author a : authors) {
         //   regionController.addAuthor(a);
         // }

          // armasController.printMagazines(armasController.readMagazinesFile("src/main/resources/revistes.txt"));
          // armasController.p1
          // rintMagazines();

          List<Region> regions = regionController.readRegionesFile("src/main/resources/Regiónes.csv");
          List<Personaje> personajes = personajeController.readPersonajesFile("src/main/resources/personajes.csv", "src/main/resources/Regiónes.csv", "src/main/resources/Armas.csv");
          List<Armas> armas = armasController.readArmasFile("src/main/resources/Armas.csv");
          ArrayList<ArrayList<String>> regionesPersonajes = personajesController.readRegionesPersonajes("src/main/resources/RegionesPersonajes.csv");

          System.out.println("Revistes llegides des del fitxer");
          for (int i = 0; i < armas.size(); i++) {
            System.out.println(armas.get(i).toString()+"\n");
            for (int j = 0; j < armas.get(i).getPersonaje().getPersonajeID(); j++) {
              Region region = armas.get(i).getPersonaje().getRegion();

              System.out.println("La Región:");
              System.out.println(region);

              //Personaje personaje = armas.get(i).getPersonaje().region.getPersonajes();
              //personaje.setRegion(region);

              //System.out.println("Personajes:");
              //System.out.println(personaje);

              //personajeController.addArticle(personaje);

              for (ArrayList<String> regionPersonaje : regionesPersonajes) {
                if (Integer.parseInt(regionPersonaje.get(0)) == region.getRegionid()) {

                  // Este es el ID del presonaje
                  // Este personaje hay que meterlo a la lista de personajes
                  regionPersonaje.get(1);
                //  region.getPersonajes().add();

                  // Buscamos el personaje que tiene ese ID
                  for (Personaje p : personajes) {
                    if (p.getPersonajeID() == Integer.parseInt(regionPersonaje.get(1))) {
                      region.getPersonajes().add(p);
                    }
                  }
                }


              }

              regionController.addRegion(region);

            }

            armasController.addArma(armas.get(i));
          }

/*
          for (Magazine m : magazines) {
            System.out.println(m);
            armasController.addMagazine(m);
          }

          for (Author a : authors) {
            regionController.addAuthor(a);
          }

          for (Article ar : articles) {
            personajeController.addArticle(ar);
          }
*/
        } catch (NumberFormatException | IOException e) {

          e.printStackTrace();
        }
        break;

      default:
        System.out.println("Adeu!!");
        System.exit(1);
        break;

    }
  }
}


/*


    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 101; i <= 105; i++) {
                userObj = new User();
                userObj.setUserid(i);
                userObj.setUsername("Editor " + i);
                userObj.setCreatedBy("Administrator");
                userObj.setCreatedDate(new Date());

                sessionObj.save(userObj);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }


*/

