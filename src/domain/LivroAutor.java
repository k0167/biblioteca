package domain;

public class LivroAutor {
    Integer idAutor;
    Integer idLivro;

    public LivroAutor() {
    }

    public LivroAutor(Integer idAutor, Integer idLivro) {
        this.idAutor = idAutor;
        this.idLivro = idLivro;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public String toString() {
        return "LivroAutorDao [idAutor=" + idAutor + ", idLivro=" + idLivro + "]";
    }
    
}
