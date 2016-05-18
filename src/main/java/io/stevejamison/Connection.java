package io.stevejamison;

/**
 * Created by stevejaminson on 5/17/16.
 */
public interface Connection {

    public String getIP();
    public int getPort();
    public String getProtocol();

    public String connect();
    public void close();
}
