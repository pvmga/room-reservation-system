package br.com.pvmga.roomreservation.service;

import br.com.pvmga.roomreservation.domain.Usuario;
import br.com.pvmga.roomreservation.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        if (usuarioRepository == null) {
            throw new IllegalArgumentException(
                    "O repositório de usuários é obrigatório."
            );
        }

        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário é obrigatório.");
        }

        validarEmailDuplicado(usuario.getEmail(), null);

        return usuarioRepository.salvar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listar();
    }

    public Usuario buscarPorId(Long id) {
        validarId(id);

        Usuario usuario = usuarioRepository.buscarPorId(id);
        validarUsuarioEncontrado(usuario);

        return usuario;
    }

    public Usuario buscarPorNome(String nome) {
        Usuario usuario = usuarioRepository.buscarPorNome(
                Usuario.normalizarNome(nome)
        );
        validarUsuarioEncontrado(usuario);

        return usuario;
    }

    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.buscarPorEmail(
                Usuario.normalizarEmail(email)
        );
        validarUsuarioEncontrado(usuario);

        return usuario;
    }

    public Usuario alterarUsuario(
            Long id,
            String nome,
            String email
    ) {
        Usuario usuario = buscarPorId(id);

        String emailNormalizado = Usuario.normalizarEmail(email);
        validarEmailDuplicado(emailNormalizado, id);

        usuario.alterarDados(nome, email);

        return usuario;
    }

    public Usuario desativarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.desativar();

        return usuario;
    }

    public Usuario reativarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.reativar();

        return usuario;
    }

    private void validarEmailDuplicado(
            String email,
            Long usuarioId
    ) {
        if (usuarioRepository.existeOutroUsuarioComEmail(email, usuarioId)) {
            throw new IllegalArgumentException(
                    "Já existe um usuário cadastrado com esse e-mail."
            );
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }
    }

    private void validarUsuarioEncontrado(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }
}
