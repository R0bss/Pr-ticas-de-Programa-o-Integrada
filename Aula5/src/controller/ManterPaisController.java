package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;
/**
 * Servlet implementation class ManterPaisController
 */
@WebServlet("/Pais")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManterPaisController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String pNome = request.getParameter("nome");
		long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		double pArea = Double.parseDouble(request.getParameter("area"));
		//instanciar o javabean
				Pais pais = new Pais();
				pais.setNome(pNome);
				pais.setPopulacao(pPopulacao);
				pais.setArea(pArea);
				
				//instanciar o service
				PaisService cs = new PaisService();
				cs.criar(pais);
				pais = cs.carregar(pais.getId());
				
//				PrintWriter out = response.getWriter();
//				out.println("<html><head><title>Pais Cadastrado</title></head><body>");
//				out.println(	"id: "+pais.getId()+"<br>");
//				out.println(	"nome: "+pais.getNome()+"<br>");
//				out.println(	"populacao: "+pais.getPopulacao()+"<br>");
//				out.println(	"area: "+pais.getArea()+"<br>");
//			    out.println("</body></html>");
				request.setAttribute("Pais", pais);
				
				RequestDispatcher view =
						request.getRequestDispatcher("Pais.jsp");
				view.forward(request, response);
				
	}

}
