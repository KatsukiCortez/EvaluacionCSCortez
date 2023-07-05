package org.probandoopenjpa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PersistenceUtil.initialize();

        while (true) {
            System.out.println("1. Agregar persona");
            System.out.println("2. Borrar persona");
            System.out.println("3. Mostrar personas");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarPersona();
                    break;
                case 2:
                    borrarPersona();
                    break;
                case 3:
                    mostrarPersonas();
                    break;
                case 4:
                    PersistenceUtil.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private static void agregarPersona() {
        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese los nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese los apellidos: ");
        String apellidos = scanner.nextLine();

        Persona persona = new Persona();
        persona.setDni(dni);
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);

        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
        em.close();

        System.out.println("Persona agregada exitosamente");
    }

    private static void borrarPersona() {
        System.out.print("Ingrese el DNI de la persona a borrar: ");
        String dni = scanner.nextLine();

        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Persona persona = em.find(Persona.class, dni);
        if (persona != null) {
            em.remove(persona);
            System.out.println("Persona borrada exitosamente");
        } else {
            System.out.println("No se encontró ninguna persona con ese DNI");
        }
        em.getTransaction().commit();
        em.close();
    }

    private static void mostrarPersonas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        em.getTransaction().commit();
        em.close();

        System.out.println("Personas registradas:");
        for (Persona persona : personas) {
            System.out.println("DNI: " + persona.getDni());
            System.out.println("Nombres: " + persona.getNombres());
            System.out.println("Apellidos: " + persona.getApellidos());
            System.out.println();
        }
    }
}
