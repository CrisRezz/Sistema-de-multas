package menuExtensao;

import java.util.Scanner;

import br.com.mildevs.entity.Multa;

public class MultasMenu {

		
		public static void menu() {
			System.out.println();
			System.out.println("----------------------------Menu Multas-----------------------------------");
			System.out.println("(1) Cadastrar multa.");
			System.out.println("(2) Remover multa.");
			System.out.println("(3) Consultar multa(s).");
			System.out.println("(4) Listar multas.");
			System.out.println("(5) Voltar para menu principal.");
			System.out.println();
			System.out.print("Escolha uma opção: ");
		 
			}
		
		
		public static Multa cadastrar(Scanner tc) {
			
			Multa multa = new Multa();

			System.out.print("Valor: ");
			double valor = tc.nextDouble();
			
			System.out.print("Pontuação: ");
			int pontuacao = tc.nextInt();
			
			multa.setValor(valor);
			multa.setPontuacao(pontuacao);
			
			return multa;
		}
	}
	
	
	

