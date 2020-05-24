package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;

import service.PaisesService;


@WebServlet("/listar_pais.do")
public class ListarPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	  public ListarPaisController() {
		  super();
	  }
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		PaisesService paisservice = new PaisesService();
		ArrayList<Pais> lista = null;
		HttpSession session =request.getSession();
		

			if (acao.equals("buscar")) {
				if (chave != null&&chave.length() > 0) {
					lista = paisservice.listarPais(chave);
				}else {
				
					lista = paisservice.listarPais();
				}
				session.setAttribute("lista", lista);
			}else if (acao.equals("reiniciar")) {
				session.setAttribute("lista", null);
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListarPais.jsp");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
