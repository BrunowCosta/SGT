package br.com.empresa.sgt.persistence.dao;

import br.com.empresa.sgt.model.Usuario;

public interface UsuarioDAO {

	public Usuario authenticate(String login, String senha);
	
}