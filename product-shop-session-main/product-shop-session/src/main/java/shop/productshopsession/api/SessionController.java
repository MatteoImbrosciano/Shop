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
    @Autowired
    private final SessionService sessionService;
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Status getSession(@PathVariable("id") String id){
        return sessionService.getSession(id);
    }

    @PostMapping(path = "/{id}")
    public void setSession(@PathVariable("id") String id, @RequestBody String dto) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Status status = null;
        try {
            status = mapper.readValue(dto,  new TypeReference<Status>(){});
            Status s = sessionService.refresh(id, status);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }


}
