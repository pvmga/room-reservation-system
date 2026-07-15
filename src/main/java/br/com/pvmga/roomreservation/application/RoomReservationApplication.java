package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.SalaRepository;
import br.com.pvmga.roomreservation.service.SalaService;

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

        List<Sala> buscarSalasDisponiveisPorCapacidade =
                salaService.buscarSalasDisponiveisPorCapacidade(19);
        System.out.println(buscarSalasDisponiveisPorCapacidade);

        List<Sala> buscarSalasDisponiveisPorIntervaloDeCapacidade =
                salaService.buscarSalasDisponiveisPorIntervaloDeCapacidade(20, 21);
        System.out.println(buscarSalasDisponiveisPorIntervaloDeCapacidade);
    }
}
