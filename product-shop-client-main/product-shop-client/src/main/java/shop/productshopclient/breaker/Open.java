package shop.productshopclient.breaker;

public class Open extends OpenState{
    public Open(){
        super(200, "open");
    }

    @Override
    public BreakerState nextState(){
        return nextState(() -> new HalfOpen());
    }

    public boolean isClosed(){
        return false;
    }
}
