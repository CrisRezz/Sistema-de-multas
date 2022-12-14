package br.com.mildevs.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Veiculo {
//Veiculo (placa, ano, modelo, marca, Condutor, List<Multa> multas))

	@Id
	@Column(nullable = false)
	private String placa;

	@Column(nullable = false)
	private int ano;

	@Column
	private String modelo;

	@Column
	private String marca;

	@OneToOne
	@JoinColumn(name = "codigomulta", referencedColumnName = "codigomulta")
	private Multa multas;

	@OneToOne
	@JoinColumn(name = "nro_cnh", referencedColumnName = "nro_Cnh")
	private Condutor condutor;

	@OneToMany(mappedBy = "veiculo")
	private List<Multa> multa;

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public List<Multa> getMulta() {
		return multa;
	}

	public void setMulta(List<Multa> multa) {
		this.multa = multa;
	}

	public void setMultas(Multa multas) {
		this.multas = multas;
	}

	public List<Multa> getMultas() {
		return multa;
	}

	public void setMultas(List<Multa> multas) {
		this.multa = multas;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
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
		return "\nPlaca: " + placa + "\nModelo: " + modelo + " " + ano + "\nMarca: " + marca + "\nCNH do proprietário: "
				+ condutor.getNroCnh();

	
	}
}
