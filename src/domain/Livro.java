package domain;

public class Livro {
    private Integer idLivro;
    private String titulo;
    private String isbn;
    private Integer edicao;
    private String descricao;

    public Livro(){

    }

    public Livro(Integer idLivro, String titulo, String isbn, Integer edicao, String descricao) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicao = edicao;
        this.descricao = descricao;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + idLivro +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", edicao=" + edicao +
                ", descricao='" + descricao + '\'' +
                '}';
    }
    
}
