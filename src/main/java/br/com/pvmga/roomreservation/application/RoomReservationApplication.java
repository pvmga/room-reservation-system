package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.SalaRepository;

import java.util.ArrayList;
import java.util.List;

public class RoomReservationApplication {
    public static void main(String[] args) {

        SalaRepository salaRepository = new SalaRepository();

        long proximoId = 1L;

        salaRepository.salvar(new Sala(proximoId++, "Sala Azul", 20));
        salaRepository.salvar(new Sala(proximoId++, "Sala Verde", 21));

        salaRepository
                .listar()
                .forEach(System.out::println);

        // buscar por id
        Sala salaBuscadaPorId = salaRepository.buscarPorId(1L);
        System.out.println(salaBuscadaPorId);

        // buscar por nome
        Sala salaBuscadaPorNome = salaRepository.buscarPorNome("Sala Verde");
        System.out.println(salaBuscadaPorNome);

        // desativar por id
        Sala salaDesativada = salaRepository.desativarSala(2L);
        System.out.println(salaDesativada);
    }
}
