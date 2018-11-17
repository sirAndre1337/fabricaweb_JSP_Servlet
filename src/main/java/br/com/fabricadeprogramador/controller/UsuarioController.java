package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.model.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {

	public UsuarioController() {
		System.out.println("Chamo o construtor");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("chamo o init");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDAO ud = new UsuarioDAO();
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		String id = req.getParameter("id");
		
		if(acao.equals("excluir")) {
		Usuario usuario = new Usuario();
		if (id != null)
			usuario.setId(Integer.parseInt(id));
		ud.excluir(usuario);

		resp.sendRedirect("usucontroller.do?acao=listar");
		}
		
		
		if(acao.equals("listar")) {
			List<Usuario> listar = ud.listar();
			req.setAttribute("lista", listar);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		}
		
		if(acao.equals("atualizar")) {
			Usuario usuario = ud.buscarUsuarioID(Integer.parseInt(id));
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulariousu.jsp");
			dispatcher.forward(req, resp);
		}
		
		if(acao.equals("novo")) {
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulariousu.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = new Usuario();

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		Integer idParsed = new Integer(id);
		
		
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setId(Integer.parseInt(id));

		UsuarioDAO ud = new UsuarioDAO();
		if (idParsed != 0) {
			ud.atualizar(usuario);
		} if(idParsed == 0){
			ud.salvar(usuario);
		}
		
		resp.sendRedirect("usucontroller.do?acao=listar");

	}

	@Override
	public void destroy() {
		System.out.println("chamou Destroy!");
		super.destroy();
	}
}
