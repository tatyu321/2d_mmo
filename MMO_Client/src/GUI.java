import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
	
		private main dieSteuerung;		
		JPanel anmelden = new JPanel();
	
	public GUI(main Strg){
		dieSteuerung = Strg;
		init();
	}

	private void init(){
		setSize(715,700);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
		anmeldePane();
		this.setVisible(true);
	}
	
	private void anmeldePane(){
		
			anmelden.setBackground(Color.GRAY);
			anmelden.setSize(300, 250);
			anmelden.setLocation(25, 375);
			anmelden.setBorder(BorderFactory.createLineBorder(Color.black));
			anmelden.setLayout(null);
			
			JLabel anm_l = new JLabel("Anmelden");
			anm_l.setLocation(25, 10);
			anm_l.setSize(250, 30);
			anmelden.add(anm_l);
	    	
			final JTextField name_a = new JTextField("Nutzername");
			name_a.setBounds(25,45,250,30);
			anmelden.add(name_a);
			
			final JTextField passwort_a = new JTextField("Passwort");
			passwort_a.setBounds(25,95,250,30);
			anmelden.add(passwort_a);
			
			JButton einloggen_b = new JButton("Einloggen");
			anmelden.add(einloggen_b);
			einloggen_b.setSize(250, 30);
			einloggen_b.setLocation(25, 145);
			einloggen_b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dieSteuerung.login(name_a.getText(), passwort_a.getText());	
				}
			});
			
			anmelden.setVisible(true);
			this.add(anmelden);
			
			//Registrieren
		
			JPanel registrieren = new JPanel();
			
			registrieren.setBackground(Color.GRAY);
			registrieren.setSize(300, 250);
			registrieren.setLocation(375, 375);
			registrieren.setBorder(BorderFactory.createLineBorder(Color.black));
			registrieren.setLayout(null);
			
			JLabel reg_l = new JLabel("Registrieren");
			reg_l.setLocation(25, 10);
			reg_l.setSize(250, 30);
			registrieren.add(reg_l);
	    	
			final JTextField name_r = new JTextField("Nutzername");
			name_r.setBounds(25,45,250,30);
			registrieren.add(name_r);
			
			final JTextField passwort_r = new JTextField("Passwort");
			passwort_r.setBounds(25,95,250,30);
			registrieren.add(passwort_r);
			
			JButton registrieren_b = new JButton("Registrieren");
			registrieren.add(registrieren_b);
			registrieren_b.setSize(250, 30);
			registrieren_b.setLocation(25, 145);
			registrieren_b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dieSteuerung.register(name_r.getText(), passwort_r.getText());
				}
			});
			
			registrieren.setVisible(true);
			this.add(registrieren);
			
			//hintergrund
			
			JPanel background = new JPanel();
			background.setLayout(null);
			background.setBackground(Color.DARK_GRAY);
			background.setLocation(0, 0);
			background.setSize(715, 700);
			add(background);
	}

}