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
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        ReservaRepository reservaRepository = new ReservaRepository();

        SalaService salaService = new SalaService(salaRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        ReservaService reservaService = new ReservaService(
                salaRepository,
                usuarioRepository,
                reservaRepository
        );

        Sala salaParaReserva = demonstrarSalas(salaService);
        Usuario usuarioParaReserva = demonstrarUsuarios(usuarioService);
        demonstrarReservas(
                reservaService,
                salaParaReserva,
                usuarioParaReserva
        );
    }

    private static Sala demonstrarSalas(SalaService salaService) {
        exibirModulo("SALAS");

        exibirTeste(1, "Cadastro");
        Sala salaAzul = salaService.cadastrarSala(
                new Sala("Sala Azul", 20)
        );
        Sala salaVerde = salaService.cadastrarSala(
                new Sala("Sala Verde", 21)
        );
        Sala salaRoxa = salaService.cadastrarSala(
                new Sala("Sala Roxa", 21)
        );
        exibirLista(salaService.listarSalas());

        exibirTeste(2, "Busca por ID");
        System.out.println("Resultado: " + salaService.buscarPorId(salaAzul.getId()));

        exibirTeste(3, "Busca por nome");
        System.out.println("Resultado: " + salaService.buscarPorNome("Sala Verde"));

        exibirTeste(4, "Alteração");
        System.out.println(
                "Sala alterada: "
                        + salaService.alterarSala(
                        salaRoxa.getId(),
                        "Sala Rosa",
                        19
                )
        );

        exibirTeste(5, "Desativação e reativação");
        System.out.println(
                "Sala desativada: "
                        + salaService.desativarSala(salaVerde.getId())
        );
        System.out.println(
                "Sala reativada: "
                        + salaService.reativarSala(salaVerde.getId())
        );

        exibirTeste(6, "Busca por capacidade mínima");
        exibirLista(
                salaService.buscarSalasDisponiveisPorCapacidade(19)
        );

        exibirTeste(7, "Busca por intervalo de capacidade");
        exibirLista(
                salaService.buscarSalasDisponiveisPorIntervaloDeCapacidade(
                        20,
                        21
                )
        );

        exibirTeste(8, "Busca com filtro");
        SalaFiltro filtro = new SalaFiltro(null, 10, 30, true);
        exibirLista(salaService.buscarSalas(filtro));

        return salaAzul;
    }

    private static Usuario demonstrarUsuarios(
            UsuarioService usuarioService
    ) {
        exibirModulo("USUÁRIOS");

        exibirTeste(1, "Cadastro");
        Usuario usuarioReserva = usuarioService.cadastrarUsuario(
                new Usuario(
                        "Responsável pela reserva",
                        "reserva@email.com"
                )
        );
        Usuario paulo = usuarioService.cadastrarUsuario(
                new Usuario("  Paulo Vinicius  ", "  PAULO@EMAIL.COM  ")
        );
        Usuario maria = usuarioService.cadastrarUsuario(
                new Usuario("Maria", "maria@email.com")
        );
        exibirLista(usuarioService.listarUsuarios());

        exibirTeste(2, "Busca por ID");
        System.out.println("Resultado: " + usuarioService.buscarPorId(paulo.getId()));

        exibirTeste(3, "Busca por nome");
        System.out.println("Resultado: " + usuarioService.buscarPorNome("  maria "));

        exibirTeste(4, "Busca por e-mail");
        System.out.println(
                "Resultado: "
                        + usuarioService.buscarPorEmail(" PAULO@EMAIL.COM ")
        );

        exibirTeste(5, "Alteração");
        System.out.println(
                "Usuário alterado: "
                        + usuarioService.alterarUsuario(
                        paulo.getId(),
                        "Paulo Vinicius Martim",
                        "paulo.vinicius@email.com"
                )
        );

        exibirTeste(6, "Desativação e reativação");
        System.out.println(
                "Usuário desativado: "
                        + usuarioService.desativarUsuario(maria.getId())
        );
        System.out.println(
                "Usuário reativado: "
                        + usuarioService.reativarUsuario(maria.getId())
        );

        exibirTeste(7, "Validações");
        executarComErro(
                "Cadastro sem nome",
                () -> usuarioService.cadastrarUsuario(
                        new Usuario("   ", "teste@email.com")
                )
        );
        executarComErro(
                "E-mail duplicado",
                () -> usuarioService.cadastrarUsuario(
                        new Usuario("Outro Paulo", "paulo.vinicius@email.com")
                )
        );
        executarComErro(
                "ID inexistente",
                () -> usuarioService.buscarPorId(999L)
        );

        return usuarioReserva;
    }

    private static void demonstrarReservas(
            ReservaService reservaService,
            Sala sala,
            Usuario usuario
    ) {
        exibirModulo("RESERVAS");

        exibirTeste(1, "Cadastro");
        Reserva reservaJulho = reservaService.cadastrarReserva(
                sala.getId(),
                usuario.getId(),
                LocalDateTime.of(2026, 7, 21, 9, 0),
                LocalDateTime.of(2026, 7, 21, 10, 0)
        );
        Reserva reservaSetembro = reservaService.cadastrarReserva(
                sala.getId(),
                usuario.getId(),
                LocalDateTime.of(2026, 9, 21, 9, 0),
                LocalDateTime.of(2026, 9, 21, 10, 0)
        );
        System.out.println("Reserva cadastrada: " + reservaJulho);
        System.out.println("Reserva cadastrada: " + reservaSetembro);

        exibirTeste(2, "Cancelamento e reativação");
        System.out.println(
                "Reserva cancelada: "
                        + reservaService.cancelarReserva(reservaJulho.getId())
        );
        System.out.println(
                "Reserva reativada: "
                        + reservaService.reativarReserva(reservaJulho.getId())
        );

        exibirTeste(3, "Busca por ID");
        System.out.println(
                "Resultado: "
                        + reservaService.buscarReservaPorId(reservaJulho.getId())
        );

        exibirTeste(4, "Reservas por sala");
        exibirLista(reservaService.buscarReservasPorSala(sala.getId()));

        exibirTeste(5, "Reservas por usuário");
        exibirLista(reservaService.buscarReservasPorUsuario(usuario.getId()));

        exibirTeste(6, "Reservas por período");
        exibirLista(
                reservaService.buscarReservasPorPeriodo(
                        LocalDateTime.of(2026, 7, 21, 9, 30),
                        LocalDateTime.of(2026, 7, 21, 11, 30)
                )
        );

        exibirTeste(7, "Todas as reservas");
        exibirLista(reservaService.listarReservas());
    }

    private static void exibirModulo(String nome) {
        System.out.println("\n==================================================");
        System.out.println("MÓDULO: " + nome);
        System.out.println("==================================================");
    }

    private static void exibirTeste(int numero, String descricao) {
        System.out.printf("%n--- TESTE %02d: %s ---%n", numero, descricao);
    }

    private static void exibirLista(List<?> itens) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        itens.forEach(item -> System.out.println("- " + item));
    }

    private static void executarComErro(String descricao, Runnable teste) {
        try {
            teste.run();
        } catch (Exception e) {
            System.out.println(descricao + ": " + e.getMessage());
        }
    }
}
