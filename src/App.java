
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
        @Override
        public void start(Stage primaryStage) throws Exception{

            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);
            
            primaryStage.setScene(tela);


            primaryStage.setTitle("Biblioteca");

            primaryStage.setResizable(false);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
            
            /*
            Connection conn;
            try {
                conn= ConfigDB.getConnection();
                LivroAutorDao ld= new LivroAutorDao(conn);
                List<LivroAutor> livros = ld.findByLivro(6);

                for(LivroAutor l: livros){
                    System.out.println(l);
                }
            } catch (Exception e) {
                //TODO: handle exception
            }*/
            


        }
}
