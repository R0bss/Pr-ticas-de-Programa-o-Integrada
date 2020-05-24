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
		String psenha = request.getParameter("senha");
		String acao = request.getParameter("acao");
		
		Usuario usuario = new Usuario();
		Usuario arqlog  = new Usuario();
		//Usuario teste = new Usuario();
		
		
		usuario.setNome(pnome);
		usuario.setSenha(psenha);
		
		arqlog.setNome(pnome);
		arqlog.setSenha(psenha);
		
		//ArqLog arqlog;
		UsuarioService us = new UsuarioService();
		RequestDispatcher dispatcher = null;
		
		//arqlog = new ArqLog(usuario.getNome(), usuario.getSenha());
		//teste = arqlog.ArqLogLogin();
			
			if(acao.equals("Login")) {
				usuario = us.checkLogin(usuario.getNome(), usuario.getSenha());
				//manda parametro para o JSP via request
				request.setAttribute("usuario", usuario);
				//dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher = request.getRequestDispatcher("Login.jsp");
				
			}else if(acao.equals("Logando")) {
				usuario = us.checkLogin(usuario.getNome(), usuario.getSenha());
				//arqlog = new ArqLog(usuario.getNome(), usuario.getSenha());
				ArqLog.ArqLogLogin(arqlog.getNome(), arqlog.getSenha());

				if(usuario != null) {
					//request.setAttribute("usuario", usuario);
					dispatcher = request.getRequestDispatcher("ListarPais.jsp");
				}else { request.setAttribute("usuario", usuario); 
					dispatcher = request.getRequestDispatcher("Login.jsp"); 
				}
			}
			dispatcher.forward(request, response);
			}

}
