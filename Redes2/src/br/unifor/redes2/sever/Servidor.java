package br.unifor.redes2.sever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.unifor.redes2.cliente.Cliente;

public class Servidor {
	private static String nameCliente;
	private static int port=8080;
	private static String host="localhost";
	private static Cliente c;
	private static ArrayList<Cliente> clientes;

	public Servidor(int port) {
		this.port = port;
		clientes = new ArrayList<>();

	}
	
	private static void run() {
		try {

			System.out.println("Iniciando o servidor na porta: " + port + "...");

			System.out.println("Aguardando conexões...");
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();
			System.out.println("Cliente conectado...");

			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			PrintStream out = new PrintStream(output);

			while (true) {
				String mgs = in.readLine();
				System.out.println("Mensagem recebida do cliente: "+ mgs);
				
				out.println(mgs);
				
				System.out.println("Conexão encerrada...");
				in.close();
				out.close();
				socket.close();
				System.out.println("Encerrando servidor...");
				serverSocket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		run();
	}

}
