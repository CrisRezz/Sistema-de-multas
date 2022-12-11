package menuExtensao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import br.com.mildevs.entity.Condutor;

public class CondutorMenu {
	
	
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
}
	
	
	

