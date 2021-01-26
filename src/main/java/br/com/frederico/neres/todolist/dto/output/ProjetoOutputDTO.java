package br.com.frederico.neres.todolist.dto.output;

public class ProjetoOutputDTO {

    private Long id;
    private String titulo;
    private String descricao;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    private ProjetoOutputDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public static class Builder {
        private Long id;
        private String titulo;
        private String descricao;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public ProjetoOutputDTO build() {
            return new ProjetoOutputDTO(id, titulo, descricao);
        }
    }


}
