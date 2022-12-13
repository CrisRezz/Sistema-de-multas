package br.com.mildevs.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Condutor {
	
	//Condutor(nroCnh, dataEmissao, orgaoEmissor, pontuacao, Veiculo)
	
	@Id
	@Column(name = "nro_cnh", nullable = false)
	private int nroCnh;
	
	@Column (name = "data_emissao", nullable = false)
	private LocalDate dataEmissao;
	
	@Column (name = "orgao_emissor", nullable = false)
	private String orgaoEmissor;
	
	@Column (nullable = false)
	private int pontuacao;
	

	@OneToOne (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "veiculo", referencedColumnName = "placa")
	private Veiculo veiculo;
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public int getNroCnh() {
		return nroCnh;
	}

	public void setNroCnh(int nroCnh) {
		this.nroCnh = nroCnh;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}


	
	@Override
	public String toString() {
		return "\nNumero CNH: " + getNroCnh() +
				"\nData de emissão: " + dataEmissao +
				"\nOrgão emissor: " + orgaoEmissor +
				"\nPontuação: " + pontuacao;
	}
	
	

}