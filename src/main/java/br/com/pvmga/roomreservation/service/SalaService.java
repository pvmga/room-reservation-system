package br.com.pvmga.roomreservation.service;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.SalaRepository;

import java.util.List;

public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala cadastrarSala(Sala sala) {
        Sala salaExistente = salaRepository.buscarPorNome(sala.getNome());

        if (salaExistente != null) {
            return null;
        }

        return salaRepository.salvar(sala);
    }

    public List<Sala> listarSalas() {

        return salaRepository.listar();
    }

    public Sala buscarPorId(Long id) {
        return salaRepository.buscarPorId(id);
    }

    public Sala buscarPorNome(String nome) {
        return salaRepository.buscarPorNome(nome);
    }

    public Sala desativarSala(Long id) {
        return salaRepository.desativarSala(id);
    }

}
