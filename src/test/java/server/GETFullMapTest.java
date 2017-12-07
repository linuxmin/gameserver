package server;

import org.junit.Test;

import static org.junit.Assert.*;

public class GETFullMapTest {
    @Test
    public void getMsg() throws Exception {
        GETFullMap getFullMap = new GETFullMap();
        assertEquals(204,getFullMap.getMsg(715).getStatus());
        assertEquals(200,getFullMap.getMsg(165).getStatus());
    }

}