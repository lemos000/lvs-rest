package br.com.fiap.beans;

public class Endereco {
	 private int endereco_id;
	 private String cep;
	 private String logradouro;
	 private String bairro;
	 private String cidade;
	 private String estado;
	 
	 
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Endereco(int endereco_id, String cep, String logradouro, String bairro, String cidade, String estado) {
		super();
		this.endereco_id = endereco_id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public int getEndereco_id() {
		return endereco_id;
	}
	public void setEndereco_id(int endereco_id) {
		this.endereco_id = endereco_id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
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
	 
	 

}
