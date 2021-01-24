package client;

class Main {
    public static void main(String[] args) {

        int port = 5000;

        client.ChatClient client = new client.ChatClient("localhost", port);
        client.execute();
    }
}
