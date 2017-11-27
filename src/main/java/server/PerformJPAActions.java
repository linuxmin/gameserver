package server;

import javax.persistence.EntityManager;

public class PerformJPAActions {
    public static void startTransaction(EntityManager entityManager){
        try {
            entityManager.getTransaction().begin();
        }catch(Exception e){
            entityManager.close();
        }
    }

    public static void commitTransaction(EntityManager entityManager){
        try {
            entityManager.getTransaction().commit();
        }catch(Exception e){

        }finally{
            entityManager.close();
        }
    }
}
