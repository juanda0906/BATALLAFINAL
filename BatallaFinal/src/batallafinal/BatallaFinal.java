package batallafinal;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author USER
 */
public class BatallaFinal {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("¡Comienza la batalla épica entre Deadpool y Wolverine!");
        System.out.print("Introduce los puntos de vida iniciales para Deadpool: ");
        int vidaDeadpool = sc.nextInt();
        System.out.print("Introduce los puntos de vida iniciales para Wolverine: ");
        int vidaWolverine = sc.nextInt();

        Personaje deadpool = new Deadpool(vidaDeadpool);
        Personaje wolverine = new Wolverine(vidaWolverine);

        int turno = 1;
        while (deadpool.estaVivo() && wolverine.estaVivo()) {
            System.out.println("\n***** TURNO " + turno + " *****");
            TimeUnit.SECONDS.sleep(1);

            /* --- Turno de Deadpool --- */
            if (deadpool.pierdeEsteTurno()) {
                System.out.println(deadpool.nombre + " ¡GOLPE CRITICO! pierde turno por regeneracion.");
            } else {
                System.out.print("¡Ataca " + deadpool.nombre + "! ");
                System.out.print(deadpool.atacar(wolverine));
            }

            mostrarVidas(deadpool, wolverine);
            if (!wolverine.estaVivo()) break;
            TimeUnit.SECONDS.sleep(1);

            /* --- Turno de Wolverine --- */
            if (wolverine.pierdeEsteTurno()) {
                System.out.println(wolverine.nombre + " ¡GOLPE CRITICO! pierde turno por regeneracion.");
            } else {
                System.out.print("¡Ataca " + wolverine.nombre + "! ");
                System.out.print(wolverine.atacar(deadpool));
            }

            mostrarVidas(deadpool, wolverine);
            turno++;
        }

        /* Resultado */
        System.out.println("\n\n=======/////////////////////////////////=======");
        System.out.println("              ¡LA BATALLA HA TERMINADO!              ");
        System.out.println("=======/////////////////////////////////=======");
        if (deadpool.estaVivo()) {
            System.out.println("\n¡DEADPOOL ES EL GANADOR!");
        } else {
            System.out.println("\n¡WOLVERINE ES EL GANADOR!");
        }
        mostrarVidas(deadpool, wolverine);
    }

    private static void mostrarVidas(Personaje p1, Personaje p2) {
        System.out.println("Vida restante -> " + p1.nombre + ": "
                + p1.getVida() + " | " + p2.nombre + ": " + p2.getVida());
    }
}