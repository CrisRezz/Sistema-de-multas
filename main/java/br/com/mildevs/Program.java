package br.com.mildevs;
import java.util.Scanner;

import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menuExtensao.CondutorMenu;
import menuExtensao.MultasMenu;
import menuExtensao.VeiculoMenu;

public class Program {

	public static Scanner tc = new Scanner(System.in);

	public static void main(String[] args) {

		CondutorDAO condutorDAO = new CondutorDAO();
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		MultaDAO multaDAO  = new MultaDAO();

		System.out.println("-------------------Escolha uma das opções abaixo:\n " + "(1) Informações/alterações sobre Condutores.\n"
				+ "(2) Informações/aletrações sobre Veiculos.\n" + "(3) Informações/alterações sobre multas.\n"
				+ "(4) Sair");

		int opcao = tc.nextInt();

		switch (opcao) {
		case 1:
			CondutorMenu.menu();
			
			break;

		case 2:
			VeiculoMenu.menu();
			break;

		case 3:
			MultasMenu.menu();
			break;

		case 4:
              System.out.println("Programa finalizado :)");
			break;

		case 5:
			return;
		default:
			System.out.println("Opção inválida");
			break;
		}
	}
}
