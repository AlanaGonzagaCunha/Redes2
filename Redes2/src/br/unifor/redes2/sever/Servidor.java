package br.unifor.redes2.sever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.unifor.redes2.cliente.Cliente;

public class Servidor {
	private static int port;
	private static String host;
	private static ArrayList<Cliente>clientes;
	
	public Servidor(Cliente cliente) {
		this.port = cliente.getPort();
		this.host = cliente.getHost();
		clientes = new ArrayList<>();
	}
	
	private void run() {

	}
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket= new ServerSocket(port);
			Socket socket;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
