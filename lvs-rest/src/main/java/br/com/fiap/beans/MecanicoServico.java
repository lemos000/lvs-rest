package br.com.fiap.beans;

public class MecanicoServico {
	private int mecanico_id;
    private int servico_id;
    
    
	public MecanicoServico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MecanicoServico(int mecanico_id, int servico_id) {
		super();
		this.mecanico_id = mecanico_id;
		this.servico_id = servico_id;
	}
	public int getMecanico_id() {
		return mecanico_id;
	}
	public void setMecanico_id(int mecanico_id) {
		this.mecanico_id = mecanico_id;
	}
	public int getServico_id() {
		return servico_id;
	}
	public void setServico_id(int servico_id) {
		this.servico_id = servico_id;
	}
    
    
}
