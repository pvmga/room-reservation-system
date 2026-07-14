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

        // listar todas as salas
        List<Sala> salas = salaService.listarSalas();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada");
        } else {
            salas.forEach(System.out::println);
        }

        // buscar por id
        Sala salaBuscadaPorId = salaService.buscarPorId(22L);

        if (salaBuscadaPorId == null) {
            System.out.println("Sala não localizada");
        } else {
            System.out.println(salaBuscadaPorId);
        }

        // buscar por nome
        Sala SalaBuscadaPorNome = salaService.buscarPorNome("Sala Verde");

        if (SalaBuscadaPorNome == null) {
            System.out.println("Sala cadastrada!");
        }  else {
            System.out.println(SalaBuscadaPorNome);
        }

        // desativar por id
        Sala salaDesativada = salaRepository.desativarSala(1L);
        System.out.println(salaDesativada);

    }
}
