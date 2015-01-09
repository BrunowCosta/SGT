package br.com.empresa.sgt.persistence.dao.hibernate;
 
import br.com.empresa.sgt.model.RegistroAcesso;
import br.com.empresa.sgt.persistence.arq.GenericHibernateDAO;
import br.com.empresa.sgt.persistence.dao.RegistroAcessoDAO;
 
public class HibernateRegistroAcessoDAO extends GenericHibernateDAO<RegistroAcesso, Integer> implements RegistroAcessoDAO{
 
	public HibernateRegistroAcessoDAO() {
		super();
	}
	
}