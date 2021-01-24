package server;


import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class UserThread extends Thread {
    private Socket socket;
    private ChatServer chatServer;
    private PrintWriter writer;
    private BufferedReader reader;
    private String clientName;

    public String getClientName() {
        return clientName;
    }
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    UserThread(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = null;

            output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
                writer.println("Please type unique name and password!");
                String[] line = reader.readLine().split(" ");
                String name = line[0];
                String pass = line[1];
            while (this.chatServer.containsUser(name)) {
                writer.println("This username is exists, please type another username ");
                name = reader.readLine();
            }
            this.clientName = name;

            chatServer.addUserThread(name, this);

            String msg;
            do {
                Feed(Main.p);
                sendMessage("What do you want?[post | update]..\n" + "Type bye to terminate connection.");
                msg = reader.readLine();
                switch (msg) {
                    case "bye":
                        break;
                    case "update":
                        sendMessage("Updating");
                        sendMessage(Main.p.feedUpdate());
                        break;
                    case "post":
                        sendMessage("Posting");
                        sendMessage("Enter title:");
                        String title = reader.readLine();
                        Main.p.setTitle(title);
                        sendMessage("Enter content:");
                        String content = reader.readLine();
                        Main.p.setContent(content);
                        Main.p.postslist.add(new Posts(title,content));
                        break;
                }
            }while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Feed(Posts p) {
        final Runnable beeper = new Runnable() {
            public void run() { clearScreen(); p.feedUpdate(); }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, MINUTES);
        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, SECONDS);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

}
