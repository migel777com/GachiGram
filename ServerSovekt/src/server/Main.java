package server;


import java.io.IOException;
import java.sql.SQLException;

class Main {
    public static Posts p = new Posts("","");
    public static void main(String[] args) throws IOException, SQLException {
        int port = 5000;
        ChatServer server = new ChatServer(port);
        server.exec();
    }
}
