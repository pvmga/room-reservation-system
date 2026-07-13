package br.com.pvmga.roomreservation.domain;

public class Sala {
    private Long id;
    private String nome;
    private Integer capacidade;
    private Boolean ativa;

    public Sala(Long id, String nome, Integer capacidade) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ativa = true;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public void desativar() {
        this.ativa = false;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", ativa=" + ativa +
                '}';
    }
}
