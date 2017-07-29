package com.tanya.com;


import java.io.IOException;
import java.util.ArrayList;

public class Hello {

    public static String myString = "staticString";

    String s = new String("asdas");

    public String nonStaticString = "anotherString";

    int a;


    String p = s;


    public interface FoodInterface {
        boolean isEdible();
    }

    public class Food {
    }

    public class Fruit extends Food {

    }

    public class Mango extends Fruit implements FoodInterface{

        @Override
        public boolean isEdible() {

            ArrayList<String> names = new ArrayList<>();

            int a = 10;
            int b = 0;
            int c;
            try {
                c = a/b;
            } catch (IOException|NullPointerException|ArrayIndexOutOfBoundsException ioe) {

            }  finally {

            }

            return false;
        }


    }

}
