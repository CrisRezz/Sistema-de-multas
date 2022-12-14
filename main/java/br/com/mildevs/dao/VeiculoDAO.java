package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class VeiculoDAO {

	private static EntityManager manager;

	public VeiculoDAO() {
		VeiculoDAO.manager = Persistence.createEntityManagerFactory("Multa").createEntityManager();
		CondutorDAO condutorDAO = new CondutorDAO();
		
	}

	// criar
	public boolean criarVeiculo(Veiculo veiculo, Condutor condutor) {
		String placa = veiculo.getPlaca();
		Veiculo veiculoCadastrado = manager.find(Veiculo.class, placa);
		if (veiculoCadastrado != null) {
			System.out.println();
			System.err.println("Esta placa já está cadastrada");
			return false;
		}
        veiculo.setCondutor(condutor);
		condutor.setVeiculo(veiculo);
		manager.getTransaction().begin();
		manager.persist(veiculo);
		manager.merge(condutor);
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
		else {
		Condutor condutor = VeiculoASerRemovido.getCondutor();
		condutor.setVeiculo(null);
		manager.getTransaction().begin();
		manager.remove(VeiculoASerRemovido);
		manager.merge(condutor);
		manager.getTransaction().commit();
		System.out.println("Veiculo removido com sucesso!");
		return true;
		}
	}
	
	
	public void venderVeiculo(Condutor vendedor, Condutor comprador, Veiculo veiculo) {	
		veiculo.setCondutor(comprador);
		vendedor.setVeiculo(null);
		
		if (comprador.getVeiculo() != null) {
			String placaAntiga = comprador.getVeiculo().getPlaca();
			Veiculo veiculoAntigo = manager.find(Veiculo.class, placaAntiga);
			
			comprador.setVeiculo(veiculo);
			
			manager.getTransaction().begin();
			manager.merge(vendedor);
			manager.merge(comprador);
			manager.merge(veiculo);
			manager.remove(veiculoAntigo);
			manager.getTransaction().commit();
			
		} else {
			comprador.setVeiculo(veiculo);
			manager.getTransaction().begin();
			manager.merge(vendedor);
			manager.merge(comprador);
			manager.merge(veiculo);
			manager.getTransaction().commit();

		}
		
	}
	}
	