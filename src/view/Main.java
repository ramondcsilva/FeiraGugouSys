package view;

import controller.SeachController;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    int relevancia = 1;

    public static void main(String[] args) throws IOException {
        SeachController.carregaArquivos();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = new VBox(10, new Label("   "));
        vbox.getChildren().add(new Label("Buscador de Palavras"));
        vbox.setAlignment(Pos.TOP_CENTER);

        HBox hbox = new HBox(15, new Label("  Palavra: "));
        vbox.setAlignment(Pos.TOP_CENTER);

        TextField text = new TextField();
        text.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(text);
        vbox.getChildren().add(hbox);
        
        //Pesuisa usando um botao;
        Button b = new Button("Pesquisar");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Voce buscou: " + text.getText());
                Palavra n = (Palavra) SeachController.getRank().getPalavras().buscarObj(new Palavra(text.getText()));
                System.out.println("Foi achado: " + n);
            }
        });
        
        //Pesquisa usando a tecla enter;
        text.setOnAction(e -> {
            System.out.println("Voce buscou: " + text.getText());
            Palavra n = (Palavra) SeachController.getRank().getPalavras().buscarObj(new Palavra(text.getText()));
            System.out.println("Foi achado: " + n);
        });

        hbox.getChildren().add(b);

        Button b1 = new Button("Palavras");
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(text.getText());
                SeachController.getRank().getPalavras().printTree();
            }
        });
        vbox.getChildren().add(b1);
        /////////////////////////////////////////////////////////////
        /*
        vbox.getChildren().add(new Label("       "));
        vbox.getChildren().add(new Label("Buscador de Palavras"));
        vbox.setAlignment(Pos.CENTER);
            
        HBox hboxcenter = new HBox(15, new Label("    Nome:"));
        vbox.setAlignment(Pos.CENTER);
        
        TextField textt = new TextField();
        text.setAlignment(Pos.CENTER);
        
        hboxcenter.getChildren().add(textt);
        vbox.getChildren().add(hboxcenter);
        
        Button b0 = new Button("Pesquisar");
        b0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Voce buscou: "+text.getText()); 
                //Pagina n = (Pagina)SeachController.getRank().getPaginas().buscarObj(new Pagina(text.getText()+".txt"));
                //System.out.println("Foi achado: "+n);
            }
        });
        hboxcenter.getChildren().add(b0);
         */
        primaryStage.setTitle("FeiraGugouSys");
        primaryStage.setScene(new Scene(vbox, 300, 180));
        primaryStage.show();
    }
}
//Renomear arquivos, reformatar os arquivos.
