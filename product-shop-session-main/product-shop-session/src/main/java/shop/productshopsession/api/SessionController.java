package shop.productshopsession.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import shop.productshopsession.data.Status;
import shop.productshopsession.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("session")
@RestController
public class SessionController {

    // Iniezione automatica del servizio SessionService
    @Autowired
    private final SessionService sessionService;

    // Costruttore per iniettare il SessionService
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    // Mappa le richieste GET all'endpoint /session/{id}
    @GetMapping(path = "/{id}")
    public @ResponseBody Status getSession(@PathVariable("id") String id){
        return sessionService.getSession(id); // Restituisce la sessione dell'utente specificato
    }

    // Mappa le richieste POST all'endpoint /session/{id}
    @PostMapping(path = "/{id}")
    public void setSession(@PathVariable("id") String id, @RequestBody String dto) {
        
        ObjectMapper mapper = new ObjectMapper(); // Crea un'istanza di ObjectMapper per deserializzare JSON
        mapper.registerModule(new JavaTimeModule()); // Registra un modulo per gestire i tipi di data e ora

        Status status = null;
        try {
            // Deserializza il corpo della richiesta JSON in un oggetto Status
            status = mapper.readValue(dto,  new TypeReference<Status>(){});
            // Aggiorna la sessione dell'utente specificato
            Status s = sessionService.refresh(id, status);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // Lancia un'eccezione runtime in caso di errore nella deserializzazione JSON
        }
    }
}
