package br.com.sisco.models;

import java.util.Calendar;

/**
 *
 * @author brunorocha
 */
public class Paciente {
    
    private int idPaciente;   
    private String nomeCompleto;
    private Calendar dataNascimento;
    private String matricula;
    private String vinculo;
    private String telefone;
            
    public Paciente() {
        
    }
    
    /*
    
        Esse construtor foi adicionado para inicializar os atributos nomeCompleto e telefone
        por que na classe PacientesController utiliza a classe pra criar os itens de exemplo da
        lista. Deve ser refatorado posteriormente.
        
    */
    public Paciente(String nomeCompleto, Calendar dataNascimento, String matricula, String vinculo, String telefone) {
        this.idPaciente = 0;
        this.nomeCompleto = nomeCompleto;        
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
        this.vinculo = vinculo;
        this.telefone = telefone;
    }        

    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
