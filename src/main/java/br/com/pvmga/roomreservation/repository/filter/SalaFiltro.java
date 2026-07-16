package br.com.pvmga.roomreservation.repository.filter;

public class SalaFiltro {
    private final String nome;
    private final Integer capacidadeMinima;
    private final Integer capacidadeMaxima;
    private final Boolean ativa;

    public SalaFiltro(String nome, Integer capacidadeMinima, Integer capacidadeMaxima, Boolean ativa) {
        this.nome = nome;
        this.capacidadeMinima = capacidadeMinima;
        this.capacidadeMaxima = capacidadeMaxima;
        this.ativa = ativa;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCapacidadeMinima() {
        return capacidadeMinima;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public Boolean getAtiva() {
        return ativa;
    }
}
