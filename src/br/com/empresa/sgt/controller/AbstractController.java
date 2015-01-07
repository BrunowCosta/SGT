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
		//TODO ver como botar um prefixo.
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage((e.getSeverity() == null ? FacesMessage.SEVERITY_ERROR : e.getSeverity()),
				"", e.getMessage()));
	}
	
	protected void addInterfaceMessage(String mensagem, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, "", mensagem));
	}
}
