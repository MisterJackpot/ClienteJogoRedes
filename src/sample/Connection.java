package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
    private int port;
    private String host;
    public Socket socket;
    public ObjectOutputStream os;
    public ObjectInputStream is;

    public Connection(int port, String host) {
        this.port = port;
        this.host = host;
        try {
            this.socket = this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket start() throws IOException {
        InetAddress ipHost = InetAddress.getByName("127.0.0.1");
        Socket s = new Socket(ipHost,3004,ipHost,port);

        return s;
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
