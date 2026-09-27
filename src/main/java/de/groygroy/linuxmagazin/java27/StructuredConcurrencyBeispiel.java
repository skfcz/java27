package de.groygroy.linuxmagazin.java27;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * Dieses Beispiel zeigt die Verwendung von strukturierter Nebenläufigkeit in Java 27.
 * Definiert in JEP 533
 */
public class StructuredConcurrencyBeispiel {

    private final static ScopedValue<Double> Kontext = ScopedValue.newInstance();

    static void main() {
        // setzt den Kontext, der in den Tasks verwendet wird
        var ergebniss = ScopedValue.where(Kontext, Math.random()).call(() -> {

            System.out.println("Starte StructuredTaskScope mit Kontext: " + Kontext.get() + " in Thread " + Thread.currentThread().getName());

            // Erstellen eines neuen TaskScope
            try (var scope = StructuredTaskScope.open()) {

                // Starten der Aufgaben im Scope
                StructuredTaskScope.Subtask<String> aufgabe1 = scope.fork(() -> aufgabe1());
                StructuredTaskScope.Subtask<Double> aufgabe2 = scope.fork(() -> aufgabe2());

                // Warten auf die Fertigstellung der Aufgaben
                scope.join();

                var resultat = aufgabe1.get() + "_" + aufgabe2.get();

                return resultat;
            } catch (ExecutionException exp)  {
                return "Fehler in einem StructuredTask" + exp.getCause();
            }  catch (Exception exp) {
                return "Sonstiger Fehler " + exp;
            }
        });
        System.out.println("Ergebnis: " + ergebniss);

    }

    private static String aufgabe1() {

        System.out.println("aufgabe1 mit Kontext: " + Kontext.get() + " in Thread " + Thread.currentThread());
        try {
            Thread.sleep(1000); // Simuliert eine Verzögerung
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Kontext.get().toString();
    }
    private static Double aufgabe2() {
        System.out.println("aufgabe2 mit Kontext: " + Kontext.get() + " in Thread " + Thread.currentThread());

        var gleit = Kontext.get();
        if ( gleit > 0.5 ) {
            throw new IllegalStateException("Fehler für " + gleit +"> 0.5");
        }
        return Math.sqrt(gleit);
    }
}
