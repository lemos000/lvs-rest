package br.com.fiap.beans;

import java.time.LocalDateTime;

public class OrdensServico {
    private int ordem_serv_id;
    private LocalDateTime data_abertura;
    private String status;
    private String descricao_problema;
    private int veiculo_id;
    private Veiculo veiculo;
    
    
	public OrdensServico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdensServico(int ordem_serv_id, LocalDateTime data_abertura, String status, String descricao_problema,
			int veiculo_id, Veiculo veiculo) {
		super();
		this.ordem_serv_id = ordem_serv_id;
		this.data_abertura = data_abertura;
		this.status = status;
		this.descricao_problema = descricao_problema;
		this.veiculo_id = veiculo_id;
		this.veiculo = veiculo;
	}
	public int getOrdem_serv_id() {
		return ordem_serv_id;
	}
	public void setOrdem_serv_id(int ordem_serv_id) {
		this.ordem_serv_id = ordem_serv_id;
	}
	public LocalDateTime getData_abertura() {
		return data_abertura;
	}
	public void setData_abertura(LocalDateTime data_abertura) {
		this.data_abertura = data_abertura;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescricao_problema() {
		return descricao_problema;
	}
	public void setDescricao_problema(String descricao_problema) {
		this.descricao_problema = descricao_problema;
	}
	public int getVeiculo_id() {
		return veiculo_id;
	}
	public void setVeiculo_id(int veiculo_id) {
		this.veiculo_id = veiculo_id;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
    
    
}
