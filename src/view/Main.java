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
    public List<Palavra> ALpalavras = new ArrayList();
    public List<Pagina> ALpaginas = new ArrayList();
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
        hbox.setAlignment(Pos.TOP_CENTER);
        
        TextField tfPalavras = new TextField();
        tfPalavras.setAlignment(Pos.TOP_CENTER);
        
        hbox.getChildren().add(tfPalavras);
        vbox.getChildren().add(hbox);
        
        observableListPalavras = FXCollections.observableArrayList(ALpalavras);
        
        //Pesuisa usando um botao;
        Button bPesquisar = new Button("Pesquisar");
        bPesquisar.setOnAction((ActionEvent event) -> {
            System.out.println("Voce buscou: " + tfPalavras.getText());
            Palavra n = SearchController.search(new Palavra(tfPalavras.getText()));
            System.out.println("Foi achado: " + n);
            if(n != null && !observableListPalavras.contains(n)){
                observableListPalavras.add(n);
                for(int i = 0;  i < n.getPaginas().size(); i++){
                    Pagina pag = (Pagina)n.getPaginas().get(i);
                    if(!ALpaginas.contains(pag)){
                        ALpaginas.add(pag);
                        pag.setRelevancia(1);
                    }else{
                        int index = n.getPaginas().indexOf(pag);
                        Pagina pagRetorno = (Pagina)n.getPaginas().get(index);
                        pagRetorno.setRelevancia(1);
                    }
                }
            }else if(observableListPalavras.contains(n)){
                int i = observableListPalavras.indexOf(n);
                Palavra palavraRetorno = (Palavra)observableListPalavras.remove(i);
                observableListPalavras.add(i,palavraRetorno);
            }
        });
        
        //Mostra as paginas adicionadas;
        Button b = new Button("Raking");
        b.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();
            TableView tvPaginas = new TableView();
            HBox boxH = new HBox();
            HBox boxPagina = new HBox();
            HBox boxPalavras = new HBox();
            
            TableColumn tcPaginas = new TableColumn("Paginas");
            tcPaginas.setMaxWidth(136);
            tcPaginas.setMinWidth(136);
            tcPaginas.setCellValueFactory(new PropertyValueFactory<>("nome"));

            
            TableColumn tcRelevanciaPagina = new TableColumn("R");
            tcRelevanciaPagina.setMaxWidth(37);
            tcRelevanciaPagina.setMinWidth(37);
            tcRelevanciaPagina.setCellValueFactory(new PropertyValueFactory<>("relevancia"));
            
            observableListPaginas = FXCollections.observableArrayList(ALpaginas);
            tvPaginas.setItems(observableListPaginas);
            tvPaginas.getColumns().addAll(tcPaginas,tcRelevanciaPagina);
            
            TableView tvPalavras = new TableView();
            tvPalavras.setEditable(true);

            TableColumn tcPalavras = new TableColumn("Palavras");
            tcPalavras.setMaxWidth(136);
            tcPalavras.setMinWidth(136);
            tcPalavras.setCellValueFactory(new PropertyValueFactory<>("word"));

            TableColumn tcRelevancia = new TableColumn("R");
            tcRelevancia.setMaxWidth(37);
            tcRelevancia.setMinWidth(37);
            tcRelevancia.setCellValueFactory(new PropertyValueFactory<>("relevancia"));

            tvPalavras.setItems(observableListPalavras);
            tvPalavras.getColumns().addAll(tcPalavras,tcRelevancia);
            
            boxH.getChildren().addAll(boxPagina,boxPalavras);
            boxPalavras.getChildren().add(tvPalavras);
            boxPagina.getChildren().addAll(tvPaginas);
            
            stage.setTitle("Raking");
            stage.setScene(new Scene(boxH, 375, 375));
            stage.show();
            
            for(int i = 0; i < ALpaginas.size();i++){
                Pagina p = (Pagina) ALpaginas.get(i);
                System.out.print(p+" ");
                System.out.println(p.getRelevancia());
            }
        });
        
        //Pesquisa usando a tecla enter;
        tfPalavras.setOnAction(e -> {
            System.out.println("Voce buscou: " + tfPalavras.getText());
            Palavra n = SearchController.search(new Palavra(tfPalavras.getText()));
            System.out.println("Foi achado: " + n);
            if(n != null && !observableListPalavras.contains(n)){
                observableListPalavras.add(n);
                for(int i = 0;  i < n.getPaginas().size(); i++){
                    Pagina pag = (Pagina)n.getPaginas().get(i);
                    if(!ALpaginas.contains(pag)){
                        ALpaginas.add(pag);
                        pag.setRelevancia(1);
                    }else{
                        int index = n.getPaginas().indexOf(pag);
                        Pagina pagRetorno = (Pagina)n.getPaginas().get(index);
                        pagRetorno.setRelevancia(1);
                    }
                }
            }else if(observableListPalavras.contains(n)){
                int i = observableListPalavras.indexOf(n);
                Palavra palavraRetorno = (Palavra)observableListPalavras.remove(i);
                observableListPalavras.add(i,palavraRetorno);
            }
        });
        
        hbox.getChildren().add(bPesquisar);
        hbox.getChildren().add(b);
        
        //mostra todas as palavras;
        Button bPalavras = new Button("Palavras");
        bPalavras.setOnAction((ActionEvent event) -> {
            System.out.println(tfPalavras.getText());
            SearchController.getRank().getPalavras().printTree();
        });
        
        vbox.getChildren().add(bPalavras);
        HBox hboxTV = new HBox();

        vbox.getChildren().add(hboxTV);
        
        primaryStage.setTitle("FeiraGugouSys");
        primaryStage.setScene(new Scene(vbox, 375, 375));
        primaryStage.show();
    }
}