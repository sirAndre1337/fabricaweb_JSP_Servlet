package br.com.fabricadeprogramador;

import java.util.List;

import org.junit.Test;

import br.com.fabricadeprogramador.model.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	@Test
	public void testaSalvarUsuario() {
		Usuario u1 = new Usuario();
		u1.setLogin("mila");
		u1.setNome("Camila");
		u1.setSenha("123");
		UsuarioDAO ud = new UsuarioDAO();
		ud.salvar(u1);
	}

	@Test
	public void testaAtualizarUsuario() {
		Usuario u1 = new Usuario();
		u1.setId(8);
		u1.setLogin("mila");
		u1.setNome("Camila atualizada pelo java");
		u1.setSenha("123456");
		UsuarioDAO ud = new UsuarioDAO();
		ud.atualizar(u1);

	}

	@Test
	public void testExcluirUsuario() {
		UsuarioDAO ud = new UsuarioDAO();
		Usuario u1 = ud.buscarUsuarioID(8);
		ud.excluir(u1);
	}

	@Test
	public void testBuscaUsuarioPorID() {
		UsuarioDAO ud = new UsuarioDAO();
		System.out.println(ud.buscarUsuarioID(8));
	}

	@Test
	public void testBuscaTodosUsuarios() {
		UsuarioDAO ud = new UsuarioDAO();
		List<Usuario> usuarios = ud.listar();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
}
