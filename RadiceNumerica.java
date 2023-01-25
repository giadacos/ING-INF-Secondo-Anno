package appello_12_07_2017;

public class RadiceNumerica {

	private String numero;
	private final int MAX = 9;
	
	public RadiceNumerica(int n) {
		String numero = String.valueOf(n);
		this.numero = numero;
	}
	
	public void risolvi() {
		int sommaCifre = calcolaSommaCifre();
		int risultato = calcolaRisultato(sommaCifre);
		scriviRisultato(risultato);
	}//risolvi
	private void scriviRisultato(int risultato) {
		System.out.println(risultato);
	}//scriviRisultato
	
	private int calcolaSommaCifre() {
		return calcolaSommaCifre(0, 0);
	}//calcolaSommaCifre
	private int calcolaSommaCifre(int somma, int i) {
		if(i == numero.length())
			return somma;
		somma += Character.getNumericValue(numero.charAt(i));
		return calcolaSommaCifre(somma, i+1);
	}//calcolaSommaCifre
	
	private int calcolaRisultato(int sommaCifre) {
		if(sommaCifre < MAX)
			return sommaCifre;
		if(sommaCifre == MAX)
			return 0;
		if(sommaCifre > MAX) {
			numero = String.valueOf(sommaCifre);
			int ret = calcolaSommaCifre();
			return ret;
		}//if
		return -1;
	}//calcolaRisultato
	
	
	public static void main(String...strings) {
		
		RadiceNumerica rn = new RadiceNumerica(873482);
		rn.risolvi();
		
	}//main
	
}//RadiceNumerica
