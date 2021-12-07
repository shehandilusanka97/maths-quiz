package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mathsQuizTM.scoreboradTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CheckScoreFormController implements Initializable {

    public TableView<scoreboradTM> tblScore;
    public TableColumn clmName;
    public TableColumn clmScore;
    public JFXButton btnBack;
    public TableColumn clmId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblScore.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblScore.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblScore.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("score"));

        try {
            LoadAllPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void LoadAllPlayers() throws SQLException, ClassNotFoundException {
        try {
        ObservableList<scoreboradTM> customers = tblScore.getItems();
        customers.clear();
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Players");
        while (rst.next()) {
            int id = rst.getInt(1);
            String name = rst.getString(2);
            int score = rst.getInt(3);
            customers.add(new scoreboradTM(id, name, score));
        }

    }catch (SQLException e) {
            e.printStackTrace();
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
