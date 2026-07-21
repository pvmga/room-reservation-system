package br.com.pvmga.roomreservation.application;

import br.com.pvmga.roomreservation.domain.Reserva;
import br.com.pvmga.roomreservation.domain.Sala;
import br.com.pvmga.roomreservation.domain.Usuario;
import br.com.pvmga.roomreservation.repository.ReservaRepository;
import br.com.pvmga.roomreservation.repository.SalaRepository;
import br.com.pvmga.roomreservation.repository.UsuarioRepository;
import br.com.pvmga.roomreservation.repository.filter.SalaFiltro;
import br.com.pvmga.roomreservation.service.ReservaService;
import br.com.pvmga.roomreservation.service.SalaService;
import br.com.pvmga.roomreservation.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;

public class RoomReservationApplication {
    public static void main(String[] args) {

        SalaRepository salaRepository = new SalaRepository();
        SalaService salaService = new SalaService(salaRepository);

        Sala salaCadastrada =
                salaService.cadastrarSala(new Sala("Sala Azul", 20));
        //System.out.println(salaCadastrada);

        Sala salaCadastradaTwo =
                salaService.cadastrarSala(new Sala("Sala Verde", 21));
        //System.out.println(salaCadastradaTwo);


        Sala salaCadastradaThree =
                salaService.cadastrarSala(new Sala("Sala Roxa", 21));
        //System.out.println(salaCadastradaThree);


        // buscar por id
        Sala salaBuscadaPorId = salaService.buscarPorId(22L);

        if (salaBuscadaPorId == null) {
            System.out.println("Sala não localizada");
        } else {
            //System.out.println(salaBuscadaPorId);
        }

        // buscar por nome
        Sala salaBuscadaPorNome = salaService.buscarPorNome("Sala Verde");

        if (salaBuscadaPorNome == null) {
            System.out.println("Sala não localizada");
        }  else {
            //System.out.println(salaBuscadaPorNome);
        }


        // // bloco de teste
        Sala salaDesativada = salaService.desativarSala(1L);
        //System.out.println("Sala desativada: " + salaDesativada);
        Sala salaDesativada2 = salaService.desativarSala(2L);
        //System.out.println("Sala desativada: " + salaDesativada2 );

        // bloco de teste
        salaService.alterarSala(3L, "Sala Executiva", 20);
        salaService.alterarSala(3L, "Sala Executiva", 22);
        salaService.alterarSala(3L, "Sala Rosa", 19);

        // bloco de teste
        Sala salaReativada = salaService.reativarSala(1L);
        //System.out.println("Sala reativada: " + salaReativada);

        Sala salaReativadaNovamente = salaService.reativarSala(2L);
//        System.out.println(salaReativadaNovamente);


        // listar todas as salas
        List<Sala> salas = salaService.listarSalas();
        //salas.clear();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada");
        } else {
            salas.forEach(System.out::println);
        }

        // bloco de teste
        List<Sala> buscarSalasDisponiveisPorCapacidade =
                salaService.buscarSalasDisponiveisPorCapacidade(19);
        System.out.println(buscarSalasDisponiveisPorCapacidade);

        List<Sala> buscarSalasDisponiveisPorIntervaloDeCapacidade =
                salaService.buscarSalasDisponiveisPorIntervaloDeCapacidade(20, 21);
        System.out.println(buscarSalasDisponiveisPorIntervaloDeCapacidade);

        // bloco de teste FILTRAR

        // Todas as salas
        //new SalaFiltro(null, null, null, null);
        // Somente ativas
        //new SalaFiltro(null, null, null, true);
        // Nome parcial
        //new SalaFiltro("reunião", null, null, null);
        SalaFiltro filtro = new SalaFiltro(
                null,
                10,
                30,
                true
        );
        List<Sala> salasFiltradas = salaService.buscarSalas(filtro);

        System.out.println(salasFiltradas);

        // bloco de teste CADASTRAR RESERVA
        ReservaRepository reservaRepository = new ReservaRepository();
        ReservaService reservaService = new ReservaService(salaRepository, reservaRepository);
//
//        Reserva reserva = reservaService.cadastrarReserva(
//                salaCadastrada.getId(),
//                LocalDateTime.of(2026, 7, 21, 9, 0),
//                LocalDateTime.of(2026, 7, 21, 10, 0)
//        );
//
//        Reserva reserva2 = reservaService.cadastrarReserva(
//                salaCadastrada.getId(),
//                LocalDateTime.of(2026, 8, 21, 9, 0),
//                LocalDateTime.of(2026, 8, 21, 10, 0)
//        );

        // bloco de teste CANCELAR
//        Reserva reservaCancelada = reservaService.cancelarReserva(reserva.getId());
//        System.out.println(reservaCancelada);

        // bloco de teste CANCELAR DEPOIS REATIVAR
        Reserva reserva1 = reservaService.cadastrarReserva(
                salaCadastrada.getId(),
                LocalDateTime.of(2026, 7, 21, 9, 0),
                LocalDateTime.of(2026, 7, 21, 10, 0)
        );

        reservaService.cancelarReserva(reserva1.getId());

