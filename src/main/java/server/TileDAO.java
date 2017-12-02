package server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class TileDAO {
    private final EntityManager entityManager;

    TileDAO(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //CREATE
    public Tile createTile(final Tile tile){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tile);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return tile;
    }

    //READ
    public Tile findTileByID(final Integer tile_id) throws Exception{
        return entityManager.find(Tile.class,tile_id);
    }

    //UPDATE (still empty



    //DELETE
    public void deleteTileByID(final Integer tile_id) throws Exception{
        final Tile tile = findTileByID(tile_id);
        if (tile != null) {
            entityManager.remove(tile);
        }
    }
}
