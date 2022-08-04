package com.example.project;

public class Exercise1 {

    public HashLinearProbing tb;

    public static void main(String[] args) {
        Exercise1 obj = new Exercise1();

        obj.tb = new HashLinearProbing(100);

        obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
        obj.insertarPersona(new Persona("12345679", "Juan Perez"));

        System.out.println(obj.encontrarPersona("12345678"));

        obj.eliminarPersona("12345678");

        System.out.println(obj.encontrarPersona("12345678"));
    }

    public void insertarPersona(Persona obj){
        // Llama al metodo 'insertHash' modificado
    	tb.insertHash(obj);
    }

    public void eliminarPersona(String dni){
        // Llama al metodo 'deleteHash' modificado
    	tb.deleteHash(dni);
    }

    // Retorna NULL quando no se encontro el dni, y el nombre de la persona si lo encontro
    public String encontrarPersona(String dni){
        Persona p = tb.findHash(dni); //findHash nos retornara una peroson si encuentra o null si no lo hace
        if(p == null) { //si p es null simplemente retornaremos null
        	return null;
        }
        return p.nombre; //si no retornamos el nombre
    }    
}
