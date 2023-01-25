package appello_12_07_2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Determinante {
	
	private File file = null;
	private double[][] matrice;
	private int riga;
	private int colonna;
	private double valore;
	private int dimensione;
	private double determinante;
	
	public Determinante(File file) {
		this.file = file;
	}
	
	public void risolvi() throws IOException {
		dimensione = calcolaDimensione();
		matrice = scriviMatrice();
		determinante = calcolaDeterminante();
		scriviRisultato();
	}//risolvi
	private void scriviRisultato() {
		System.out.println("-Matrice-");
		for(int i=0; i<matrice.length; ++i) {
			for(int j=0; j<matrice[i].length; ++j) {
				System.out.print(matrice[i][j] + " ");
			}//for
			System.out.println();
		}//for
		System.out.println();
		System.out.println();
		System.out.println("Determinante = " + determinante);
	}//scriviRisultato
	
	private int calcolaDimensione() throws IOException {
		String path = file.getAbsolutePath();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		String linea;
		int dim = 0;
		for(;;) {
			linea = br.readLine();
			if(linea == null)
				break;
			else
				dim++;
		}//for
		return (int) Math.sqrt(dim);
	}//calcolaDimensione
	
	private double[][] scriviMatrice() throws IOException{
		double[][] ret = new double[dimensione][dimensione];
		return scriviMatrice(ret);
	}//scriviMatrice
	private double[][] scriviMatrice(double[][] ret) throws IOException{
		String path = file.getAbsolutePath();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		String linea;
		for(;;) {
			linea = br.readLine();
			if(linea == null)
				break;
			else {
				StringTokenizer st = new StringTokenizer(linea);
				scriviMatrice(ret, st);
			}//else
		}//for
		return ret;
	}//scriviMatrice
	private void scriviMatrice(double[][] ret, StringTokenizer st) {
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			riconoscitoreToken(token);
			ret[riga][colonna] = valore;
		}//while
	}//scriviMatrice
	private void riconoscitoreToken(String token) {
		if(token.charAt(0) == 'i')
			riga = Character.getNumericValue(token.charAt(2));
		if(token.charAt(0) == 'j')
			colonna = Character.getNumericValue(token.charAt(2));
		if(token.charAt(0) == 'v')
			valore = Character.getNumericValue(token.charAt(2));
	}//riconoscitoreToken
	
	private double calcolaDeterminante() {
		return Matrix.determinante(matrice);
	}//calcolaDeterminante
	
	
	public static void main(String...strings) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(">");
		String nome = sc.nextLine();
		File file = new File(nome);
		Determinante d = new Determinante(file);
		d.risolvi();
		
		sc.close();
		
	}//main
	
}//Determinante
