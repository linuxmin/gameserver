package server;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void setTimeStart() throws Exception {
        Game game = new Game();
        Timestamp timestamp = new Timestamp(12345);
        game.setTimeStart(timestamp.toString());
        assertEquals(timestamp.toString(),game.getTime_start());
    }

    @Test
    public void getGame_id() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        Integer expected = 1;
        assertEquals(expected,game.getGame_id());
    }

    @Test
    public void setGame_id() throws Exception {
        Game game = new Game();
        game.setGame_id(1);
        Game game2 = new Game(game);
        Integer expected = 1;
        assertEquals(expected,game2.getGame_id());
    }

    @Test
    public void getTime_start() throws Exception {
        Game game = new Game();
        Timestamp timestamp = new Timestamp(12345);
        game.setTimeStart(timestamp.toString());
        assertEquals(timestamp.toString(),game.getTime_start());
    }

    @Test
    public void getTime_end() throws Exception {
        Game game = new Game();
        Timestamp timestamp = new Timestamp(12345);
        game.setTime_end(timestamp.toString());
        assertEquals(timestamp.toString(),game.getTime_end());
    }

    @Test
    public void setTime_end() throws Exception {
        Game game = new Game("12345",1);
        Timestamp timestamp = new Timestamp(12345);
        game.setTime_end(timestamp.toString());
        assertEquals(timestamp.toString(),game.getTime_end());
    }

    @Test
    public void getEnd_code() throws Exception {
        Game game = new Game();
        game.setEnd_code(1);
        Integer expected = 1;
        assertEquals(expected,game.getEnd_code());
    }

    @Test
    public void setEnd_code() throws Exception {
        Game game = new Game();
        game.setEnd_code(1);
        Integer expected = 1;
        assertEquals(expected,game.getEnd_code());
    }

    @Test
    public void getloser_id() throws Exception {
        Game game = new Game();
        game.setloser_id(1);
        Integer expected = 1;
        assertEquals(expected,game.getloser_id());
    }

    @Test
    public void setloser_id() throws Exception {
        Game game = new Game();
        game.setloser_id(1);
        Integer expected = 1;
        assertEquals(expected,game.getloser_id());
    }

    @Test
    public void getWinner_id() throws Exception {
        Game game = new Game();
        game.setWinner_id(1);
        Integer expected = 1;
        assertEquals(expected,game.getWinner_id());
    }

    @Test
    public void setWinner_id() throws Exception {
        Game game = new Game();
        game.setWinner_id(1);
        Integer expected = 1;
        assertEquals(expected,game.getWinner_id());
    }

    @Test
    public void getPlayers_no() throws Exception {
        Game game = new Game();
        game.setPlayers_no();
        Integer expected = 1;
        assertEquals(expected,game.getPlayers_no());
    }

    @Test
    public void setPlayers_no() throws Exception {
        Game game = new Game();
        game.setPlayers_no();
        Integer expected = 1;
        assertEquals(expected,game.getPlayers_no());
    }


}