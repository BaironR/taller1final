import edu.princeton.cs.stdlib.StdDraw;
import edu.princeton.cs.stdlib.StdOut;

import java.awt.*;

public class Taller1 {

    public static void main(String[] args) {

        // Rango de dibujo
        double min = -2.0;
        double max = 2.0;

        // Definición de la escala del lienzo de dibujo
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        // Evitar el parpadeo de la pantalla
        StdDraw.enableDoubleBuffering();

        // Colores de las seis líneas
        Color[] colores = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.RED, Color.CYAN};

        // Posición inicial de la primera línea
        double[] posX0 = new double[6];
        double[] posY0 = new double[6];
        double[] posX1 = new double[6];
        double[] posY1 = new double[6];
        posX0[0] = posicion(min,max);
        posY0[0] = posicion(min,max);
        posX1[0] = posicion(min,max);
        posY1[0] = posicion(min,max);

        // Velocidad inicial de la primera línea
        double velocidadX0 = velocidades();
        double velocidadY0 = velocidades();
        double velocidadX1 = velocidades();
        double velocidadY1 = velocidades();
        double[] velocidadesX0 = new double[5];
        double[] velocidadesY0 = new double[5];
        double[] velocidadesX1 = new double[5];
        double[] velocidadesY1 = new double[5];

        // Contador de iteraciones
        int k = 4;

        //Ciclo infinito
        while (true){

            // Colisión
            velocidadX0 = cambioVelocidad(posX0[0],velocidadX0);
            velocidadY0 = cambioVelocidad(posY0[0],velocidadY0);
            velocidadX1 = cambioVelocidad(posX1[0],velocidadX1);
            velocidadY1 = cambioVelocidad(posY1[0],velocidadY1);

            // Actualización de posición de la primera línea
            posX0[0] += velocidadX0;
            posY0[0] += velocidadY0;
            posX1[0] += velocidadX1;
            posY1[0] += velocidadY1;

            // Guardar velocidades y posiciones en un vector
            velocidadLineas(velocidadX0,velocidadesX0,k);
            velocidadLineas(velocidadY0,velocidadesY0,k);
            velocidadLineas(velocidadX1,velocidadesX1,k);
            velocidadLineas(velocidadY1,velocidadesY1,k);

            // Actualización de posición del resto de líneas
            for (int i=1; i<6; i++){

                posX0[i] = posX0[i-1] - (velocidadesX0[i-1]*3);
                posY0[i] = posY0[i-1] - (velocidadesY0[i-1]*3);
                posX1[i] = posX1[i-1] - (velocidadesX1[i-1]*3);
                posY1[i] = posY1[i-1] - (velocidadesY1[i-1]*3);
            }

            // Limpiar el fondo
            StdDraw.clear();

            // Dibujar las líneas
            for (int i = 0; i<6; i++){

                StdDraw.setPenColor(colores[i]);
                StdDraw.line(posX0[i],posY0[i],posX1[i],posY1[i]);
            }

            k--;
            // Mostrar la pantalla y esperar
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    // Posición inicial de la primera línea
    public static double posicion(double min,double max){

        return min + (max - min) * Math.random();
    }

    // Velocidad inicial de la primera línea
    public static double velocidades(){

        return 0.01 + Math.random()*0.007;
    }

    // Colisión
    public static double cambioVelocidad(double posicion, double velocidadPos){

        if (Math.abs(posicion + velocidadPos) > 2.0){

            velocidadPos = -velocidadPos;
        }

        return velocidadPos;
    }

    // Velocidad del resto de líneas
    public static void velocidadLineas(double velocidadPos, double[] vector, int k){

        if (k >= 0 ){

            vector[k] = velocidadPos;

        } else {

            for (int i=3; i>=0; i--){

                vector[i+1] = vector[i];
            }

            vector[0] = velocidadPos;
        }
    }
}

