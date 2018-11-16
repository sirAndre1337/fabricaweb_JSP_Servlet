package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public Usuario buscarUsuarioID(int id) {
		Usuario usuario = null;
		String sql = "select * from usuario where id = ?";
		try {
			PreparedStatement buscar = connection.prepareStatement(sql);
			buscar.setInt(1, id);
			ResultSet resultado = buscar.executeQuery();
			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			}
			buscar.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try {
			Statement busca = connection.createStatement();
			ResultSet resultado = busca.executeQuery(sql);
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuarios.add(usuario);
			}
			busca.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

}
