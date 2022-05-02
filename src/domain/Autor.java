package domain;

public class Autor  {
    Integer idAutor;
    String nome;
    String naturalidade;
    Integer anoNasc;

    public Autor() {
    }

    public Autor(Integer idAutor, String nome, String naturalidade, Integer anoNasc) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.naturalidade = naturalidade;
        this.anoNasc = anoNasc;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Integer getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(Integer anoNasc) {
        this.anoNasc = anoNasc;
    }

    @Override
    public String toString() {
        return idAutor + " - "+  nome ;
    }

}
