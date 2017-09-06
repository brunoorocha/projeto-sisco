
package br.com.sisco.models;

import java.util.List;

/**
 *
 * @author brunorocha
 */
public class Prontuario {
    
    private String queixaPrincipal;
    private String doencaGrave;
    private String febreReumatica;
    private String tratamentoMedico;
    private String medicacao;
    private String gravida;
    private String alergico;
    private String hipertenco;
    private String diabetico;
    private String problemasGrastricos;
    private String anestesiaLocal;
    private String hemorragia;
    private int idPaciente;
    private List<Procedimento> procedimentos;
    

    public String getQueixaPrincipal() {
        return queixaPrincipal;
    }

    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    public String getDoencaGrave() {
        return doencaGrave;
    }

    public void setDoencaGrave(String doencaGrave) {
        this.doencaGrave = doencaGrave;
    }

    public String getFebreReumatica() {
        return febreReumatica;
    }

    public void setFebreReumatica(String febreReumatica) {
        this.febreReumatica = febreReumatica;
    }

    public String getTratamentoMedico() {
        return tratamentoMedico;
    }

    public void setTratamentoMedico(String tratamentoMedico) {
        this.tratamentoMedico = tratamentoMedico;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getGravida() {
        return gravida;
    }

    public void setGravida(String gravida) {
        this.gravida = gravida;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getHipertenco() {
        return hipertenco;
    }

    public void setHipertenco(String hipertenco) {
        this.hipertenco = hipertenco;
    }

    public String getDiabetico() {
        return diabetico;
    }

    public void setDiabetico(String diabetico) {
        this.diabetico = diabetico;
    }

    public String getProblemasGrastricos() {
        return problemasGrastricos;
    }

    public void setProblemasGrastricos(String problemasGrastricos) {
        this.problemasGrastricos = problemasGrastricos;
    }

    public String getAnestesiaLocal() {
        return anestesiaLocal;
    }

    public void setAnestesiaLocal(String anestesiaLocal) {
        this.anestesiaLocal = anestesiaLocal;
    }

    public String getHemorragia() {
        return hemorragia;
    }

    public void setHemorragia(String hemorragia) {
        this.hemorragia = hemorragia;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }
}
