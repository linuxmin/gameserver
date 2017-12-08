package server;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public ActionMove findLastAction() throws  Exception{ // function to find last action of a game
        Integer action_id;
        action_id= entityManager.createNamedQuery("get_last_move", Integer.class).getSingleResult();
        ActionMove actionMove = new ActionMove();
        actionMove = findActionMoveByID(action_id);
        return actionMove;
    }

    public ActionMove findActions(ActionMove actionMove, Integer number) throws  Exception{ //function to find all actions of certain game
        ActionMove actionMove1 = new ActionMove();
        Query query = entityManager.createNamedQuery("get_actions_by_game_id", ActionMove.class).setParameter("game_id",actionMove.getGame_id());
        List<ActionMove> list = query.getResultList();
        actionMove = list.get(number);
        return actionMove;
    }

    public ActionMove findMyLastAction(ActionMove actionMove) throws  Exception{ // function to find last action of actual player
        Integer action_id;
        action_id= entityManager.createNamedQuery("get_last_move_player", Integer.class).setParameter("player_id",actionMove.getPlayer_id()).getSingleResult();
        ActionMove actionMove1 = new ActionMove();
        actionMove1 = findActionMoveByID(action_id);
        return actionMove1;
    }

    public Long countActions(ActionMove actionMove) throws  Exception{ //function to execute query which counts actions of a game, returns no of actions
        Long count;
        count= entityManager.createNamedQuery("count_actions", Long.class).setParameter("game_id",actionMove.getGame_id()).getSingleResult();
        return count;
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
