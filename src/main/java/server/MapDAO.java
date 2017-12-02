package server;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class MapDAO {
    private final EntityManager entityManager;

    MapDAO(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //CREATE
    public Map createMap(final Map map){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(map);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return map;
    }

    //READ
    public Map findMapByID(final Integer map_id){
        try{
            Map map = entityManager.find(Map.class,map_id);
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return entityManager.find(Map.class,map_id);
    }

    public Integer findOpenMapByPlayerID(Integer player_id){
        Integer map_id = 3;
        try{
            map_id = entityManager.createNamedQuery("get_map_by_player_id", Integer.class).setParameter("id",player_id).getSingleResult();
            //map_id = map.getMap_id();
            return map_id;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //UPDATE (still empty
    public void insertTiles(TileList tileList){
        Integer count = tileList.getTiles().size();
        Map map = new Map();
        map=findMapByID(115);
        try {
            entityManager.getTransaction().begin();
            //Map map = new Map();
            //map = findMapByID(115);
            //System.out.println(map.getMap_id());
            //map.getTiles().add(tile);
            //entityManager.persist(map);
           // entityManager.merge(tile);
            //entityManager.getTransaction().commit();
            //entityManager.flush();

        }catch(Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    //DELETE
    public void deleteMapByID(final Integer map_id) {
        final Map map = findMapByID(map_id);
        if (map != null) {
            entityManager.remove(map);
        }
    }
}
