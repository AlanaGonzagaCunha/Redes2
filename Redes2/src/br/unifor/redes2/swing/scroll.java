package br.unifor.redes2.swing;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class scroll extends JFrame{
    private JTextField text;
    private JScrollPane jscroll;
    String valor[] = {"11,00","15,11","51,11"};
  
    public scroll(){
      super("Teste com scrollpane");
      setSize(800, 600);
      this.getContentPane().setLayout(null);
      
      text = new JTextField(valor[0]);
      jscroll = new JScrollPane();
      jscroll.setBounds(200, 300, 64, 36);
      jscroll.setViewportView(text);
      jscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      jscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
      this.getContentPane().add(jscroll);
      this.setVisible(true);
   }
    public static void main(String args[]){
    	scroll app = new scroll();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}							