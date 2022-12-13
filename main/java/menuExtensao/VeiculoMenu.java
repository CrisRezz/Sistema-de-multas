package menuExtensao;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;

public class VeiculoMenu {

	private static final Condutor Condutor = null;
	public static Scanner tc = new Scanner(System.in);
	private static VeiculoDAO veiculoDAO = new VeiculoDAO();
    private static CondutorDAO condutorDAO = new CondutorDAO();
    
	public static void  menu() {
		System.out.println();
		System.out.println("--------------------Menu veículo----------------------");
		System.out.println();
		System.out.println("1-Cadastrar veículo");
		System.out.println("2-Remover veículo");
		System.out.println("3-Consultar veículo");
		System.out.println("4-Listar veículos");
		System.out.println("5-Vender veículo");
		System.out.println("6-Voltar para menu principal");
		System.out.print("----Escolha uma opção: ");

		while (true) {
			int resp = tc.nextInt();

			switch (resp) {
			case 1:
				cadastrarVeiculo();
				break;

			case 2:
				removerVeiculo();
				break;

			case 3:
				consultarVeiculo();
				break;

			case 4:
				listarVeiculos();
				break;

			case 5:
				// venderVeiculo();
				break;

			case 6:
				return;

			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}

	public static void cadastrarVeiculo() {
		System.out.println("====== Insira os dados nescessários para Cadastrar o veículo");
		System.out.println();
         Veiculo veiculo = VeiculoMenu.cadastrar(tc);
         VeiculoDAO veiculodao = new VeiculoDAO();
         veiculodao.criarVeiculo(veiculo, Condutor);
         		

		}

	public static Veiculo cadastrar(Scanner tc) {
        System.out.print("CNH do condutor: ");
        int cnh = tc.nextInt();
		Condutor condutor = CondutorDAO.consultarCondutor((cnh));
            if  (condutor == null) {
            	System.err.println("Este condutor não está cadastrado");
      }
		else if (condutor.getVeiculo() != null) {
			System.out.println();
			System.err.println("Este condutor já possui um veículo cadastrado");

		} else {
		
		Veiculo veiculo = new Veiculo();
		System.out.print("Placa: ");
		String placa = tc.next();
		System.out.print("Modelo: ");
		String modelo = tc.next();
		System.out.print("Marca: ");
		String marca = tc.next();
		System.out.print("Ano: ");
		int ano = tc.nextInt();
       System.out.println("Veiculo cadastrado com sucesso! "
        		+ "\nRedirecionando ao menu veiculos, aguarde :)");
       		
		veiculo.setPlaca(placa);
		veiculo.setModelo(modelo);
		veiculo.setMarca(marca);
		veiculo.setAno(ano);
		veiculo.setMultas(new ArrayList<Multa>());
       
        return veiculo;
	}
		return null;
	}
	
	private static void removerVeiculo() {
		System.out.println();
		System.out.println("=== Adicione os dados necessários para remoção ===");
		System.out.println();

		System.out.print("Placa: ");
		String placa = tc.next();

		veiculoDAO.removeVeiculo(placa);
	}

	private static void consultarVeiculo() {
		System.out.println();
		System.out.println("==== Adicione os dados necessários para a consulta ====");
		System.out.print("Placa: ");
		String placa = tc.next();

		Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);
		if (veiculo == null) {
			System.out.println();
			System.err.println("Nenhum veículo encontrado");

		} else {
			System.out.println(veiculo);
		}
	}

	private static void listarVeiculos() {
		System.out.println("Lista de veículos");
		System.out.println();

		java.util.List<Veiculo> veiculos = veiculoDAO.listaveiculos();

		if (veiculos == null || veiculos.isEmpty()) {
			System.out.println();
			System.err.println("Nenhum veículo encontrado");

		} else {
			for (Veiculo v : veiculos) {
				System.out.println(v);
			}

		}

	}

}
