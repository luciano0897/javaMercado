package main;

import modelo.Produto;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Mercado {
	
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Produto> produtos;
	private static Map<Produto, Integer> carrinho;
	
	public static void main(String[] args) {
		produtos = new ArrayList<>();
		carrinho = new HashMap<>();
		menu();
	}
	private static void menu() {
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("-------------Seja Bem Vindo ao Mercado do Zé------------------");
		System.out.println("--------------------------------------------------------------");
		System.out.println("********Selecione uma operação que deseja realizar*************");
		System.out.println("--------------------------------------------------------------");
		System.out.println("|     Opção 1 - Cadastrar      |");
		System.out.println("|     Opção 2 - Listar         |");
		System.out.println("|     Opção 3 - Comprar        |");
		System.out.println("|     Opção 4 - Carrinho       |");
		System.out.println("|     Opção 5 - Sair           |");
		
		int option = input.nextInt();
		
		switch (option) {
			case 1:
				cadastrarProdutos();
				break;
			case 2:
				listarProdutos();
				break;
			case 3:
				comprarProdutos();
				break;
			case 4:
				verCarrinho();
				break;
			case 5:
				System.out.println("Obrigado pela prefência!");
				System.exit(0);
				default:
					System.out.println("Opção Invalida!");
					menu();
					break;
		}
	
	}
	private static void cadastrarProdutos() {
		System.out.println("nome do produto: ");
		String nome = input.next();
		
		System.out.println("Preço do produto:");
		Double preco = input.nextDouble();
		
		
		Produto produto = new Produto(nome, preco);
		produtos.add(produto);
		
		System.out.println(produto.getNome() + " cadastrado com sucesso!");
		menu();
		
	}
	
	private static void listarProdutos() {
		if (produtos.size() > 0) {
			System.out.println(" Listar de produtos!  \n");
			
			for (Produto p: produtos) {
				System.out.println(p);
			}
		}else {
			System.out.println("Nenhum produto cadastrado!");
		}
		menu();
	}
	
	private static void comprarProdutos() {
		if (produtos.size() > 0) {
			System.out.println(" Código do produto:  \n");
			
			System.out.println("-----------------Produtos Disponiveis-----------------");
			for (Produto p: produtos) {
				System.out.println(p + "\n");
			}
			int id = Integer.parseInt(input.next());
			boolean isPresent = false;
			
			for (Produto p: produtos) {
				if (p.getId() == id) {
					int qtd = 0;
					try {
						qtd = carrinho.get(p);
						// checa se o produto já está no carrinho, incrementa quantidade.
						carrinho.put(p, qtd +1);
					}catch(NullPointerException e) {
						// se o produto for o primeiro no carrinho
					
						carrinho.put(p, 1);
					}
					System.out.println(p.getNome()+ "adicionado ao carrinho.");
					isPresent = true;
					
					if (isPresent) {
						System.out.println("Deseja adionar outro produtos ao carrinho? ");
						System.out.println("Digite 1 para sim, ou 0 para finalizar a compra. \n ");
						
						int option = Integer.parseInt(input.next());
						
						if (option == 1) {
							comprarProdutos();
						}else {
							finalizarCompra();
						}
					}
				 }else {
					 System.out.println("Produto não encontrado");
					 menu();
				 }
			}
		}else {
			System.out.println(" Não existem produtos cadastrados!");
			menu();
		}
	}
	private static void verCarrinho() {
		System.out.println("-----Produtos no seu carrinho!--------");
		if (carrinho.size() > 0) {
			for (Produto p : carrinho.keySet()) {
				System.out.println("Produto: " + p + "\nQuantidade: " + carrinho.get(p));
			}
		}else {
			System.out.println("Carrinho vazio!");
		}
		menu();
	}
	private static void finalizarCompra() {
		Double valorDaCompra = 0.0;
		System.out.println("Seus produtos!");
		
		for (Produto p : carrinho.keySet()) {
			int qtd = carrinho.get(p);
			valorDaCompra += p.getPreco() * qtd;
			System.out.println(p);
			System.out.println("Quantidade: " +qtd);
		}
		System.out.println("O valor da sua comprar é " + Utils.doubleToString(valorDaCompra));
		carrinho.clear();
		
		System.out.println("Volte Sempre!");
		menu();
		
	}
	
}
