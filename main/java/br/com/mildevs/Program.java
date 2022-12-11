package br.com.mildevs;

import java.util.List;
import java.util.Scanner;

import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menuExtensao.CondutorMenu;
import menuExtensao.MultasMenu;
import menuExtensao.VeiculoMenu;





public class Program {
	
    public static Scanner tc = new Scanner(System.in);
	private static CondutorDAO condutorDAO = new CondutorDAO();
	private static VeiculoDAO veiculoDAO = new VeiculoDAO();
	private static MultaDAO multaDAO = new MultaDAO();
	
	
public static void main(String[] args) {


System.out.println("Escolha uma das opções abaixo:\n "
		+ "(1) Informações/alterações sobre Condutores.\n"
		+ "(2) Informações/aletrações sobre Veiculos.\n"
		+ "(3) Informações/alterações sobre multas.\n"
		+ "(4) Sair");
int opcao = tc.nextInt();
	
	switch (opcao) {
	case 1:
		Condutor();
		break;
		
	case 2:
		veiculo();
		break;
		
	case 3:
		multas();
		break;
		
	case 4:
		
		break;
		
	case 5:
		
		return;
	default:
		System.out.println("Opção inválida");
		break;
	}
}



private static void multas() {
	
	while (true) {
		MultasMenu.menu();
		int opcao3 = tc.nextInt();
		
		switch (opcao3) {
		case 1:
			cadastrarMulta();
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
			
			return;
			
		default:
			System.out.println("Opção inválida");
			break;
		}
	}
}

private static void cadastrarMulta() {
	System.out.println();
	System.out.println("Cadastrar multa");
	System.out.println();
	
	System.out.print("Placa do veículo: ");
	int placa = tc.nextInt();
	
	Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);
	if (veiculo == null) {
		System.out.println();
		System.err.println("Este veículo não está cadastrado");
		
	} else {
		Multa multa = MultasMenu.cadastrar(tc);
		Condutor condutor = condutorDAO.consultarCondutor(veiculo.getCondutor().getNroCnh());
		
		multaDAO.criarMulta( multa , veiculo, condutor);
		
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
	int placa = tc.nextInt();
	
	long inicio = System.nanoTime();
	long inicio2 = System.nanoTime();
	
	Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);
	
	long fim = System.nanoTime();
	System.out.println("Tempo de carregamento do veículo: " + (fim-inicio));
	
	if (veiculo == null) {
		System.out.println();
		System.err.println("Nenhum veículo encontrado");
		
	} else {
		if (veiculo.getMultas().isEmpty()){
			System.out.println();
			System.err.println("Nenhuma multa encontrada");
			
		} else {	
			for (Multa m : veiculo.getMultas()) {
				System.out.println(m);
			}
			
			long fim2 = System.nanoTime();
			System.out.println("Tempo de listagem: " + (fim2-inicio2));
		}
	}
}

private static void listarMultas() {
	System.out.println();
	System.out.println("Listar multas");
	System.out.println();
	
	List<Multa> multas = multaDAO.listaMultas();
	
	if (multas == null || multas.isEmpty()) {
		System.out.println();
		System.err.println("Nenhuma multa encontrada");
		
	} else {
		for (Multa m : multas) {
			System.out.println(m);
		}
		
	}
}

	
	













private static void veiculo() {
	
while (true) {
	VeiculoMenu.menu();
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
		venderVeiculo();
		break;
		
	case 6:
		return;
		
	default:
		System.err.println("Opção inválida");
		break;
	}
}
}


private static void cadastrarVeiculo() {
System.out.println();
System.out.println("Cadastrar veículo");
System.out.println();

System.out.print("CNH do condutor: ");
int cnh = tc.nextInt();

Condutor condutor = condutorDAO.consultarCondutor(cnh);
if (condutor == null) {
	System.out.println();
	System.err.println("Este condutor não está cadastrado");

} else if (condutor.getVeiculo()!=null) {
	System.out.println();
	System.err.println("Este condutor já possui um veículo cadastrado");
	
} else {
	Veiculo veiculo = VeiculoMenu.cadastrar(tc);
	
	veiculoDAO.criarVeiculo(veiculo, condutor);
	
}
}

