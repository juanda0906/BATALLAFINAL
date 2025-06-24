/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batallafinal;
import java.util.Random;
/**
 *
 * @author USER
 */
public abstract class Personaje {
     String nombre;
     int vida;
     final int minDanio;
     final int maxDanio;
     final double probEvasion;
     boolean pierdeTurno;          // Si quedó aturdido en el turno anterior

    private final Random rnd = new Random();

    public Personaje(String nombre, int vida, int minDanio, int maxDanio, double probEvasion) {
        this.nombre = nombre;
        this.vida = vida;
        this.minDanio = minDanio;
        this.maxDanio = maxDanio;
        this.probEvasion = probEvasion;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public int getVida() {
        return Math.max(0, vida);
    }

    public boolean pierdeEsteTurno() {
        if (pierdeTurno) {
            pierdeTurno = false;   // Solo pierde un turno
            return true;
        }
        return false;
    }

    /** Devuelve el texto descriptivo del ataque */
    public String atacar(Personaje rival) {
        StringBuilder evento = new StringBuilder();

        // ¿rival esquiva?
        if (rnd.nextDouble() < rival.probEvasion) {
            evento.append(rival.nombre).append(" esquiva el ataque!");
            return evento.toString();
        }

        int danio = rnd.nextInt(maxDanio - minDanio + 1) + maxDanio;
        rival.vida -= danio;

        boolean critico = (danio >= maxDanio);
        if (critico) {
            evento.append("¡GOLPE CRÍTICO! ");
            rival.pierdeTurno = true;
            evento.append(nombre)
                  .append(" inflige ")
                  .append(danio)
                  .append(" puntos de daño y se empieza a regenerarse ")
                  .append(rival.nombre)
                  .append(".\n");
        } else {
            evento.append(nombre)
                  .append(" inflige ")
                  .append(danio)
                  .append(" puntos de daño.\n");
        }
        return evento.toString();
    }
}