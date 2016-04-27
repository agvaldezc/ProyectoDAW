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
public class Maestro {
    
    private String nomina;
    private String password;
    private String nombre;
    private String telefono;
    private String email;
    private int cursosImpartidos;

    public Maestro(String nomina, String password, String nombre, String telefono, String email, int cursosImpartidos) {
        this.nomina = nomina;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cursosImpartidos = cursosImpartidos;
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCursosImpartidos() {
        return cursosImpartidos;
    }

    public void setCursosImpartidos(int cursosImpartidos) {
        this.cursosImpartidos = cursosImpartidos;
    }
}
