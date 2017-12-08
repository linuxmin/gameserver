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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return game;
    }

    //READ
    public Game findGameByID(final Integer game_id) throws Exception {
        return entityManager.find(Game.class, game_id);
    }

    public Game findOpenGame() throws  Exception{ //finds the last open game
        Integer game_id = 0;
        game_id= entityManager.createNamedQuery("get_open_game", Integer.class).getSingleResult();
        Game game = findGameByID(game_id);
        return game;
    }

    public Integer findWinner(Game game) throws  Exception{ //finds the id of the winner of a game
        Integer winner_id = 0;
        winner_id= entityManager.createNamedQuery("get_game_id_winner", Integer.class).setParameter("game_id",game.getGame_id()).getSingleResult();
        return winner_id;
    }

    //UPDATE

    public void iterateGamePlayerNo(Integer game_id) throws Exception{ // iterates the player_no of a game (+1)
        Game game = findGameByID(game_id);
        game.setPlayers_no();
     //   System.out.println(game.getGame_id());
        createGame(game);
    }

    //DELETE
    public void deleteGameByID(final Integer game_id) throws Exception {
        final Game game = findGameByID(game_id);
        if (game != null) {
            entityManager.remove(game);
        }
    }
}
