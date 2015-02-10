package br.com.empresa.sgt.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.empresa.sgt.utils.MessageBundleUtils;

@Entity
@Table(name="T_USUARIO", schema="ACESSO")
@SequenceGenerator(name="SEQ_ID_USUARIO", sequenceName="ACESSO.SEQ_ID_USUARIO", schema="ACESSO")
@NamedQuery(name = "Usuario.autenticacao", query = "select u from Usuario u where u.login = :login and u.senha = :senha")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8221347861916518793L;
	
	public final static String QUERY_AUTENTICACAO = "Usuario.autenticacao";
	
	public final static char STATUS_INATIVO = 'I';
	public final static String STATUS_INATIVO_DESCRICAO = "usuario.inativo";
	public final static char STATUS_ATIVO = 'A';
	public final static String STATUS_ATIVO_DESCRICAO = "usario.ativo";
	public final static char STATUS_CANCELADO = 'C';
	public final static String STATUS_CANCElADO_DESCRICAO = "usuario.cancelado";
	public final static char STATUS_BLOQUEADO = 'B';
	public final static String STATUS_BLOQUEADO_DESCRICAO = "usuario.bloqueado";
	
	@Id
	@Column(name="IDUSUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_USUARIO")
	private Integer id;
	
	//TODO botar unique
	@Column(nullable=false, length=30)
	private String login;
	
	//É hashado com salt.
	@Column(nullable=false, length=32)
	private String senha;
	
	@Column(nullable=false)
	private char status;
	
	// Dados Pessoais
	@Column(nullable=false, length=50)
	private String nome;
	
	@Column(nullable=false, length=10)
	private String nomeExibicao;
	
	@Column(nullable=false, length=30)
	private String email;
	
	@Column(nullable=false, length=11)
	private String cpf;
	
	@Column(nullable=false)
	private String registroGeral;
	
	private String orgaoExpeditor;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	
	private String telefonePrincipal;
	
	private String telefoneAuxiliar;
	
	@Column(nullable=false)
	private char sexo;
	
	private String urlFotoPerfil;
	
	// Endereço
	@Column(nullable=false, length=8)
	private String cep;
	
	@Column(nullable=false)
	private String rua;
	
	@Column(nullable=false)
	private String bairro;
	
	private String complemento;
	
	@Column(nullable=false)
	private String cidade;
	
	@Column(nullable=false)
	private String estado;
	
	@Column(nullable=false)
	private String pais;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar blockDate;
	
	// Milisegundos do cadastro ou ultima alteração de senha do usuário.
	@Column(nullable=false)
	private String saltAgent;
	
	public Usuario() {}
	
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

	public String getSaltAgent() {
		return saltAgent;
	}

	public void setSaltAgent(String saltAgent) {
		this.saltAgent = saltAgent;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeExibicao() {
		return nomeExibicao;
	}

	public void setNomeExibicao(String nomeExibicao) {
		this.nomeExibicao = nomeExibicao;
	}

	public String getRegistroGeral() {
		return registroGeral;
	}

	public void setRegistroGeral(String registroGeral) {
		this.registroGeral = registroGeral;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getTelefoneAuxiliar() {
		return telefoneAuxiliar;
	}

	public void setTelefoneAuxiliar(String telefoneAuxiliar) {
		this.telefoneAuxiliar = telefoneAuxiliar;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Calendar getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(Calendar blockDate) {
		this.blockDate = blockDate;
	}
	
	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDescricaoStatus() {
		
		switch (this.getStatus()) {
			case Usuario.STATUS_ATIVO:
				return MessageBundleUtils.getInstance().getMessage(Usuario.STATUS_ATIVO_DESCRICAO);
				
			case Usuario.STATUS_BLOQUEADO:
				return MessageBundleUtils.getInstance().getMessage(Usuario.STATUS_BLOQUEADO_DESCRICAO);
				
			case Usuario.STATUS_CANCELADO:
				return MessageBundleUtils.getInstance().getMessage(Usuario.STATUS_CANCElADO_DESCRICAO);
				
			case Usuario.STATUS_INATIVO:
				return MessageBundleUtils.getInstance().getMessage(Usuario.STATUS_INATIVO_DESCRICAO);
	
			default: return MessageBundleUtils.getInstance().getMessage("system.outro");
		}
	}

} 
