package br.com.pvmga.roomreservation.repository;

import br.com.pvmga.roomreservation.domain.Reserva;
import br.com.pvmga.roomreservation.domain.ReservaStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private Long proximoId = 1L;

    public Reserva salvar(Reserva reserva) {
        reserva.atribuirId(proximoId);
        proximoId++;

        reservas.add(reserva);

        return reserva;
    }

    public List<Reserva> listar() {
        return reservas;
    }

    public Reserva buscarPorId(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean existeConflito(
            Long salaId,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        return reservas.stream()
                .filter(reserva -> reserva.getStatus() == ReservaStatus.ATIVA)
                .filter(reserva -> reserva.getSala().getId().equals(salaId))
                .anyMatch(reserva ->
                        inicio.isBefore(reserva.getFim())
                                && fim.isAfter(reserva.getInicio())
                );
    }

    public List<Reserva> buscarPorSala(Long salaId) {
        return reservas.stream()
                .filter(reserva -> reserva.getSala().getId().equals(salaId))
                .toList();
    }

    public List<Reserva> buscarPorUsuario(Long usuarioId) {
        return reservas.stream()
                .filter(reserva ->
                        reserva.getUsuario().getId().equals(usuarioId)
                )
                .toList();
    }

    public List<Reserva> buscarPorPeriodo(
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        return reservas.stream()
                .filter(reserva ->
                        reserva.getInicio().isBefore(fim)
                                && reserva.getFim().isAfter(inicio)
                )
                .toList();
    }

}
