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

    public Integer findOpenGame() throws  Exception{
        Integer game_id = 0;
        game_id= entityManager.createNamedQuery("get_open_game", Integer.class).getSingleResult();
        return game_id;
    }

    //UPDATE

    public void iterateGamePlayerNo(Integer game_id) throws Exception{
        Game game = findGameByID(game_id);
        game.setPlayers_no();
        System.out.println(game.getGame_id());
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
