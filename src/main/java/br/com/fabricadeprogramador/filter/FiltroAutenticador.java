package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
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

@WebFilter(dispatcherTypes=DispatcherType.REQUEST , urlPatterns="/*")
public class FiltroAutenticador implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request; // Cast no request pq ele esta Generico (ServletRequest) , eu precisso do HttpServletRequest.
		 HttpServletResponse resp = (HttpServletResponse) response;
		 HttpSession sessao = req.getSession(false);
		 
		 String uri = req.getRequestURI();
		 
		 if(sessao != null || uri.lastIndexOf("login.html") != -1 || uri.lastIndexOf("autenticador.do") !=-1 || uri.equals("/fabricaweb/") == true){
			 chain.doFilter(request, response);
		 } else {
			 resp.sendRedirect("login.html");
			 }
		 }
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
