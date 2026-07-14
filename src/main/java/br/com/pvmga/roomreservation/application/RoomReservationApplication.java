package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.SalaRepository;
import br.com.pvmga.roomreservation.service.SalaService;

public class RoomReservationApplication {
    public static void main(String[] args) {

        SalaRepository salaRepository = new SalaRepository();
        SalaService salaService = new SalaService(salaRepository);

        Sala salaCadastrada =
                salaService.cadastrarSala(new Sala("Sala Azul", 20));

        if (salaCadastrada == null) {
            System.out.println("Sala já cadastrada!");
        } else {
            System.out.println("Sala cadastrada com sucesso!");
            System.out.println(salaCadastrada);
        }

        Sala salaCadastradaTwo =
                salaService.cadastrarSala(new Sala("Sala Azul", 21));

        if (salaCadastradaTwo == null) {
            System.out.println("Sala já cadastrada!");
        } else {
            System.out.println("Sala cadastrada com sucesso!");
            System.out.println(salaCadastradaTwo);
        }

        Sala salaCadastradaThre =
                salaService.cadastrarSala(new Sala("Sala Verde", 21));

        if (salaCadastradaThre == null) {
            System.out.println("Sala já cadastrada!");
        } else {
            System.out.println("Sala cadastrada com sucesso!");
            System.out.println(salaCadastradaThre);
        }

//        salaRepository.salvar(new Sala(proximoId++, "Sala Azul", 20));
//        salaRepository.salvar(new Sala(proximoId++, "Sala Verde", 21));

//        salaRepository
//                .listar()
//                .forEach(System.out::println);
//
//        // buscar por id
//        Sala salaBuscadaPorId = salaRepository.buscarPorId(1L);
//        System.out.println(salaBuscadaPorId);
//
//        // buscar por nome
//        Sala salaBuscadaPorNome = salaRepository.buscarPorNome("Sala Verde");
//        System.out.println(salaBuscadaPorNome);
//
//        // desativar por id
//        Sala salaDesativada = salaRepository.desativarSala(2L);
//        System.out.println(salaDesativada);
    }
}
