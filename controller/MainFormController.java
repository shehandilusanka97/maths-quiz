package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public JFXButton btnNewGame;
    public JFXButton btnCheckScore;
    public AnchorPane root;

    public void onAction_btnNewGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/Level1Form.fxml"));
        Stage stage2 = new Stage();
        Scene scene = new Scene(root);
        stage2.setScene(scene);
        stage2.show();
        Stage primaryStage = (Stage) btnNewGame.getScene().getWindow();
        primaryStage.close();
        stage2.setTitle("Level 1");



    }

    public void onAction_btnCheckScore(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/CheckScoreForm.fxml"));
        Stage stage2 = new Stage();
        Scene scene = new Scene(root);
        stage2.setScene(scene);
        stage2.show();
        Stage primaryStage = (Stage) btnCheckScore.getScene().getWindow();
        primaryStage.close();
        stage2.setTitle("Score Board");

    }
}
