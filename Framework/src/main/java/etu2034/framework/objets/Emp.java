package etu2034.framework.objets;

import etu2034.framework.MethodAnnotation;

public class Emp {

    @MethodAnnotation(url = "hi")
    public void Hello(){
        System.out.println("Hello World");
    }

    public void Bye(){
        System.out.println("Bye Bye");
    }

    @MethodAnnotation(url = "work")
    public void travailler(){
        System.out.println("I'm Working");
    }
}
