// Forma de compilação: javac Cliente.java
// Forma de execução: java Clienteoláola

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        try (
            // Cria um socket e conecta ao servidor na porta 8001
            Socket socket = new Socket("localhost", 8001);
            // Cria os leitores e escritores para comunicação com o servidor
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao servidor. Digite 'sair' para encerrar o chat.");

            while (true) {
                // Solicita ao usuário para digitar uma mensagem
                System.out.print("Cliente: ");
                String message = scanner.nextLine();
                // Envia a mensagem para o servidor
                pr.println(message);

                // Se o cliente digitar "sair", encerra o chat
                if (message.equalsIgnoreCase("sair")) {
                    System.out.println("Chat encerrado pelo cliente.");
                    break;
                }

                // Lê a resposta do servidor e exibe
                String response = bf.readLine();
                System.out.println("Servidor: " + response);
            }
        }
    }
}