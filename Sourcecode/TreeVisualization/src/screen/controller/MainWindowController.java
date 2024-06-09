package screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnGenericTree;

    @FXML
    private Button btnBinaryTree;

    @FXML
    private Button btnBalancedTree;

    @FXML
    private Button btnBalancedBinaryTree;

    private Stage mainStage;
   
    @FXML
    private Button helpButton;
    
    @FXML
    void btnGenericTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GenericTreeController genericController = new GenericTreeController(mainStage, "Generic Tree Visualizer");
        loader.setController(genericController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Generic Tree");
        this.mainStage.setScene(scene);
        this.mainStage.setFullScreen(true);
        this.mainStage.show();
    }

    @FXML
    void btnBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BinaryTreeController binaryController = new BinaryTreeController(mainStage, "Binary Tree Visualizer");
        loader.setController(binaryController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Binary Tree");
        this.mainStage.setFullScreen(true);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnBalancedTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(null);
        dialog.setHeaderText("Enter the maximum difference in distance\n from root of the leaf nodes");
        dialog.setContentText("MAX_DIFF_DISTANCE:");
        dialog.setGraphic(null);
        Optional<String> result = dialog.showAndWait();
        int max_depth = Integer.parseInt(result.get());

        BalancedTreeController balancedTreeController = new BalancedTreeController(mainStage,
                "Balanced Tree Visualizer", max_depth);
        loader.setController(balancedTreeController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Balanced Tree");
        this.mainStage.setFullScreen(true);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML

    void btnBalancedBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(null);
        dialog.setHeaderText("Enter the maximum difference in distance\n from root of the leaf nodes");
        dialog.setContentText("MAX_DIFF_DISTANCE:");
        dialog.setGraphic(null);
        Optional<String> result = dialog.showAndWait();
        int max_depth = Integer.parseInt(result.get());

        BalancedBinaryTreeController balancedBinaryTreeController = new BalancedBinaryTreeController(mainStage,
                "Balanced Binary Tree Visualizer", max_depth);
        loader.setController(balancedBinaryTreeController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Balanced Binary Tree");
        this.mainStage.setFullScreen(true);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnExitPressed(ActionEvent event) {
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.close();
    }

    public void helpPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/help.fxml"));
        HelpController helpController = new HelpController();
        loader.setController(helpController);
        Stage helpStage = new Stage();
        Scene scene = new Scene(loader.load());
        helpStage.setScene(scene);
        helpStage.setTitle("Help");
        helpStage.showAndWait();
    }
}