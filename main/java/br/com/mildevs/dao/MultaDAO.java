package br.com.mildevs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;


public class MultaDAO {

	
	private EntityManager manager;

	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("Multa").createEntityManager();
	}
		//criar
	public void criarMulta(Multa multa, Veiculo veiculo, Condutor condutor) {	
		List<Multa> multas = veiculo.getMultas();
		if (multas == null || multas.isEmpty()) {
			multas = new ArrayList<Multa>();
		}
		
		multas.add(multa);
		veiculo.setMultas(multas);
		
		multa.setVeiculo(veiculo);
		condutor.setPontuacao(condutor.getPontuacao() + multa.getPontuacao());
		
		manager.getTransaction().begin();
		manager.persist(multa);
		manager.merge(veiculo);
		manager.merge(condutor);
		manager.getTransaction().commit();
		
	}
		
		//consultar 
		
		public Multa consultarMultas(int codigoMulta) {
			return manager.find(Multa.class,codigoMulta);
			
		}
		
		//Listar
		public List<Multa> listaMultas(){
			Query query = manager.createQuery("SELECT id"
					+ "from multas as m and condutor as c");
					
			return query.getResultList();
		}
		
		
		//Remover
		
		public boolean removeMulta (int codigoMulta) {
			Multa multaASerRemovida = manager.find(Multa.class, codigoMulta);
			if (multaASerRemovida == null)
				return false;
			
				  manager.getTransaction().begin();
			      manager.remove(multaASerRemovida);
			      manager.getTransaction().commit();
			    return true;
			
		}
		
		
}

