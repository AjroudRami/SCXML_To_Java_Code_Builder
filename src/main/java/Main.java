import generator.SCXMLToJava;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Rami on 09/03/2017.
 */
public class Main {

    public static void main(String[] args){
        System.out.println(Arrays.toString(args));
        if(args.length == 2){
            File output = new File(args[0]);
            if(output.exists() && !output.isDirectory()){
                System.out.println("Invalid Path: " + args[0] + " File exists.");
                System.exit(1);
            }
            if(!output.exists()){
                output.mkdir();
            }
            try {
                SCXMLToJava builder = new SCXMLToJava(new File(args[1]));
                builder.generateJavaCode(output);
            } catch (Exception e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }else{
            System.out.println("Bad Arguments");
        }


    }
}
