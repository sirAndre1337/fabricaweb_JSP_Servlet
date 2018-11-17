package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.model.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessao = req.getSession(false);//Req.getSession passando False quer dizer que se não tiver sessao não e para criar uma nova.Pq por padrao se não tiver ele cria.
		
		//Matando a sessão!
		if(sessao != null) {
			sessao.invalidate();
		}
		
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Capturando dados da tela de login
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		//Instanciando o usuario passando os parametros vindo da tela de login
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		UsuarioDAO usuDAO = new UsuarioDAO();
		//Verificando se o usuario tem registro no BD
		Usuario autenticado = usuDAO.autenticar(usuario);
		
		if (autenticado == null) {
			resp.getWriter().print("<script> window.alert ('Login ou Senha incorretos');location.href='login.html'</script>");
			//resp.sendRedirect("login.html");
		} else {
			//Colocando o usuario logado na Sessao.
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", autenticado);
			//Encamiando para a tela inicial pos login.
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/index.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
