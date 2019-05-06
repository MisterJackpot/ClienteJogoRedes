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
        InetAddress ipHost = InetAddress.getByName("192.168.0.167");
        InetAddress ipHost2 = InetAddress.getByName(host);
        Socket s = new Socket(ipHost2,3000,ipHost,0);

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
