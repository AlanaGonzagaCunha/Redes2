package br.unifor.redes2.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Cliente {

	private static int port = 8080;
	private static String host = "localhost";
	private static String name;

	public Cliente(String host, int port, String name) {
		this.port = port;
		this.host = host;
		this.name = name;
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

	public void recebeMgs(Object append) {
		// TODO Auto-generated method stub

	}

	public static void run() throws IOException {
		System.out.println("Iniciando cliente...");

			System.out.println("Iniciando conexão com o servidor...");
			Socket socket = new Socket(host, port);
			System.out.println("Conexão cliente estabelecida como servidor ");

			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			PrintStream out = new PrintStream(output);

			Scanner entrada = new Scanner(System.in);
			
			while (true) {
				System.out.println("Digite mgs;");
				String m = entrada.nextLine();

				out.println(m);
				m = in.readLine();
				System.out.println("Messagem recebida: " + m);

			}
		
		System.out.println("Encerrando conexões...");
		in.close();
		out.close();
		socket.close();

		}

	

	public static void main(String[] args) {
		try {
			run();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
