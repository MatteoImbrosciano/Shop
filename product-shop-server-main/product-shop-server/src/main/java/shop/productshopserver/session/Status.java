package shop.productshopserver.session;

import shop.productshopserver.data.Acquisto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record Status(List<Acquisto> acquisti, Instant timestamp) implements Serializable {

}
