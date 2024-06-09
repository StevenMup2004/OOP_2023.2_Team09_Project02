package screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import exception.TreeException;
import screen.controller.operation.InsertPressed;
import treedatastructure.BinaryTree;

public class BinaryTreeController extends GenericTreeController {
    public BinaryTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeDataStructure(new BinaryTree());
        this.setTreeType(treeType);
    }

    @FXML
    @Override
    protected void btnInsertPressed(ActionEvent event) throws TreeException {

        try {
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(true);
            vBoxDelete.setVisible(false);
            vBoxUpdate.setVisible(false);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);
            String node_val = this.getTfNodeInsert().getText();
            String parent_val = this.getTfParentInsert().getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);

            this.getTreeDataStructure().checkInsertNode(intParentVal, intNodeVal);

            InsertPressed insertPressed = new InsertPressed(this.getTreeDataStructure(), this, this.getScenePane(),
                    intNodeVal, intParentVal);

            insertPressed.run();

            this.getHistory().add(insertPressed);
        }

        catch (NodeExistedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Look likes the parent node does not exist.");

            alert.showAndWait();
        }

        catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the child node has existed.");

            alert.showAndWait();
        }

        catch (NodeFullChildrenException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node invades the binary property of tree.");
            alert.showAndWait();
        }
        this.getTfNodeInsert().clear();
        this.getTfParentInsert().clear();
    }

}
