package program.src;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	
	private Integer errors = 0;
	private Integer count = 0;
	private String sorted;
	private String tip;
	
	private ArrayList<String> words = new ArrayList<>(); // um array de Palavras.
	private ArrayList<String> tips = new ArrayList<>(); // um array de Dicas.
	
	private ArrayList<Integer> index = new ArrayList<Integer>(); // index de letras corretas fornecidas pelo usuário.
	private ArrayList<String> errorList = new ArrayList<String>(); //lista de letras incorretas fornecidas pelo usuário.
	
	private ArrayList<String> sortedWord = new ArrayList<String>(); // palavra sorteada em formato de Array (letras).
	
	
	
	
	
	
	public String getSorted() {
		return sorted;
	}

	public String getTip() {
		return tip;
	}



	//lê as palavras/dicas do arquivo palavras.txt. Lança a exceção “arquivo de palavras inexistente”, caso o
	//arquivo não exista dentro do projeto.
	public JogoDaForca() throws Exception{

			// abrir arquivo palavras.txt da pasta interna "/data" para leitura
			InputStream stream = this.getClass().getResourceAsStream("data/palavras.txt");
			if (stream == null)
				throw new Exception("Arquivo de palavras inexistente");
			
			Scanner sc = new Scanner(stream);
			String linha;
			
			while (sc.hasNext()) {
				
				linha = sc.nextLine().toUpperCase();
				this.words.add(linha.split(";")[0]);
				this.tips.add(linha.split(";")[1]);
				
			}
			sc.close();
		}
	
	
	//realiza o sorteio de uma palavra/dica.
	public void iniciar() {
		Random rd = new Random();
		int index = rd.nextInt(words.size());
		this.sorted = words.get(index);
		this.tip = tips.get(index);
		
		for (int x = 0; x < this.sorted.length(); x++) {
			sortedWord.add(String.valueOf(this.sorted.charAt(x))); // transforma a palavra sorteada em uma lista de caracteres;
		}
	}
	
	//retorna a dica associada à palavra sorteada no momento.
	public String getDica() {
		return this.tip;
	}
	
	//retorna o tamanho da palavra sorteada no momento
	public int getTamanho() {
		return this.sorted.length();
	}
	
	public ArrayList<Integer> getOcorrencias(String letra) throws Exception{
		int count = 0;
		int i = 1;
		
		if (letra.length() != 1) {
			throw new InputMismatchException("A letra deve possuir apenas 1 Caracter");
		}
		
		for (String x : sortedWord) {
			if (letra.toUpperCase().equals(x)){
				if (index.contains(i)) { //verifica se a letra digitada ja foi acertada pelo usuário
					throw new InputMismatchException("Esse caracter ja foi acertado!");
				}
				else{ 
					index.add(i); // adiciona index a lista de indexes adivinhados.
					count ++; // qnt de acertos
				}
			}
			if (errorList.contains(letra.toUpperCase())){ //verifica se a letra digitada ja foi errada pelo usuário
				throw new InputMismatchException("Esse caracter ja foi errado!");
			}
			i++;
		}
		if (count == 0) {
			errorList.add(letra.toUpperCase());
			this.errors ++;
		}
		else {
			this.count += count;
		}
		return index;
	}
	
	
	//retorna true se o jogo terminou.
	public boolean terminou() {
		if (getNumeroPenalidade() == 6 || !getPalavraAdivinhada().contains("*")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//retorna a palavra contendo as letras adivinhadas até o momento e “*” nas letras ainda não adivinhadas
	public String getPalavraAdivinhada() {
		String response = "";
		int i = 1;
		
		for (String x : sortedWord) {
			if (index.contains(i)) {
				response += x;
			}
			else {
				response += "*";
			}
			i++;
		}
		return response;
	}
	
	//retorna o total de acertos
	public int getAcertos() {
		return this.count;
	}

	//retorna o numero (0 a 6) da penalidade atual
	public int getNumeroPenalidade() {
		return this.errors;
	}
	
	//retorna o nome da penalidade atual
	public String getNomePenalidade() {
		
		if (getNumeroPenalidade() == 1) {
			return "perdeu primeira perna";
		}
		if (getNumeroPenalidade() == 2) {
			return "perdeu segunda perna";
		}
		if (getNumeroPenalidade() == 3) {
			return "perdeu primeiro braço";
		}
		if (getNumeroPenalidade() == 4) {
			return "perdeu segundo braço";
		}
		if (getNumeroPenalidade() == 5) {
			return "perdeu tronco";
		}
		if (getNumeroPenalidade() == 6) {
			return "perdeu tronco";
		}
		else {
			return "sem penalidades";
		}
	}
	
	//retorna uma das 3 opções: “você venceu” ou “você foi enforcado” (se o jogo terminou) ou “jogo em
	//andamento” (se o jogo ainda não terminou).
	public String getResultado() {
		if (!terminou()) {
			return ("jogo em andamento");
		}
		else{
			if (getNumeroPenalidade() == 6) {
				return ("você foi enforcado");
			}
			else {
				return ("você venceu");
			}
		}
	}


}
