package server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class PlayerDAO {
    private final EntityManager entityManager;

    PlayerDAO(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //CREATE
    public Player createPlayer(final Player player){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(player);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return player;
    }

    //READ
    public Player findPlayerByID(final Integer player_id) throws Exception{
        return entityManager.find(Player.class,player_id);
    }

    //UPDATE (still empty
    public void insertPlayerData(Player player) throws Exception{
        Player player2 = findPlayerByID(player.getPlayer_id());
        player2.setPlayerProperties(player);
        createPlayer(player2);
        System.out.println(player2.getFirst_name());
    }



    //DELETE
    public void deletePlayerByID(final Integer player_id) throws Exception{
        final Player player = findPlayerByID(player_id);
        if (player != null) {
            entityManager.remove(player);
        }
    }
}
