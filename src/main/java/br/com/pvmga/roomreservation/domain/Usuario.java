package br.com.pvmga.roomreservation.domain;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    public Usuario(String nome, String email) {
        this.nome = normalizarNome(nome);
        this.email = normalizarEmail(email);
        this.ativo = true;
    }

    public void atribuirId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }

        if (this.id != null) {
            throw new IllegalStateException(
                    "O usuário já possui um ID."
            );
        }

        this.id = id;
    }

    public void alterarDados(String nome, String email) {
        if (!ativo) {
            throw new IllegalStateException(
                    "Não é possível alterar um usuário desativado."
            );
        }

        this.nome = normalizarNome(nome);
        this.email = normalizarEmail(email);
    }

    public void desativar() {
        if (!ativo) {
            throw new IllegalStateException(
                    "O usuário já está desativado."
            );
        }

        this.ativo = false;
    }

    public void reativar() {
        if (ativo) {
            throw new IllegalStateException(
                    "O usuário já está ativo."
            );
        }

        this.ativo = true;
    }

    public static String normalizarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do usuário é obrigatório."
            );
        }

        return nome.trim();
    }

    public static String normalizarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail do usuário é obrigatório."
            );
        }

        String emailNormalizado = email.trim().toLowerCase();

        if (!emailNormalizado.contains("@")
                || emailNormalizado.startsWith("@")
                || emailNormalizado.endsWith("@")) {
            throw new IllegalArgumentException(
                    "O e-mail do usuário é inválido."
            );
        }

        return emailNormalizado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean getAtivo() {
        return ativo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
