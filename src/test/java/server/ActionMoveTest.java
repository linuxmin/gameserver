package server;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ActionMoveTest {
    @Test
    public void setAction_id() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setAction_id(1);
        assertEquals(expected,actionMove.getAction_id());
    }

    @Test
    public void setMap_id() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setMap_id(1);
        assertEquals(expected,actionMove.getMap_id());
    }

    @Test
    public void setGame_id() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setGame_id(1);
        assertEquals(expected,actionMove.getGame_id());
    }

    @Test
    public void setPlayer_id() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setPlayer_id(1);
        assertEquals(expected,actionMove.getPlayer_id());
    }

    @Test
    public void setTime_start() throws Exception {
        ActionMove actionMove = new ActionMove();
        Timestamp timestamp = new Timestamp(12345);
        actionMove.setTime_start(timestamp.getTime());
        Long expected = timestamp.getTime();
        assertEquals(expected,actionMove.getTime_start());
    }

    @Test
    public void setTime_end() throws Exception {
        ActionMove actionMove = new ActionMove();
        Timestamp timestamp = new Timestamp(12345);
        actionMove.setTime_end(timestamp.getTime());
        Long expected = timestamp.getTime();
        assertEquals(expected,actionMove.getTime_end());
    }

    @Test
    public void setStart_tile() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setStart_tile(1);
        assertEquals(expected,actionMove.getStart_tile());
    }

    @Test
    public void setTarget_tile() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setTarget_tile(1);
        assertEquals(expected,actionMove.getTarget_tile());
    }

    @Test
    public void setActions_left() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setActions_left(1);
        assertEquals(expected,actionMove.getActions_left());
    }

    @Test
    public void setInventory() throws Exception {
        ActionMove actionMove = new ActionMove();
        Integer expected = 1;
        actionMove.setInventory(1);
        assertEquals(expected,actionMove.getInventory());
    }

}