package clientSystem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class ClientGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField tf;
	private JTextField tfip, tfPort;
	private JButton login, logout;
	private JTextArea ta;

	private JTextArea ta2;

	private boolean connected;
	private Client client;


	ClientGUI(String host, int port) {

		super("Gruppuppgift");

		JPanel northPanel = new JPanel(new GridLayout(3,1));
		JPanel ipAndPort = new JPanel(new GridLayout(1,5, 1, 3));
		tfip = new JTextField(host);
		tfPort = new JTextField("" + port);
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);

		ipAndPort.add(new JLabel("ip:  "));
		ipAndPort.add(tfip);
		ipAndPort.add(new JLabel("Port:  "));
		ipAndPort.add(tfPort);
		ipAndPort.add(new JLabel(""));
		northPanel.add(ipAndPort);

		label = new JLabel("Ange användarnamn", SwingConstants.CENTER);
		northPanel.add(label);
		tf = new JTextField(" ");
		tf.setBackground(Color.WHITE);
		northPanel.add(tf);
		add(northPanel, BorderLayout.NORTH);

		ta = new JTextArea("Välkommen\n", 80, 80);
		JPanel centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.add(new JScrollPane(ta));
		ta.setEditable(false);
		add(centerPanel, BorderLayout.CENTER);

		login = new JButton("Logga in");
		login.addActionListener(this);
		logout = new JButton("Logga ut");
		logout.addActionListener(this);
		logout.setEnabled(false);		

		JPanel southPanel = new JPanel();
		southPanel.add(login);
		southPanel.add(logout);

		add(southPanel, BorderLayout.SOUTH);


		JPanel eastPanel = new JPanel(new GridLayout(1,1));
		add(eastPanel, BorderLayout.EAST);
		ta2 = new JTextArea("Användare online", 80, 80);
		eastPanel.setPreferredSize(new Dimension(150,600));
		eastPanel.add(new JScrollPane(ta2));
		ta2.setEditable(false);
		add(eastPanel, BorderLayout.EAST);


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);
		tf.requestFocus();
	}

	void append(String str) {
		ta.append(str);
		ta.setCaretPosition(ta.getText().length() - 1);
	}

	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);

		label.setText("Ange ditt användarnamn nedan	");
		tf.setText("Anonym");
		tfPort.setText("") ;

		tfip.setEditable(false);
		tfPort.setEditable(false);
		tf.removeActionListener(this);
		connected = false;
	}


	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if(o == logout) {
			return;
		}
		if(connected) {

			tf.setText("");
			return;
		}

		if(o == login) {
			String username = tf.getText().trim();
			if(username.length() == 0)
				return;
			String ip = tfip.getText().trim();
			if(ip.length() == 0)
				return;
			String portNumber = tfPort.getText().trim();
			if(portNumber.length() == 0)
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			}
			catch(Exception en) {
				return;
			}
			try {
				client = new Client(username, ip, port);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			tf.setText("");
			label.setText("Skriv dtt meddelande nedan");
			connected = true;
			login.setEnabled(false);
			logout.setEnabled(true);
			tfip.setEditable(false);
			tfPort.setEditable(false);
			tf.addActionListener(this);
		}

	}

	public static void main(String[] args) {
		new ClientGUI(" ", 0);
	}
}
