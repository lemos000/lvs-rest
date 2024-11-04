package br.com.fiap.beans;

public class Oficina {
	private String nome;
	private String endereco;
	private String contato;
	private String status;
	
	
	public Oficina(String nome, String endereco, String contato, String status) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.contato = contato;
		this.status = status;
	}
	
	public Oficina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
