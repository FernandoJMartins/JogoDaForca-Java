package program.src;

/*
 * IFPB - TSI - POO - PROJETO1
 *  
 * Aplica��o console para testar a classe JogoDaForca
 * 
 */



import java.util.ArrayList;
import java.util.Scanner;

public class AplicacaoConsole {
	private JogoDaForca jogo;
	private Scanner teclado;
	private String letraDigitada;		//letra lida do teclado
	private ArrayList<Integer> ocorrencias; // posicoes adivinhadas

	public AplicacaoConsole() {
		try {
			jogo = new JogoDaForca();
			jogo.iniciar();

			System.out.println("tamanho da palavra = " + jogo.getTamanho());
			System.out.println("dica: " + jogo.getDica());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		AplicacaoConsole app = new AplicacaoConsole();
	}
}
