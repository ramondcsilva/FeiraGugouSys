/*  ******************************************************************************
 *  Autor: Sergio e Ramon
 *  Componente curricular: Módulo Integrador de Programação
 *  Concluido em: 
 *  Declaro que este código foi elaborado por mim de forma individual e não contém
 *  nenhum trecho de código de outro colega ou de outro autor, tais como provindos
 *  de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 *  Qualquer trecho de código de outra autoria que não a minha está destacado com
 *  uma citação para o autor e a fonte do código, e estou ciente que estes trechos
 *  não serão considerados para fins de avaliação.
 *  ******************************************************************************/
package view;

import controller.SearchController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    public List<Palavra> alPalavras = new ArrayList();
    public List<Pagina> alPaginas = new ArrayList();
    public List<Pagina> alPaginasBuscadas = new ArrayList();
    public ObservableList<Palavra> observableListPalavras;
    public ObservableList<Pagina> observableListPaginas;
    public ObservableList<Pagina> observableListPaginasBuscadas;
    
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        SearchController.carregaArquivos();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stageRaking = new Stage();

        HBox hboxPesquisa = new HBox();

        VBox vboxPesquisa = new VBox(10, new Label("   "));
        vboxPesquisa.getChildren().add(new Label("Buscador de Palavras"));
        vboxPesquisa.setAlignment(Pos.TOP_CENTER);

        HBox hboxLabel = new HBox(15, new Label("  Palavra: "));
        hboxLabel.setAlignment(Pos.TOP_CENTER);

        TextField tfPalavras = new TextField();
        tfPalavras.setAlignment(Pos.TOP_CENTER);

        hboxLabel.getChildren().add(tfPalavras);
        vboxPesquisa.getChildren().add(hboxLabel);

        observableListPalavras = FXCollections.observableArrayList(alPalavras);

        //Pesuisa usando um botao;
        Button bPesquisar = new Button("Pesquisar");
        bPesquisar.setOnAction((ActionEvent event) -> {
            System.out.println("Voce buscou: " + tfPalavras.getText());
            Palavra n = SearchController.search(new Palavra(tfPalavras.getText()));
            System.out.println("Foi achado: " + n);
            if (n != null && !observableListPalavras.contains(n)) {
                observableListPalavras.add(n);
                for (int i = 0; i < n.getPaginas().size(); i++) {
                    Pagina pag = (Pagina) n.getPaginas().get(i);
                    if(!alPaginas.contains(pag)) {
                        alPaginas.add(pag);
                        pag.setRelevanciaPalavras(1);
                    } else {
                        int index = n.getPaginas().indexOf(pag);
                        Pagina pagRetorno = (Pagina) n.getPaginas().get(index);
                        pagRetorno.setRelevanciaPalavras(1);
                    }
                }
            } else if (observableListPalavras.contains(n)) {
                int i = observableListPalavras.indexOf(n);
                Palavra palavraRetorno = (Palavra) observableListPalavras.remove(i);
                observableListPalavras.add(i, palavraRetorno);
            }
        });

        //Mostra as paginas adicionadas;
        Button b = new Button("Raking");
        b.setOnAction((ActionEvent event) -> {

            Stage stage = new Stage();
            stage.setScene(null);
            
            TableView tvPaginas = new TableView();

            HBox hboxRaking = new HBox();
            HBox boxPagina = new HBox();
            HBox boxPalavras = new HBox();

            TableColumn tcPaginas = new TableColumn("Paginas");
            tcPaginas.setMaxWidth(136);
            tcPaginas.setMinWidth(136);
            tcPaginas.setCellValueFactory(new PropertyValueFactory<>("nome"));

            TableColumn tcRelevanciaPagina = new TableColumn("V");
            tcRelevanciaPagina.setSortType(TableColumn.SortType.DESCENDING);
            tcRelevanciaPagina.setMaxWidth(37);
            tcRelevanciaPagina.setMinWidth(37);
            tcRelevanciaPagina.setCellValueFactory(new PropertyValueFactory<>("visitas"));

            observableListPaginas = FXCollections.observableArrayList(alPaginas);
            tvPaginas.setItems(observableListPaginas);
            tvPaginas.getColumns().addAll(tcPaginas, tcRelevanciaPagina);
            tvPaginas.getSortOrder().add(tcRelevanciaPagina);
            
            TableView tvPalavras = new TableView();
            tvPalavras.setEditable(true);

            TableColumn tcPalavras = new TableColumn("Palavras");
            tcPalavras.setMaxWidth(136);
            tcPalavras.setMinWidth(136);
            tcPalavras.setCellValueFactory(new PropertyValueFactory<>("word"));

            TableColumn tcRelevancia = new TableColumn("R");
            tcRelevancia.setSortType(TableColumn.SortType.DESCENDING);
            tcRelevancia.setMaxWidth(37);
            tcRelevancia.setMinWidth(37);
            tcRelevancia.setCellValueFactory(new PropertyValueFactory<>("relevancia"));

            tvPalavras.setItems(observableListPalavras);
            tvPalavras.getColumns().addAll(tcPalavras, tcRelevancia);
            tvPalavras.getSortOrder().add(tcRelevancia);
            
            hboxRaking.getChildren().addAll(boxPagina, boxPalavras);
            boxPalavras.getChildren().add(tvPalavras);
            boxPagina.getChildren().addAll(tvPaginas);

            tvPaginas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (tvPaginas.getSelectionModel().getSelectedItem() != null) {
                        Pagina paginaClicada = (Pagina) tvPaginas.getSelectionModel().getSelectedItem();
                        paginaClicada.setVisitas(1);
                        
                        TableViewSelectionModel selectionModel = tvPaginas.getSelectionModel();
                        ObservableList selectedCells = selectionModel.getSelectedCells();
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        Object val = tablePosition.getTableColumn().getCellData(newValue);
                        System.out.println("Selected Value: " + val);

                        String textRetorno = "";
                        try {
                            textRetorno = SearchController.imprimeTxt((String) val);
                        } catch (IOException ex) {
                        }
                        
                        Stage text = new Stage();
                        HBox hboxText = new HBox();
                        
                        VBox vboxSelection = new VBox(10,new Label(" "));
                        vboxSelection.setMinSize(100, 400);
                        vboxSelection.setMaxSize(100, 400);

                        TextArea taText = new TextArea();
                        taText.setPrefSize(450, 400);
                        taText.setEditable(false);
                        taText.setText(textRetorno);
                        
                        String guardaTA = taText.getText();
                        
                        TextArea taTextNovo = new TextArea();
                        taTextNovo.setPrefSize(450, 400);
                        taTextNovo.setEditable(true);
                        
                        TextField tfNovo = new TextField();
                        tfNovo.setMaxSize(70, 30);
                        tfNovo.setMinSize(70, 30);
                        
                        Button bSalvarArq = new Button("Salvar");
                        bSalvarArq.setMaxSize(70, 30);
                        bSalvarArq.setMinSize(70, 30);
                        bSalvarArq.setOnAction((ActionEvent event) -> {
                            System.out.println("Passou");
                            String textF = taTextNovo.getText();
                            String dir = "src/view/txt/"+ tfNovo.getText()+".txt";
                            System.out.println(textF);
                            try { 
                                SearchController.salvarArq(dir, textF);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        
                        Button bNovo = new Button("Novo");
                        bNovo.setMaxSize(70, 30);
                        bNovo.setMinSize(70, 30);
                        bNovo.setOnAction((ActionEvent event) -> {
                            Stage novo = new Stage();
                            HBox hboxTextNovo = new HBox();
                            
                            VBox vboxSelectionNovo = new VBox(10,new Label(" "));
                            vboxSelectionNovo.setMinSize(100, 400);
                            vboxSelectionNovo.setMaxSize(100, 400);

                            Button bSalvarNovo = new Button("Salvar");
                            bSalvarNovo.setMaxSize(70, 30);
                            bSalvarNovo.setMinSize(70, 30);
                            bSalvarNovo.setOnAction(bSalvarArq.getOnAction());
                            
                            HBox vSalvarNovo = new HBox(10);
                            HBox vLabel = new HBox(10);
                            HBox vTFNovo = new HBox(10);
                            vSalvarNovo.getChildren().addAll(new Label(" "), bSalvarNovo);
                            vLabel.getChildren().addAll(new Label(" "), new Label("    Arquivo"));
                            vTFNovo.getChildren().addAll(new Label(" "), tfNovo);
                            
                            vboxSelectionNovo.getChildren().addAll(vLabel, vTFNovo, vSalvarNovo);
                            hboxTextNovo.getChildren().addAll(taTextNovo, vboxSelectionNovo);
                            novo.setTitle("Novo Arquivo");
                            novo.setScene(new Scene(hboxTextNovo, 500, 350));
                            novo.show();
                        });
                        
                        Button bEditar = new Button("Editar");
                        bEditar.setMaxSize(70, 30);
                        bEditar.setMinSize(70, 30);
                        bEditar.setOnAction((ActionEvent event) -> {
                            taText.setEditable(true);
                        });

                        Button bCancelar = new Button("Cancelar");
                        bCancelar.setMaxSize(70, 30);
                        bCancelar.setMinSize(70, 30);
                        bCancelar.setOnAction((ActionEvent event) -> {
                            taText.setText(guardaTA);
                            taText.setEditable(false);
                        });
                        
                        Button bSalvar = new Button("Salvar");
                        bSalvar.setMaxSize(70, 30);
                        bSalvar.setMinSize(70, 30);
                        bSalvar.setOnAction((ActionEvent event) -> {
                            System.out.println("Passou");
                            String textF = taText.getText();
                            String dir = "src/view/txt/"+ (String)val;
                            System.out.println(textF);
                            try {
                                SearchController.salvarArq(dir, textF);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        
                        Button bRemover = new Button("Deletar");
                        bRemover.setMaxSize(70, 30);
                        bRemover.setMinSize(70, 30);
                        bRemover.setOnAction((ActionEvent event) -> {
                            //DELETAR corretamente;
                            String dir = "src/view/txt/"+ (String)val;
                            File arquivoDeletar = new File(dir);
                            arquivoDeletar.delete();
                            alPaginas.remove(paginaClicada);
                            
                            stage.setScene(null);
                            
                            stageRaking.setTitle("Ranking");
                            stageRaking.setScene(stage.getScene());
                            stageRaking.setResizable(false);
                            stageRaking.show();
                            
                            text.close();
                        });
                        
                        HBox vEditar = new HBox(10);
                        HBox vCancelar = new HBox(10);
                        HBox vSalvar = new HBox(10);
                        HBox vRemover = new HBox(10);
                        HBox vNovo = new HBox(10);
                        vEditar.getChildren().addAll(new Label(" "), bEditar);
                        vCancelar.getChildren().addAll(new Label(" "), bCancelar);
                        vSalvar.getChildren().addAll(new Label(" "), bSalvar);
                        vRemover.getChildren().addAll(new Label(" "), bRemover);
                        vNovo.getChildren().addAll(new Label(" "), bNovo);
                        
                        vboxSelection.getChildren().addAll(vNovo, vEditar, vCancelar, vSalvar, vRemover);
                        hboxText.getChildren().addAll(taText, vboxSelection);
                        text.setTitle("Editor");
                        text.setScene(new Scene(hboxText, 500, 350));
                        text.show();
                    }
                }
            });

            stage.setScene(new Scene(hboxRaking, 375, 375));
            stageRaking.setTitle("Ranking");
            stageRaking.setScene(stage.getScene());
            stageRaking.setResizable(false);
            stageRaking.show();
        });

        //Pesquisa usando a tecla enter;
        tfPalavras.setOnAction(e -> {
            System.out.println("Voce buscou: " + tfPalavras.getText());
            Palavra n = SearchController.search(new Palavra(tfPalavras.getText()));
            System.out.println("Foi achado: " + n);
            alPaginasBuscadas.clear();
            //Adiciona palavras nas listas; 
            if (n != null && !observableListPalavras.contains(n)) {
                observableListPalavras.add(n);
                for (int i = 0; i < n.getPaginas().size(); i++) {
                    Pagina pag = (Pagina) n.getPaginas().get(i);
                    if (!alPaginas.contains(pag)) {
                        pag.setRelevanciaPalavras(1);
                        alPaginas.add(pag);
                    } else {
                        int index = n.getPaginas().indexOf(pag);
                        Pagina pagRetorno = (Pagina) n.getPaginas().remove(index);
                        pagRetorno.setRelevanciaPalavras(1);
                        n.getPaginas().add(index, pagRetorno);
                    }
                }
            } else if (observableListPalavras.contains(n)) {
                int i = observableListPalavras.indexOf(n);
                Palavra palavraRetorno = (Palavra) observableListPalavras.remove(i);
                observableListPalavras.add(i, palavraRetorno);
            }
            
            //Lista de paginas encontradas
            if (n != null) {
                for (int i = 0; i < n.getPaginas().size(); i++) {
                    Pagina pag = (Pagina) n.getPaginas().get(i);
                    if (!alPaginasBuscadas.contains(pag)) {
                        alPaginasBuscadas.add(pag);
                    }
                }
            }
            
            Stage stage = new Stage();
            stage.setScene(null);
            TableView tvPaginas = new TableView();

            HBox hboxRaking = new HBox();
            HBox boxPagina = new HBox();

            TableColumn tcPaginas = new TableColumn("Paginas encontradas");
            
            tcPaginas.setMaxWidth(344);
            tcPaginas.setMinWidth(344);
            tcPaginas.setCellValueFactory(new PropertyValueFactory<>("nome"));

            TableColumn tcRelevancia = new TableColumn("R");
            tcRelevancia.setSortType(TableColumn.SortType.DESCENDING);
            
            tcRelevancia.setMaxWidth(37);
            tcRelevancia.setMinWidth(37);
            tcRelevancia.setCellValueFactory(new PropertyValueFactory<>("relevanciaPalavras"));
            
            observableListPaginasBuscadas = FXCollections.observableArrayList(alPaginasBuscadas);
            tvPaginas.setItems(observableListPaginasBuscadas);
            tvPaginas.getColumns().addAll(tcPaginas,tcRelevancia);
            tvPaginas.getSortOrder().add(tcRelevancia);
            
            hboxRaking.getChildren().addAll(boxPagina);
            boxPagina.getChildren().addAll(tvPaginas);

            tvPaginas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (tvPaginas.getSelectionModel().getSelectedItem() != null) {
                        Pagina paginaClicada = (Pagina) tvPaginas.getSelectionModel().getSelectedItem();
                        paginaClicada.setVisitas(1);
                        
                        TableViewSelectionModel selectionModel = tvPaginas.getSelectionModel();
                        ObservableList selectedCells = selectionModel.getSelectedCells();
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        Object val = tablePosition.getTableColumn().getCellData(newValue);
                        System.out.println("Selected Value: " + val);

                        String textRetorno = "";
                        try {
                            textRetorno = SearchController.imprimeTxt((String) val);
                        } catch (IOException ex) {
                        }
                        
                        Stage text = new Stage();
                        HBox hboxText = new HBox();
                        
                        VBox vboxSelection = new VBox(10,new Label(" "));
                        vboxSelection.setMinSize(100, 400);
                        vboxSelection.setMaxSize(100, 400);

                        TextArea taText = new TextArea();
                        taText.setPrefSize(450, 400);
                        taText.setEditable(false);
                        taText.setText(textRetorno);
                        
                        String guardaTA = taText.getText();
                        
                        TextArea taTextNovo = new TextArea();
                        taTextNovo.setPrefSize(450, 400);
                        taTextNovo.setEditable(true);
                        
                        TextField tfNovo = new TextField();
                        tfNovo.setMaxSize(70, 30);
                        tfNovo.setMinSize(70, 30);
                        
                        Button bSalvarArq = new Button("Salvar");
                        bSalvarArq.setMaxSize(70, 30);
                        bSalvarArq.setMinSize(70, 30);
                        bSalvarArq.setOnAction((ActionEvent event) -> {
                            System.out.println("Passou");
                            String textF = taTextNovo.getText();
                            String dir = "src/view/txt/"+ tfNovo.getText()+".txt";
                            System.out.println(textF);
                            try { 
                                SearchController.salvarArq(dir, textF);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        
                        Button bNovo = new Button("Novo");
                        bNovo.setMaxSize(70, 30);
                        bNovo.setMinSize(70, 30);
                        bNovo.setOnAction((ActionEvent event) -> {
                            Stage novo = new Stage();
                            HBox hboxTextNovo = new HBox();
                            
                            VBox vboxSelectionNovo = new VBox(10,new Label(" "));
                            vboxSelectionNovo.setMinSize(100, 400);
                            vboxSelectionNovo.setMaxSize(100, 400);

                            Button bSalvarNovo = new Button("Salvar");
                            bSalvarNovo.setMaxSize(70, 30);
                            bSalvarNovo.setMinSize(70, 30);
                            bSalvarNovo.setOnAction(bSalvarArq.getOnAction());
                            
                            HBox vSalvarNovo = new HBox(10);
                            HBox vLabel = new HBox(10);
                            HBox vTFNovo = new HBox(10);
                            vSalvarNovo.getChildren().addAll(new Label(" "), bSalvarNovo);
                            vLabel.getChildren().addAll(new Label(" "), new Label("    Arquivo"));
                            vTFNovo.getChildren().addAll(new Label(" "), tfNovo);
                            
                            vboxSelectionNovo.getChildren().addAll(vLabel, vTFNovo, vSalvarNovo);
                            hboxTextNovo.getChildren().addAll(taTextNovo, vboxSelectionNovo);
                            novo.setTitle("Novo Arquivo");
                            novo.setScene(new Scene(hboxTextNovo, 500, 350));
                            novo.show();
                        });
                        
                        Button bEditar = new Button("Editar");
                        bEditar.setMaxSize(70, 30);
                        bEditar.setMinSize(70, 30);
                        bEditar.setOnAction((ActionEvent event) -> {
                            taText.setEditable(true);
                        });

                        Button bCancelar = new Button("Cancelar");
                        bCancelar.setMaxSize(70, 30);
                        bCancelar.setMinSize(70, 30);
                        bCancelar.setOnAction((ActionEvent event) -> {
                            taText.setText(guardaTA);
                            taText.setEditable(false);
                        });
                        
                        Button bSalvar = new Button("Salvar");
                        bSalvar.setMaxSize(70, 30);
                        bSalvar.setMinSize(70, 30);
                        bSalvar.setOnAction((ActionEvent event) -> {
                            System.out.println("Passou");
                            String textF = taText.getText();
                            String dir = "src/view/txt/"+ (String)val;
                            System.out.println(textF);
                            try {
                                SearchController.salvarArq(dir, textF);
                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        
                        Button bRemover = new Button("Deletar");
                        bRemover.setMaxSize(70, 30);
                        bRemover.setMinSize(70, 30);
                        bRemover.setOnAction((ActionEvent event) -> {
                            //DELETAR corretamente;
                            String dir = "src/view/txt/"+ (String)val;
                            File arquivoDeletar = new File(dir);
                            arquivoDeletar.delete();
                            alPaginas.remove(paginaClicada);
                            
                            stage.setScene(null);
                            
                            stageRaking.setTitle("Ranking");
                            stageRaking.setScene(stage.getScene());
                            stageRaking.setResizable(false);
                            stageRaking.show();
                            
                            text.close();
                        });
                        
                        HBox vEditar = new HBox(10);
                        HBox vCancelar = new HBox(10);
                        HBox vSalvar = new HBox(10);
                        HBox vRemover = new HBox(10);
                        HBox vNovo = new HBox(10);
                        vEditar.getChildren().addAll(new Label(" "), bEditar);
                        vCancelar.getChildren().addAll(new Label(" "), bCancelar);
                        vSalvar.getChildren().addAll(new Label(" "), bSalvar);
                        vRemover.getChildren().addAll(new Label(" "), bRemover);
                        vNovo.getChildren().addAll(new Label(" "), bNovo);
                        
                        vboxSelection.getChildren().addAll(vNovo, vEditar, vCancelar, vSalvar, vRemover);
                        hboxText.getChildren().addAll(taText, vboxSelection);
                        text.setTitle("Editor");
                        text.setScene(new Scene(hboxText, 500, 350));
                        text.show();
                    }
                }
            });

            stage.setScene(new Scene(hboxRaking, 375, 375));
            stageRaking.setTitle("Ranking");
            stageRaking.setScene(stage.getScene());
            stageRaking.setResizable(false);
            stageRaking.show();
        });

        hboxLabel.getChildren().add(bPesquisar);
        hboxLabel.getChildren().add(b);

        HBox hboxTV = new HBox();

        vboxPesquisa.getChildren().add(hboxTV);
        hboxPesquisa.getChildren().add(vboxPesquisa);

        primaryStage.setTitle("FeiraGugou");
        primaryStage.setScene(new Scene(hboxPesquisa, 375, 120));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}