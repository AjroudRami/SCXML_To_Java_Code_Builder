package stateMachine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.Thread.sleep;

/**
 * Created by Rami on 12/03/2017.
 */
public class Caller implements Callable {

    Object calledObject;
    Method calledMethod;
    Object[] args;
    int delay;

    public Caller(){
    }

    public Caller(Object calledObject, Method calledMethod, int delay){
        this.calledMethod = calledMethod;
        this.calledObject = calledObject;
        this.delay = 0;
    }

    public Caller(Object calledObject, Method calledMethod, Object[] args, int delay){
        this(calledObject, calledMethod, delay);
        this.args = args;
    }

    public void call(){
        new Thread(new Runnable(){
            public void run(){
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if(args==null){
                        calledMethod.invoke(calledObject);
                    }else{
                        calledMethod.invoke(calledObject, args);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int getDelay(){
        return this.delay;
    }
}
