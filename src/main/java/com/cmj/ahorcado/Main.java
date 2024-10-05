package com.cmj.ahorcado;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Juego simple del ahorcado
    @author Carlos Madrid Jimenez
*/

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String padding = "----------";
        String[] monigote = {"", "o", "o\n|", "o\n|\n|", " o\n_|\n |", " o\n_|_\n |", " o\n_|_\n |\n/", " o\n_|_\n |\n/ \\"};

        boolean terminada = false;
        int fallos = 0; //Máx 7
        ListaPalabras[] listaPalabras = ListaPalabras.values();
        String palabra;
        String[] huecosPalabra;
        char letra;

        //Flujo principal del juego
        palabra = escogerPalabra(listaPalabras);
        huecosPalabra = new String[palabra.length()];
        inicializarHuecos(huecosPalabra);
        System.out.printf("%s JUEGO DEL AHORCADO %s\n\n", padding, padding);
        while(!terminada){
            imprimirArray(huecosPalabra);
            System.out.print("Introduce una letra: ");
            letra = pedirLetra();

            if(palabra.contains("" + letra)){
                reemplazarHueco(palabra, huecosPalabra, letra);
            }else{
                fallos++;

                if(fallos == 7){
                    System.out.println("Has perdido :(");
                    System.exit(0);
                }
            }

            System.out.println(monigote[fallos]);
            terminada = comprobarHuecos(huecosPalabra);
        }

        System.out.println("\nHas ganado :D");
    }

    public static void imprimirArray(Object[] arr){
        for (Object o: arr) {
            System.out.print(o);
        }
        System.out.println();
    }

    public static void inicializarHuecos(String[] arr){
        Arrays.fill(arr, "_");
    }

    public static boolean comprobarHuecos(String[] arr){
        for (String s: arr) {
            if (s.equals("_")) {
                return false;
            }
        }

        return true;
    }

    public static void reemplazarHueco(String palabra, String[] arr, char letra){
        char c;

        for(int i = 0; i < palabra.length(); i++){
            c = palabra.charAt(i);
            if(c == letra) arr[i] = "" + c;
        }
    }

    public static String escogerPalabra(ListaPalabras[] arr){
        int random = (int) (Math.random() * arr.length);
        return String.valueOf(arr[random]);
    }

    public static char pedirLetra(){
        String entrada;
        char c;

        while(true){
            entrada = input.nextLine();
            if(!entrada.isEmpty()){
                c = entrada.charAt(0);
                if(!Character.isDigit(c)) break;
                System.out.printf("%S no es una letra válida: ", c);
            }else{
                System.out.print("No se ha introducido nada: ");
            }
        }

        return c;
    }
}