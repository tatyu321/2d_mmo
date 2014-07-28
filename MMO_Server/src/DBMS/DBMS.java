package DBMS;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * Objektorientiertes DBMS
 * Benutzung unter Angabe des Autors erlaubt
 * 
 * @author Simon Wetzel
 * @version 1.1
 *
 */

public class DBMS {

	private Class[] Klassen;
	private Object[][] Objekte;
	private DB datenbank;
	
	Timer timer = new Timer();
	
	/**
	 * 
	 * Erzeugt das DBMS, initiiert den Background
	 * initiiert ein automatisches speichern der Daten alle 0,5 Sek
	 * 
	 */
	
	public DBMS (){	
		datenbank = new DB();
		
		Klassen=datenbank.leseKlassenDB();
		Objekte=datenbank.leseDatenDB(Klassen);
		
		timer.schedule(new TimerTask(){				//automatisiertes speichern alle 0,5 sek
				public void run() {
				datenbank.schreibe(Objekte, Klassen);
			  }
		},500,500);
		
	}
	
	/**
	 * 
	 * Speichert ein beliebiges Serializable Objekt in der Datenbank
	 * Unterklassen koennen ebenfalls gespeichert werden, falls sie Serializable sind
	 * 
	 * @param NeuerEintrag (belibiges Serializable Objekt)
	 */
	
	public void speichern(Serializable NeuerEintrag){
		int klassen_index = Klasse_pruefen(NeuerEintrag);
		Objekt_array_pruefen(klassen_index);
		Objekt_eintragen(klassen_index, NeuerEintrag);
		datenbank.schreibe(Objekte, Klassen);
	}
	
	/**
	 * 
	 * loescht Objekt der angegebnen Klasse an der Index-nummer
	 * Index-nummer entspricht Objektposition im Array einer Abfrage
	 * 
	 * @param klasse
	 * @param index
	 */
	
	public void loeschen(Class klasse, int index){
		int id = bestandsdaten_durchsuchen(klasse);
		if (id!=-1){
			if ((index<Objekte[id].length)&&(index>=0)){
				Object[] neuesFeld = new Object[Objekte[id].length-1];
				for (int i=0;i<index;i++){
					neuesFeld[i]=Objekte[id][i];
				}
				for (int i=index+1;i<Objekte[id].length;i++){
					neuesFeld[i-1]=Objekte[id][i];
				}
				Objekte[id]=neuesFeld;
				if (Objekte[id].length==0){
					Object[][] neueFelder = new Object[Objekte.length-1][];
					Class[] neueKlassen = new Class[Objekte.length-1];
					for (int i=0;i<id;i++){
						neueFelder[i]=Objekte[i];
						neueKlassen[i]=Klassen[i];
					}
					for (int i=id+1;i<Objekte.length;i++){
						neueFelder[i-1]=Objekte[i];
						neueKlassen[i-1]=Klassen[i];
					}
					Objekte=neueFelder;
					Klassen=neueKlassen;
				}
			datenbank.schreibe(Objekte, Klassen);
			}		
		}
		else{System.out.println("Klasse nicht vorhanden");}
	}
	
	
	/**
	 * 
	 * Gibt ein Objekt-Array mit allen Objekten des Parameters klasse
	 * 
	 * @param klasse
	 * @return Objekt-Array der Klasse param klasse
	 */
	
	public <T> T[] abfrage(Class <T> klasse){
		int id = bestandsdaten_durchsuchen(klasse);
		if (id==-1)return null;
		try{
			T[] rueck = (T[]) Array.newInstance(klasse, Objekte[id].length);
			for (int i=0; i < Objekte[id].length; i++){
				rueck[i] = (T) Objekte[id][i];
			}
			return rueck;
		}
		catch(ClassCastException e){
			return null;
		}
	}
	
	private int Klasse_pruefen(Object NeuerEintrag){
		Class Klassen_eintrag=NeuerEintrag.getClass();
		int klassen_index = bestandsdaten_abgleichen(Klassen_eintrag);
		return klassen_index;
	}
	
	private int bestandsdaten_durchsuchen(Class Klassen_eintrag){
		int vorhanden=-1;
		for (int i=0; i < Klassen.length; i++){
			if (Klassen[i]==Klassen_eintrag){vorhanden=i;}
		}
		return vorhanden;
	}
	
	private int bestandsdaten_abgleichen(Class Klassen_eintrag){
		
		int vorhanden=bestandsdaten_durchsuchen(Klassen_eintrag);
		
		if (vorhanden<0){
			int neuer_index =Klassen.length;
			Class [] Klassen_tausch = Klassen;
			Klassen = new Class[(neuer_index+1)];
			for (int i=0; i < neuer_index; i++){
				Klassen[i]=Klassen_tausch[i];
			}
			Klassen[neuer_index]=Klassen_eintrag;
			return neuer_index;
		}else{
			return vorhanden;
		}
	}

	private void Objekt_array_pruefen(int klassen_referenz){
		if (Objekte!=null){
			int klassen_anz=Objekte.length;
			if (Objekte.length<=klassen_referenz){
				Object[][] Objekte_ueber=Objekte;
				Objekte=new Object[(klassen_anz+1)][];
				for (int i=0; i < klassen_anz; i++){
					Objekte[i]=Objekte_ueber[i];
				}
			}
		}
	}

	private void Objekt_eintragen(int klassen_referenz, Object NeuerEintrag){
		Object [] uebergangsarray = Objekte[klassen_referenz];
		if (uebergangsarray==null)
			{
				Objekte[klassen_referenz]=new Object[1];
				Objekte[klassen_referenz][0]=NeuerEintrag;
			}
			else {
				Objekte[klassen_referenz]=new Object[uebergangsarray.length+1];
				for (int i=0; i < uebergangsarray.length ; i++){
					Objekte[klassen_referenz][i]=uebergangsarray[i];
				}
				Objekte[klassen_referenz][uebergangsarray.length]=NeuerEintrag;
			}
	}
}

