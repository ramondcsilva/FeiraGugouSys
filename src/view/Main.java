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
    public List<Palavra> list = new ArrayList();
    public List<Pagina> l = new ArrayList();
    public ObservableList<Palavra> observableListPalavras;
    public ObservableList<Pagina> observableListPaginas;
    
    public Main(){
    }
    
    public static void main(String[] args) throws IOException {
        SearchController.carregaArquivos();
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
        b.setOnAction((ActionEvent event) -> {
            System.out.println("Voce buscou: " + text.getText());
            Palavra n = SearchController.search(new Palavra(text.getText()));
            System.out.println("Foi achado: " + n);
            if(n != null && !observableListPalavras.contains(n)){
                observableListPalavras.add(n);
                l = (List<Pagina>) n.getPaginas();
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
        tc.setMinWidth(100);
        tc.setCellValueFactory(new PropertyValueFactory<>("word"));
        
        TableColumn tc1 = new TableColumn("Relevância");
        tc1.setMinWidth(90);
        tc1.setCellValueFactory(new PropertyValueFactory<>("relevancia"));
        
        TableColumn tc2 = new TableColumn("Paginas");
        tc2.setMinWidth(190);
        tc2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        observableListPalavras = FXCollections.observableArrayList(list);
        table.setItems(observableListPalavras);
        table.getColumns().addAll(tc,tc1);
        
        HBox h = new HBox();
        
        TableView table1 = new TableView();
        table1.setEditable(true);
        
        h.getChildren().add(table1);
        h.getChildren().add(table);
        vbox.getChildren().add(h);
        
        observableListPaginas = FXCollections.observableArrayList(l);
        table1.setItems(observableListPaginas);
        table1.getColumns().addAll(tc2);
        
        primaryStage.setTitle("FeiraGugouSys");
        primaryStage.setScene(new Scene(vbox, 375, 375));
        primaryStage.show();
    }
}