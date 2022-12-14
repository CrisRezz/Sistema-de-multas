package menuExtensao;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.mildevs.Program;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;

public class VeiculoMenu {

	private static final String placa = null;
	public static Scanner tc = new Scanner(System.in);
	private static VeiculoDAO veiculoDAO = new VeiculoDAO();
	private static CondutorDAO condutorDAO = new CondutorDAO();

	public static void menu() {
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
				cadastrarcoVeiculo(tc);
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
				venderVeiculo();
				break;

			case 6:
				Program.menuPrincipal();
				return;

			default:
				System.err.println("Opção inválida");
				break;
			}
		}
	}

	public static void cadastrarcoVeiculo(Scanner tc) {
		System.out.println("====== Insira os dados nescessários para Cadastrar o veículo");
		System.out.println();
		System.out.print("CNH do condutor: ");
		int cnh = tc.nextInt();
		Condutor condutor = CondutorDAO.consultarCondutor((cnh));
		if (condutor == null) {
			System.err.println("Este condutor não está cadastrado"
					+ "Cadastre o condutor e tente novamente");
			menu();
		} else if (condutor.getVeiculo() != null) {
			System.out.println();
			System.err.println("Este condutor já possui um veículo cadastrado"+
			"Revise os dados e tente novamente.");
			menu();
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
			System.out.println("Veiculo cadastrado com sucesso! " + "\nRedirecionando ao menu veiculos, aguarde :)");

			veiculo.setPlaca(placa);
			veiculo.setModelo(modelo);
			veiculo.setMarca(marca);
			veiculo.setAno(ano);
			veiculo.setMultas(new ArrayList<Multa>());
			VeiculoDAO veiculodao = new VeiculoDAO();
			veiculodao.criarVeiculo(veiculo, condutor);
			menu();

		}

	}

	private static void removerVeiculo() {
		System.out.println();
		System.out.println("==== Insira os dados nescessários para a remoção do veiculo:");
		System.out.print("Placa: ");
		String placa = tc.next();
		Veiculo veiculo = VeiculoDAO.consultarVeiculos(placa);
		if (veiculo == null) {
			System.out.println("Veiculo nao encontrado, confira os dados e tente novamente");
			menu();
		} else if (veiculo.getMulta() != null) {
			System.out.println("Não é possivel remover veiculos com multas.");
			menu();

		} else {
			veiculoDAO.removeVeiculo(placa);
			menu();
		}
	}

	private static void consultarVeiculo() {
		System.out.println();
		System.out.println("==== Adicione os dados necessários para a consulta ====");
		System.out.print("Placa: ");
		String placa = tc.next();
		Veiculo veiculo = VeiculoDAO.consultarVeiculos(placa);

		if (veiculo == null) {
			System.out.println();
			System.err.println("Nenhum veículo encontrado, confira os dados e tente novamente.");
			menu();
		} else {
			System.out.println(veiculo);
			System.out.println("Você será retornado ao menu veiculos");
			menu();
		}
	}

	private static void listarVeiculos() {
		System.out.println("Lista de veículos");
		System.out.println();

		java.util.List<Veiculo> veiculos = veiculoDAO.listaveiculos();

		if (veiculos == null || veiculos.isEmpty()) {
			System.out.println();
			System.err.println("Nenhum veículo encontrado, confira os dados e tente novamente");
			menu();
		} else {
			for (Veiculo v : veiculos) {
				System.out.println(v);
				menu();
			}

		}

	}

	private static void venderVeiculo() {
		System.out.println();
		System.out.println("Vender veículo");
		System.out.println();

		System.out.print("CNH do vendedor: ");
		int origem = tc.nextInt();

		System.out.print("CNH do comprador: ");
		int destino = tc.nextInt();

		Condutor vendedor = condutorDAO.consultarCondutor(origem);
		Condutor comprador = condutorDAO.consultarCondutor(destino);

		if (vendedor == null || comprador == null) {
			System.out.println();
			System.err.println("Condutor não encontrado, confira os dados e tente novamente.");
			menu();
		} else if (vendedor.equals(comprador)) {
			System.out.println();
			System.err.println("Vendedor e comprador não podem ser a mesma pessoa");
			menu();

		} else {
			System.out.print("Placa do carro: ");
			String placa = tc.next();
			Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);

			if (veiculo == null) {
				System.out.println();
				System.err.println("Nenhum veículo encontrado");
				menu();
			} else {
				int cnhDono = veiculo.getCondutor().getNroCnh();

				if (cnhDono == (vendedor.getNroCnh())) {
					System.out.println();
					System.err.println("Este veículo não pertence ao vendedor");
					menu();
				} else if (!(veiculo.getMultas().isEmpty())) {
					System.out.println();
					System.err.println("Veículo não pode ser vendido pois tem multas não resolvidas");
					menu();
				} else {
					veiculoDAO.venderVeiculo(vendedor, comprador, veiculo);

				}
			}
		}
	}

}
