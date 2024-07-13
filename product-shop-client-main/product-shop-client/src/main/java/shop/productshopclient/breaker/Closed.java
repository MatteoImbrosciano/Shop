package shop.productshopclient.breaker;

public class Closed implements BreakerState{

    public Closed(){
        System.out.println("go to closed");
    }

    @Override
    public BreakerState nextState() {
        return this;
    }

    @Override
    public boolean isClosed() {
        return true;
    }
}
