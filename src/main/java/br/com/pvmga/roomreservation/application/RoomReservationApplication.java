package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Reserva;
import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.ReservaRepository;
import br.com.pvmga.roomreservation.repository.SalaRepository;
import br.com.pvmga.roomreservation.repository.filter.SalaFiltro;
import br.com.pvmga.roomreservation.service.ReservaService;
import br.com.pvmga.roomreservation.service.SalaService;

import java.time.LocalDateTime;
import java.util.List;

public class RoomReservationApplication {
    public static void main(String[] args) {

        SalaRepository salaRepository = new SalaRepository();
        SalaService salaService = new SalaService(salaRepository);

        Sala salaCadastrada =
                salaService.cadastrarSala(new Sala("Sala Azul", 20));
        //System.out.println(salaCadastrada);

        Sala salaCadastradaTwo =
                salaService.cadastrarSala(new Sala("Sala Verde", 21));
        //System.out.println(salaCadastradaTwo);


        Sala salaCadastradaThree =
                salaService.cadastrarSala(new Sala("Sala Roxa", 21));
        //System.out.println(salaCadastradaThree);


        // buscar por id
        Sala salaBuscadaPorId = salaService.buscarPorId(22L);

        if (salaBuscadaPorId == null) {
            System.out.println("Sala não localizada");
        } else {
            //System.out.println(salaBuscadaPorId);
        }

        // buscar por nome
        Sala salaBuscadaPorNome = salaService.buscarPorNome("Sala Verde");

        if (salaBuscadaPorNome == null) {
            System.out.println("Sala não localizada");
        }  else {
            //System.out.println(salaBuscadaPorNome);
        }


        // // bloco de teste
        Sala salaDesativada = salaService.desativarSala(1L);
        //System.out.println("Sala desativada: " + salaDesativada);
        Sala salaDesativada2 = salaService.desativarSala(2L);
        //System.out.println("Sala desativada: " + salaDesativada2 );

        // bloco de teste
        salaService.alterarSala(3L, "Sala Executiva", 20);
        salaService.alterarSala(3L, "Sala Executiva", 22);
        salaService.alterarSala(3L, "Sala Rosa", 19);

        // bloco de teste
        Sala salaReativada = salaService.reativarSala(1L);
        //System.out.println("Sala reativada: " + salaReativada);

        Sala salaReativadaNovamente = salaService.reativarSala(2L);
//        System.out.println(salaReativadaNovamente);


        // listar todas as salas
        List<Sala> salas = salaService.listarSalas();
        //salas.clear();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada");
        } else {
            salas.forEach(System.out::println);
        }

        // bloco de teste
        List<Sala> buscarSalasDisponiveisPorCapacidade =
                salaService.buscarSalasDisponiveisPorCapacidade(19);
        System.out.println(buscarSalasDisponiveisPorCapacidade);

        List<Sala> buscarSalasDisponiveisPorIntervaloDeCapacidade =
                salaService.buscarSalasDisponiveisPorIntervaloDeCapacidade(20, 21);
        System.out.println(buscarSalasDisponiveisPorIntervaloDeCapacidade);

        // bloco de teste
        // Todas as salas
        //new SalaFiltro(null, null, null, null);
        // Somente ativas
        //new SalaFiltro(null, null, null, true);
        // Nome parcial
        //new SalaFiltro("reunião", null, null, null);
        SalaFiltro filtro = new SalaFiltro(
                null,
                10,
                30,
                true
        );
        List<Sala> salasFiltradas = salaService.buscarSalas(filtro);

        System.out.println(salasFiltradas);

        ReservaRepository reservaRepository = new ReservaRepository();
        ReservaService reservaService = new ReservaService(salaRepository, reservaRepository);

        Reserva reserva = reservaService.cadastrarReserva(
                salaCadastrada.getId(),
                LocalDateTime.of(2026, 7, 21, 9, 0),
                LocalDateTime.of(2026, 7, 21, 10, 0)
        );

        Reserva reserva2 = reservaService.cadastrarReserva(
                salaCadastrada.getId(),
                LocalDateTime.of(2026, 7, 21, 9, 0),
                LocalDateTime.of(2026, 7, 21, 10, 0)
        );

    }
}
