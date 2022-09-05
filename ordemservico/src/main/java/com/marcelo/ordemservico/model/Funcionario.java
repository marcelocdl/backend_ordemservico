package com.marcelo.ordemservico.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_generator")
    @SequenceGenerator(name="func_generator", sequenceName = "func_seq", allocationSize=1)
    @Column(name = "id_funcionario", updatable = false, nullable = false)
    private int id_funcionario;
    private String nome;
    private String endereco;

    @Column(name = "num_cpf")
    private String numCpf;
    private String telefone;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(int id_funcionario, String nome, String endereco, String numCpf, String telefone, Usuario usuario) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.endereco = endereco;
        this.numCpf = numCpf;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public Funcionario(String nome, String endereco, String numCpf, String telefone, Usuario usuario) {
        this.nome = nome;
        this.endereco = endereco;
        this.numCpf = numCpf;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return id_funcionario == that.id_funcionario && Objects.equals(nome, that.nome) && Objects.equals(endereco, that.endereco) && numCpf.equals(that.numCpf) && Objects.equals(telefone, that.telefone) && usuario.equals(that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_funcionario, nome, endereco, numCpf, telefone, usuario);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id_funcionario=" + id_funcionario +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numCpf='" + numCpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
