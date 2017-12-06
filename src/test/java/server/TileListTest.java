package server;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TileListTest {
    @Test
    public void setTiles() throws Exception {
        TileList tileList = new TileList();
        Tile tile = new Tile();
        tile.setTile_id(1);
        List<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        tileList.setTiles(tiles);
        Integer expected = 1;
        assertEquals(expected,tileList.getTiles().get(0).getTile_id());
    }

    @Test
    public void setTime_end_generation() throws Exception {
        TileList tileList = new TileList();
        Timestamp timestamp = new Timestamp(12345);
        tileList.setTime_end_generation(timestamp.getTime());
        Long expected = timestamp.getTime();
        assertEquals(expected,tileList.getTime_end_generation());
    }

    @Test
    public void setPlayer_id() throws Exception {
        TileList tileList = new TileList();
        tileList.setPlayer_id(1);
        Integer expected = 1;
        assertEquals(expected,tileList.getPlayer_id());
    }

}