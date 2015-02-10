package br.com.empresa.sgt.persistence.dao;

import br.com.empresa.sgt.model.Usuario;
import br.com.empresa.sgt.persistence.arq.GenericDao;

public interface UsuarioDAO	extends GenericDao <Usuario, Integer>{

	public Usuario authenticate(String login, String senha);
	
}