package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Sala;

import java.util.ArrayList;
import java.util.List;

public class RoomReservationApplication {
    public static void main(String[] args) {
        List<Sala> salas = new ArrayList<>();

        long proximoId = 1L;

        Sala salaAzul = new Sala(proximoId++,"Sala Azul", 20);
        Sala salaVerde = new Sala(proximoId++,"Sala Verde", 21);

        salas.add(salaAzul);
        salas.add(salaVerde);

        System.out.println(salas);

    }
}
