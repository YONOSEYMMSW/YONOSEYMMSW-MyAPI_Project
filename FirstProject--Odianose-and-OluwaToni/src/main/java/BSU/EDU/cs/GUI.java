package BSU.EDU.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class GUI extends Application {


    @FXML
    private TextField articleTextField;

    @FXML
    private Button fetchButton;

    @FXML
    private TextArea resultTextArea;

    @Override
    public void start(Stage stage) throws Exception {
        // Loading the FXML file using GUI.fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/BSU/EDU/cs/GUI.fxml")));

       //Check for null
        if (root == null) {
            throw new IllegalStateException("FXML file not found! Please check the path.");
        }

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Wikipedia Recent Changes");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void initialize() {
        fetchButton.setOnAction(event -> fetchWikipediaData());
    }

    private void fetchWikipediaData() {
        String articleName = articleTextField.getText();


        if (articleName == null || articleName.trim().isEmpty()) {
            showErrorPopup("Please enter a valid article name.");
            return;
        }


        ArticleDataFetcher dataFetcher = new ArticleDataFetcher();
        try {

            String result = dataFetcher.fetchArticleData(articleName);
            resultTextArea.setText(result);
        } catch (IOException e) {

            showErrorPopup("Error fetching data: " + e.getMessage());
        } catch (RuntimeException e) {

            showErrorPopup("An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
