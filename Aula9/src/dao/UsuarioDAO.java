package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Usuario;

public class UsuarioDAO {
	
	public Usuario checkLogin(String login , String senha) {
		Usuario usuario = new Usuario();
		String sqlSelect = "SELECT login, senha FROM pais.user WHERE login =? ";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, login);
			//stm.setString(2, senha);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setLogin(rs.getString("login"));
					usuario.setSenha(rs.getString("senha"));
					if (!rs.getString("senha").equals(senha)) {
						usuario = null;
					}
				}else {
					usuario = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
	}
	public Usuario checkUsuario(String login) {
		Usuario usuario = new Usuario();
		String sqlSelect = "SELECT login FROM pais.user WHERE login =?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, login);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setLogin(rs.getString("login"));
				}else {
					usuario = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
	}	
	
}
