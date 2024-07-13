package shop.productshopclient.breaker;

import java.util.Random;

public class HalfOpen extends OpenState{
    public HalfOpen (){
        super(100, "half open");
    }

    @Override
    public BreakerState nextState(){
        return nextState(() -> new Closed());
    }

    public boolean isClosed(){
        return (0 == new Random().nextInt(2));
    }

}
