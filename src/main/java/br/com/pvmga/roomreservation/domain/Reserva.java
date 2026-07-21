package br.com.pvmga.roomreservation.domain;

import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private final Sala sala;
    private final LocalDateTime inicio;
    private final LocalDateTime fim;
    private ReservaStatus status;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim) {

        if (sala == null) {
            throw new IllegalArgumentException("A sala é obrigatória.");
        }

        if (inicio == null) {
            throw new IllegalArgumentException("A data e hora de inicio são obrigatórias.");
        }

        if (fim == null) {
            throw new IllegalArgumentException("A data e hora de término são obrigatórias.");
        }

        if (!fim.isAfter(inicio)) {
            throw new IllegalArgumentException("O término da reserva deve ser posterior ao início");
        }

        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
        this.status = ReservaStatus.ATIVA;
    }

    public void atribuirId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("A reserva já possui um ID.");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID da reserva deve ser maior que zero.");
        }

        this.id = id;
    }

    public void cancelar() {
        if (status == ReservaStatus.CANCELADA) {
            throw new IllegalStateException("A reserva já está cancelada.");
        }

        status = ReservaStatus.CANCELADA;
    }

    public void reativar() {
        if (status == ReservaStatus.ATIVA) {
            throw new IllegalStateException("A reserva já está ativa.");
        }

        status = ReservaStatus.ATIVA;
    }

    public Long getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public ReservaStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", sala=" + sala +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", status=" + status +
                '}';
    }
}
