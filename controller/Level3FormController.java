package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class Level3FormController implements Initializable {
    public Label score;
    public Label level;
    public JFXButton btnBack;
    public Label num1;
    public Label num2;
    public Label num3;
    public JFXTextField txtTotal;
    public JFXButton btnSubmit;
    Random rand = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        num1.setText((valueOf(rand.nextInt(20))));
        num2.setText((valueOf(rand.nextInt(20))));
        num3.setText((valueOf(rand.nextInt(20))));
        System.out.println(getTotal());

    }


    public int getTotal(){
        int value1 = Integer.parseInt(num1.getText());
        int value2 = Integer.parseInt(num2.getText());
        int value3 = Integer.parseInt(num3.getText());
        int total = value1*value2*value3;
        return total;

    }


    public void onAction_Submit(ActionEvent actionEvent) throws IOException {
        int answer = Integer.parseInt(txtTotal.getText());
        if (getTotal()==answer) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Level4Form.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) btnSubmit.getScene().getWindow();
            primaryStage.close();
            stage.setTitle("Level 4");
        }else{
            new Alert(Alert.AlertType.ERROR, "Game Over ..!!!", ButtonType.OK).show();
            txtTotal.setDisable(true);
            try {
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement("insert into players (pName, score) values (?,?);");
                pst.setString(1,Name.name);
                pst.setInt(2,20);
                pst.execute();


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            txtTotal.clear();
            txtTotal.setDisable(true);


        }
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
