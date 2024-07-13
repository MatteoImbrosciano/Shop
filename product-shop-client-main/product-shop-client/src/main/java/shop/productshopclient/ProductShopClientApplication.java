package shop.productshopclient;

import shop.productshopclient.breaker.CircuitBreaker;
import shop.productshopclient.data.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class ProductShopClientApplication {

	private static final Scanner scanner = new Scanner(System.in);
	private static final CircuitBreaker c = new CircuitBreaker();

	public static void main(String[] args) {
		SpringApplication.run(ProductShopClientApplication.class, args);

		String nome;


		System.out.println("Inserisci il tuo nome");
		nome = scanner.next();
		System.out.println(nome.charAt(nome.length()-1));

		Client client = new Client(nome, c);

		char c;

		do{
			System.out.println("\nVisualizza carrello: [a] \nVisualizza tutti i prodotti: [b] \nAggiungi al carrello un prodotto: [c] \nCerca [d] \nTermina [z]");
			c = scanner.next().charAt(0);

			switch (c){
				case 'a':
					client.visualizzaCarrello();
					break;
				case 'b':
					client.getAllProducts();
					break;
				case 'c':
					System.out.println("Scrivi il nome del prodotto da aggiungere al carrello");
					String disp = scanner.next();
					client.aggiungiAlCarrello(disp);
					break;

				case 'd':
					System.out.println("Scrivi: ");
					String cerca = scanner.next();
					client.cercaProdotti(cerca);
					break;
				
				case 'z':
					break;

				default: break;
			}


		}while (c != 'z');

		exit(0);

	}

}
