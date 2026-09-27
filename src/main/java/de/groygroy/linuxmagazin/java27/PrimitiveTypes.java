package de.groygroy.linuxmagazin.java27;

import java.util.Date;

/**
 * Beispiel für die Verwendung von JEP 532 Primitive Types in Patterns, instanceof, and switch
 *
 */
public class PrimitiveTypes {
    void main() {
        var jetzt = new Date();
        System.out.println("es ist " + jetzt);

        var minuten = jetzt.getMinutes();
        var stunde = jetzt.getHours() %12;
        switch (minuten) {
            case 59,0,1  -> System.out.println( stunde + " Uhr");
            case 14,15,16  -> System.out.println("viertel nach " + stunde );
            case 29,30,31  -> System.out.println("halb" + ( stunde+1));
            case 44,45,46  -> System.out.println("viertel vor " + (stunde +1) );
            case int i when i < 14 -> System.out.println("kurz nach");
            case int i when i < 29 -> System.out.println("kurz vor halb");
            case int i when i < 44 -> System.out.println("kurz nach halb");
            case int i when i < 59 -> System.out.println("kurz vor");
            case int i -> System.out.println( "was anderes " + i);
        }

        byte  byteVar = 'A';
        if ( byteVar instanceof byte b) {
            System.out.println("als byte " + b);
        }
        if ( byteVar instanceof int i) { // impliziter Cast
            System.out.println("als int " + i);
        }
        if ( byteVar instanceof float f) { // impliziter Cast
            System.out.println("als float " + f);
        }
        // Kompiliert nur, wenn Variable als Object vorliegt
        Object var = byteVar;
        if ( var instanceof boolean b) { // impliziter Cast
             System.out.println("als boolean " + b);
          } else {
              System.out.println("keine Abbildung byte->boolean");
          }



    }
}


record Punkt(int x, int y) {}

