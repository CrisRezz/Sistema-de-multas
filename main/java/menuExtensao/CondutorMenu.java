package menuExtensao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.entity.Condutor;

public class CondutorMenu {

	private static CondutorDAO condutorDAO = new CondutorDAO();

	public static Scanner tc = new Scanner(System.in);

	public static void menu() {
		System.out.println();
		System.out.println("----------------------------Menu condutor-----------------------------------");
		System.out.println("(1) Cadastrar condutor.");
		System.out.println("(2) Remover condutor.");
		System.out.println("(3) Consultar condutor.");
		System.out.println("(4) Listar condutores.");
		System.out.println("(5) Voltar para menu principal.");
		System.out.println();
		System.out.print("Escolha uma opção: ");

		while (true) {
			int opcao = tc.nextInt();

			switch (opcao) {
			case 1:
				cadastrarCondutor();
				break;

			case 2:
				removerCondutor();
				break;

			case 3:
				consultarCondutor();
				break;

			case 4:
				listarCondutores();
				break;

			case 5:
				return;

			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}

	public static Condutor cadastrar(Scanner tc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Condutor condutor = new Condutor();

		System.out.print("Número da CNH: ");
		int nroCnh = tc.nextInt();
		System.out.print("Data de emissão: ");
		LocalDate dataEmissao = LocalDate.parse(tc.next(), formatter);
		tc.nextLine();
		System.out.print("Orgão emissor: ");
		String orgaoEmissor = tc.nextLine();

		condutor.setNroCnh(nroCnh);
		condutor.setDataEmissao(dataEmissao);
		condutor.setOrgaoEmissor(orgaoEmissor);
		condutor.setPontuacao(0);

		return condutor;
	}

	private static void cadastrarCondutor() {
		System.out.println();
		System.out.println("--------Complete os dados para o cadastro:-----------");
		Condutor condutor = CondutorMenu.cadastrar(tc);
		CondutorDAO condutorDAO = new CondutorDAO();
		condutorDAO.criarCondutor(condutor);
	}

	private static void removerCondutor() {
		System.out.println();
		System.out.println("=== Para remover o condutor, insira os dados abaixo =====");
		System.out.println();
		System.out.print("Número da CNH: ");
		int cnh = tc.nextInt();
		CondutorDAO.removeCondutor(cnh);
	}

	private static void consultarCondutor() {
		System.out.println();
		System.out.println("Consultar condutor");
		System.out.println();

		System.out.print("Número da CNH: ");
		int cnh = tc.nextInt();

		Condutor condutor = CondutorDAO.consultarCondutor(cnh);
		if (condutor == null) {
			System.out.println();
			System.err.println("Nenhum condutor encontrado!");

		} else {
			System.out.println("Condutor encontrado:");
			System.out.println(condutor);
		}
	}

	private static void listarCondutores() {
		System.out.println("Lista de condutores:");
		System.out.println();
		java.util.List<Condutor> condutores = CondutorDAO.listaCondutor();
		if (condutores == null || condutores.isEmpty()) {
			System.out.println("Nenhum condutor encontrado!");

		} else {
			for (Condutor c : condutores) {
				System.out.println(c);
			}

		}
	}
}
