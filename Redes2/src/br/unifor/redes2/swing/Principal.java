package br.unifor.redes2.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.unifor.redes2.cliente.Cliente;

public class Principal {
	static Cliente cliente;
	private JFrame janela;
	private JButton btnEnviar;
	private JPanel painelChat;
	private JTextArea txtArea;
	private JScrollPane scrollTxt;
	private JTextArea exibeMgs;

	public Principal(Cliente cliente) {
		montaJanela();
	}

	public void montaJanela( ) {
		janela = new JFrame();
		btnEnviar = new JButton("Enviar");
		painelChat = new JPanel();
		txtArea = new JTextArea();
		exibeMgs = new JTextArea();
		scrollTxt = new JScrollPane(txtArea);

		
		janela.setTitle("Chat Redes 2");
		janela.setVisible(true);
		janela.setSize(600, 500);
		janela.setLocation(200, 100);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(null);
	
		
		btnEnviar.setSize(100, 110);
		btnEnviar.setLocation(480, 345);

		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setSize(500, 110);
		txtArea.setLocation(5, 345);
		
		exibeMgs.setSize(570,330);
		exibeMgs.setLocation(5,10);
		exibeMgs.setEditable(false);
		
		
		janela.add(btnEnviar);
		janela.add(txtArea);	
		janela.add(exibeMgs);
		janela.add(scrollTxt);
		
		exibeMgs.setText("Chat iniciado... Porta: "+ cliente.getPort() +", " +" Host: "+ cliente.getHost() +"\n");	
		
		janela.repaint();
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!txtArea.getText().isEmpty() && !txtArea.isFocusOwner()) {
					exibeMgs.append(cliente.getName() + ": " + txtArea.getText()+"\n");
					 txtArea.setText("");
				}
			}		
		});
	}

	public static void main(String[] args) {
		String nameCliente= JOptionPane.showInputDialog("Entre com seu nome: ");
		if(!nameCliente.isEmpty()) {
			cliente  = new Cliente("localhost", 8080, nameCliente);
			Principal p = new Principal(cliente);
			
		}else {
		JOptionPane.showMessageDialog(null, "Por favor digite o nome...");
		}

	}
}