        Reserva reserva3 = reservaService.cadastrarReserva(
                salaCadastrada.getId(),
                LocalDateTime.of(2026, 9, 21, 9, 0),
                LocalDateTime.of(2026, 9, 21, 10, 0)
        );

        reservaService.reativarReserva(reserva1.getId());

        // bloco de teste BUSCAR POR ID
        Reserva reservaEncontrada =
                reservaService.buscarReservaPorId(reserva1.getId());

        System.out.println("Reserva Filtrada: " + reservaEncontrada);

        // teste buscar Reserva Por Sala
        System.out.println(
                reservaService.buscarReservasPorSala(salaCadastrada.getId())
        );


        // bloco de teste BUSCAR POR PERIODO
        System.out.println("Reservas entre 09:30 e 11:30:");
        reservaService.buscarReservasPorPeriodo(
                LocalDateTime.of(2026, 7, 21, 9, 30),
                LocalDateTime.of(2026, 7, 21, 11, 30)
        ).forEach(System.out::println);

        System.out.println(reservaService.listarReservas());

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        System.out.println("======================================");
        System.out.println("TESTE 01 - Cadastro");
        System.out.println("======================================");

        Usuario usuario1 = new Usuario(
                "  Paulo Vinicius  ",
                "  PAULO@EMAIL.COM  "
        );

        Usuario usuario2 = new Usuario(
                "Maria",
                "maria@email.com"
        );

        Usuario usuario3 = new Usuario(
                "Paulo",
                "outro.paulo@email.com"
        );

        usuarioService.cadastrarUsuario(usuario1);
        usuarioService.cadastrarUsuario(usuario2);
        usuarioService.cadastrarUsuario(usuario3);

        usuarioService.listarUsuarios()
                .forEach(System.out::println);

        System.out.println("\n======================================");
        System.out.println("TESTE 02 - Buscar por ID");
        System.out.println("======================================");

        System.out.println(
                usuarioService.buscarPorId(usuario1.getId())
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 03 - Buscar por Nome");
        System.out.println("======================================");

        System.out.println(
                usuarioService.buscarPorNome("  maria ")
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 04 - Buscar por Email");
        System.out.println("======================================");

        System.out.println(
                usuarioService.buscarPorEmail(" PAULO@EMAIL.COM ")
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 05 - Alteração");
        System.out.println("======================================");

        System.out.println(
                usuarioService.alterarUsuario(
                        usuario1.getId(),
                        " Paulo Vinicius Martim ",
                        " PAULO.VINICIUS@EMAIL.COM "
                )
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 06 - Desativação");
        System.out.println("======================================");

        System.out.println(
                usuarioService.desativarUsuario(
                        usuario2.getId()
                )
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 07 - Reativação");
        System.out.println("======================================");

        System.out.println(
                usuarioService.reativarUsuario(
                        usuario2.getId()
                )
        );

        System.out.println("\n======================================");
        System.out.println("TESTE 08 - Usuário Nulo");
        System.out.println("======================================");

        try {
            usuarioService.cadastrarUsuario(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 09 - Nome Obrigatório");
        System.out.println("======================================");

        try {
            usuarioService.cadastrarUsuario(
                    new Usuario(
                            "   ",
                            "teste@email.com"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 10 - Email Obrigatório");
        System.out.println("======================================");

        try {
            usuarioService.cadastrarUsuario(
                    new Usuario(
                            "Teste",
                            "   "
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 11 - Email Inválido");
        System.out.println("======================================");

        try {
            usuarioService.cadastrarUsuario(
                    new Usuario(
                            "Teste",
                            "email-invalido"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 12 - Email Duplicado no Cadastro");
        System.out.println("======================================");

        try {
            usuarioService.cadastrarUsuario(
                    new Usuario(
                            "Outro Paulo",
                            "PAULO.VINICIUS@EMAIL.COM"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 13 - Email Duplicado na Alteração");
        System.out.println("======================================");

        try {
            usuarioService.alterarUsuario(
                    usuario3.getId(),
                    "Paulo Alterado",
                    "maria@email.com"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 14 - Buscar ID inexistente");
        System.out.println("======================================");

        try {
            usuarioService.buscarPorId(999L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 15 - Buscar ID inválido");
        System.out.println("======================================");

        try {
            usuarioService.buscarPorId(0L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 16 - Alterar Usuário Desativado");
        System.out.println("======================================");

        try {

            usuarioService.desativarUsuario(
                    usuario1.getId()
            );

            usuarioService.alterarUsuario(
                    usuario1.getId(),
                    "Novo Nome",
                    "novo@email.com"
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 17 - Desativar Duas Vezes");
        System.out.println("======================================");

        try {

            usuarioService.desativarUsuario(
                    usuario3.getId()
            );

            usuarioService.desativarUsuario(
                    usuario3.getId()
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("TESTE 18 - Reativar Usuário Já Ativo");
        System.out.println("======================================");

        try {

            usuarioService.reativarUsuario(
                    usuario2.getId()
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n======================================");
        System.out.println("FIM DOS TESTES");
        System.out.println("======================================");

    }
}
