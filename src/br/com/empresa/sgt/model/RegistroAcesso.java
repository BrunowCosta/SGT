package br.com.empresa.sgt.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_REGISTROACESSO", schema="ACESSO")
// FIXME Parace que a propriedade shema na nesta anotação está bugada, por isso o "sequenceName" foi adaptado.
@SequenceGenerator(name="SEQ_ID_REGISTROACESSO", sequenceName="ACESSO.SEQ_ID_REGISTROACESSO", schema="ACESSO")
public class RegistroAcesso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static char TIPO_NEGADO = 'D';
	public final static char TIPO_SUCESSO = 'S';
	public final static char TIPO_BLOQUEADO = 'B';
	
	@Id
	@Column(name="IDREGISTROACESSO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_REGISTROACESSO")
	private Integer id;
	
	@Column(nullable=false, length=30)
	private String login;
	
	@Column(nullable=false, length=50)
	private String senha;
	
	@Column(nullable=false)
	private String ip;
	
	private String browser;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar time;
	
	private char type;
	
	public RegistroAcesso() {}
	
	public RegistroAcesso(String login, String senha, String ip, String browser, Calendar time, char type) {
		super();
		this.login = login;
		this.senha = senha;
		this.ip = ip;
		this.browser = browser;
		this.time = time;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
}
