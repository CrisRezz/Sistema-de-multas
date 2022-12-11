package br.com.mildevs.entity;



import java.util.List;

import javax.management.Query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
public class Veiculo {
//Veiculo (placa, ano, modelo, marca, Condutor, List<Multa> multas))
	
	
	@Id
	@Column(nullable = false)
	private int placa;
	
	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}


	@Column (nullable = false)
	private int ano;
	
	
	
	
	
	public List<Multa> getMulta() {
		return multa;
	}

	public void setMulta(List<Multa> multa) {
		this.multa = multa;
	}

	public void setMultas(Multa multas) {
		this.multas = multas;
	}


	@Column 
	private String modelo;
	
	@Column 
	private String marca;
	
	
	@ManyToMany
	@JoinTable(name = "multa", 
			joinColumns = @JoinColumn(name = "codigomulta", referencedColumnName = "codigomulta"), 
			inverseJoinColumns = @JoinColumn(name = "nro_cnh", referencedColumnName = "nro_cnh"))
	private List<Multa> multa;
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "codigomulta", referencedColumnName = "codigomulta")
	private Multa multas;


	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "nro_cnh", referencedColumnName = "nro_cnh")
	private Condutor condutor;

	
	
	public List<Multa> getMultas() {
		return multa;
	}

	public void setMultas(List<Multa> multas) {
		this.multa = multas;
	}
	
	
	
	

	public int getPlaca() {
		return placa;
	}

	public void setPlaca(int placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
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

	
	public String toString() {
		return "\nPlaca: " + placa 
				+ "\nModelo: "+ modelo + " " + ano + "\nMarca: " + marca
				+ "\nCNH do proprietário: " + condutor.getNroCnh();
					
	
}
}
