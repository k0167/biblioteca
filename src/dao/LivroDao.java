package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Livro;

public class LivroDao {

    private Connection connection;
    
    public LivroDao(Connection connection) {
        this.connection = connection;
    }

    public void commit() throws SQLException {
        connection.commit();
    }


    public void insert(Livro livro){

        String sql = "INSERT INTO livro(titulo, isbn, edicao, descricao) VALUES (?, ?, ?, ?)";
        try(
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
            ){ 
                statement.setString(1, livro.getTitulo());
                statement.setString(2, livro.getIsbn());
                statement.setInt(3, livro.getEdicao());
                statement.setString(4, livro.getDescricao());

                statement.executeUpdate();
                
                ResultSet resultSet = statement.getGeneratedKeys();

                if(resultSet.next()){
                    livro.setIdLivro(resultSet.getInt("id_livro"));
                }
            }               
                //close...
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<Livro> findAll(Integer qtde){

        String sql = "SELECT id_livro, titulo, isbn, edicao, descricao FROM LIVRO LIMIT ?";
            ArrayList<Livro> livros = null;
        try (
            PreparedStatement statement = connection.prepareStatement(sql);  
        ){
            statement.setInt(1, qtde);

            ResultSet resultSet = statement.executeQuery();
            livros = new ArrayList<Livro>();
            Livro livro;
            
            while (resultSet.next()) {
                livro = getLivroByResultSet(resultSet);
                livros.add(livro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro findById(Integer id){
        String sql = "SELECT id_livro, titulo, isbn, edicao, descricao FROM LIVRO WHERE id_livro = ?";
            Livro livro = null;
                try (
                    PreparedStatement statement = connection.prepareStatement(sql);
                ){

                    
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
            
                    if(resultSet.next()){
                        livro = getLivroByResultSet(resultSet);    
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return livro;
    }

    public List<Livro> findByTitulo(String titulo){
        String sql = "SELECT id_livro, titulo, isbn, edicao, descricao FROM LIVRO WHERE titulo like ?";
        List<Livro> livros = null;
            try (
                    PreparedStatement statement = connection.prepareStatement(sql);
                ){
                    statement.setString(1, titulo);
                    ResultSet resultSet = statement.executeQuery();

                    Livro livro;
                    livros = new ArrayList<Livro>();
            
                    while (resultSet.next()) {
                        livro = getLivroByResultSet(resultSet);
                        livros.add(livro);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return livros;
    }

    public void delete(Integer id){
        String sql = "DELETE FROM livro WHERE id_livro = ?";
        try (
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Livro livro){
        String sql = "UPDATE livro SET titulo = ?, isbn = ?, edicao = ?, descricao = ? WHERE id_livro = ?";

            try(
                PreparedStatement statement = connection.prepareStatement(sql);
            ){

                statement.setString(1, livro.getTitulo());
                statement.setString(2, livro.getIsbn());
                statement.setInt(3, livro.getEdicao());
                statement.setString(4, livro.getDescricao());

                statement.setLong(5, livro.getIdLivro());

                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    private Livro getLivroByResultSet(ResultSet resultSet) throws SQLException {
        Livro livro = new Livro();    
        livro.setIdLivro(resultSet.getInt("id_livro"));
        livro.setTitulo(resultSet.getString("titulo"));
        livro.setIsbn(resultSet.getString("isbn"));
        livro.setEdicao(resultSet.getInt("edicao"));
        livro.setDescricao(resultSet.getString("descricao"));

        return livro;
    }
}
