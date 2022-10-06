package br.senai.sp.cotia.listadeafazeres.model;

public class SubTarefa {
    private Long idSubtarefa;
    private String descricao;
    private String foto;
    private boolean concluida;
    private Long idTarefa;

    public Long getIdSubtarefa() {
        return idSubtarefa;
    }

    public void setIdSubtarefa(Long idSubtarefa) {
        this.idSubtarefa = idSubtarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }
}
