package br.com.empresa.sgt.persistence;
 
import java.util.HashMap;
import java.util.Map;

import br.com.empresa.sgt.model.Usuario;
 
public class UsuarioDAO extends GenericAbstractDao<Usuario, Integer> {
 
	public UsuarioDAO() {
		super();
	}
	
	public Usuario authenticate(String login, String senha){
       Map<String, Object> parameters = new HashMap<String, Object>();
       parameters.put("login", login);     
       parameters.put("senha", senha);     
 
       return super.findOneResult(Usuario.QUERY_AUTENTICACAO, parameters);
    }
}