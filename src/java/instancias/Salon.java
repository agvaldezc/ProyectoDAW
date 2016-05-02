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
public class Salon {
    private String id;
    private int capacidad;
    private String administracion;

    public Salon(String id, int capacidad, String administracion) {
        this.id = id;
        this.capacidad = capacidad;
        this.administracion = administracion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }
}
