
package br.com.sisco.models;

import java.util.Date;

/**
 *
 * @author brunorocha
 */
public class Procedimento {
    
    private Date data;
    private String resumo;
    private int idProntuario;        

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public int getIdProntuario() {
        return idProntuario;
    }

    public void setIdProntuario(int idProntuario) {
        this.idProntuario = idProntuario;
    }
}
