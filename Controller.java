package clientSystem;

import java.io.IOException;
import java.net.UnknownHostException;

public class Controller {


	public static void main(String []args) throws UnknownHostException, IOException, InterruptedException{
		int port =  2533; //�ndra till valfri port vid behov
		String ip = "192.168.1.103"; //�ndra till er ip
		Server server = new Server(port);

		Client client1 = new Client("Vedrana", ip, port);
		Client client2 = new Client("Adda", ip, port);
		Client client3 = new Client("Sandra", ip, port);
		Client client4 = new Client("Evelyn", ip, port);

		client1.connect();
		client2.connect();
		client3.connect();
		client4.connect();

		Thread.sleep(1000);
		client1.sendMessage("Bara");
		Thread.sleep(1000);
		client1.sendMessage("S�");
		Thread.sleep(1000);
		client1.sendMessage("Ni");
		Thread.sleep(1000);
		client1.sendMessage("Vet");
		client2.sendMessage("S� funkar det!");
		client2.disconnect();
		Thread.sleep(1000);
		client2.connect();
		Thread.sleep(1000);
		client2.sendMessage("JAG �R TILLBAKA!!!");
		Thread.sleep(1000);

		client2.disconnect();
		client1.disconnect();

		Thread.sleep(1000);


		client3.sendMessage("Tja! N�gon h�r?");
		Thread.sleep(500);
		client4.sendMessage("Jag �r h�r!?");

		server.disconnect();
		
		client1.connect();
		

	}

}
