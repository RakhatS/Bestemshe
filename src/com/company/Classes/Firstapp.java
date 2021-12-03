package com.company.Classes;


import com.company.MainGameField;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Firstapp extends GameField{
    @FXML
    private VBox vbox;
    @FXML
    private Button resButton;
    @FXML
    private Button buttonPlay;
    @FXML
    private TextField txtFirstPlayerName;
    @FXML
    private TextField txtSecondPlayerName;
    @FXML
    private Text txtError;
    @FXML
    private Text txtWin;
    @FXML
    private Button buttonClose;

    @FXML
    void initialize() {
       txtWin.setText(getWinTxt());
        buttonClose.setOnAction(actionEvent -> {
            System.exit(0);

        });
        buttonPlay.setOnAction(actionEvent -> {
            setNamePlayers(txtFirstPlayerName.getText(), txtSecondPlayerName.getText());
            if(txtFirstPlayerName.getText().trim().isEmpty() || txtSecondPlayerName.getText().trim().isEmpty()){ // ойыншының атын енгізуді міндет ететін шарт
                txtError.setText("Oiynşylardyñ atyn engızıñız");
            }
            else if(txtFirstPlayerName.getText().length() > 10 || txtSecondPlayerName.getText().length() > 10){ // ойыншының аты 10 әріптен аспауы қажет
                txtError.setText("Oiynşylardyñ esımı 10 ärıpten aspauy qajet");
            }
            else {
                buttonPlay.getScene().getWindow().hide(); // экран жабылады
                MainGameField app = new MainGameField(); // обьект класса MainGameField
                try {
                    app.start(new Stage()); // MainGameField классын запустить ету
                } catch (Exception e) { // исключение
                    e.printStackTrace();
                }
            }
        });
    }
}