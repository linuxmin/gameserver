package server;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void setMap_id() throws Exception {
        Map map = new Map(1,2,3);
        Integer map_id =1;
        map.setMap_id(1);
        assertEquals(map_id,map.getMap_id());

    }



    @Test
    public void setGame_id() throws Exception {
        Game game = new Game();
        game.setGame_id(2);
        Map map = new Map(game);
        map.setGame_id(game.getGame_id());
        Integer game_id=2;
        assertEquals(game_id,map.getGame_id());
    }


    @Test
    public void setPlayer_id() throws Exception {
        Player player = new Player();
        Map map = new Map(player);
        map.setPlayer_id(3);
        Integer player_id=3;
        assertEquals(player_id,map.getPlayer_id());
    }

    @Test
    public void getTiles() throws Exception {
        Tile tile = new Tile();
        Map map = new Map(tile);
        assertEquals(tile,map.getTiles().get(0));

    }


    @Test
    public void setTime_start_generation() throws Exception {
        Map map = new Map();
        Timestamp timestamp = new Timestamp(12345);
        map.setTime_start_generation(timestamp);
        assertEquals(timestamp,map.getTime_start_generation());
    }


    @Test
    public void setTime_end_generation() throws Exception {
        Map map = new Map();
        Timestamp timestamp = new Timestamp(12345);
        map.setTime_end_generation(timestamp);
        assertEquals(timestamp,map.getTime_end_generation());
    }

    @Test
    public void checkSeconds() throws Exception {
        Map map = new Map();
        Timestamp timestamp = new Timestamp(12345);
        Timestamp timestamp1 = new Timestamp(12346);
        map.setTime_start_generation(timestamp);
        map.setTime_end_generation(timestamp1);
        Boolean ok = map.checkSeconds();
        assertEquals(true,ok);
  
    }

}