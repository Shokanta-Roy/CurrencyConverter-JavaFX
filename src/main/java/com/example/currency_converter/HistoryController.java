package com.example.currency_converter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;


public class HistoryController {
    @FXML
    private TableView<ConversionRecord> historyTable;
    @FXML
    private TableColumn<ConversionRecord,String> fromCol;
    @FXML
    private TableColumn<ConversionRecord,String> toCol;
    @FXML
    private TableColumn<ConversionRecord,Double> amountCol;
    @FXML
    private TableColumn<ConversionRecord,Double> resultCol;


    @FXML
    public void initialize() {
        fromCol.setCellValueFactory(new PropertyValueFactory<>("fromCurrency"));
        toCol.setCellValueFactory(new PropertyValueFactory<>("toCurrency"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("result"));

        historyTable.setItems(FXCollections.observableArrayList(HelloApplication.history));


    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("converter.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) historyTable.getScene().getWindow();
        stage.setScene(scene);
    }
}
