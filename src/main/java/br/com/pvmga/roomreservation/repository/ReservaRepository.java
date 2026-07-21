package br.com.pvmga.roomreservation.repository;

import br.com.pvmga.roomreservation.domain.Reserva;

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
}