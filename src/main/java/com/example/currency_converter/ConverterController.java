package com.example.currency_converter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;

public class ConverterController {
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> fromCurrency;
    @FXML
    private ComboBox<String> toCurrency;
    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        fromCurrency.getItems().addAll("USD","EUR","BDT","INR","BTC");
        toCurrency.getItems().addAll("USD","EUR","BDT","INR","BTC");
    }
    @FXML
    void handleConvert(ActionEvent event) {
        String amountText = amountField.getText();
        if (amountText.isEmpty() || !amountText.matches("\\d+(\\.\\d+)?")) {
            resultLabel.setText("Invalid Amount");
            return;
        }

        if (fromCurrency.getValue() == null || toCurrency.getValue() == null) {
            resultLabel.setText("Please select Both Currencies");
            return;
        }

        double amount = 0;
        try {
            amount = Double.parseDouble(amountField.getText().trim());
            // conversion logic...
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid Amount");
        }

        double rate = getConversionRate(fromCurrency.getValue(), toCurrency.getValue());
        double result = amount * rate;

        resultLabel.setText(String.format("Result: %.2f %s", result, toCurrency.getValue()));

        //save history
        HelloApplication.history.add(new ConversionRecord(
                fromCurrency.getValue(), toCurrency.getValue(),
                amount, result
        ));
    }

    private double getConversionRate(String from, String to) {
        if (from.equals(to)) {
            return 1.0;
        }
        if (from.equals("USD") && to.equals("BDT")) {
            return 110.0;
        }
        if (from.equals("BDT") && to.equals("USD")) {
            return 0.0091;
        }
        if (from.equals("USD") && to.equals("EUR")) {
            return 0.9;
        }
        if (from.equals("EUR") && to.equals("USD")) {
            return 1.1;
        }
        if (from.equals("USD") && to.equals("INR")) {
            return 83.0;
        }
        if (from.equals("INR") && to.equals("USD")) {
            return 0.012;
        }

        return 1.0; //same currency
    }


    @FXML
    void handleViewHistory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) amountField.getScene().getWindow();
        stage.setScene(scene);
    }


}
