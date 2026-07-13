package br.com.pvmga.roomreservation.repository;

import br.com.pvmga.roomreservation.domain.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaRepository {

    private final List<Sala> salas = new ArrayList<>();

    public void salvar(Sala sala) {
        salas.add(sala);
    }

    public List<Sala> listar() {
        return salas;
    }

    public Sala buscarPorId(Long id) {
        return salas.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Sala buscarPorNome(String nome) {
        return salas.stream()
                .filter(sala -> sala.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Sala desativarSala(Long id) {
        Sala sala = buscarPorId(id);

        if (sala == null) {
            return null;
        }

        sala.desativar();

        return sala;
    }

}
