package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

//@WebFilter("/Aula8/*")
@WebFilter(urlPatterns = {"/listar_pais.do", "/ManterPais.do"})

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String acao = request.getParameter("acao");

		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();
		Usuario logado = (Usuario) session.getAttribute("logado");
		String path = req.getContextPath();
		String uri = req.getRequestURI();

		
		  if (logado != null && !uri.equals(path + "/Login.jsp") && !acao.equals("Login") && !acao.equals("Logando") && !uri.equals(path + "/index.jsp")) {
			  ((HttpServletResponse) response).sendRedirect(path + "/Login.jsp"); 
		  }else{ 
			  chain.doFilter(request, response); 
		  }
		 
		
	}
	public void init(FilterConfig fConfig) throws ServletException{
		
	}
}
