
package br.usjt.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JTextArea;


public class ServidorSocket {

    ServerSocket servidor;
    Socket cliente;
    
    public void iniciar(String porta, JTextArea campoMensagens) throws IOException {
        servidor = new ServerSocket(Integer.parseInt(porta));
        //InetAddress end = InetAddres.getByName("0.0.0.0"); 
        //servidor = new ServerSocket(Integer.parseInt(porta) , 50 , end);
        cliente = servidor.accept();
        Scanner scanner = new Scanner(cliente.getInputStream());
        while (true) {
            while (scanner.hasNextLine()) {
                campoMensagens.append("[CLIENTE] " + scanner.nextLine() + "\n");
            }
        }

    }
    
    public void enviarMensagem(String mensagem) throws IOException {
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println(mensagem);
    }


} */

public class ServidorSocket extends Thread {
	private static ArrayList<BufferedWriter>clienteSocket;
	private static ServerSocket server;
	private String nome;
	private Socket con;
	private InputStream in;
	private InputStreamReader inr;
	private BufferedReader bfr;
	
	/* 
	 * Metodo construtor
	 * @param com do tipo Socket
	 */
	
	public ServidorSocket(Socket con) {
		this.con = con;
		try {
			in = con.getInputStream();
			inr = new InputStreamReader(in);
			bfr = new BufferedReader(inr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	/*
	 * Metodo run
	 */
	public void run() {
		try {
			String msg;
			OutputStream ou = this.con.getOutputStream();
			Writer ouw = new OutputStreamWriter(ou);
			BufferedWriter bfw = new BufferedWriter(ouw);
			clienteSocket.add(bfw);
			nome = msg = bfr.readLine();
			
			while(!"Sair".equalsIgnoreCase(msg) && msg != null)
			{
				msg = bfr.readLine();
				sendToAll(bfw, msg);
				System.out.println(msg);
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	/*
	 *  Metodo usado para enviar mensagem para todos os clientes
	 *  @param bwSaida do tipo BufferedWriter
	 *  @param msg do tipo String
	 *  @throws IOException
	 */
	public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException{
		BufferedWriter bwS;
		
		for(BufferedWriter bw : clienteSocket) {
			bwS = (BufferedWriter)bw;
			if(!(bwSaida == bwS)){
				bw.write(nome + " -> " + msg + "\r\n");
				bw.flush();
			}
		}
	}
	/*
	 * Metodo main
	 * @param args
	 */
	
	public static void main(String []args) {
		try {
			//Cria os objetos necessario para instanciar o Servidor
			JLabel lblMessage = new JLabel("Porta do Servidor:");
			JTextField txtPorta = new JTextField("12345");
			Object[] texts = {lblMessage, txtPorta};
			JOptionPane.showMessageDialog(null, texts);
			server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
			clienteSocket = new ArrayList<BufferedWriter>();
			JOptionPane.showMessageDialog(null, "Servidor ativo na porta: " + txtPorta.getText());
			
			while(true) {
				System.out.println("Aguardando conexão...");
				Socket con = server.accept();
				System.out.println("Cliente conectado...");
				Thread t = new ServidorSocket(con);
				t.start();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // Fim do metodo main
} // Fim da classe