private static void removerVeiculo() {
System.out.println();
System.out.println("Remover veículo");
System.out.println();

System.out.print("Placa: ");
int placa = tc.nextInt();

veiculoDAO.removeVeiculo(placa);
}

private static void consultarVeiculo() {
System.out.println();
System.out.println("Consultar veículo");
System.out.println();

System.out.print("Placa: ");
int placa = tc.nextInt();

Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);
if (veiculo == null) {
	System.out.println();
	System.err.println("Nenhum veículo encontrado");
	
} else {
	System.out.println(veiculo);
}
}

private static void listarVeiculos() {
System.out.println();
System.out.println("Listar veículos");
System.out.println();

List<Veiculo> veiculos = veiculoDAO.listaveiculos();

if (veiculos == null || veiculos.isEmpty()) {
	System.out.println();
	System.err.println("Nenhum veículo encontrado");
	
} else {
	for (Veiculo v : veiculos) {
		System.out.println(v);
	}
	
}
}

private static void venderVeiculo() {
System.out.println();
System.out.println("---Vender veículo-------");
System.out.println();

System.out.print("CNH do vendedor: ");
int origem = tc.nextInt();

System.out.print("CNH do comprador: ");
int destino = tc.nextInt();

Condutor vendedor = condutorDAO.consultarCondutor(origem);
Condutor comprador = condutorDAO.consultarCondutor(destino);

if (vendedor == null || comprador == null) {
	System.err.println("Condutor não encontrado");
	
} else if (vendedor == comprador) {
	System.err.println("Vendedor e comprador não podem ser a mesma pessoa");
	
} else {
	System.out.print("Placa do carro: ");
	int placa = tc.nextInt();
	Veiculo veiculo = veiculoDAO.consultarVeiculos(placa);
	
	if (veiculo == null) {
		System.out.println();
		System.err.println("Nenhum veículo encontrado");
		
	} else  {
		int cnhDono = veiculo.getCondutor().getNroCnh();

		if (cnhDono == vendedor.getNroCnh()) {
			System.out.println();
			System.err.println("Este veículo não pertence ao vendedor");
			
		} else if (!(veiculo.getMultas().isEmpty())) {
			System.out.println();
			System.err.println("Veículo não pode ser vendido pois tem multas não resolvidas");
			
		} else {
			veiculoDAO.venderVeiculo(vendedor, comprador, veiculo);
			
		}
	}
}
}




private static void Condutor() {
	
	while (true) {
	CondutorMenu.menu();
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
			System.err.println("Opção inválida");
			break;
		}
	}
}
	
	

private static void cadastrarCondutor() {
	System.out.println();
	System.out.println("Cadastrar condutor");
	System.out.println();
	
	Condutor condutor = CondutorMenu.cadastrar(tc);
	condutorDAO.criarCondutor(condutor);
}

private static void removerCondutor() {
	System.out.println();
	System.out.println("Remover condutor");
	System.out.println();
	
	System.out.print("Número da CNH: ");
	int cnh = tc.nextInt();
	
	condutorDAO.removeCondutor(cnh);
}

private static void consultarCondutor() {
	System.out.println();
	System.out.println("Consultar condutor");
	System.out.println();
	
	System.out.print("Número da CNH: ");
	int cnh = tc.nextInt();
	
	Condutor condutor = condutorDAO.consultarCondutor(cnh);
	if (condutor == null) {
		System.out.println();
		System.err.println("Nenhum condutor encontrado");
		
	} else {
		System.out.println(condutor);
	}
}

private static void listarCondutores() {
	System.out.println("Listar condutores");
	System.out.println();
	
	List<Condutor> condutores = condutorDAO.listaCondutor();
	if (condutores == null || condutores.isEmpty()) {
		System.out.println("Nenhum condutor encontrado");
		
	} else {
		for (Condutor c : condutores) {
			System.out.println(c);
		}
		
	}
}
	
}

	

	

