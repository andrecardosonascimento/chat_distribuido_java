package cliente;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServidorChat {
    
    private static final int PORTA = 12345;
    private static final Set<PrintWriter> clientes = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        System.out.println("Server Iniciado na Porta" + PORTA);

        try (ServerSocket servidor = new ServerSocket(PORTA)){
            while (true) {
                Socket socket = servidor.accept();
                System.out.println("novo cliente conectado" + socket.getInetAddress());
                new Thread(new ManipuladorCliente(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    private static class ManipuladorCliente implements Runnable{
        private Socket socket;
        private PrintWriter saida;
        private BufferedReader entrada;
        private String nome;

        public ManipuladorCliente(Socket soquete){
            this.socket = soquete;
        }

        @Override
        public void run(){

        }
    }

}