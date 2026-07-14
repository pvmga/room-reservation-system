package br.com.pvmga.roomreservation.service;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.SalaRepository;

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

}
