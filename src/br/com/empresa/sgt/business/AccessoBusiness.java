package br.com.empresa.sgt.business;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.reference.crypto.JavaEncryptor;

import br.com.empresa.sgt.model.RegistroAcesso;
import br.com.empresa.sgt.model.Usuario;
import br.com.empresa.sgt.persistence.arq.DAOFactory;
import br.com.empresa.sgt.persistence.arq.GenericDao;
import br.com.empresa.sgt.persistence.dao.UsuarioDAO;
import br.com.empresa.sgt.utils.MessageBundleUtils;


public class AccessoBusiness {
	
	// Sigleton Pattern
	private static AccessoBusiness instance;
	
	private DAOFactory HibernateDAOFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
	
	private AccessoBusiness(){}
	
	public static synchronized AccessoBusiness getInstance() {
		if(instance == null) {
			instance = new AccessoBusiness();
		}
		
		return instance;
	}
	
	// TODO Validar dados da entraga para evitar sql enjection
	public Usuario autenticar(String login, String senha, HttpServletRequest request) throws BusinessException, EncryptionException {
		
		UsuarioDAO usuarioDAO = HibernateDAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.findOneByField("login", GenericDao.CONDICAO_IGUAL, login);
		
		String erroLogicoPrefixo =  MessageBundleUtils.getInstance().getMessage("sistema.erroPrefixo.logico");
		// Usuário informou um login valido
		if(usuario != null) { 
			if(usuario.getStatus() == Usuario.STATUS_ATIVO) {
				// Caso a autenticação esteja correta
				if(this.aplicaHash(senha, usuario.getSaltAgent()).equals(usuario.getSenha())) {
					if(usuario.getStatus() == Usuario.STATUS_ATIVO) {
						this.registerAccess(RegistroAcesso.TIPO_SUCESSO, login, senha, request);
						return usuario;
					} else {
						// Usuario com status invalido/bloqueado
						this.registerAccess(RegistroAcesso.TIPO_BLOQUEADO, login, senha, request);
						String mensagem = MessageBundleUtils.getInstance().getMessage("sistema.erro.login.impedido") + usuario.getDescricaoStatus() + ".";
						throw new BusinessException(mensagem, erroLogicoPrefixo, BusinessException.SEVERITY_ERROR, null);
					}
				}
			}
		}
		
		// O usuário informado não existe ou a senha é invalida
		//TODO verificar as informações possiveis para isso aqui (ip, hora, bla bla)
		this.registerAccess(RegistroAcesso.TIPO_NEGADO, login, senha, request);
		throw new BusinessException(MessageBundleUtils.getInstance().getMessage("sistema.erro.login.invalido"), erroLogicoPrefixo, BusinessException.SEVERITY_ERROR, null);
	}
	
	public String aplicaHash(String senha, String saltAgent) throws EncryptionException {
		// Aplica o Hash + Salt a senha do usuário.
		JavaEncryptor enc = (JavaEncryptor) JavaEncryptor.getInstance();
		return enc.hash(senha,saltAgent);
	}
	
	// TODO VER COMO PEGAR INFORMAÇÔES DO REQUEST
	public void registerAccess(char type, String login, String password, HttpServletRequest request) {
		RegistroAcesso registro = new RegistroAcesso(login, password, "ip", "browser", Calendar.getInstance(), type);
		HibernateDAOFactory.getRegistroAcessoDAO().persist(registro);
	}
}
