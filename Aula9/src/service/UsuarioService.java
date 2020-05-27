package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
	Usuario usuario = new Usuario();
	UsuarioDAO user = new UsuarioDAO();
	
	public Usuario checkLogin(String nome, String senha) {
		return user.checkLogin(nome, senha);
	}
	public Usuario checkUsuario(String nome) {
		return user.checkUsuario(nome);
		
	}
}
