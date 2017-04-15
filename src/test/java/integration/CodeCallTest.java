package integration;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by user on 15/04/2017.
 */
public class CodeCallTest extends TestEnv {
    private int count = 1;
    @BeforeClass
    public static void prep(){
        prepareEnv("test4.scxml");
    }

    @Test
    public void testCallMethod(){
        try {
            super.stateMachine.connectToEvent("increment", this, this.getClass().getMethod("incr"));
            super.stateMachine.connectToEvent("square", this, this.getClass().getMethod("sqr"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        super.stateMachine.notifyEvent("event1");
        super.stateMachine.start();
        super.stateMachine.notifyEvent("event2");

        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }

        //After calling incr() and sqr() on this object the value of count should be sqr(incr(count)) = 4
        assertEquals(4, this.count);
    }


    public void incr(){
        this.count ++;
    }

    public void sqr(){
        this.count = this.count * this.count;
    }
}
