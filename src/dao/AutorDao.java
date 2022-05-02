package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Autor;

public class AutorDao {

    private Connection connection;
    
    public AutorDao(Connection connection) {
        this.connection = connection;
    }

    public void commit() throws SQLException {
        connection.commit();
    }


    public void insert(Autor autor){

        String sql = "INSERT INTO AUTOR(nome, naturalidade, ano_nasc) VALUES (?, ?, ?)";
        try(
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
            ){ 
                statement.setString(1, autor.getNome());
                statement.setString(2, autor.getNaturalidade());
                statement.setInt(3, autor.getAnoNasc());

                statement.executeUpdate();
                
                ResultSet resultSet = statement.getGeneratedKeys();

                if(resultSet.next()){
                    autor.setIdAutor(resultSet.getInt("id_autor"));
                }
            }               
                //close...
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Autor> findAll(){

        String sql = "SELECT id_autor, nome, naturalidade, ano_nasc FROM AUTOR";
            List<Autor> autores = null;
        try (
            Statement statement = connection.createStatement();
        ){
            ResultSet resultSet = statement.executeQuery(sql);
            autores = new ArrayList<Autor>();
            Autor autor;
            
            while (resultSet.next()) {
                autor = getAutorByResultSet(resultSet);
                autores.add(autor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public Autor findById(Integer id){
        String sql = "SELECT id_autor, nome, naturalidade, ano_nasc FROM AUTOR WHERE id_autor = ?";
            Autor autor = null;
                try (
                    PreparedStatement statement = connection.prepareStatement(sql);
                ){

                    
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
            
                    if(resultSet.next()){
                        autor = getAutorByResultSet(resultSet);    
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return autor;
    }

    public List<Autor> findByNome(String nome){
        String sql = "SELECT id_autor, nome, naturalidade, ano_nasc FROM AUTOR WHERE nome like ?";
        List<Autor> autores = null;
            try (
                    PreparedStatement statement = connection.prepareStatement(sql);
                ){
                    statement.setString(1, nome);
                    ResultSet resultSet = statement.executeQuery();

                    Autor autor;
                    autores = new ArrayList<Autor>();
            
                    while (resultSet.next()) {
                        autor = getAutorByResultSet(resultSet);
                        autores.add(autor);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return autores;
    }

    public void delete(Integer id){
        String sql = "DELETE FROM AUTOR WHERE id_autor = ?";
        try (
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Autor autor){
        String sql = "UPDATE AUTOR SET nome = ?, naturalidade = ?, ano_nasc = ? WHERE id_autor = ?";

            try(
                PreparedStatement statement = connection.prepareStatement(sql);
            ){

                statement.setString(1, autor.getNome());
                statement.setString(2, autor.getNaturalidade());
                statement.setInt(3, autor.getAnoNasc());

                statement.setLong(4, autor.getIdAutor());

                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    private Autor getAutorByResultSet(ResultSet resultSet) throws SQLException {
        Autor autor = new Autor();    
        autor.setIdAutor(resultSet.getInt("id_autor"));
        autor.setNome(resultSet.getString("nome"));
        autor.setNaturalidade(resultSet.getString("naturalidade"));
        autor.setAnoNasc(resultSet.getInt("ano_nasc"));
        return autor;
    }
    
}
