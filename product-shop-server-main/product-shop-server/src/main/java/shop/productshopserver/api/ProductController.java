package shop.productshopserver.api;

import shop.productshopserver.data.Acquisto;
import shop.productshopserver.data.Product;
import shop.productshopserver.service.ProdService;
import shop.productshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private final ProdService prodService;

    public ProductController(ProdService prodService){
        this.prodService = prodService;
    }

    //andiamo a mappare le richieste get
    @GetMapping
    public List<Product> getAllProduct(){
        return prodService.getAllProduct();
    }

    @GetMapping(path = "/{id}")
    public List<Acquisto> getCarrello(@PathVariable("id") String id) {
            return prodService.getCarrello(id);
    }

    @PostMapping(path = "/{id}")
    public String aggiungiCarrello(@PathVariable("id") String id, @RequestBody String prod){
        return prodService.aggiungiCarrello(id, prod);
    }

    @GetMapping(path = "/find/{key}")
    public List<Product> cercaProdotti(@PathVariable("key") String key){
        return prodService.cercaProdotti(key);
    }

}
