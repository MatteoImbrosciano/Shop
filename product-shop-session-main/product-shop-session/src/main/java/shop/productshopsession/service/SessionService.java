package shop.productshopsession.service;

import shop.productshopsession.data.DiskSer;
import shop.productshopsession.data.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SessionService {

    private final DiskSer diskSer = new DiskSer(); // Istanza di DiskSer per leggere e scrivere sessioni su disco
    private final Map<String, Status> sessions = new HashMap<>(); // Mappa per mantenere le sessioni in memoria
    private final int expireTime = 200; // Tempo di scadenza delle sessioni

    //ottengo la sessione corrispondente all' id
    public Status getSession(String id){
        Status s = sessions.get(id); //cerchiamo la sessione corrispondente
        if(s != null)
            return refresh(id, s); //se si trova si aggiorna il timestamp
        dropSessions(); //rimuove le sessioni scadute
        s = diskSer.read(id); //leggiamo la sessione da disco
        if(s != null)
            return refresh(id, s); //ok trovata
        s = new Status(new ArrayList<>(), Instant.now()); //altrimenti la crea
        sessions.put(id, s);
        diskSer.write(id,s);
        return s;
    }

    //svuota la lista degli acquisti della sessione
    public void checkout(String id){
        getSession(id).acquisti().clear();
    }

    //rimuovo tutte le sessioni scadute
    private void dropSessions(){
        List<Map.Entry<String, Status>> expired = sessions.entrySet().stream().filter(e -> isExpired(e.getValue())).map(e -> saveToDisk(e)).toList();
        if(expired.size() > 0) 
            sessions.entrySet().removeAll(expired);
    }

    //Aggiorna il timestamp della sessione e la salva nella mappa sessions
    public Status refresh(String id, Status s){
        Status refreshed = new Status(s.acquisti(), Instant.now());
        sessions.put(id, refreshed); //aggiorniamo la sessione in memoria
        diskSer.write(id,s); //scrive la sessione aggiornata su disco
        return refreshed;
    }

    //Controlla se una sessione è scaduta
    private boolean isExpired(Status s){
        return s.timestamp().plus(expireTime, ChronoUnit.MILLIS).isBefore(Instant.now());
    }

    //Salva una sessione su disco e restituisce l'entry
    private Map.Entry<String, Status> saveToDisk(Map.Entry<String, Status> e){
        diskSer.write(e.getKey(), e.getValue());
        return e;
    }

}
