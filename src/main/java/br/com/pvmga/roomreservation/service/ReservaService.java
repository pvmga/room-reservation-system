package br.com.pvmga.roomreservation.service;

import br.com.pvmga.roomreservation.domain.Reserva;
import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.ReservaRepository;
import br.com.pvmga.roomreservation.repository.SalaRepository;

import java.time.LocalDateTime;

public class ReservaService {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public ReservaService(
            SalaRepository salaRepository,
            ReservaRepository reservaRepository
    ) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva cadastrarReserva(
            Long salaId,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        if (salaId == null || salaId <= 0) {
            throw new IllegalArgumentException(
                    "O ID da sala deve ser maior que zero."
            );
        }

        Sala sala = salaRepository.buscarPorId(salaId);

        if (sala == null) {
            throw new IllegalArgumentException(
                    "Sala não encontrada."
            );
        }

        if (!sala.getAtiva()) {
            throw new IllegalStateException(
                    "Não é possível reservar uma sala desativada."
            );
        }

        Reserva reserva = new Reserva(
                sala,
                inicio,
                fim
        );

        if (reservaRepository.existeConflito(salaId, inicio, fim)) {
            throw new IllegalStateException(
                    "Já existe uma reserva ativa para esta sala no período informado."
            );
        }

        return reservaRepository.salvar(reserva);
    }
}