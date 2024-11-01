package br.com.fiap.beans;

public class Clientes {
	private int user_id;
	private String nome;
	private String cpf;
	private String celular1;
	private String celular2;
	private String telefone1;
	private String telefone2;
	private String email;
	private int endereco_id;
	private String numero;
	private String complemento;
	
	
	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Clientes(int user_id, String nome, String cpf, String celular1, String celular2, String telefone1,
			String telefone2, String email, int endereco_id, String numero, String complemento) {
		super();
		this.user_id = user_id;
		this.nome = nome;
		this.cpf = cpf;
		this.celular1 = celular1;
		this.celular2 = celular2;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.email = email;
		this.endereco_id = endereco_id;
		this.numero = numero;
		this.complemento = complemento;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular1() {
		return celular1;
	}
	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}
	public String getCelular2() {
		return celular2;
	}
	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEndereco_id() {
		return endereco_id;
	}
	public void setEndereco_id(int endereco_id) {
		this.endereco_id = endereco_id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
}