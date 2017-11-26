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
        entityManager.persist(player);
        return player;
    }

    //READ
    public Player findPlayerByID(final Integer player_id){
        return entityManager.find(Player.class,player_id);
    }

    //UPDATE (still empty)

    //DELETE
    public void deletePlayerByID(final Integer player_id) {
        final Player player = findPlayerByID(player_id);
        if (player != null) {
            entityManager.remove(player);
        }
    }
}
