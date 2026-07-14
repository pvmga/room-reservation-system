package br.com.pvmga.roomreservation.domain;

public class Sala {
    private Long id;
    private String nome;
    private Integer capacidade;
    private Boolean ativa;

    public Sala(String nome, Integer capacidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome vazio");
        }

        if  (capacidade == null || capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade menor que ou igual a zero ou nula");
        }

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

    public void atribuirId(Long id) {
        if (this.id != null) {
            throw  new IllegalArgumentException("A sala já possui um ID.");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser mairo que zero.");
        }

        this.id = id;
    }

    public void desativar() {

        if (!this.ativa) {
            throw new IllegalStateException("A sala já está desativada.");
        }

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
