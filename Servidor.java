// Forma de compilação: javac Servidor.java
// Forma de execução: java Servidor

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
 public static void main(String[] args) throws IOException {
        // Cria um servidor socket na porta 8001
        ServerSocket serverSocket = new ServerSocket(8001);
        System.out.println("Servidor aguardando conexões...");

        while (true) {
            // Aguarda a conexão de um cliente
            Socket socket = serverSocket.accept();

            try (
                // Cria os leitores e escritores para comunicação com o cliente
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                PrintWriter pr = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String str;
                // Lê as mensagens do cliente e responde
                while ((str = bf.readLine()) != null) {
                    System.out.println("Cliente: " + str);
                    pr.println("Servidor: Recebi sua mensagem - " + str);

                    // Se o cliente digitar "sair", encerra o chat e o servidor
                    if (str.equalsIgnoreCase("sair")) {
                        System.out.println("Chat encerrado pelo cliente.");
                        encerrarServidor(serverSocket);
                        return; // Termina o programa após encerrar o servidor
                    }
                }
            }

            // Fecha a conexão com o cliente
            socket.close();
        }
    }

    private static void encerrarServidor(ServerSocket serverSocket) {
        try {
            // Fecha o servidor socket e libera a porta
            serverSocket.close();
            System.out.println("Servidor encerrado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}