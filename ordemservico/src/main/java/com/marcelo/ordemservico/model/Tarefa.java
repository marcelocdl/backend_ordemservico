package com.marcelo.ordemservico.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_generator")
    @SequenceGenerator(name="tarefa_generator", sequenceName = "tarefa_seq", allocationSize=1)
    @Column(name = "id_tarefa", updatable = false, nullable = false)
    private int id_tarefa;
    private String nome;
    private String descricao;
    private String localizacao;

    public Tarefa() {
    }

    public Tarefa(int id_tarefa, String nome, String descricao, String localizacao, Funcionario funcionario) {
        this.id_tarefa = id_tarefa;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.funcionario = funcionario;
    }

    public Tarefa(String nome, String descricao, String localizacao, Funcionario funcionario) {
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.funcionario = funcionario;
    }

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id_tarefa == tarefa.id_tarefa && Objects.equals(nome, tarefa.nome) && Objects.equals(descricao, tarefa.descricao) && Objects.equals(localizacao, tarefa.localizacao) && Objects.equals(funcionario, tarefa.funcionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_tarefa, nome, descricao, localizacao, funcionario);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id_tarefa=" + id_tarefa +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", funcionario=" + funcionario +
                '}';
    }
}
