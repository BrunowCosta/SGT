package br.com.empresa.sgt.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.empresa.sgt.business.AccessoBusiness;
import br.com.empresa.sgt.business.BusinessException;
import br.com.empresa.sgt.model.Usuario;

@ManagedBean(name="acessoController")
@RequestScoped
public class AcessoController extends AbstractController {
	
	public static final String USUARIO_LOGADO = "usuarioLogado";
	
	// Campos da tela
	private String login;
	private String senha;
	
	public AcessoController() {}
	
	public String logon() {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();  
			HttpSession session = (HttpSession) context.getSession(true);
			HttpServletRequest request = (HttpServletRequest)context.getRequest();
			
			Usuario user = AccessoBusiness.getInstance().autenticar(this.login, this.senha, request);
			session.setAttribute(AcessoController.USUARIO_LOGADO, user);
			
			// TODO Ver por que as vezes da pau no firefox
			// TODO Ver um maneira legal de botar o caminho das telas.
			// Provavelmente vai ser utilizado no prettyface.
			return "/resources/view/usuario/incluirUsuario";
		} catch (BusinessException e) {
			super.addInterfaceMessage(e);
		} catch (Exception e) {
			e.printStackTrace();
			super.addGenericErroMessage();
		}
		return null;
//		return "/resources/view/controleAcesso/usuario/incluirUsuario";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "pagina_inicio";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
