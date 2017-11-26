package server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class GameDAO {
    private final EntityManager entityManager;

    GameDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //CREATE
    public Game createGame(final Game game) {
        entityManager.persist(game);
        return game;
    }

    //READ
    public Game findGameByID(final Integer game_id) {
        return entityManager.find(Game.class, game_id);
    }

    public boolean findOpenGame(final Integer player_id) {
        try {
            Game game = entityManager.createNamedQuery("get_open_game", Game.class)
                    .getSingleResult();
            PerformJPAActions.startTransaction(this.entityManager);
            game.setPlayer2_id(player_id);
            PerformJPAActions.commitTransaction(this.entityManager);
            return true;
        }catch(javax.persistence.NoResultException e){
            return false;
        }
    }


    //UPDATE (still empty)

    //DELETE
    public void deleteGameByID(final Integer game_id) {
        final Game game = findGameByID(game_id);
        if (game != null) {
            entityManager.remove(game);
        }
    }
}
