package io.stevejamison;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by stevejaminson on 5/18/16.
 */
public class ConnectionManagerSpec {

    @Test
    public void makeConnectionTestAllParam(){
        ConnectionManager manager = new ConnectionManager(5);
        Connection connect = manager.makeConnection("123.456",8899,"HTTP");
        String expected = "123.456";
        String actual = connect.getIP();
        assertEquals(expected, actual);
    }

    @Test
    public void makeConnectionTestIPProtocol(){
        ConnectionManager manager = new ConnectionManager(5);
        Connection connect = manager.makeConnection("123.456","HTTP");
        String expected = "HTTP";
        String actual = connect.getProtocol();
        assertEquals(expected, actual);
    }

    @Test
    public void makeConnectionTestIPPort(){
        ConnectionManager manager = new ConnectionManager(5);
        Connection connect = manager.makeConnection("123.456",8899);
        int expected = 8899;
        int actual = connect.getPort();
        assertEquals("Get port should return 8899",expected, actual);
    }

    @Test
    public void makeConnectionTestConnect(){
        ConnectionManager manager = new ConnectionManager(5);
        Connection connect = manager.makeConnection("123.456",8899);
        String expected = "Connected";
        String actual = connect.connect();
        assertEquals("Should return Connected",expected, actual);
    }
}
