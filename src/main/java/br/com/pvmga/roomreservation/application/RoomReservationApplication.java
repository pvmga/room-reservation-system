package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Sala;

import java.util.ArrayList;
import java.util.List;

public class RoomReservationApplication {
    public static void main(String[] args) {
        List<Sala> salas = new ArrayList<>();

        // sala 1
        Sala sala = new Sala("Sala Azul", 20);
        salas.add(sala);

        // sala 2
        Sala sala2 = new Sala("Sala Verde", 21);
        salas.add(sala2);

        System.out.println(salas);

    }
}
