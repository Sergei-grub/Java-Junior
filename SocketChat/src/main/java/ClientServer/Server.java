package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final int PORT = 8880;
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running and waiting for connections..");

            // Прием входящих соединений
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);


                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Рассылка сообщения клиентам
    public static void broadcast(String message, ClientHandler sender)
    {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // Внутренний класс для обработки клиентских подключений
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;


        public ClientHandler(Socket socket) {
            this.clientSocket = socket;

            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Метод для обработки взаимодействия с клиентом
        @Override
        public void run() {
            try {
                String username = getUsername();
                System.out.println("User " + username + " connected.");

                out.println("Welcome to the chat, " + username + "!");
                out.println("Type Your Message");
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("[" + username + "]: " + inputLine);

                    broadcast("[" + username + "]: " + inputLine, this);
                }

                clients.remove(this);

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getUsername() throws IOException {
            out.println("Enter your username:");
            return in.readLine();
        }


        public void sendMessage(String message) {
            out.println(message);
            out.println("Type Your Message");
        }
    }
}