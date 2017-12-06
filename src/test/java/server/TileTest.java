package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void setTile_id() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setTile_id(1);
        assertEquals(expected,tile.getTile_id());
    }

    @Test
    public void setType() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setType(1);
        Tile tile1 = new Tile(tile);
        assertEquals(expected,tile1.getType());
    }

    @Test
    public void setX() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setX(1);
        assertEquals(expected,tile.getX());
    }

    @Test
    public void setY() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setY(1);
        assertEquals(expected,tile.getY());
    }

    @Test
    public void setCastle() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setCastle(1);
        assertEquals(expected,tile.getCastle());
    }

    @Test
    public void setTreasure() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setTreasure(1);
        assertEquals(expected,tile.getTreasure());
    }

    @Test
    public void setMap_id() throws Exception {
        Tile tile = new Tile();
        Integer expected = 1;
        tile.setMap_id(1);
        assertEquals(expected,tile.getMap_id());
    }

}