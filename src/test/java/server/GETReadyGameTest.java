package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class GETReadyGameTest {
    @Test
    public void getMsg() throws Exception {
        GETReadyGame getReadyGame = new GETReadyGame();
        assertEquals(200,getReadyGame.getMsg(716).getStatus());
        assertEquals(417,getReadyGame.getMsg(7160).getStatus());

    }

}