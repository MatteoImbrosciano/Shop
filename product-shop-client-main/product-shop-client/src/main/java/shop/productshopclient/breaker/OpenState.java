package shop.productshopclient.breaker;

import java.util.function.Supplier;

public abstract class OpenState implements BreakerState{
    private long openTime;
    private long deltaTimeOpen;
    private String st;

    public OpenState(long delta, String name){
        openTime = System.currentTimeMillis();
        deltaTimeOpen = delta;
        st = name;
    }

    public BreakerState nextState(Supplier<BreakerState> s){
        long elapsed = System.currentTimeMillis() - openTime;
        System.out.println("time in " + st + " " + elapsed + " ms");
        if(elapsed < deltaTimeOpen){
            System.out.println("stay " + st);
            return this;
        }
        return s.get();
    }

}
