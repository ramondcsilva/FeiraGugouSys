package view;

import controller.SearchController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    private static int relevancia = 1;
    private List<Palavra> list = new ArrayList();
    private ObservableList<Palavra> observableListPalavras;
    
    public static void main(String[] args) throws IOException {
        SearchController.carregaArquivos();
        launch(args);
    }
    public Main(){
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
        b.setOnAction((ActionEvent event) -> {
            System.out.println("Voce buscou: " + text.getText());
            Palavra n = SearchController.search(new Palavra(text.getText()));
            System.out.println("Foi achado: " + n);
            if(n != null && !observableListPalavras.contains(n)){
                observableListPalavras.add(n);
            }else if(observableListPalavras.contains(n)){
                int i = observableListPalavras.indexOf(n);
                observableListPalavras.remove(i);
                observableListPalavras.add(i,n);
            }        
        });
        
        //Pesquisa usando a tecla enter;
        text.setOnAction(e -> {
            System.out.println("Voce buscou: " + text.getText());
            Palavra n = SearchController.search(new Palavra(text.getText()));
            System.out.println("Foi achado: " + n);
            if(n != null && !observableListPalavras.contains(n)){
                observableListPalavras.add(n);
            }else if(observableListPalavras.contains(n)){
                int i = observableListPalavras.indexOf(n);
                observableListPalavras.remove(i);
                observableListPalavras.add(i,n);
            }
        });
        
        hbox.getChildren().add(b);

        Button b1 = new Button("Palavras");
        b1.setOnAction((ActionEvent event) -> {
            System.out.println(text.getText());
            SearchController.getRank().getPalavras().printTree();
        });
        vbox.getChildren().add(b1);

        TableView table = new TableView();
        table.setEditable(true);
        TableColumn tc = new TableColumn("Palavras");
        tc.setCellValueFactory(new PropertyValueFactory<>("word"));
        TableColumn tc1 = new TableColumn("Relevancia");
        tc1.setCellValueFactory(new PropertyValueFactory<>("relevancia"));
        observableListPalavras = FXCollections.observableArrayList(list);
        table.setItems(observableListPalavras);
        table.getColumns().addAll(tc,tc1);
        vbox.getChildren().add(table);
        
        primaryStage.setTitle("FeiraGugouSys");
        primaryStage.setScene(new Scene(vbox, 375, 375));
        primaryStage.show();
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
    }
}