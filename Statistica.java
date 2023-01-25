package appello_15_06_2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Statistica {

	private File file = null;
	private List<Occorrenza> l = new ArrayList<>();
	
	public Statistica(String file) {
		this.file = new File(file);
	}
	
	public void risolvi() throws IOException {
		calcolaStatistica();
		scrivi();
	}//risolvi
	private void scrivi() {
		for(int i=0; i<l.size(); ++i)
			System.out.println(l.get(i));
	}//scrivi
	
	private void calcolaStatistica() throws IOException {
		String path = file.getAbsolutePath();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringTokenizer st;
		for(;;) {
			String linea = br.readLine();
			if(linea == null)
				break;
			else {
				st = new StringTokenizer(linea);
				calcolaStatistica(st);
			}//else
		}//for
	}//calcolaStatistica
	private void calcolaStatistica(StringTokenizer st) {
		String pre;
		String succ;
		while(st.hasMoreTokens()) {
			pre = st.nextToken();
			if(st.hasMoreTokens()) {
				succ = st.nextToken();
				nuovaOccorrenza(pre, succ);
				pre = succ;
				succ = "";
			}//if
			else
				break;
		}//while
	}//calcolaStatistica
	private void nuovaOccorrenza(String pre, String succ) {
		int p = Integer.parseInt(pre);
		int s = Integer.parseInt(succ);
		Occorrenza occ = new Occorrenza(p, s);
		if(!l.contains(occ)) {
			l.add(occ);
			Collections.sort(l);
		}//if
		else {
			for(int i=0; i<l.size(); ++i) {
				if(l.get(i).equals(occ))
					l.get(i).aggiungiOcc();
			}//for
		}//else
	}//nuovaOccorrenza
	
	
	private class Occorrenza implements Comparable<Occorrenza>{
		private int pre;
		private int succ;
		private int occ;
		
		public Occorrenza (int numero, int successivo) {
			this.pre = numero;
			this.succ = successivo;
			this.occ = 1;
		}
		
		public void aggiungiOcc() {
			occ++;
		}//aggiungiOcc
		
		@Override
		public String toString() {
			if(occ == 1)
				return "" + pre + " seguito da " + succ + "  -- " + occ + " volta";
			else
				return "" + pre + " seguito da " + succ + " -- " + occ + " volte";
		}//toString 
		
		@Override
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if(!(o instanceof Occorrenza))
				return false;
			Occorrenza occ = (Occorrenza)o;
			return this.pre == occ.pre && this.succ == occ.succ;
		}//equals

		@Override
		public int compareTo(Occorrenza o) {
			if(this.pre > o.pre)
				return 1;
			if(this.pre < o.pre)
				return -1;
			return 0;
		}//compareTo
	}//Occorrenza
	
	
	public static void main(String...strings) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.print(">");
		
		String file = sc.nextLine();
		Statistica s = new Statistica(file);
		s.risolvi();
		
		sc.close();
		
	}//main
	
}//Statistica