package br.com.empresa.sgt.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.empresa.sgt.business.BusinessException;
import br.com.empresa.sgt.model.Usuario;

public class AbstractController {

	protected Usuario getLoggedUser() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario userLogged = (Usuario) session.getAttribute(AcessoController.USUARIO_LOGADO);
		return userLogged;
	}
	
	// Metódos utilizados para exibir um mensagem amigavel na interface.
	protected void addInterfaceMessage(BusinessException e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.getServerity(e.getSeverity()), (e.getPrefixo() != null ? e.getPrefixo() : ""), e.getMessage()));
	}
	
	protected void addInterfaceMessage(String mensagem, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, "", mensagem));
	}
	
	private Severity getServerity(String codigo) {
		
		if(codigo == null) {
			return FacesMessage.SEVERITY_ERROR;
		}
		
		switch (codigo) {
		case BusinessException.SEVERITY_ERROR:
			return FacesMessage.SEVERITY_ERROR;
		case BusinessException.SEVERITY_FATAL:
			return FacesMessage.SEVERITY_FATAL;
		case BusinessException.SEVERITY_INFO:
			return FacesMessage.SEVERITY_INFO;
		case BusinessException.SEVERITY_WARNNING:
			return FacesMessage.SEVERITY_WARN;
		default:
			return FacesMessage.SEVERITY_ERROR;
		}
	}
}
