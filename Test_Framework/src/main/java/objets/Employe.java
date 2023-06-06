package objets;

import etu2034.framework.MethodAnnotation;
import etu2034.framework.ModelView;

import java.io.PrintWriter;

public class Employe {
    String nom;
    double salaire;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Employe() {
    }

    @MethodAnnotation(url = "emp-list")
    public ModelView findAll(){
        ModelView mv=new ModelView("view.jsp");
        mv.addItem("Emp1",23000);
        mv.addItem("Emp2",35000);
        return mv;
    }

    @MethodAnnotation(url = "save-emp")
    public void save(){
        System.out.println("<br>"+this.getNom());
        System.out.println("<br>"+this.getSalaire());
    }
}
