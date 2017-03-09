package stateMachine;

/**
 * Created by Rami on 08/03/2017.
 */
public abstract class Guard {

    public abstract void init();
    public abstract boolean evaluate();
    public abstract void destroy();
}
