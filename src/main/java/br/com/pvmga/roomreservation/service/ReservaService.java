package br.com.pvmga.roomreservation.service;

import br.com.pvmga.roomreservation.domain.Reserva;
import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.domain.Usuario;
import br.com.pvmga.roomreservation.repository.ReservaRepository;
import br.com.pvmga.roomreservation.repository.SalaRepository;
import br.com.pvmga.roomreservation.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;

    public ReservaService(
            SalaRepository salaRepository,
            UsuarioRepository usuarioRepository,
            ReservaRepository reservaRepository
    ) {
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva cadastrarReserva(
            Long salaId,
            Long usuarioId,
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

        validarIdUsuario(usuarioId);

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        // Usuários desativados não podem criar reservas.
        validarUsuarioAtivo(usuario);

        Reserva reserva = new Reserva(
                sala,
                usuario,
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

    public List<Reserva> listarReservas() {

        return reservaRepository.listar();
    }

    public Reserva cancelarReserva(Long reservaId) {
        Reserva reserva = buscarReservaPorId(reservaId);

        reserva.cancelar();

        return reserva;
    }

    public Reserva reativarReserva(Long reservaId) {
        Reserva reserva = buscarReservaPorId(reservaId);

        // A reserva só pode voltar a ficar ativa se o usuário estiver ativo.
        validarUsuarioAtivo(reserva.getUsuario());

        boolean existeConflito = reservaRepository.existeConflito(
                reserva.getSala().getId(),
                reserva.getInicio(),
                reserva.getFim()
        );

        if (existeConflito) {
            throw new IllegalStateException(
                    "Não é possível reativar a reserva porque existe conflito de horário."
            );
        }

        reserva.reativar();

        return reserva;
    }

    public Reserva buscarReservaPorId(Long reservaId) {
        if (reservaId == null || reservaId <= 0) {
            throw new IllegalArgumentException(
                    "O ID da reserva deve ser maior que zero."
            );
        }

        Reserva reserva = reservaRepository.buscarPorId(reservaId);

        if (reserva == null) {
            throw new IllegalArgumentException(
                    "Reserva não encontrada."
            );
        }

        return reserva;
    }

    public List<Reserva> buscarReservasPorSala(Long salaId) {

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

        return reservaRepository.buscarPorSala(salaId);
    }

    public List<Reserva> buscarReservasPorUsuario(Long usuarioId) {
        validarIdUsuario(usuarioId);

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return reservaRepository.buscarPorUsuario(usuarioId);
    }

    public List<Reserva> buscarReservasPorPeriodo(
            LocalDateTime inicio,
            LocalDateTime fim
    ) {
        Reserva.validarPeriodo(inicio, fim);

        return reservaRepository.buscarPorPeriodo(inicio, fim);
    }

    private void validarIdUsuario(Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }
    }

    private void validarUsuarioAtivo(Usuario usuario) {
        if (!usuario.getAtivo()) {
            throw new IllegalStateException(
                    "Não é possível reservar para um usuário desativado."
            );
        }
    }

}
