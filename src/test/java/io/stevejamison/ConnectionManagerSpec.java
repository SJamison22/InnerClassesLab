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

    @Test(expected = NullPointerException.class)
    public void tooManyConnectionsTest1(){
        ConnectionManager manager = new ConnectionManager(1);
        Connection connect = manager.makeConnection("123.456",8899);
        Connection connect4 = manager.makeConnection("122.333",9988);
        connect4.connect();
    }

    @Test(expected = NullPointerException.class)
    public void tooManyConnectionsTest2(){
        ConnectionManager manager = new ConnectionManager(1);
        Connection connect = manager.makeConnection("123.456",8899,"HTTP");
        Connection connect4 = manager.makeConnection("122.333",9988,"HTTP");
        connect4.connect();
    }

    @Test(expected = NullPointerException.class)
    public void wrongProtocolTest(){
        ConnectionManager manager = new ConnectionManager(1);
        Connection connect = manager.makeConnection("123.456","HTLP");
        Connection connect4 = manager.makeConnection("122.333","HTLP");
        connect4.connect();
    }

    @Test
    public void closeConnectionTest(){
        ConnectionManager manager = new ConnectionManager(5);
        Connection connect = manager.makeConnection("123.456",8899);
        connect.close();
        String expected = "Connection closed";
        String actual = connect.connect();
        assertEquals("Should return Connection closed",expected, actual);
    }
}
