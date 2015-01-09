package br.com.empresa.sgt.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MessageBundleUtils {

	private static MessageBundleUtils instance;
	
	public static MessageBundleUtils getInstance(){
		if (instance == null)
			instance = new MessageBundleUtils();
		return instance;
	}
	
	private MessageBundleUtils() {}
	
	private ResourceBundle getResourceBundle(Locale locale) {
		if (locale == null) {
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		}
		return  ResourceBundle.getBundle("br.com.empresa.sgt.mensagens.mensagens", locale);
	}
	
	public String getMessage(String key) {
		return this.getResourceBundle(null).getString(key); 
	}
	
	public String getMessage(String key, Locale locale) {
		return this.getResourceBundle(locale).getString(key); 
	}
	
}