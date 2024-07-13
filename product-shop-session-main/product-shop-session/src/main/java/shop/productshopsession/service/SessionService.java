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

    private final DiskSer diskSer = new DiskSer();
    private final Map<String, Status> sessions = new HashMap<>();
    private final int exipireTime = 200;

    public Status getSession(String id){
        Status s = sessions.get(id);
        if(s != null)
            return refresh(id, s);
        dropSessions();
        s = diskSer.read(id);
        if(s != null)
            return refresh(id, s);
        s = new Status(new ArrayList<>(), Instant.now());
        sessions.put(id, s);
        return s;
    }


    public void checkout(String id){
        getSession(id).acquisti().clear();
    }

    private void dropSessions(){
        List<Map.Entry<String, Status>> expired = sessions.entrySet().stream().filter(e -> isExpired(e.getValue())).map(e -> saveToDisk(e)).toList();
        if(expired.size() > 0)
            sessions.entrySet().removeAll(expired);
    }
    public Status refresh(String id, Status s){
        Status refreshed = new Status(s.acquisti(), Instant.now());
        sessions.put(id, refreshed);
        return refreshed;
    }
    private boolean isExpired(Status s){
        return s.timestamp().plus(exipireTime, ChronoUnit.MILLIS).isBefore(Instant.now());
    }
    private Map.Entry<String, Status> saveToDisk(Map.Entry<String, Status> e){
        diskSer.write(e.getKey(), e.getValue());
        return e;
    }

}
