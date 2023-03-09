package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int option;

    public Menu() {
        super();
    }

    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Borrar todas las Tablas ");
            System.out.println("2. Crear todas las Tablas ");
            System.out.println("3. Repoblar todas las Tablas ");
            System.out.println("4. Todos los elementos que contegan un texto concreto.");
            System.out.println("5. Todos los elementos que cumplan con una condicion. ");
            System.out.println("6. Seleccionar elementos concretos ");
            System.out.println("7. Modificar un elemento ");
            System.out.println("8. Modificar diferentes registros ");
            System.out.println("9. Eliminar un registro concreto ");
            System.out.println("10. Eliminar un conjunto de registros");

            System.out.println("0. Salir del programa");

            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
        } while (option != 0  && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10);

        return option;
    }






}