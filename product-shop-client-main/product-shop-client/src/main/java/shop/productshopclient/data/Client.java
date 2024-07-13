package shop.productshopclient.data;

import shop.productshopclient.api.HttpSender;

import shop.productshopclient.breaker.CircuitBreaker;

public class Client {
    private final String name;
    private final CircuitBreaker c;

    //private final Service s

    public Client(String name, CircuitBreaker c){
        this.name = name;
        this.c = c;
    }

    public void getAllProducts(){
       int statusCode = c.getAllProducts();
       if (statusCode != -1)
           System.out.println("Status code: " + statusCode);
    }

    public void visualizzaCarrello(){
        int statusCode = c.visualizzaCarrello(name);
        if (statusCode != -1)
            System.out.println("Status code: " + statusCode);
    }
    public void aggiungiAlCarrello(String disp){
        int statusCode = c.aggiungiAlCarrello(name, disp);
        if (statusCode != -1)
            System.out.println("Status code: " + statusCode);
    }
    public void cercaProdotti(String cerca){
        int statusCode = c.cercaProdotti(cerca);
        if (statusCode != -1)
            System.out.println("Status code: " + statusCode);
    }
}
