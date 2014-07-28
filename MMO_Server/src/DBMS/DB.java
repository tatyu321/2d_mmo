package DBMS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * Klasse DB - Datenbank
 * Kontrolliert Datenspeicherung und konsistenz
 * 
 * @author Simon Wetzel
 *
 */


public class DB {
	
	private File fl1_k;
	private File fl2_k;
	private File fl_akt_mir;
	
	private String pfad_mirror1 = "Objekte/mirror1/";
	private String pfad_mirror2 = "Objekte/mirror2/";
	
	private int akt_mirror;
	
	/**
	 * 
	 * Konstruktor
	 * 
	 * prueft benoetigte Verzeichnis und erstellt sie gegebenenfalls
	 * erzeugt benoetigte Daten oder laed vorhandene Informationen
	 * 
	 */
	
	protected DB(){		
		verzeichnis_pruefen("System");
		verzeichnis_pruefen("Objekte");
		verzeichnis_pruefen("Objekte/mirror1");
		verzeichnis_pruefen("Objekte/mirror2");
		
		fl1_k = new File( "System/db_mir1_k.sw" );
		fl2_k = new File( "System/db_mir2_k.sw" );
		fl_akt_mir = new File( "System/fl_akt_mir.mir" );
		
		try { 
			FileReader in = new FileReader(fl_akt_mir) ;
			akt_mirror=(int)in.read();
			in.close();
		}
		catch(Exception e){
			akt_mirror=1;
		}
	}
	
	private void verzeichnis_pruefen(String dirName){
		File f = new File(dirName);
		if (!(f.isDirectory())) {
			f.mkdir();
			System.out.println("Verzeichnis "+ dirName+" wurde erstellt");
		}
	}
	
	/**
	 * 
	 * Schreibt alle Objekte (2Dim-Array) und alle dazu passenden Klassen auf HardDisk
	 * 
	 * @param Objekte
	 * @param Klassen
	 */
	
	protected void schreibe (Object[][] Objekte, Class[] Klassen){
		ObjectOutputStream out_k;
		ObjectOutputStream out_d;
		try { 
			if (akt_mirror==1){
				out_k = new ObjectOutputStream( new FileOutputStream(fl1_k)) ;
			}else{
				out_k = new ObjectOutputStream( new FileOutputStream(fl2_k)) ;
			}
			out_k.writeObject(Klassen);
			out_k.close();
			
			for (int i=0; i<Klassen.length; i++){
				if (akt_mirror==1){
					out_d = new ObjectOutputStream( new FileOutputStream(new File( pfad_mirror1 + "Klasse"+i+"mir"+"1.sw" ))) ;
				}else{
					out_d = new ObjectOutputStream( new FileOutputStream(new File( pfad_mirror2 + "Klasse"+i+"mir"+"2.sw" ))) ;
				}
				for (int j=0; j<Objekte[i].length; j++) out_d.writeObject(Objekte[i][j]);
				out_d.close();
			}
			
			FileWriter out_m = new FileWriter(fl_akt_mir) ;
			out_m.write(akt_mirror);
			out_m.close();
			//System.out.println("Speichern Erfolgreich Mirror:"+akt_mirror);
			if (akt_mirror==1){akt_mirror=2;}else{akt_mirror=1;}
		}
		catch( IOException e ) {
			System.out.println( "Fehler beim Schreiben" ) ;
		}
	}
	
	/**
	 * 
	 * Liest alle Objekte von der HardDisk
	 * 
	 * @param Informationen zu allen gespeicherten Objekten (Klassen der Objekte)
	 * @return 2-Dim Array an Objekten
	 */
	
	protected Object[][] leseDatenDB(Class[] Klassen){
		Object[][] aus=new Object[Klassen.length][0];
		ObjectInputStream in;
		try {
			for (int i=0; i<Klassen.length; i++){
			
				if (akt_mirror==1){
					in = new ObjectInputStream( new FileInputStream( pfad_mirror1 + "Klasse"+i+"mir"+"1.sw" )) ;
				}else{
					in = new ObjectInputStream( new FileInputStream( pfad_mirror2 + "Klasse"+i+"mir"+"2.sw" )) ;
				}
				
				Object[] geleseneObjekte = new Object[0];
				Object[] geleseneObjekte_tausch;	
				try{
					while (true){
						Object gelesenesObjekt = (Klassen[i].cast(in.readObject()));
						geleseneObjekte_tausch=geleseneObjekte;
						geleseneObjekte= new Object[geleseneObjekte.length+1];
						for (int j=0; j<geleseneObjekte_tausch.length; j++){
							geleseneObjekte[j]=geleseneObjekte_tausch[j];
						}
						geleseneObjekte[geleseneObjekte_tausch.length]=gelesenesObjekt;
					}
				}
				catch(Exception e ) {		
					aus[i]=geleseneObjekte;
					in.close();
				}			
			}
			
			System.out.println("Laden Objekte Erfolgreich");
			return aus;
		}
		catch( IOException e ) {
			System.out.println( "DB Objekte unlesbar" ) ;
			return aus;
		}
		catch(Exception e ) {
			System.out.println( "DB Objekte Datei beschaedigt" ) ;
			return aus;
		}
	}
	
	/**
	 * 
	 * Liest Informationen zu allen gespeicherten Klassen
	 * 
	 * @return Array an Klasseninformationen
	 */
	
	protected Class[] leseKlassenDB(){
		Class[] aus=new Class[0];
		ObjectInputStream in;
		try {
			if (akt_mirror==1){
				in = new ObjectInputStream( new FileInputStream(fl1_k)) ;
			}else{
				in = new ObjectInputStream( new FileInputStream(fl2_k));
			}
			aus = (Class[]) in.readObject();
			in.close();
			
			System.out.println("Laden Klassen Erfolgreich");
			return aus;
		}
		catch( IOException e ) {
			System.out.println( "DB Klassen unlesbar" ) ;
			return aus;
		}
		catch(Exception e ) {
			System.out.println( "DB Klassen Datei beschaedigt" ) ;
			return aus;
		}
	}
}
