//Este es un comentario. Por lo tanto, Java no hara caso a este texto cuando póngamos el programa a funcionar y nos sirve para poner Notas en el código

//Esta línea, indica el "Paquete" en el que nuestra "Clase" esta ubicado (Después veremos que es una clase)
package com.guekho64.Test;

//Los espacios en blanco nos sirven para que puedamos leer el código fácilmente, pero no es obligatorio ponerlas si es que asi no nos gusta

//Esta línea nos indica la "Clase" con la que estamos trabajando, que usualmente se llama igual que el archivo
public class Test {
 
//Esta línea nos indica la función "main", que en este caso, será la que lleve a cabo todo el trabajo ("main" = "principal" en español)  
  public static void main(String[] args) {
    
    //Esta linea dice que estamos "creando" la variable "HolaMundo", la cual contiene el mensaje "Hola Mundo de Java"
    final String HolaMundo = "Hola Mundo de Java";
    
    //Esta linea le dice a Java: Lo que la variable "HolaMundo" tenga como mensaje, ponlo.
    System.out.println(HolaMundo);
   
    //No olvidar cerrar todas nuestras llaves y paréntesis. Por cada (, [, { hay que cerrarlo con ),], }, dependiendo el caso
    
  }
}
