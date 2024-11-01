package br.com.fiap.beans;

public class Servico {
	   private int servico_id;
	    private String descricao;
	    private String tipo_veiculo;
	    private double preco;
	    private String exigencia_tecnica;
	    private String duracao;

	    public int getServicoId() {
	        return servico_id;
	    }

	    public void setServicoId(int servico_id) {
	        this.servico_id = servico_id;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getTipoVeiculo() {
	        return tipo_veiculo;
	    }

	    public void setTipoVeiculo(String tipo_veiculo) {
	        this.tipo_veiculo = tipo_veiculo;
	    }

	    public double getPreco() {
	        return preco;
	    }

	    public void setPreco(double preco) {
	        this.preco = preco;
	    }

	    public String getExigenciaTecnica() {
	        return exigencia_tecnica;
	    }

	    public void setExigenciaTecnica(String exigencia_tecnica) {
	        this.exigencia_tecnica = exigencia_tecnica;
	    }

	    public String getDuracao() {
	        return duracao;
	    }

	    public void setDuracao(String duracao) {
	        this.duracao = duracao;
	    }

	    public Servico() {
	    }

	    public Servico(int servico_id, String descricao, String tipo_veiculo, 
	                   double preco, String exigencia_tecnica, String duracao) {
	        this.servico_id = servico_id;
	        this.descricao = descricao;
	        this.tipo_veiculo = tipo_veiculo;
	        this.preco = preco;
	        this.exigencia_tecnica = exigencia_tecnica;
	        this.duracao = duracao;
	    }
}
