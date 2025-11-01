// package servidor;

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
            try {
               
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                saida = new PrintWriter(socket.getOutputStream(), true);

                saida.println("Digite o seu nome de usuário: ");
                nome = entrada.readLine();
                System.err.println(" entrou no chat.");

                clientes.add(saida);
                broadcast("-- " + nome + " entrou no chat");

                String msg;
                while ((msg = entrada.readLine()) != null) {
                    if (msg.equalsIgnoreCase("/sair")) break;
                    broadcast("-- " + nome + ": " + msg);
                }
            } catch (IOException e) {
                System.err.println("Erro com cliente" + nome + ": " + e.getMessage());
            } finally {
                sair();
            }
        }

        private void broadcast(String msg){
            for(PrintWriter cliente : clientes){
                cliente.println(msg);
            }
        }

        private void sair(){
            try {
                clientes.remove(saida);
                if(nome != null){
                    broadcast("-- " + nome + " saiu do chat");
                }
                socket.close();
            } catch (IOException e) {
                System.out.println("Erro ao desconectar cliente: " + e.getMessage());
            }
        }
    }
}