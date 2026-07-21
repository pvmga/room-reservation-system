package br.com.pvmga.roomreservation.domain;

import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private final Sala sala;
    private final Usuario usuario;
    private final LocalDateTime inicio;
    private final LocalDateTime fim;
    private ReservaStatus status;

    public Reserva(
            Sala sala,
            Usuario usuario,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        validarPeriodo(inicio, fim);

        if (sala == null) {
            throw new IllegalArgumentException("A sala é obrigatória.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("O usuário é obrigatório.");
        }

        this.sala = sala;
        this.usuario = usuario;
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

    public static void validarPeriodo(
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException(
                    "Data e hora de início e fim são obrigatórias."
            );
        }

        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException(
                    "A data/hora de início deve ser anterior à data/hora de fim."
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Usuario getUsuario() {
        return usuario;
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
                ", usuario=" + usuario +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", status=" + status +
                '}';
    }
}
