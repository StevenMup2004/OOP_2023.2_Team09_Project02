package screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import exception.*;
import screen.controller.operation.DeleteMakeBalancedPressed;
import screen.controller.operation.DeletePressed;
import screen.controller.operation.InsertPressed;
import treedatastructure.BalancedTree;
import treedatastructure.GenericTree;
import treedatastructure.Node;

public class BalancedTreeController extends GenericTreeController {

    public BalancedTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeType(treeType);
        this.setTreeDataStructure(new BalancedTree());
    }

    public BalancedTreeController(Stage stage, String treeType, int max_depth) {
        super(stage, treeType);
        this.setTreeType(treeType);
        this.setTreeDataStructure(new BalancedTree(max_depth));
    }

    @FXML
    protected void btnInsertPressed(ActionEvent event) throws TreeException {

        String node_val = this.getTfNodeInsert().getText();
        String parent_val = this.getTfParentInsert().getText();
        int intNodeVal = Integer.parseInt(node_val);
        int intParentVal = Integer.parseInt(parent_val);

        try {
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxInsertB.setVisible(true);
            vBoxDeleteB.setVisible(false);
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
            alert.setContentText("Look likes the child node has existed.");

            alert.showAndWait();
        }

        catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the parent node does not exist.");

            alert.showAndWait();
        }

        catch (TreeNotBalancedException e) {
            BalancedTree balancedTree = (BalancedTree) this.getTreeDataStructure();
            Node minDepthNode = balancedTree.findMinDepthLeaf();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Looks like the inserted node invades the balance property of tree. Do you still want to insert it?" + "\nIf you continue to insert, the parent node is changed to"+ minDepthNode.getNodeId());
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("User clicked OK");
                    InsertPressed insertPressed = new InsertPressed(this.getTreeDataStructure(), this, this.getScenePane(),
                    intNodeVal, minDepthNode.getNodeId());
                    insertPressed.run();
                    this.getHistory().add(insertPressed);
                }
            });
            ;
        }
    }

    @FXML
    protected void btnDeletePressed(ActionEvent event) throws TreeException {
        String delNodeVal = this.getTfNodeDelete().getText();

        int intDelNodeVal = Integer.parseInt(delNodeVal);
        try {
            this.getTreeDataStructure().checkDeleteNode(intDelNodeVal);
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxInsertB.setVisible(false);
            vBoxDeleteB.setVisible(true);
            DeletePressed deletePressed = new DeletePressed((GenericTree) this.getTreeDataStructure(), this.getScenePane(), this, intDelNodeVal);
            deletePressed.run();
            this.getHistory().add(deletePressed);

        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you want to delete does not exist.");

            alert.showAndWait();
        } catch (TreeNotBalancedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.getDialogPane().setPrefSize(480, 320);
            alert.setContentText(
                    "Looks like the deleted node will invade the balance property of tree..\nDo you want to delete?\nWARNING: We will change the tree to restore its balance!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    DeleteMakeBalancedPressed deleteMakeBalancedPressed = new DeleteMakeBalancedPressed(this,
                            this.getTreeDataStructure(), intDelNodeVal, scenePane);
                    deleteMakeBalancedPressed.run();
                    this.getHistory().add(deleteMakeBalancedPressed);

                }
            });
        }

    }

    public void removeTreeFromGUI() {
        this.getScenePane().getChildren().clear();
    }

}
