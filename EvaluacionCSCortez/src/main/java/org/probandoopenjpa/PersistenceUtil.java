package org.probandoopenjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static final String PERSISTENCE_UNIT_NAME = "MyPersistenceUnit"; // Nombre de la unidad de persistencia

    private static EntityManagerFactory emFactory;

    public static void initialize() {
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static void close() {
        if (emFactory != null) {
            emFactory.close();
        }
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
}
