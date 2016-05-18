package io.stevejamison;

import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Created by stevejaminson on 5/18/16.
 */
public class ConnectionManager {

    public int limit;
    public int connectionCount = 0;

    public ConnectionManager(int limit){
        this.limit = limit;
    }

    public ManagedConnection makeConnection(String IP, String protocol){
        Pattern protoPattern = Pattern.compile("(SSH|TCP|HTTP)");
        if(protoPattern.matcher(protocol).matches() && connectionCount < limit){
            connectionCount++;
            return new ManagedConnection(IP, 9988, protocol);
        } else {
            return null;
        }
    }

    public ManagedConnection makeConnection(String IP, int port, String protocol){
        Pattern protoPattern = Pattern.compile("(SSH|TCP|HTTP)");
        if(protoPattern.matcher(protocol).matches() && connectionCount < limit){
            connectionCount++;
            return new ManagedConnection(IP, port, protocol);
        } else {
            return null;
        }
    }

    public ManagedConnection makeConnection(String IP, int port){
        Pattern protoPattern = Pattern.compile("(SSH|TCP|HTTP)");
        if(connectionCount < limit){
            connectionCount++;
            return new ManagedConnection(IP, port, "HTTP");
        } else {
            return null;
        }
    }

    private class ManagedConnection implements Connection{
        String IP;
        int port;
        String protocol;

        public ManagedConnection(String IP, int port, String protocol){
            this.IP = IP;
            this.port = port;
            this.protocol = protocol;
        }

        public String getIP() {
            return IP;
        }

        public int getPort() {
            return port;
        }

        public String getProtocol() {
            return protocol;
        }

        public String connect(){
            if(this.IP.equals("Connection closed")){
                return "Connection closed";
            } else
            return "Connected";
        }

        public void close(){
            connectionCount--;
            this.IP = "Connection closed";
            this.port = 0000;
            this.protocol = "Connection closed";
        }
    }
}
