package br.com.pvmga.roomreservation.repository;

import br.com.pvmga.roomreservation.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private Long proximoId = 1L;
    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario salvar(Usuario usuario) {
        usuario.atribuirId(proximoId);
        proximoId++;
        usuarios.add(usuario);

        return usuario;
    }

    public List<Usuario> listar() {
        return List.copyOf(usuarios);
    }

    public Usuario buscarPorId(Long id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Usuario buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean existeOutroUsuarioComEmail(String email, Long idIgnorado) {
        return usuarios.stream()
                .anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email)
                        && !usuario.getId().equals(idIgnorado));
    }
}
