package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Level2FormController {
    public Label score;
    public Label level;
    public JFXButton btnBack;
    public Label num1;
    public Label num2;
    public JFXButton btnNum1;
    public JFXButton btnNum2;
    public JFXButton btnNum3;
    public JFXButton btnNum4;

    public void onAction_btnNum4(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Level3Form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage primaryStage = (Stage) btnNum4.getScene().getWindow();
        primaryStage.close();
        stage.setTitle("Level 3");
    }


    public void onAction_btnNum1(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.ERROR, "Game Over ..!!!", ButtonType.OK).show();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into players (pName, score) values (?,?);");
            pst.setString(1,Name.name);
            pst.setInt(2,10);
            pst.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnNum1.setDisable(true);
        btnNum2.setDisable(true);
        btnNum3.setDisable(true);
        btnNum4.setDisable(true);

    }
    public void onAction_btnNum2(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.ERROR, "Game Over ..!!!", ButtonType.OK).show();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into players (pName, score) values (?,?);");
            pst.setString(1,Name.name);
            pst.setInt(2,10);
            pst.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnNum1.setDisable(true);
        btnNum2.setDisable(true);
        btnNum3.setDisable(true);
        btnNum4.setDisable(true);
    }
    public void onAcrtion_btnNum3(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.ERROR, "Game Over ..!!!", ButtonType.OK).show();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into players (pName, score) values (?,?);");
            pst.setString(1,Name.name);
            pst.setInt(2,10);
            pst.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnNum1.setDisable(true);
        btnNum2.setDisable(true);
        btnNum3.setDisable(true);
        btnNum4.setDisable(true);
    }

    public void onAction_btnBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.close();
        stage.setTitle("Main Form");
    }
}
