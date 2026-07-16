package br.com.pvmga.roomreservation.repository;

import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.repository.filter.SalaFiltro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalaRepository {

    private Long proximoId = 1L;

    private final List<Sala> salas = new ArrayList<>();

    public Sala salvar(Sala sala) {
        sala.atribuirId(proximoId);
        this.proximoId++;
        salas.add(sala);

        return sala;
    }

    // Isso permite que uma classe externa modifique diretamente a lista:
    // salaService.listarSalas().clear();
//    public List<Sala> listar() {
//        return salas;
//    }

    public List<Sala> listar() {
        //return new ArrayList<>(salas);
        return List.copyOf(salas);
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

//    public List<Sala> buscarSalasDisponiveisPorCapacidade(
//            Integer capacidadeMinima
//    ) {
//        return salas.stream()
//                .filter(Sala::getAtiva)
//                .filter(sala -> sala.getCapacidade() >= capacidadeMinima)
//                .sorted(Comparator.comparing(Sala::getCapacidade))
//                .toList();
//    }
//
//    public List<Sala> buscarSalasDisponiveisPorIntervaloDeCapacidade(
//            Integer capacidadeMinima,
//            Integer capacidadeMaxima
//    ) {
//        return salas.stream()
//                .filter(Sala::getAtiva)
//                .filter(sala -> sala.getCapacidade() >= capacidadeMinima && sala.getCapacidade() <= capacidadeMaxima)
//                .sorted(Comparator.comparing(Sala::getCapacidade))
//                .toList();
//    }

    public List<Sala> buscarSalas(
            SalaFiltro filtro
    ) {

        String nomePesquisa = filtro.getNome() == null ? null : filtro.getNome().toLowerCase();

        return salas.stream()
                .filter(sala -> filtro.getNome() == null
                        || nomePesquisa.isBlank()
                        || sala.getNome()
                                .toLowerCase()
                                .contains(nomePesquisa)
                )
                .filter(sala ->
                        filtro.getAtiva() == null
                                || sala.getAtiva() == filtro.getAtiva()
                )
                .filter(sala ->
                        filtro.getCapacidadeMinima() == null
                                || sala.getCapacidade() >= filtro.getCapacidadeMinima()
                )
                .filter(sala ->
                        filtro.getCapacidadeMaxima() == null
                                || sala.getCapacidade() <= filtro.getCapacidadeMaxima()
                )
                .sorted(Comparator.comparing(Sala::getCapacidade))
                .toList();
    }
}
