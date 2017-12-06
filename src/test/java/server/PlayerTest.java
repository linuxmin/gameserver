package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void setPlayer_id() throws Exception {
        Player player = new Player();
        player.setPlayer_id(1);
        Integer expected = 1;
        assertEquals(expected,player.getPlayer_id());
    }

    @Test
    public void setGame_id() throws Exception {
        Player player = new Player(1,"Harald","Moser",10,"mumu");
        player.setGame_id(1);
        Integer expected = 1;
        assertEquals(expected,player.getGame_id());
    }

    @Test
    public void setMap_id() throws Exception {
        Map map = new Map();
        Game game = new Game();
        Player player = new Player(map,game);
        player.setMap_id(1);
        Integer expected = 1;
        assertEquals(expected,player.getMap_id());
    }

    @Test
    public void setFirst_name() throws Exception {
        Player player = new Player();
        player.setFirst_name("harald");
        String expected = "harald";
        assertEquals(expected,player.getFirst_name());
    }

    @Test
    public void setLast_name() throws Exception {
        Player player = new Player();
        player.setLast_name("moser");
        String expected = "moser";
        assertEquals(expected,player.getLast_name());
    }

    @Test
    public void setAge() throws Exception {
        Player player = new Player();
        player.setAge(1);
        Integer expected = 1;
        assertEquals(expected,player.getAge());
    }

    @Test
    public void setNickname() throws Exception {
        Player player = new Player();
        player.setNickname("min");
        String expected = "min";
        assertEquals(expected,player.getNickname());

    }

    @Test
    public void setPlayerProperties() throws Exception {
        Player player = new Player();
        Player player1 = new Player();
        player.setMap_id(1);
        player.setFirst_name("Huhu");
        player.setLast_name("Hai");
        player.setGame_id(1);
        player.setNickname("heidi");
        player.setAge(5);
        player1.setPlayerProperties(player);
        assertEquals(player.getFirst_name(),player1.getFirst_name());
    }

}