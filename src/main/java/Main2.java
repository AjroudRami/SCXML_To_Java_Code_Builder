import stateMachine.Event;
import stateMachine.MyStateMachine;

import java.util.Scanner;

/**
 * Created by Rami on 12/03/2017.
 */
public class Main2 {

    public static void main(String[] args){
        MyStateMachine ms = new MyStateMachine();
        Scanner sc = new Scanner(System.in);
        String input = "";
        while(!input.equals("exit")){
            input = sc.nextLine();
            //ms.notifyEvent(new Event(input).setType(Event.Type.SEND));
        }
    }
}
