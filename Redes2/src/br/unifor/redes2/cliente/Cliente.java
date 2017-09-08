package br.unifor.redes2.cliente;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

public class Cliente {

	private int port;
	private String host;
	private String name;

	public Cliente(String host, int port, String name) {
		this.port = port;
		this.host = host;
		this.name= name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void run() {
		try {
			Socket socket= new Socket(host, port);
			
		} catch (IOException e) {
			System.out.println("Não é possível se conectar com o servidor. =( ");
			e.printStackTrace();
		}
		System.out.println("Preparando Cliente...");

	}

	
}
