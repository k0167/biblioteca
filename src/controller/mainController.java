package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import config.ConfigDB;
import dao.AutorDao;
import dao.LivroAutorDao;
import dao.LivroDao;
import domain.Autor;
import domain.Livro;
import domain.LivroAutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class mainController implements Initializable {

    ///////////////////////////////////////////Autor/////////////////////////////////////////////////////

    @FXML
    private Button autor_btnAtualizar;

    @FXML
    private Button autor_btnDeletar;

    @FXML
    private Button autor_btnInserir;

    @FXML
    private Button autor_btnPesquisar;

    @FXML
    private CheckBox autor_chkCorresp;

    @FXML
    private Label autor_lbErr;

    @FXML
    private TableView<Autor> autor_table;

    @FXML
    private TableColumn<Autor, Integer> autor_tableId;

    @FXML
    private TableColumn<Autor, Integer> autor_tableNasc;

    @FXML
    private TableColumn<Autor, String> autor_tableNat;

    @FXML
    private TableColumn<Autor, String> autor_tableNome;

    @FXML
    private TextField autor_txfNasc;

    @FXML
    private TextField autor_txfNat;

    @FXML
    private TextField autor_txfNome;


    ///////////////////////////////////////////Autor/////////////////////////////////////////////////////

    ///////////////////////////////////////////Livro/////////////////////////////////////////////////////


    @FXML
    private Button livro_btnAtualizar;

    @FXML
    private Button livro_btnDeletar;

    @FXML
    private Button livro_btnInserir;

    @FXML
    private Button livro_btnPesquisar;

    @FXML
    private Button livro_btnPesquisarMais;

    @FXML
    private CheckBox livro_ckbCorresp;

    @FXML
    private ChoiceBox<Autor> livro_menuAutor;

    @FXML
    private TableView<Livro> livro_table;

    @FXML
    private TableColumn<Livro, Integer> livro_table_edicao;

    @FXML
    private TableColumn<Livro, Integer> livro_table_id;

    @FXML
    private TableColumn<Livro, String> livro_table_isbn;

    @FXML
    private TableColumn<Livro, String> livro_table_titulo;

    @FXML
    private TextArea livro_txaDesc;

    @FXML
    private TextField livro_txfEdicao;

    @FXML
    private TextField livro_txfISBN;

    @FXML
    private TextField livro_txfTitulo;


    private List<Livro> livros;
    private ObservableList<Livro> livrosObservable;

    Connection connection;
    LivroDao livroDao;
    AutorDao autorDao;
    LivroAutorDao livroAutorDao;
    Livro currentLivro;
    Integer livro_qtde = 10;
    ///////////////////////////////////////////Livro/////////////////////////////////////////////////////



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            connection=  ConfigDB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        livroDao = new LivroDao(connection);
        autorDao = new AutorDao(connection);
        livroAutorDao = new LivroAutorDao(connection);

        livro_carregarLivros();
        autor_carregarAutores();
        livro_table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                currentLivro = newValue;
            }
        });
        
        autor_table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                currentAutor = newValue;
            }
        });
    }

    ///////////////////////////////////////////Metodos Livro/////////////////////////////////////////////////////

    @FXML
    void livro_atualizar(ActionEvent event) {
        livro_resetaBordas();
        Boolean flag=true;

        if(livro_txfTitulo.getText().isEmpty()){
            livro_txfTitulo.setStyle("-fx-border-color: red");
            flag=false;
        }

        if(livro_txfISBN.getText().isEmpty()){
            livro_txfISBN.setStyle("-fx-border-color: red");
            flag=false;
        }

        if(livro_txfEdicao.getText().isEmpty()||!livro_txfEdicao.getText().matches("[0-9]+")){
            livro_txfEdicao.setStyle("-fx-border-color: red");
            flag=false;
        }

        if(livro_txaDesc.getText().isEmpty()){
            livro_txaDesc.setStyle("-fx-border-color: red");
            flag=false;
        }

        if(flag){
            Livro livro = new Livro();
            livro.setIdLivro(currentLivro.getIdLivro());
            livro.setTitulo(livro_txfTitulo.getText());
            livro.setIsbn(livro_txfISBN.getText());
            livro.setEdicao(Integer.parseInt(livro_txfEdicao.getText()));
            livro.setDescricao(livro_txaDesc.getText());

            livroDao.update(livro);

            try {
                livroDao.commit();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            livro_btnInserir.setDisable(false);
            livro_btnAtualizar.setDisable(true);
            livro_carregarLivros();
            livro_limpaCampos();
            livro_resetaBordas();
            livro_table.setDisable(false);

        }


    }

    @FXML
    void livro_Inserir(ActionEvent event) {
        livro_resetaBordas();

        Boolean flag = true;

        if(livro_txfTitulo.getText().isEmpty()){
            livro_txfTitulo.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(livro_txfEdicao.getText().isEmpty()||!livro_txfEdicao.getText().matches("[0-9]+")){
            livro_txfEdicao.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(livro_txfISBN.getText().isEmpty()){
            livro_txfISBN.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(livro_txaDesc.getText().isEmpty()){
            livro_txaDesc.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(livro_menuAutor.getValue() == null){
            livro_menuAutor.setStyle("-fx-border-color: red");
            flag = false;
        }
        if(flag){
            Livro livro = new Livro();

            livro.setTitulo(livro_txfTitulo.getText());
            livro.setEdicao(Integer.parseInt(livro_txfEdicao.getText()));
            livro.setIsbn(livro_txfISBN.getText());
            livro.setDescricao(livro_txaDesc.getText());
    
            livroDao.insert(livro);
    
            LivroAutor livroAutor = new LivroAutor();
    
            livroAutor.setIdLivro(livro.getIdLivro());
            livroAutor.setIdAutor(livro_menuAutor.getValue().getIdAutor());
    
            livroAutorDao.insert(livroAutor);
            try {
                livroAutorDao.commit();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            livro_limpaCampos();
            livro_resetaBordas();
            livro_carregarLivros();
    
        }
        
    }

    @FXML
    void livro_carregarAutores(MouseEvent event) {
        livro_menuAutor.getItems().clear();
        List<Autor> autores = autorDao.findAll();
        for(Autor autor : autores){
            livro_menuAutor.getItems().add(autor);
        }
    }

    @FXML
    void livro_deletar(ActionEvent event) {

        if(currentLivro != null){
            List<LivroAutor> livroAutores = livroAutorDao.findByLivro(currentLivro.getIdLivro());
            for(LivroAutor livroAutor : livroAutores){
                livroAutorDao.delete(livroAutor);
            }
            livroDao.delete(currentLivro.getIdLivro());

            try {
                livroAutorDao.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @FXML
    void livro_enterPressed(KeyEvent event) {
        if(currentLivro!=null){
            livro_btnInserir.setDisable(true);
            livro_btnAtualizar.setDisable(false);

            livro_table.setDisable(true);
            livro_txfTitulo.setText(currentLivro.getTitulo());
            livro_txfEdicao.setText(currentLivro.getEdicao().toString());
            livro_txfISBN.setText(currentLivro.getIsbn());
            livro_txaDesc.setText(currentLivro.getDescricao());

        }
    }

    @FXML
    void livro_listaMais(ActionEvent event) {
        livro_qtde+=5;
        livros =  livroDao.findAll(livro_qtde);
        livro_populaTabela(livros);  
    }

    @FXML
    void livro_pesquisar(ActionEvent event) {
        livro_resetaBordas();
        livro_qtde=10;
        if(livro_txfTitulo.getText().isEmpty()){
            livros = livroDao.findAll(livro_qtde);
            livro_populaTabela(livros);
            return;
        }else{
            if(livro_ckbCorresp.isSelected())
                livros = livroDao.findByTitulo(livro_txfTitulo.getText());
            else
                livros = livroDao.findByTitulo("%"+livro_txfTitulo.getText()+"%");
            livro_populaTabela(livros);
        }

    }

    private void livro_carregarLivros(){

        livro_table_edicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        livro_table_id.setCellValueFactory(new PropertyValueFactory<>("idLivro"));
        livro_table_isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        livro_table_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        livros =  livroDao.findAll(10);

        livrosObservable = FXCollections.observableArrayList(livros);

        livro_table.setItems(livrosObservable);
    }

    private void livro_resetaBordas(){

        livro_txaDesc.setStyle("");
        livro_txfEdicao.setStyle("");
        livro_txfISBN.setStyle("");
        livro_txfTitulo.setStyle("");
        livro_menuAutor.setStyle("");
    }

    private void livro_populaTabela(List<Livro> livros){

        livrosObservable = FXCollections.observableArrayList(livros);
            livro_table.getItems().clear();
            livro_table.setItems(livrosObservable);
    }

    private void livro_limpaCampos(){
        livro_txfTitulo.setText("");
        livro_txfEdicao.setText("");
        livro_txfISBN.setText("");
        livro_txaDesc.setText("");
        livro_menuAutor.setValue(null);
    }


    ///////////////////////////////////////////Metodos Livro/////////////////////////////////////////////////////
    ///////////////////////////////////////////Metodos Autor/////////////////////////////////////////////////////
    private List<Autor> autores;
    private ObservableList<Autor> autoresObservable;
    private Autor currentAutor;

    @FXML
    void autor_Atualizar(ActionEvent event) {
        autor_resetaBordas();
        Boolean flag = true;

        if(autor_txfNome.getText().isEmpty()){
            autor_txfNome.setStyle("-fx-border-color: red");
            flag = false;
        }
        if(autor_txfNat.getText().isEmpty()){
            autor_txfNat.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(autor_txfNasc.getText().isEmpty()||!autor_txfNasc.getText().matches("[0-9]+")){
            autor_txfNasc.setStyle("-fx-border-color: red");
            flag = false;
        }


        if(flag){   

            if(currentAutor != null){
                currentAutor.setNome(autor_txfNome.getText());
                currentAutor.setNaturalidade(autor_txfNat.getText());
                currentAutor.setAnoNasc(Integer.parseInt(autor_txfNasc.getText()));
                autorDao.update(currentAutor);
                try {
                    autorDao.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    autor_lbErr.setText("Erro ao atualizar autor: "+ e.getMessage());

                }
            }
            autor_carregarAutores();
            autor_limpaCampos();
            autor_resetaBordas();
            autor_table.setDisable(false);
            autor_btnInserir.setDisable(false);
            autor_btnAtualizar.setDisable(true);

        }

    }

    @FXML
    void autor_Deletar(ActionEvent event) {

        if(currentAutor != null){
            List<LivroAutor> livroauts = livroAutorDao.findByAutor(currentAutor.getIdAutor());

            if(livroauts==null){
                autor_lbErr.setText("Não é possivel deletar autor, pois ele nao existe");
                autor_lbErr.setVisible(true);
            }else if(livroauts.size()>1){
                autor_lbErr.setText("Não é possivel deletar autor, pois ele esta associado a livros");
                autor_lbErr.setVisible(true);
            }else{
                autorDao.delete(currentAutor.getIdAutor());
                try {
                    autorDao.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    autor_lbErr.setText("Erro ao deletar autor: "+ e.getMessage());

                }
            }
        }
    }

    @FXML
    void autor_Inserir(ActionEvent event) {
        autor_resetaBordas();
        Boolean flag = true;

        if(autor_txfNome.getText().isEmpty()){
            autor_txfNome.setStyle("-fx-border-color: red");
            flag = false;
        }
        if(autor_txfNat.getText().isEmpty()){
            autor_txfNat.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(autor_txfNasc.getText().isEmpty()||!autor_txfNasc.getText().matches("[0-9]+")){
            autor_txfNasc.setStyle("-fx-border-color: red");
            flag = false;
        }

        if(flag){
            Autor autor = new Autor();
            autor.setNome(autor_txfNome.getText());
            autor.setNaturalidade(autor_txfNat.getText());
            autor.setAnoNasc(Integer.parseInt(autor_txfNasc.getText()));
            autorDao.insert(autor);
            try {
                autorDao.commit();
            } catch (Exception e) {
                e.printStackTrace();
                autor_lbErr.setText("Erro ao inserir autor: "+ e.getMessage());

            }
            autor_carregarAutores();
            autor_limpaCampos();
            autor_resetaBordas();
        }

    }

    @FXML
    void autor_Pesquisar(ActionEvent event) {
        
        if(autor_txfNome.getText().isEmpty()){
            autores = autorDao.findAll();
            autor_populaTabela(autores);
        }else{
            if(autor_chkCorresp.isSelected())
                autores = autorDao.findByNome(autor_txfNome.getText());
            else
                autores = autorDao.findByNome("%"+autor_txfNome.getText()+"%");
            autor_populaTabela(autores);
        }
    }

    @FXML
    void autor_enterPressed(KeyEvent event) {
        if(currentAutor!=null){
            autor_btnInserir.setDisable(true);
            autor_btnAtualizar.setDisable(false);
            autor_table.setDisable(true);
            autor_txfNome.setText(currentAutor.getNome());
            autor_txfNat.setText(currentAutor.getNaturalidade());
            autor_txfNasc.setText(currentAutor.getAnoNasc().toString());
        }

    }

    private void autor_populaTabela(List<Autor> autoresLista){

        autoresObservable = FXCollections.observableArrayList(autoresLista);
            autor_table.getItems().clear();
            autor_table.setItems(autoresObservable);
    }


    private void autor_carregarAutores(){

        autor_tableId.setCellValueFactory(new PropertyValueFactory<>("idAutor"));
        autor_tableNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        autor_tableNat.setCellValueFactory(new PropertyValueFactory<>("Naturalidade"));
        autor_tableNasc.setCellValueFactory(new PropertyValueFactory<>("AnoNasc"));

        autores =  autorDao.findAll();

        autoresObservable = FXCollections.observableArrayList(autores);

        autor_table.setItems(autoresObservable);
    }

    private void autor_resetaBordas(){
        autor_txfNome.setStyle("");
        autor_txfNat.setStyle("");
        autor_txfNasc.setStyle("");
    }

    private void autor_limpaCampos(){
        autor_txfNome.setText("");
        autor_txfNat.setText("");
        autor_txfNasc.setText("");
    }

}
