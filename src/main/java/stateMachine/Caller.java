package stateMachine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Rami on 12/03/2017.
 */
public class Caller implements Callable {

    Object calledObject;
    Method calledMethod;
    Object[] args;

    public Caller(){
    }

    public Caller(Object calledObject, Method calledMethod){
        this.calledMethod = calledMethod;
        this.calledObject = calledObject;
    }

    public Caller(Object calledObject, Method calledMethod, Object[] args){
        this(calledObject, calledMethod);
        this.args = args;
    }

    public void call(){
        new Thread(new Runnable(){
            public void run(){
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
}
