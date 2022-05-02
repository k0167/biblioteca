package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.LivroAutor;

public class LivroAutorDao {
    
    private Connection connection;
    
    public LivroAutorDao(Connection connection) {
        this.connection = connection;
    }

    public void commit() throws SQLException {
        connection.commit();
    }


    public void insert(LivroAutor livroAutor){

        String sql = "INSERT INTO livro_autor(id_livro, id_autor) VALUES (?, ?)";
        try(
            PreparedStatement statement = connection.prepareStatement(sql);  
            ){ 
                statement.setInt(1, livroAutor.getIdLivro());
                statement.setInt(2, livroAutor.getIdAutor());

                statement.executeUpdate();

                }               
                //close...
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<LivroAutor> findAll(){

        String sql = "SELECT id_livro, id_autor FROM livro_autor";
        List<LivroAutor> livroAutores = null;
        try (
            Statement statement = connection.createStatement();
        ){
            ResultSet resultSet = statement.executeQuery(sql);
            livroAutores = new ArrayList<LivroAutor>();
            LivroAutor livroAutor;
            
            while (resultSet.next()) {
                livroAutor = getLivroAutorByResultSet(resultSet);
                livroAutores.add(livroAutor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return livroAutores;
    }


    public List<LivroAutor> findByLivro(Integer idLivro){

        String sql = "SELECT id_livro, id_autor FROM livro_autor where id_livro = "+ idLivro;
        List<LivroAutor> livroAutores = null;
        try (
            Statement statement = connection.createStatement();
        ){
            ResultSet resultSet = statement.executeQuery(sql);
            livroAutores = new ArrayList<LivroAutor>();
            LivroAutor livroAutor;
            
            while (resultSet.next()) {
                livroAutor = getLivroAutorByResultSet(resultSet);
                livroAutores.add(livroAutor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return livroAutores;
    }

    public List<LivroAutor> findByAutor(Integer idAutor){

        String sql = "SELECT id_livro, id_autor FROM livro_autor where id_autor = "+ idAutor;
        List<LivroAutor> livroAutores = null;
        try (
            Statement statement = connection.createStatement();
        ){
            ResultSet resultSet = statement.executeQuery(sql);
            livroAutores = new ArrayList<LivroAutor>();
            LivroAutor livroAutor;
            
            while (resultSet.next()) {
                livroAutor = getLivroAutorByResultSet(resultSet);
                livroAutores.add(livroAutor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return livroAutores;
    }

    
    public void delete(LivroAutor livroAutor){
        String sql = "DELETE FROM LIVRO_AUTOR WHERE id_livro = ? and id_autor = ?";
        try (
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1, livroAutor.getIdLivro());
            statement.setInt(2, livroAutor.getIdAutor());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LivroAutor getLivroAutorByResultSet(ResultSet resultSet) throws SQLException {
        LivroAutor livroAutor = new LivroAutor();    
        livroAutor.setIdLivro(resultSet.getInt("id_livro"));
        livroAutor.setIdAutor(resultSet.getInt("id_autor"));
        return livroAutor;
    }
}
