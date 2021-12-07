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
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class Level1FormController implements Initializable {
    public Label score;
    public Label level;
    public Label num1;
    public Label num2;
    public Label num4;
    public Label num5;
    public Label num3;
    public JFXButton btnBack;
    public JFXTextField txtTotal;
    public JFXButton btnSubmit;
    Random rand = new Random();
    int min = 1;
    int max = 50;

   List<Integer> r_numbers = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomApi();
        num1.setText(String.valueOf(r_numbers.get(0)));
        num2.setText(String.valueOf(r_numbers.get(1)));
        num3.setText(String.valueOf(r_numbers.get(2)));
        num4.setText(String.valueOf(r_numbers.get(3)));
        num5.setText(String.valueOf(r_numbers.get(4)));
        System.out.println(getTotal());

    }

    public void randomApi() {
        for (int i = 0; i < 5; i++) {
            try {
                URL user_url = new URL("https://csrng.net/csrng/csrng.php?min=" + min + "&max=" + max);
                HttpURLConnection connection = (HttpURLConnection) user_url.openConnection();
                connection.setConnectTimeout(1);
                connection.setReadTimeout(1 );
                connection.setDoOutput(true);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("GET");
                connection.connect();

                StringBuilder sb = new StringBuilder();
                String text = "";
                String substring = "";
                String a = "";

                int HttpResult = connection.getResponseCode();
                if (HttpResult == HttpsURLConnection.HTTP_OK) {


                    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                            "utf-8"))) {
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            text = line;

                        }
                        substring = text.substring(1, text.length() - 1);
                    }

                    JSONObject jSONObject = new JSONObject(substring);
                    a = jSONObject.get("random").toString();
                    r_numbers.add(Integer.valueOf(a));
                }

            } catch (Exception ex) {
                int size = r_numbers.size();
                for (int j = 0; j < 5 - size; j++) {
                    Random random = new Random();
                    int upperbound = max;
                    int int_random = random.nextInt(upperbound);
                    r_numbers.add(int_random);
                }

                ex.getMessage();

            }
        }


    }

    public int getTotal() {
        int value1 = Integer.parseInt(num1.getText());
        int value2 = Integer.parseInt(num2.getText());
        int value3 = Integer.parseInt(num3.getText());
        int value4 = Integer.parseInt(num4.getText());
        int value5 = Integer.parseInt(num5.getText());
        int total = value1 + value2 + value3 + value4 + value5;
        return total;

    }


    public void onAction_Submit(ActionEvent actionEvent) throws IOException {
//        //get the answer

        int answer = Integer.parseInt(txtTotal.getText());
        if (getTotal() == answer) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Level2Form.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) btnSubmit.getScene().getWindow();
            primaryStage.close();
            stage.setTitle("Level 2");


        } else {
            new Alert(Alert.AlertType.ERROR, "Game Over ..!!!", ButtonType.OK).show();
            try {
                Connection connection = DBConnection.getInstance().getConnection();
               PreparedStatement pst = connection.prepareStatement("insert into players (pName, score) values (?,?);");
               pst.setString(1,Name.name);
               pst.setInt(2,0);
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
