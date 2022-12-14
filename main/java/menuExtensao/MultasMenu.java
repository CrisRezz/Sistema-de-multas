package menuExtensao;

import java.util.Scanner;

import br.com.mildevs.Program;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;

public class MultasMenu {

	private static VeiculoDAO veiculoDAO = new VeiculoDAO();
	private static MultaDAO multaDAO = new MultaDAO();

	public static Scanner tc = new Scanner(System.in);

	public static void menu() {
		System.out.println();
		System.out.println("---------------------------- Menu Multas-----------------------------------");
		System.out.println("(1) Cadastrar multa.");
		System.out.println("(2) Remover multa.");
		System.out.println("(3) Consultar multa(s).");
		System.out.println("(4) Listar multas de um veiculo.");
		System.out.println("(5) Listar multas.");
		System.out.println("(6) Voltar para menu principal.");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		while (true) {
			int opcao = tc.nextInt();

			switch (opcao) {
			case 1:
				cadastrarMulta(tc);
				break;

			case 2:
				removerMulta();
				break;

			case 3:
				consultarMulta();
				break;

			case 4:
				listarMultasVeiculo();
				break;

			case 5:
				listarMultas();
				break;

			case 6:
                 Program.menuPrincipal();
				return;

			default:
				System.out.println("Opção inválida");
				break;
			}
		}

	}


	private static void cadastrarMulta(Scanner tc) {
		
		System.out.println("==== Adicione os dados nescessários para o cadastramento da multa ====");
		System.out.println();

		System.out.print("Placa do veículo: ");
		String placa = tc.next();

		Veiculo veiculo = VeiculoDAO.consultarVeiculos(placa);
		if (veiculo == null) {
			System.out.println();
			System.err.println("Este veículo não está cadastrado");

		} else {
		Multa multa = new Multa();
		System.out.print("Valor: ");
		double valor = tc.nextDouble();
		System.out.print("Pontuação: ");
		int pontuacao = tc.nextInt();
		
		multa.setValor(valor);
		multa.setPontuacao(pontuacao);
	    Condutor condutor = CondutorDAO.consultarCondutor(veiculo.getCondutor().getNroCnh());
        multaDAO.criarMulta(multa, veiculo, condutor);
       System.out.println(" multa cadastrada com sucesso!");
		}
	}

	private static void removerMulta() {
		System.out.println();
		System.out.println("Remover multa");
		System.out.println();

		System.out.print("Código: ");
		int codigoMulta = tc.nextInt();

		multaDAO.removeMulta(codigoMulta);
	}

	private static void consultarMulta() {
		System.out.println();
		System.out.println("Consultar multa");
		System.out.println();

		System.out.print("Código: ");
		int codigoMulta = tc.nextInt();

		Multa multa = multaDAO.consultarMultas(codigoMulta);
		if (multa == null) {
			System.err.println("Nenhuma multa encontrada");

		} else {
			System.out.println(multa);
		}
	}

	private static void listarMultasVeiculo() {
		System.out.println();
		System.out.println("Listar multas de um veículo");
		System.out.println();

		System.out.print("Placa: ");
		String placa = tc.next();

		long inicio = System.nanoTime();
		long inicio2 = System.nanoTime();

		Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);

		long fim = System.nanoTime();
		System.out.println("Tempo de carregamento do veículo: " + (fim - inicio));

		if (veiculo == null) {
			System.out.println();
			System.err.println("Nenhum veículo encontrado");

		} else {
			if (veiculo.getMultas().isEmpty()) {
				System.out.println();
				System.err.println("Nenhuma multa encontrada");

			} else {
				for (Multa m : veiculo.getMultas()) {
					System.out.println(m);
				}

				long fim2 = System.nanoTime();
				System.out.println("Tempo de listagem: " + (fim2 - inicio2));
			}
		}
	}

	private static void listarMultas() {
		System.out.println();
		System.out.println("Listar multas");
		System.out.println();

		java.util.List<Multa> multas = multaDAO.listaMultas();

		if (multas == null || multas.isEmpty()) {
			System.out.println();
			System.err.println("Nenhuma multa encontrada");

		} else {
			for (Multa m : multas) {
				System.out.println(m);
			}

		}
	}

}
