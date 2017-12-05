package server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class ActionMoveDAO {
    private final EntityManager entityManager;

    ActionMoveDAO(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //CREATE
    public ActionMove createActionMove(final ActionMove actionMove){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(actionMove);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return actionMove;
    }

    //READ
    public ActionMove findActionMoveByID(final Integer action_id) throws Exception{
        return entityManager.find(ActionMove.class,action_id);
    }

    //UPDATE (still empty



    //DELETE
    public void deleteActionMoveByID(final Integer action_id) throws Exception{
        final ActionMove actionMove = findActionMoveByID(action_id);
        if (actionMove != null) {
            entityManager.remove(actionMove);
        }
    }
}
