package shop.productshopserver.data;

import java.io.Serializable;

public record Acquisto(String nome,  double prezzo) implements Serializable {
}
