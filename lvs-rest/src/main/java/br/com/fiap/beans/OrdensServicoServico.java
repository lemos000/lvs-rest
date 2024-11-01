package br.com.fiap.beans;

public class OrdensServicoServico {
	private int ordem_serv_id;
    private int servico_id;
	public int getOrdem_serv_id() {
		return ordem_serv_id;
	}
	public void setOrdem_serv_id(int ordem_serv_id) {
		this.ordem_serv_id = ordem_serv_id;
	}
	public int getServico_id() {
		return servico_id;
	}
	public void setServico_id(int servico_id) {
		this.servico_id = servico_id;
	}
	public OrdensServicoServico(int ordem_serv_id, int servico_id) {
		super();
		this.ordem_serv_id = ordem_serv_id;
		this.servico_id = servico_id;
	}
	public OrdensServicoServico() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
}
