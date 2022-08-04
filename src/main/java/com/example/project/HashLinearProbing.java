package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private String AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = Integer.MIN_VALUE+""; //tambien convertmos el AVAILABLE
        this.size = 0;
    }

    public int hashing(String key) {
    	int comKey= Integer.parseInt(key); //convertimos el DNI a int
    	while(esPrimo(hsize)) { //el tamano lo convertimos a primo para que la funcion hash se mas eficiente
    		hsize--;
    	}
        int hash = comKey % hsize; //dividimos el DNI convertido a entero por el numero primo que generamos 
        return hash;
    }
    
    public boolean esPrimo(int n) { //metodo para verificar si es primo lo usaremos para encontrar el primo mas
    	//cercano en la funcion hash
    	for(int i = 2; i < n; i++) {
    		if(n % i == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //El resto del codigo fue modificado para que funcione con la clase persona
    //Cambiamos los parametros que recibia la funcion hash, por el DNI, y comparamos con equal
    //ya que ahora son String
    //en findHash retornamos ahora una persona

    public void insertHash(Persona p) {
        int hash = hashing(p.DNI);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null || buckets[hash].DNI.equals(AVAILABLE)) {
                buckets[hash] = p;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String key) {
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(key)) {
                buckets[hash].DNI = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i].DNI.equals(AVAILABLE)) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public Persona findHash(String key) {
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(key)) {
                    return buckets[hash];
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

}
