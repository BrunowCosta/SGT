package br.com.empresa.sgt.business;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import br.com.empresa.sgt.utils.MessageBundleUtils;


@SuppressWarnings("serial")
public class BusinessException extends Exception {
	
	public static final String SEVERITY_ERROR = "E";
	public static final String SEVERITY_INFO = "I";
	public static final String SEVERITY_FATAL = "F";
	public static final String SEVERITY_WARNNING = "W";
	
	private String severity;
	private String prefixo;
	private Exception excecaoAnterior;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, String prefixo, String severity, Exception excecaoAnterior) {
		super(message);
		this.prefixo = prefixo;
		this.severity = severity;
		this.excecaoAnterior = excecaoAnterior;
	}
	
	public BusinessException(Exception excecaoAnterior) {
		super(MessageBundleUtils.getInstance().getMessage("sistema.erro.generico"));
		this.prefixo = MessageBundleUtils.getInstance().getMessage("sistema.erroPrefixo.generico");
		this.severity = BusinessException.SEVERITY_ERROR;
		this.excecaoAnterior = excecaoAnterior;
	}
	
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public Exception getExcecaoAnterior() {
		return excecaoAnterior;
	}

	public void setExcecaoAnterior(Exception excecaoAnterior) {
		this.excecaoAnterior = excecaoAnterior;
	}
	
}
