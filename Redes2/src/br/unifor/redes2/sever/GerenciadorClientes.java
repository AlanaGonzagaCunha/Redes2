package br.unifor.redes2.sever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GerenciadorClientes extends Thread {
	private static String nomeCLiente;
	private Socket cliente;
	private BufferedReader in;
	private PrintWriter out;
	private static Map<String, GerenciadorClientes> mapCliente;

	public GerenciadorClientes(Socket cliente) {
		this.cliente = cliente;
		mapCliente = new HashMap<String, GerenciadorClientes>();
		start();

	}

	public static String getNomeCLiente() {
		return nomeCLiente;
	}

	public PrintWriter getOut() {
		return out;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			out = new PrintWriter(cliente.getOutputStream(), true);
			out.println("Digite seu nome: ");
			String mgs = in.readLine();
			this.nomeCLiente = mgs.toLowerCase().replaceAll(",", "");
			out.println("nome cliente: " + this.nomeCLiente);
			mapCliente.put(this.nomeCLiente, this);

			while (true) {
				mgs = in.readLine();

				if (mgs.equalsIgnoreCase("::SAIR")) {
					this.cliente.close();

				} else if (mgs.toLowerCase().startsWith("::mgs")) {
					String nomeD = mgs.substring(5, mgs.length());
					System.out.println("enviando mgs para: " + nomeD);
					GerenciadorClientes destinario = mapCliente.get(nomeD);

					if (destinario == null) {
						out.println("O cliente não existe!");
					} else {
						out.println("digite umas mgs para " + destinario.getNomeCLiente());
						destinario.getOut().println(this.nomeCLiente + " disse:" + in.readLine());
					}
				}
				else if (mgs.equalsIgnoreCase("::listar-clientes")) {
					StringBuffer str = new StringBuffer();

					for (String c : mapCliente.keySet()) {
						str.append(c);
						str.append(",");
					}
					str.delete(str.length() - 1, str.length());
					out.println(str.toString());

				} else {
					out.println(this.nomeCLiente + " disse: " + mgs);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
