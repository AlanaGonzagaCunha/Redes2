package br.unifor.redes2.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.unifor.redes2.cliente.Cliente;

public class Principal extends JFrame {
	static Cliente cliente;
	private JList exibeUsuarios;
	private JFrame janela;
	private JButton btnEnviar;
	private JPanel painelChat;
	private JTextArea txtArea;
	private JScrollPane scrollTxt;
	private JTextArea exibeMgs;
	private Socket socket;
	private static int port = 8080;
	private static String host = "localhost";
	private PrintWriter out;

	public Principal(Cliente cliente) {
		montaJanela();
	}

	public void montaJanela() {
		janela = new JFrame();
		btnEnviar = new JButton("Enviar");
		painelChat = new JPanel();
		txtArea = new JTextArea();
		exibeMgs = new JTextArea();
		exibeUsuarios = new JList();
		scrollTxt = new JScrollPane(txtArea);
		

		setTitle("Chat Redes 2");
		setVisible(true);
		setSize(600, 500);
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);	

		btnEnviar.setSize(100, 110);
		btnEnviar.setLocation(480, 345);

		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setSize(370, 110);
		txtArea.setLocation(105, 345);

		exibeMgs.setSize(470, 330);
		exibeMgs.setLocation(105, 10);
		exibeMgs.setEditable(false);
			
		exibeUsuarios.setBackground(Color.gray);
		exibeUsuarios.setSize(100, 450);
		exibeUsuarios.setLocation(0, 10);

		scrollTxt.setBounds(105, 10, 470, 290);
		scrollTxt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTxt.setViewportView(exibeMgs);
		repaint();

		add(exibeUsuarios);
		add(btnEnviar);
		add(txtArea);
		getContentPane().add(scrollTxt);

		exibeMgs.setText("Chat iniciado... Porta: " + port + ", " + " Host: " + host + "\n");

		repaint();
		tratarEventos();
		String [] users = {"Alana", "Maycom", "Milla", "Nina", "Valks"}; 
		prencherListaDeUsuarios(users);
	}

	private void prencherListaDeUsuarios(String [] usuarios) {

		DefaultListModel model = new DefaultListModel<>();
		exibeUsuarios.setModel(model);
		
		for (String user: usuarios) {
			model.addElement(user);
		}
	}

	private void tratarEventos() {

		txtArea.addKeyListener(new KeyListener() {


			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtArea.getText().isEmpty()) {
						exibeMgs.append(txtArea.getText());
						
						out = new PrintWriter(cliente.getOutputStream(), true);
						BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));

						while (true) {

							String mgsTerminal = terminal.readLine();
							if(mgsTerminal== null || mgsTerminal.length() ==0) {
								continue;
							}
							out.println(mgsTerminal);
							if (mgsTerminal.equalsIgnoreCase("::SAIR")) {
								System.exit(0);

							} 
						}
						
						txtArea.setText("");
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (!txtArea.getText().isEmpty() && !txtArea.isFocusOwner()) {
					exibeMgs.append(txtArea.getText() + "\n");
					txtArea.setText("");
				}

			}

		});
	}

	public static void iniciarChat() {

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
	public static void main(String[] args) {
		String nameCliente = JOptionPane.showInputDialog("Entre com seu nome: ");
		if (!nameCliente.isEmpty()) {

			Principal p = new Principal(cliente);

		} else {
			JOptionPane.showMessageDialog(null, "Por favor digite o nome...");
		}

	}
}
