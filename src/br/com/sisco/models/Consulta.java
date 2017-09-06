
package br.com.sisco.models;

import java.util.Date;

/**
 *
 * @author brunorocha
 */
public class Consulta {
    
    private Date data;
    private String hora;
    private int idPaciente;
        
    public void cancelarConsulta(){
        
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
