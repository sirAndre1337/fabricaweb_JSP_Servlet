package br.com.fabricadeprogramador;

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
		Usuario u1 = new Usuario();
		u1.setId(6);
		UsuarioDAO ud = new UsuarioDAO();
		ud.excluir(u1);
	}
	
	
}
