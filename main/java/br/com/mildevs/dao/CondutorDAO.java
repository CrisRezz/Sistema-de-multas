package br.com.mildevs.dao;


import java.util.List;
import br.com.mildevs.entity.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import net.bytebuddy.asm.Advice.This;


public class CondutorDAO {

	private static EntityManager manager;

	public CondutorDAO() {
		CondutorDAO.manager = Persistence.createEntityManagerFactory("Multa").createEntityManager();
	}

	// criar
	public boolean criarCondutor(Condutor condutor) {
		int nroCnh = condutor.getNroCnh();						
		Condutor condutorCadastrado = manager.find(Condutor.class, nroCnh);
		
		if (condutorCadastrado != null) {
			System.out.println();
			System.out.println("Esta CNH já está cadastrada!");
			return false;
		}
		
		manager.getTransaction().begin();
		manager.persist(condutor);
		manager.getTransaction().commit();
		return true;
	}

	// consultar

	public static Condutor consultarCondutor(int Cnh) {
		return manager.find(Condutor.class,Cnh);
		
	}
	// Listar
	public static List<Condutor> listaCondutor(){
		jakarta.persistence.Query query = manager.createQuery("SELECT c from veiculo as c");
		return query.getResultList();
	}

	// Remover

	public static boolean removeCondutor(int cnh) {
		Condutor CondutorASerRemovido = manager.find(Condutor.class, cnh);
		if (CondutorASerRemovido == null) {
			System.out.println("condutor não encontrado no sistema!");
			return false;
		}
		manager.getTransaction().begin();
		manager.remove(CondutorASerRemovido);
		manager.getTransaction().commit();
		System.out.println("Condutor removido com sucesso");
		return true;

	}

	
	
	
}

