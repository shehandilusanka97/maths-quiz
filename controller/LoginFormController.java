package controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXTextField txtName;

    int score = 0;
    public JFXButton btnPlay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtName.requestFocus();

    }



    public void txtNameEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            btnPlay.requestFocus();

        }

    }

    public void onAction_btnPlay(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().matches("^[A-z]{2,}$")){
            Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) btnPlay.getScene().getWindow();
            primaryStage.close();
            stage.setTitle("Main Form");
            Name.name=txtName.getText();


        }else {
            txtName.setUnFocusColor(Paint.valueOf("Red"));
            new Shake(txtName).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
            txtName.setUnFocusColor(Paint.valueOf("Red"));
            txtName.setText("");
            txtName.requestFocus();

        }

    }

}
