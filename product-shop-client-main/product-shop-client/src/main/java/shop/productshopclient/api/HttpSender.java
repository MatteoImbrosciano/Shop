package shop.productshopclient.api;
//per mandare la richiesta
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSender {

    public HttpSender(){}

    private String uri = "http://localhost:8080/product";

    public int getAllProducts() throws IOException, InterruptedException{
        // Crea una richiesta HTTP GET all'URI specificato, utilizzando la versione HTTP/2
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(uri)).GET().version(java.net.http.HttpClient.Version.HTTP_2).build();
        // Crea un'istanza di HttpClient per inviare la richiesta
        HttpClient client = HttpClient.newBuilder().build();

        // Invia la richiesta e riceve la risposta, che viene convertita in una stringa
        HttpResponse resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Dati: " + resp.body());

        return resp.statusCode();

    }

    public int visualizzaCarrello(String id) throws IOException, InterruptedException{
        String newUri = uri + "/" + id; // Crea un nuovo URI combinando l'URI base con l'ID specificato
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(newUri)).GET().version(java.net.http.HttpClient.Version.HTTP_2).build();
        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse resp = client.send(req, HttpResponse.BodyHandlers.ofString()); // Invia la richiesta e riceve la risposta
        System.out.println("Dati: " + resp.body());

        return resp.statusCode();
    }

    public int aggiungiAlCarrello(String id, String prod) throws IOException, InterruptedException{
        String newUri = uri + "/" + id;

        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(newUri)).POST(HttpRequest.BodyPublishers.ofString(prod)).version(java.net.http.HttpClient.Version.HTTP_2).build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Risposta: " + resp.body());

        return resp.statusCode();
    }

    public int cercaProdotti(String cerca) throws IOException, InterruptedException{
        String newUri = uri + "/find/" + cerca;
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(newUri)).GET().version(java.net.http.HttpClient.Version.HTTP_2).build();
        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Dati: " + resp.body());

        return resp.statusCode();
    }

}
