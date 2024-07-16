package shop.productshopserver.data;

import java.io.Serializable;

public record Product(String nome, String tipologia, double prezzo) implements Serializable {
}
