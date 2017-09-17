package br.unifor.redes2.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Cliente {

	private static int port = 8080;
	private static String host = "localhost";

	public static void main(String[] args) {

		try {
			Socket cliente = new Socket(host, port);

			new Thread() {
				public void run() {

					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

						while (true) {
							try {
								String mgs = in.readLine();
								if(mgs == null || mgs.isEmpty())
									continue;
								System.out.println("Servidor: " + mgs);

							} catch (IOException e) {
								e.printStackTrace();
								System.out.println("Impossível receber messagens do servidor... ");
								
							}

						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				};

			}.start();

			

		} catch (UnknownHostException e) {
			System.out.println("Endereço inválido! ");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não é possível conectar com o servidor. ");
		}
	}

}
