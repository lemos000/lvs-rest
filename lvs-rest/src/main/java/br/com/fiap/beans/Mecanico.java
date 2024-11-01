package br.com.fiap.beans;

public class Mecanico {
    private int mecanico_id;
    private String nome;
    private String cnpj;
    private String celular1;
    private String celular2;
    private String telefone1;
    private String telefone2;
    private String email;
    private int endereco_id;
    private String numero;
    private String complemento;
    
    
	public Mecanico(int mecanico_id, String nome, String cnpj, String celular1, String celular2, String telefone1,
			String telefone2, String email, int endereco_id, String numero, String complemento) {
		super();
		this.mecanico_id = mecanico_id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.celular1 = celular1;
		this.celular2 = celular2;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.email = email;
		this.endereco_id = endereco_id;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public Mecanico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMecanico_id() {
		return mecanico_id;
	}
	public void setMecanico_id(int mecanico_id) {
		this.mecanico_id = mecanico_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
