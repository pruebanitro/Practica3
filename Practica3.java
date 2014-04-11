/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author yo
 */
public class Practica3 {

    /**
     * @param args the command line arguments
     */
    
    static int contador=0;
    public static void main(String[] args) {
        
        
        // TODO code application logic here
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            // Nos dicen el numero total de numeros "n"
            String n = entrada.readLine();
            int totalNumeros = Integer.parseInt(n);
            // Nos dicen cada uno de los numeros de la coleccion C
            String[] numeros = entrada.readLine().split(" ");
            int coleccion[] = new int[totalNumeros];
            for(int i=0; i<=totalNumeros-1; i++){
                    coleccion[i] = Integer.parseInt(numeros[i]);
            }
            // Ordenamos la coleccion de menor a mayor
            Arrays.sort(coleccion);
            // Nos dicen porque cantidad de "m" numeros tienen que estar formadas las subcolecciones
            String m = entrada.readLine();
            int tamanioSubcolecciones = Integer.parseInt(m);
            // Creamos una solucion vacia para pasarsela al backtracking
            // La solucion vacia para un ARRAY de 3 posiciones sera -->> SOLUCION = [-1, -1, -1]
            int solucion[] = new int[tamanioSubcolecciones]; 
            for (int j=0 ; j<solucion.length ; j++) {
                solucion[j]=-1;
            }
            // System.out.println("SOLUCIONES");
            // Llamada al algoritmo de BackTracking (Vuelta atras)
            backTracking (coleccion, tamanioSubcolecciones, 0, solucion);
            // Imprimimos la solucion
            System.out.println(contador);    
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } // FIN CATCH
    } // FIN DE CLASE PRINCIPAL
    
    public static void backTracking (int coleccion[], int tamanioSubcolecciones, int i, int solucion[]) {
        for (;i<coleccion.length ; i++) {
            int numero=coleccion[i]; // Escojo un elemento
            //  System.out.println("Vamos a ver si es valido el " + numero);
            if (esValido(numero, solucion)) { // Si el numero cumple las restriccion del problema -> Lo añado
                //  System.out.println("Como es valido lo incluyo en la solucion");
                incluir(numero, solucion); // Incluyo el numero en la solucion
                //  System.out.println("La solucion que llevo es: ");
                //  mostrarSolucion(solucion);
                //  System.out.println("Calculo si es nodo hoja");
                if (esNodoHoja(solucion)) { // Si alcanzo alguna solucion 
                    //  System.out.println("Tengo una solucion COMPLETA");
                    //  System.out.println("HE ENCONTRADO 1 SOLUCION");
                    contador=contador+1;
                    // mostrarSolucion(solucion);
                } else { // Sino alcanzo ninguna solucion entonces hago una llamada recursiva
                //  System.out.println("NO TENGO SOLUCION TODAVIA");
                    backTracking (coleccion, tamanioSubcolecciones, i+1, solucion);
                }
                borrar (solucion, numero); // Retroceso, eliminamos la solucion parcial
            } 
        } // FIN DEL FOR
    } // FIN DEL BACKTRACKING
    public static int dameMenor (int array[]) {
        int menor=array[0];
        for (int i=1 ; i<=array.length-1 ; i++) {
            if (array[i]<menor && array[i]>=0) {
                menor=array[i];
            }
        }
        return menor;  
    } // FIN DE ELMENOR
    public static boolean esValido (int numero, int solucion[]) {
        if (estaVacia(solucion)) {
            return true;
        } else {
            int menor= dameMenor(solucion);  
            if (!esDivisor(menor, numero)) {   
                    return false;
            }
            return true;
        }
    } // FIN DE ESVALIDO
    public static void incluir(int numero, int solucion[]) {
        int i=0;
        while (solucion[i] != -1 && i<solucion.length) {
            i++;
        }
        // i = primera posicion libre del array solucion
        solucion[i]=numero;
    } // FIN DE INCLUIR
    public static boolean esNodoHoja (int solucion[]) {
        for (int i=0 ; i<solucion.length ; i++) {
            if (solucion[i]==-1) {   
                return false;
            }
        }
        return true;
    } // FIN DE ESNODOHOJA
    public static void borrar(int solucion[], int numero) {
        boolean encontrarNumero=false;
        int indice=0;
        // Miramos el array de derecha a izquierda para encontrarnos con el numero que acabamos de meter
        // ya que si lo hicieramos de otra forma podriamos borrar alguna repeticion de ese numero
        for (int i=solucion.length-1; i>=0 && !encontrarNumero; i--) {
            if (solucion[i]==numero) {
                indice=i;
                encontrarNumero=true;
            }
        }
        solucion[indice]=-1;
    } // FIN DE BORRAR
    public static boolean esDivisor (int menor, int numero) {
        if (numero%menor==0) {
            return true;
        }
        return false;
    } // FIN DE ESDIVISOR
    public static boolean estaVacia (int solucion[]) { 
        for (int i=0; i<solucion.length ; i++) {
            if (solucion[i]!=-1) {
                return false;
            }
        }
        return true;
    } // FIN DE ESTAVACIA
    public static void mostrarSolucion (int solucion []) {
        for (int i=0; i<solucion.length ; i++) {
            System.out.print(solucion[i]+ " ");
        }
        System.out.println();
    } // FIN DE MOSTRARSOLUCION
} // FIN DE PRACTICA 3
