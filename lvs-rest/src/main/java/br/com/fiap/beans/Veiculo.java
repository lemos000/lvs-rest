package br.com.fiap.beans;

public class Veiculo {
	   	private int veiculo_id;
	    private String modelo;
	    private String marca;
	    private String ano;
	    private int user_id;
	    private String carroceria;
	    private Integer cilindrada;
	    private String porte;
	    private Integer capacidade_carga;
	    private Clientes cliente;
	    
	    

		public Veiculo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Veiculo(int veiculo_id, String modelo, String marca, String ano, int user_id, String carroceria,
				Integer cilindrada, String porte, Integer capacidade_carga, Clientes cliente) {
			super();
			this.veiculo_id = veiculo_id;
			this.modelo = modelo;
			this.marca = marca;
			this.ano = ano;
			this.user_id = user_id;
			this.carroceria = carroceria;
			this.cilindrada = cilindrada;
			this.porte = porte;
			this.capacidade_carga = capacidade_carga;
			this.cliente = cliente;
		}

		public int getVeiculo_id() {
			return veiculo_id;
		}

		public void setVeiculo_id(int veiculo_id) {
			this.veiculo_id = veiculo_id;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public String getCarroceria() {
			return carroceria;
		}

		public void setCarroceria(String carroceria) {
			this.carroceria = carroceria;
		}

		public Integer getCilindrada() {
			return cilindrada;
		}

		public void setCilindrada(Integer cilindrada) {
			this.cilindrada = cilindrada;
		}

		public String getPorte() {
			return porte;
		}

		public void setPorte(String porte) {
			this.porte = porte;
		}

		public Integer getCapacidade_carga() {
			return capacidade_carga;
		}

		public void setCapacidade_carga(Integer capacidade_carga) {
			this.capacidade_carga = capacidade_carga;
		}

		public Clientes getCliente() {
			return cliente;
		}

		public void setCliente(Clientes cliente) {
			this.cliente = cliente;
		}
	    
	    
	    
}
