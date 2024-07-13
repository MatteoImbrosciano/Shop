package shop.productshopserver.data;

import java.util.ArrayList;
import java.util.List;

public class ProductTable {
    private final List<Product> prodotti = new ArrayList<>();

    public ProductTable(){
        prodotti.add(new Product("Ralph-Lauren", "T-shirt",  50));
        prodotti.add(new Product("Nike", "Scarpe",  110));
        prodotti.add(new Product("Guess", "Pantaloni",  65));
        prodotti.add(new Product("Adidas", "Scarpe",  90));
        prodotti.add(new Product("Guess", "T-Shirt",  65));
        prodotti.add(new Product("Armani", "Pantaloni",  120));
        prodotti.add(new Product("Nike-Air-Force", "Scarpe",  200));
        prodotti.add(new Product("Puma", "T-Shirt",  65));
        prodotti.add(new Product("Diesel", "Pantaloni",  120));
        prodotti.add(new Product("Converse", "Scarpe",  200));
    }

    public Product getProduct(String nome){
        return prodotti.stream().filter(a -> a.nome().equals(nome)).findAny().orElse(null);
    }

    public List<Product> getList(String key){
        return prodotti.stream().filter(a -> a.nome().indexOf(key) > -1).toList();
    }
    public List<Product> getAllProduct(){
        return prodotti;
    }
}
