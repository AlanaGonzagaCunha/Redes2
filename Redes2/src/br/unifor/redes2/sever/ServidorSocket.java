package br.unifor.redes2.sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	static ServerSocket server;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(8080);

			while (true) {
				System.out.println("Esperando conex�es...");
				Socket cliente =	server.accept();
				new GerenciadorClientes(cliente);
				
			}

		} catch (IOException e) {
			System.out.println("N�o � poss�vel conectar com o servidor...");

			try {
				if (server != null) {
					System.out.println("Fechando servidor...");
					server.close();
				}

			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("N�o � poss�vel fechar o servidor...");
			}
			e.printStackTrace();
		}
	}

}
