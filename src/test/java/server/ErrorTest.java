package server;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ErrorTest {

    @Test
    public void getError_id() throws Exception {
        Error error = new Error();
        error.setError_id(1);
        Integer expected = 1;
        Integer actual = error.getError_id();
        assertEquals(expected,actual);
    }

    @Test
    public void setError_id() throws Exception {
        Error error = new Error();
        error.setError_id(1);
        Integer expected = 1;
        Integer actual = error.getError_id();
        assertEquals(expected,actual);
    }

    @Test
    public void getMessage() throws Exception {
        Error error = new Error();
        error.setMessage("Hallo I bims!");
        String expected = "Hallo I bims!";
        String actual = error.getMessage();
        assertEquals(expected,actual);
    }

    @Test
    public void setMessage() throws Exception {
        Error error = new Error("huhu");
        error.setMessage("Hallo I bims!");
        String expected = "Hallo I bims!";
        String actual = error.getMessage();
        assertEquals(expected,actual);
    }

    @Test
    public void getTimestamp() throws Exception {
        Error error = new Error();
        Timestamp timestamp = new Timestamp(12345);
        error.setTimestamp(timestamp.toString());
        assertEquals(timestamp.toString(),error.getTimestamp());
    }

    @Test
    public void setTimestamp() throws Exception {
        Error error = new Error();
        Timestamp timestamp = new Timestamp(12345);
        error.setTimestamp(timestamp.toString());
        assertEquals(timestamp.toString(),error.getTimestamp());
    }

}