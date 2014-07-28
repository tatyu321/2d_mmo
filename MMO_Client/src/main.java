import javax.swing.JOptionPane;

import events.Event_type;
import events.Login;
import events.Register;
import game_objects.Account;


public class main {
	

	private Client_socket Socket;
	private GUI gui;
	private Account aktiverAcount;

	public static void main(String[] args) {
		new main();
	}
	
	public main(){
		aktiverAcount=null;
		Socket = new Client_socket();
		
		gui = new GUI(this);
		
	}
	
	public void register(String name, String pw){
		aktiverAcount=Socket.register(new Register(Event_type.register,name,pw));
		if (aktiverAcount!=null){
			JOptionPane.showMessageDialog(null, "Account erstellt!", "Info", JOptionPane.OK_CANCEL_OPTION);
		}else{
			JOptionPane.showMessageDialog(null, "Account schon vorhanden!", "Info", JOptionPane.OK_CANCEL_OPTION);
		}
	}
	
	public void login(String name, String pw){
		aktiverAcount=Socket.login(new Login(Event_type.login,name,pw));
		if (aktiverAcount!=null){
			JOptionPane.showMessageDialog(null, "Eingeloggt", "Info", JOptionPane.OK_CANCEL_OPTION);
		}else{
			JOptionPane.showMessageDialog(null, "Nutzername oder Passwort falsch!", "Info", JOptionPane.OK_CANCEL_OPTION);
		}
	}

}
