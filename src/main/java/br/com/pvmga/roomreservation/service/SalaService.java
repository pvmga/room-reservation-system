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
            throw new IllegalArgumentException(
                    "Já existe uma sala cadastrada com esse nome."
            );
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
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }

        Sala sala = salaRepository.buscarPorId(id);

        if (sala == null) {
            throw new IllegalArgumentException("Sala não encontrada.");
        }

        sala.desativar();

        return sala;
    }

    public Sala reativarSala(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve ser maior que zero."
            );
        }

        Sala sala = salaRepository.buscarPorId(id);

        if (sala == null) {
            throw new IllegalArgumentException(
                    "Não foi encontrada uma sala para o ID informado."
            );
        }

        sala.reativar();

        return sala;
    }

    public Sala alterarSala(Long id, String nome, Integer capacidade) {

        if  (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve ser maior que zero."
            );
        }

        Sala sala = salaRepository.buscarPorId(id);
        if (sala == null) {
            throw new IllegalArgumentException(
                    "Não foi encontrada uma sala para o ID informado."
            );
        }

        Sala salaComMesmoNome = salaRepository.buscarPorNome(nome);
        // validação do id representa isso: O nome pertence à própria sala que está sendo alterada ou a outra sala?
        if (salaComMesmoNome != null && !salaComMesmoNome.getId().equals(id)) {
            throw new IllegalArgumentException(
                    "Já existe outra sala cadastrada com esse nome."
            );
        }

        sala.alterarDados(nome, capacidade);

        return sala;
    }

    public List<Sala> buscarSalasDisponiveisPorCapacidade(
            Integer capacidadeMinima
    ) {
        if (capacidadeMinima == null || capacidadeMinima <= 0) {
            throw new IllegalArgumentException("A capacidade mínima deve ser maior que zero.");
        }

        return salaRepository.buscarSalasDisponiveisPorCapacidade(capacidadeMinima);
    }

    public List<Sala> buscarSalasDisponiveisPorIntervaloDeCapacidade(
            Integer capacidadeMinima,
            Integer capacidadeMaxima
    ) {
        if (capacidadeMinima == null || capacidadeMinima <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade mínima deve ser maior que zero."
            );
        }

        if (capacidadeMaxima == null || capacidadeMaxima <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade máxima deve ser maior que zero."
            );
        }

        if (capacidadeMinima > capacidadeMaxima) {
            throw new IllegalArgumentException(
                    "A capacidade mínima não pode ser maior que a capacidade máxima."
            );
        }

        return salaRepository.buscarSalasDisponiveisPorIntervaloDeCapacidade(
                capacidadeMinima,
                capacidadeMaxima
        );
    }

}
