package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class XMLFinishedGameTest {
    @Test
    public void setActionMove10() throws Exception {
        ActionMove actionMove = new ActionMove();
        XMLFinishedGame xmlFinishedGame = new XMLFinishedGame();
        actionMove.setAction_id(1);
        xmlFinishedGame.setActionMove10(actionMove);
        Integer expected = 1;
        assertEquals(expected,xmlFinishedGame.getActionMove10().getAction_id());
    }

    @Test
    public void setActionMove20() throws Exception {
        ActionMove actionMove = new ActionMove();
        XMLFinishedGame xmlFinishedGame = new XMLFinishedGame();
        actionMove.setAction_id(1);
        xmlFinishedGame.setActionMove20(actionMove);
        Integer expected = 1;
        assertEquals(expected,xmlFinishedGame.getActionMove20().getAction_id());
    }

    @Test
    public void setWinner_id() throws Exception {
        XMLFinishedGame xmlFinishedGame = new XMLFinishedGame();
        xmlFinishedGame.setWinner_id(1);
        Integer expected = 1;
        assertEquals(expected,xmlFinishedGame.getWinner_id());
    }

}