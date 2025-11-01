// package cliente;

import java.io.*;
import java.net.*;

public class ClienteChat {
    private static final String SERVIDOR = "localhost"; // inserir um numero de ip válido
    private static final int PORTA = 12345; // inserir uma numeração de uma porta tcp válida

    public static void main(String[] args) {
        
        try (Socket sc = new Socket(SERVIDOR, PORTA)){
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            PrintWriter saida = new PrintWriter(sc.getOutputStream(), true);


            new Thread(() -> {
                String msg;
                try {
                    while((msg = entrada.readLine()) != null){
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("conexão encerrada");
                }
            }).start();

            String texto;
            while((texto = teclado.readLine()) != null){
                saida.println(texto);
                if(texto.equalsIgnoreCase("/sair")) break;
            }

        } catch (IOException e) {
            System.out.println("erro ao conectar ao servidor -erro-> " + e.getMessage());
        }
    }
}