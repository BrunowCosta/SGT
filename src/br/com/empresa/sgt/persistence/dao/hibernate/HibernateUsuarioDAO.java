package br.com.empresa.sgt.persistence.dao.hibernate;
 
import java.util.HashMap;
import java.util.Map;

import br.com.empresa.sgt.model.Usuario;
import br.com.empresa.sgt.persistence.arq.GenericHibernateDAO;
import br.com.empresa.sgt.persistence.dao.UsuarioDAO;
// TODO botar uma interface
public class HibernateUsuarioDAO extends GenericHibernateDAO <Usuario, Integer> implements UsuarioDAO {
 
	public HibernateUsuarioDAO() {
		super();
	}
	
	public Usuario authenticate(String login, String senha){
       Map<String, Object> parameters = new HashMap<String, Object>();
       parameters.put("login", login);     
       parameters.put("senha", senha);     
 
       return super.findOneResult(Usuario.QUERY_AUTENTICACAO, parameters);
    }
}