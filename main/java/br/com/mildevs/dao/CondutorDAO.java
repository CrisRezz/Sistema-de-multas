package br.com.mildevs.dao;
import java.util.List;
import br.com.mildevs.entity.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;


public class CondutorDAO {

	private EntityManager manager;
	
	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("Multa").createEntityManager();
	}
	
	//criar
			public boolean criarCondutor(Condutor condutor) {
				int nroCnh = condutor.getNroCnh();						
				Condutor condutorCadastrado = manager.find(Condutor.class, nroCnh);
				
				if (condutorCadastrado != null) {
					System.out.println();
					System.out.println("Esta CNH já está cadastrada");
					return false;
				}
				
				manager.getTransaction().begin();
				manager.persist(condutor);
				manager.getTransaction().commit();
				return true;
			}
			
			
			//consultar 
			
			public Condutor consultarCondutor(int cnh) {
				return manager.find(Condutor.class,cnh);
				
			}
			
			//Listar
			public List<Condutor> listaCondutor(){
				jakarta.persistence.Query query = manager.createQuery("SELECT c from Veiculo as c");
				return query.getResultList();
			}
			
			//Remover
			
			public boolean removeCondutor (int nroCnh) {
				Condutor CondutorASerRemovido = manager.find(Condutor.class, nroCnh);
				if (CondutorASerRemovido == null)
					return false;
				
					  manager.getTransaction().begin();
				      manager.remove(CondutorASerRemovido);
				      manager.getTransaction().commit();
				    return true;
				
			}
			
			
	}

	
	
	
	
	

	