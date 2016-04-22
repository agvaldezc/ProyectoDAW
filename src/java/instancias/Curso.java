/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancias;

/**
 *
 * @author agvaldezc
 */
public class Curso {
    private Materia materia;
    private Salon salon;
    private boolean ingles;
    private boolean honors;

    public Curso(Materia materia, Salon salon, boolean ingles, boolean honors) {
        this.materia = materia;
        this.salon = salon;
        this.ingles = ingles;
        this.honors = honors;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public boolean isIngles() {
        return ingles;
    }

    public void setIngles(boolean ingles) {
        this.ingles = ingles;
    }

    public boolean isHonors() {
        return honors;
    }

    public void setHonors(boolean honors) {
        this.honors = honors;
    }
    
}
