package br.senai.sp.cotia.listadeafazeres.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long idTarefa;
    private String titulo;
    private String descricao;
    private long dataCriacao;
    private long dataPrevista;
    private long dataFinalizada;

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(long dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public long getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(long dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public long getDataFinalizada() {
        return dataFinalizada;
    }

    public void setDataFinalizada(long dataFinalizada) {
        this.dataFinalizada = dataFinalizada;
    }
    //informa se a tarefa está concluida
    public boolean isConcluida(){
        return  dataFinalizada != 0;
    }
}
