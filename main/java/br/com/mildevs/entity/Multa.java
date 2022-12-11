package br.com.mildevs.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( nullable = false)
	private int codigoMulta;
	
	@Column (nullable = false)
	private double valor;
	
	@Column (nullable = false)
	private int pontuacao;
	

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "veiculo", referencedColumnName = "placa")
	private Veiculo veiculo;
	
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "nro_cnh", referencedColumnName = "nro_cnh")
	private Condutor condutor;

	
	
	
	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}



	
	public int getCodigoMulta() {
		return codigoMulta;
	}

	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}


	@Override
	public String toString() {
		return "Código:" + codigoMulta +
				"\nValor: " + valor +
				"\nPontuação: " + pontuacao
				+ "\nPlaca do veiculo: " + veiculo.getPlaca();
	}

	
}
	
	

