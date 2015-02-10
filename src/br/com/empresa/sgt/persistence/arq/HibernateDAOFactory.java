package br.com.empresa.sgt.persistence.arq;

import br.com.empresa.sgt.persistence.dao.RegistroAcessoDAO;
import br.com.empresa.sgt.persistence.dao.UsuarioDAO;
import br.com.empresa.sgt.persistence.dao.hibernate.RegistroAcessoHibernateDAO;
import br.com.empresa.sgt.persistence.dao.hibernate.UsuarioHibernateDAO;

public class HibernateDAOFactory extends DAOFactory {  
	  
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO) this.instantiateDAO(UsuarioHibernateDAO.class);
	}

	@Override
	public RegistroAcessoDAO getRegistroAcessoDAO() {
		return (RegistroAcessoDAO) this.instantiateDAO(RegistroAcessoHibernateDAO.class);
	} 
  
    private GenericHibernateDAO instantiateDAO(Class daoClass) {  
        try {  
            GenericHibernateDAO dao = (GenericHibernateDAO) daoClass.newInstance();  
            return dao;  
        } catch (Exception ex) {  
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);  
        }  
    }  
  
}  