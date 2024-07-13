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

    public List<Acquisto> getCarrello(String id){
        return getSession(id).acquisti();
    }


    public String aggiungiCarrello(String id, String prod){
        Status status = getSession(id);
        Product product = productTable.getProduct(prod);
        if(product != null){

            status.acquisti().add(new Acquisto(product.nome(), product.prezzo()));
            setService(id, status);
            return "Prodotto: " + prod + " aggiunto al carrello";
        }

        return "Prodotto non presente";

    }

    public List<Product> cercaProdotti(String key){
        return productTable.getList(key);
    }

    private Status getSession(String id){
        return sender.getSession(id);
    }

    private void setService(String id, Status status){
        sender.setSession(id, status);
    }



}
