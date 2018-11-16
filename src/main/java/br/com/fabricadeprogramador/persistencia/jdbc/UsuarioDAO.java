package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fabricadeprogramador.model.Usuario;

public class UsuarioDAO {

	private Connection connection = null;

	public UsuarioDAO() {
		this.connection = ConexaoFactory.getConnection();
	}

	public void salvar(Usuario usuario) {
		String sql = "insert into usuario (nome,login,senha) values (?,?,?)";
		try {
			PreparedStatement salvar = connection.prepareStatement(sql);
			salvar.setString(1, usuario.getNome());
			salvar.setString(2, usuario.getLogin());
			salvar.setString(3, usuario.getSenha());
			salvar.execute();
			salvar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Usuario usuario) {
		String sql = "update usuario set nome = ?,login = ?,senha=? where id = ?";
		try {
			PreparedStatement atualizar = connection.prepareStatement(sql);
			atualizar.setString(1, usuario.getNome());
			atualizar.setString(2, usuario.getLogin());
			atualizar.setString(3, usuario.getSenha());
			atualizar.setInt(4, usuario.getId());
			atualizar.execute();
			atualizar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Usuario usuario) {
		String sql = "delete from usuario where id = ?";
		try {
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.setInt(1, usuario.getId());
			deletar.execute();
			deletar.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
