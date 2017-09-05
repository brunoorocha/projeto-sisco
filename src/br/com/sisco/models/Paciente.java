package br.com.sisco.models;

import java.util.Date;

/**
 *
 * @author brunorocha
 */
public class Paciente {

    private String nomeCompleto;
    private Date dataNascimento;
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
    public Paciente(String nomeCompleto, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }        

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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
