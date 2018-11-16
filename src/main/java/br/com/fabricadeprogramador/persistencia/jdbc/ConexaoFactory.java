package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static Connection connection = null;
	private static String URL = "jdbc:postgresql://localhost:5432/fabricaweb";
	private static String USER = "postgres";
	private static String PASSWORD = "admin";

	public static Connection Conexao() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// Relançando a Exception
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static Connection getConnection() {
		return Conexao();
	}
}
