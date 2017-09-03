package br.com.sisco.models;

/**
 *
 * @author brunorocha
 */
public class Paciente {

    private String nome;
    private String telefone;

    public Paciente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }
}
