package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Veiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class VeiculoDAO {

	private static EntityManager manager;

	public VeiculoDAO() {
		VeiculoDAO.manager = Persistence.createEntityManagerFactory("Multa").createEntityManager();
          CondutorDAO condutorDAO =new CondutorDAO(); 
	}

	// criar
	public boolean criarVeiculo (Veiculo veiculo , Condutor condutor) {
		String placa = veiculo.getPlaca();
		Veiculo veiculoCadastrado = manager.find(Veiculo.class, placa);
		veiculo.setCondutor(condutor);
		condutor.setVeiculo(veiculo);
		if (veiculoCadastrado != null) {
			System.out.println();
			System.err.println("Esta placa já está cadastrada");
			return false;
		}
		
		manager.getTransaction().begin();
		manager.persist(veiculo);
		manager.merge(condutor);
		manager.persist(condutor);
		manager.getTransaction().commit();
		return true;
	}
	
	
	
	// consultar

	public static Veiculo consultarVeiculos(String placa) {
		return manager.find(Veiculo.class, placa);

	}

	// Listar
	public List<Veiculo> listaveiculos() {
		jakarta.persistence.Query query = manager.createQuery("SELECT v from Veiculo as v");
		return query.getResultList();
	}

	// Remover

	public boolean removeVeiculo(String placa) {
		Veiculo VeiculoASerRemovido = manager.find(Veiculo.class, placa);
		if (VeiculoASerRemovido == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(VeiculoASerRemovido);
		manager.getTransaction().commit();
		return true;

	}
}
