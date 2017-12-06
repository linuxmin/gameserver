package server;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class GETStartNewGameTest {
    @Test
    public void getMsg() throws Exception {
       GETStartNewGame getStartNewGame = new GETStartNewGame();
       Player player = new Player();
       assertEquals(200,getStartNewGame.getMsg(1).getStatus());
       GETStartNewGame getStartNewGame1 = new GETStartNewGame();
       assertEquals(200,getStartNewGame.getMsg(2).getStatus());
       GETStartNewGame getStartNewGame2 = new GETStartNewGame();
       assertEquals(406,getStartNewGame.getMsg(3).getStatus());
       GETStartNewGame getStartNewGame3 = new GETStartNewGame();
       assertEquals(417,getStartNewGame.getMsg(null).getStatus());
    }

}