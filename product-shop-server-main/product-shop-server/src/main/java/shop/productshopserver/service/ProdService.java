package shop.productshopserver.service;

import shop.productshopserver.data.Acquisto;
import shop.productshopserver.data.Product;
import shop.productshopserver.data.ProductTable;
import shop.productshopserver.http.HttpSender;
import shop.productshopserver.session.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;

@Service
public class ProdService {

    private final ProductTable productTable = new ProductTable();

    private final HttpSender sender = new HttpSender();

    public List<Product> getAllProduct(){
        return productTable.getAllProduct();
    }

    //carrello per un utente specifico
    public List<Acquisto> getCarrello(String id){
        return getSession(id).acquisti();
    }

    public String aggiungiCarrello(String id, String prod){
        Status status = getSession(id); // Ottiene la sessione dell'utente
        Product product = productTable.getProduct(prod); // Ottiene i dettagli del prodotto
        if(product != null){
            // Aggiunge il prodotto al carrello dell'utente
            status.acquisti().add(new Acquisto(product.nome(), product.prezzo()));
            setService(id, status); // Aggiorna la sessione
            return "Prodotto: " + prod + " aggiunto al carrello";
        }

        return "Prodotto non presente";
    }

    public List<Product> cercaProdotti(String key){
        return productTable.getList(key);
    }
    
    //metodi privati per vedere e aggiornare lo stato di sessione di un utente specifico
    private Status getSession(String id){
        return sender.getSession(id);
    }

    private void setService(String id, Status status){
        sender.setSession(id, status);
    }



}
