package controller;


//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crypto.CryptoException;
import crypto.CryptoUtils;
//import exemplo.CryptoAES;
import model.Usuario;

import service.UsuarioService;
//import util.ArqLog;
import util.ArqLog;





/**
 * Servlet implementation class Login
 */
@WebServlet("/Login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileWriter logRequest;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pnome = request.getParameter("nome");
		//String psenha = request.getParameter("senha");
		String psenha;
		String acao = request.getParameter("acao");
		byte[] senhaCrifa = null;
		Usuario usuario = new Usuario();
		Usuario arqlog  = new Usuario();
		//Usuario teste = new Usuario();
		

		
		
		if(request.getParameter("senha") != null) {
			psenha = request.getParameter("senha");
		}else {
			psenha = "";
		}
		
		// Cryptografando a senha
		String chave = "Mary has one cat";
		byte[] bTexto = psenha.getBytes("UTF-8");
		
		try {
			senhaCrifa = CryptoUtils.encrypt(chave, bTexto);
		} catch (CryptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cripto = new String(senhaCrifa, "UTF-8");
		System.out.println("Senha Crypto em String: " + cripto);
		usuario.setLogin(pnome);
		usuario.setSenha(cripto);
		
		arqlog.setLogin(pnome);
		arqlog.setSenha(cripto);
		
		
		UsuarioService us = new UsuarioService();
		RequestDispatcher dispatcher = null;
		
		/*
		usuario.setLogin(pnome);
		usuario.setSenha(psenha);
		
		arqlog.setLogin(pnome);
		arqlog.setSenha(psenha);
		*/
		//String chave - ""
		//arqlog = new ArqLog(usuario.getNome(), usuario.getSenha());
		//teste = arqlog.ArqLogLogin();
			
			if(acao.equals("Login")) {
				//usuario = us.checkLogin(usuario.getLogin(), usuario.getSenha());
				//manda parametro para o JSP via request
				request.setAttribute("usuario", usuario);
				//dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher = request.getRequestDispatcher("Login.jsp");
				
			}else if(acao.equals("Logando")) {
				usuario = us.checkLogin(usuario.getLogin(), usuario.getSenha());
				
				// Verificando o Login
				System.out.println(arqlog.getLogin());
				
				// Verificação do Usuario
				System.out.println(usuario);
				
				ArqLog.ArqLogLogin(arqlog.getLogin(), arqlog.getSenha());

				if(usuario != null) {
					request.setAttribute("logado", usuario);
					dispatcher = request.getRequestDispatcher("ListarPais.jsp");
				}else { request.setAttribute("usuario", usuario); 
					dispatcher = request.getRequestDispatcher("Login.jsp"); 
				}
			}
			dispatcher.forward(request, response);
			}

}
