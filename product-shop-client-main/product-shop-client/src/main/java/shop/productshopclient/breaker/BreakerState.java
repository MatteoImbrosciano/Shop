package shop.productshopclient.breaker;

public interface BreakerState {
    public BreakerState nextState();
    public boolean isClosed();
}
