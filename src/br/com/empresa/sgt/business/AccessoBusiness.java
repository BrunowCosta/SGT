package br.com.empresa.sgt.business;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.reference.crypto.JavaEncryptor;

import br.com.empresa.sgt.model.RegistroAcesso;
import br.com.empresa.sgt.model.Usuario;
import br.com.empresa.sgt.persistence.RegistroAcessoDAO;
import br.com.empresa.sgt.persistence.UsuarioDAO;
import br.com.empresa.sgt.utils.MessageBundleUtils;


public class AccessoBusiness {
	
	// Sigleton
	private static AccessoBusiness instance;
	
	private AccessoBusiness(){}
	
	// TODO ver um padrão de projeto para isso tipo fabrica.
	UsuarioDAO userDAO = new UsuarioDAO();
	
	public static synchronized AccessoBusiness getInstance() {
		if(instance == null) {
			instance = new AccessoBusiness();
		}
		
		return instance;
	}
	
	// TODO Validar dados da entraga para evitar sql enjection
	public Usuario autenticar(String login, String senha, HttpServletRequest request) throws BusinessException {
		Usuario user = userDAO.findOneByField("login", "=", login);
		
		try {
			// Usuário informou um login valido
			if(user != null) { 
				if(user.getStatus() == Usuario.STATUS_ATIVO) {
					// Caso a autenticação esteja correta
					if(this.aplicaHash(senha, user.getSaltAgent()).equals(user.getSenha())) {
						this.registerAccess(RegistroAcesso.TIPO_SUCESSO, login, senha, request);
						return user;
					}
				} else {
					// Usuario com status invalido/bloqueado
					this.registerAccess(RegistroAcesso.TIPO_BLOQUEADO, login, senha, request);
					String mensagem = MessageBundleUtils.getInstance().getMessage("sistema.erro.login.impedido") + user.getDescricaoStatus();
					throw new BusinessException(mensagem);
				}
			}
			
			// O usuário informado não existe ou a senha é invalida
			//TODO verificar as informações possiveis para isso aqui (ip, hora, bla bla)
			this.registerAccess(RegistroAcesso.TIPO_NEGADO, login, senha, request);
			throw new BusinessException(MessageBundleUtils.getInstance().getMessage("sistema.erro.login.invalido"));
			
		// Caso tenha acontecido algum problema na criptografia retorna erro interno no servidor.
		// TODO criar um tabela para persistir os erros internos no servidor.
		} catch (EncryptionException e) {
			e.printStackTrace();
			throw new BusinessException(MessageBundleUtils.getInstance().getMessage("sistema.erro.generico"), e);
		}
	}
	
	public String aplicaHash(String senha, String saltAgent) throws EncryptionException {
		// Aplica o Hash + Salt a senha do usuário.
		JavaEncryptor enc = (JavaEncryptor) JavaEncryptor.getInstance();
		return enc.hash(senha,saltAgent);
	}
	
	// TODO VER COMO PEGAR INFORMAÇÔES DO REQUEST
	public void registerAccess(char type, String login, String password, HttpServletRequest request) {
		RegistroAcessoDAO registroAcessoDAO = new RegistroAcessoDAO();
		RegistroAcesso registro = new RegistroAcesso(login, password, "ip", "browser", Calendar.getInstance(), type);
		registroAcessoDAO.persist(registro);
	}
}
