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

    private String materia;
    private int grupo;
    private String profesor;
    private String horario;
    private String salon;
    private boolean ingles;
    private boolean honors;

    public Curso() {
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materiaId) {
        this.materia = materiaId;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
