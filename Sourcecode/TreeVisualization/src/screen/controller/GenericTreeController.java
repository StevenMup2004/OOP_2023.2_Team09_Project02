package screen.controller;

import exception.*;
import screen.controller.operation.*;
import treedatastructure.GenericTree;
import treedatastructure.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GenericTreeController {

    @FXML
    private Button btnOpsCreate;

    @FXML
    private Button btnOpsInsert;

    @FXML
    private Button btnOpsDelete;

    @FXML
    private Button btnOpsUpdate;

    @FXML
    private Button btnOpsTraverse;

    @FXML
    private StackPane stackPaneInput;

    @FXML
    private HBox hBoxCreate;

    @FXML
    private HBox hBoxManual;

    @FXML
    private TextField tfRootCreate;

    @FXML
    private Button btnCreate;

    @FXML
    private HBox hBoxTraverse;

    @FXML
    private RadioButton radioBtnBFS;

    @FXML
    private RadioButton radioBtnDFS;

    @FXML
    private TextField tfNodeTraverse;

    @FXML
    private Button btnTraverse;

    @FXML
    private HBox hBoxDelete;

    @FXML
    private TextField tfNodeDelete;

    @FXML
    private Button btnDelete;

    @FXML
    private HBox hBoxInsert;

    @FXML
    private HBox hBoxSearch;

    @FXML
    private TextField tfParentInsert;

    @FXML
    private TextField tfNodeInsert;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnSearch;

    @FXML
    private HBox hBoxUpdate;

    @FXML
    private TextField tfOldNodeUpdate;

    @FXML
    private TextField tfNewNodeUpdate;

    @FXML
    private Button btnUpdate;

    @FXML
    private VBox vBoxPseudo;

    @FXML
    protected StackPane stackPanePseudo;

    @FXML
    protected Pane scenePane;

    @FXML
    private TextField tfNodeSearch;
    @FXML
    private Rectangle recPseudoInsertB1;
    @FXML
    private Rectangle recPseudoInsertB2;
    @FXML
    private Rectangle recPseudoInsertB3;
    @FXML
    private Rectangle recPseudoInsertB4;
    @FXML
    private Rectangle recPseudoInsertB5;
    @FXML
    private Rectangle recPseudoInsertB6;
    @FXML
    private Rectangle recPseudoInsertB7;
    @FXML
    private Rectangle recPseudoDeleteB1;
    @FXML
    private Rectangle recPseudoDeleteB2;
    @FXML
    private Rectangle recPseudoDeleteB3;
    @FXML
    private Rectangle recPseudoDeleteB4;
    @FXML
    private Rectangle recPseudoDeleteB5;
    @FXML
    private Rectangle recPseudoDeleteB6;
    @FXML
    private Rectangle recPseudoDeleteB7;
    @FXML
    private Rectangle recPseudoBFS1;
    @FXML
    private Rectangle recPseudoBFS2;
    @FXML
    private Rectangle recPseudoBFS3;
    @FXML
    private Rectangle recPseudoBFS4;
    @FXML
    private Rectangle recPseudoBFS5;

    @FXML
    private Rectangle recPseudoDFS1;

    @FXML
    private Rectangle recPseudoDFS2;

    @FXML
    private Rectangle recPseudoDFS3;

    @FXML
    private Rectangle recPseudoDFS4;

    @FXML
    private Rectangle recPseudoDFS5;

    @FXML
    private Rectangle recPseudoSearch1;
    @FXML
    private Rectangle recPseudoSearch2;
    @FXML
    private Rectangle recPseudoSearch3;
    @FXML
    private Rectangle recPseudoSearch4;
    @FXML
    private Rectangle recPseudoSearch5;
    @FXML
    private Rectangle recPseudoSearch6;
    @FXML
    private Rectangle recPseudoSearch7;

    @FXML
    private Rectangle recPseudoInsert1;
    @FXML
    private Rectangle recPseudoInsert2;
    @FXML
    private Rectangle recPseudoInsert3;
    @FXML
    private Rectangle recPseudoInsert4;
    @FXML
    private Rectangle recPseudoInsert5;
    @FXML
    private Rectangle recPseudoInsert6;
    @FXML
    private Rectangle recPseudoInsert7;
    @FXML
    private Rectangle recPseudoDelete1;
    @FXML
    private Rectangle recPseudoDelete2;
    @FXML
    private Rectangle recPseudoDelete3;
    @FXML
    private Rectangle recPseudoDelete4;
    @FXML
    private Rectangle recPseudoDelete5;
    @FXML
    private Rectangle recPseudoDelete6;
    @FXML
    private Rectangle recPseudoDelete7;
    @FXML
    private Rectangle recPseudoUpdate1;
    @FXML
    private Rectangle recPseudoUpdate2;
    @FXML
    private Rectangle recPseudoUpdate3;
    @FXML
    private Rectangle recPseudoUpdate4;
    @FXML
    private Rectangle recPseudoUpdate5;
    @FXML
    private Rectangle recPseudoUpdate6;
    @FXML
    private Rectangle recPseudoUpdate7;

    @FXML
    protected VBox vBoxBFS;
    @FXML
    protected VBox vBoxUpdate;
    @FXML
    protected VBox vBoxDFS;
    @FXML
    public VBox vBoxSearch;
    @FXML
    protected VBox vBoxDelete;
    @FXML
    protected VBox vBoxInsert;
    @FXML
    protected VBox vBoxInsertB;
    @FXML
    protected VBox vBoxDeleteB;
    @FXML
    private RadioButton radioBtnManual;

    @FXML
    private RadioButton radioBtnRandom;

    @FXML
    private Label mainLabel;

    private Stage menuStage;

    private Scene mainScene;

    private String algorithm;
    @FXML
    private Button forwardTraverseBtn;
    @FXML
    private Button backwardTraverseBtn;
    @FXML
    private Button pauseTraverseBtn;
    @FXML
    private Button continueTraverseBtn;
    @FXML
    private Button okTraverseBtn;
    @FXML
    private Button forwardInsertBtn;
    @FXML
    private Button backwardInsertBtn;
    @FXML
    private Button pauseInsertBtn;
    @FXML
    private Button continueInsertBtn;
    @FXML
    private Button okInsertBtn;

    @FXML
    private Button forwardDeleteBtn;
    @FXML
    private Button backwardDeleteBtn;
    @FXML
    private Button pauseDeleteBtn;
    @FXML
    private Button continueDeleteBtn;
    @FXML
    private Button okDeleteBtn;

    @FXML
    private Button forwardSearchBtn;
    @FXML
    private Button backwardSearchBtn;
    @FXML
    private Button pauseSearchBtn;
    @FXML
    private Button continueSearchBtn;
    @FXML
    private Button okSearchBtn;

    @FXML
    private Button forwardUpdateBtn;
    @FXML
    private Button backwardUpdateBtn;
    @FXML
    private Button pauseUpdateBtn;
    @FXML
    private Button continueUpdateBtn;
    @FXML
    private Button okUpdateBtn;

    private ArrayList<UserAction> history = new ArrayList<UserAction>();

    private String treeType;

    private GenericTree treeDataStructure;

    private int SearchID;

    public GenericTreeController(Stage stage, String treeType) {
        this.menuStage = stage;
        this.setTreeType(treeType);
        this.setTreeDataStructure(new GenericTree());
    }

    public VBox getvBoxUpdate() {
        return vBoxUpdate;
    }

    public GenericTree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public void setTreeDataStructure(GenericTree tree) {
        this.treeDataStructure = tree;
    }

    public Rectangle getRecPseudoInsertB1() {
        return recPseudoInsertB1;
    }

    public Rectangle getRecPseudoInsertB2() {
        return recPseudoInsertB2;
    }

    public Rectangle getRecPseudoInsertB3() {
        return recPseudoInsertB3;
    }

    public Rectangle getRecPseudoInsertB4() {
        return recPseudoInsertB4;
    }

    public Rectangle getRecPseudoInsertB5() {
        return recPseudoInsertB5;
    }

    public Rectangle getRecPseudoInsertB6() {
        return recPseudoInsertB6;
    }

    public Rectangle getRecPseudoInsertB7() {
        return recPseudoInsertB7;
    }

    public Rectangle getRecPseudoDeleteB1() {
        return recPseudoDeleteB1;
    }

    public Rectangle getRecPseudoDeleteB2() {
        return recPseudoDeleteB2;
    }

    public Rectangle getRecPseudoDeleteB3() {
        return recPseudoDeleteB3;
    }

    public Rectangle getRecPseudoDeleteB4() {
        return recPseudoDeleteB4;
    }

    public Rectangle getRecPseudoDeleteB5() {
        return recPseudoDeleteB5;
    }

    public Rectangle getRecPseudoDeleteB6() {
        return recPseudoDeleteB6;
    }

    public Rectangle getRecPseudoDeleteB7() {
        return recPseudoDeleteB7;
    }

    public Rectangle getRecPseudoBFS1() {
        return recPseudoBFS1;
    }

    public Rectangle getRecPseudoBFS2() {
        return recPseudoBFS2;
    }

    public Rectangle getRecPseudoBFS3() {
        return recPseudoBFS3;
    }

    public Rectangle getRecPseudoBFS4() {
        return recPseudoBFS4;
    }

    public Rectangle getRecPseudoBFS5() {
        return recPseudoBFS5;
    }

    public Rectangle getRecPseudoDFS1() {
        return recPseudoDFS1;
    }

    public Rectangle getRecPseudoDFS2() {
        return recPseudoDFS2;
    }

    public Rectangle getRecPseudoDFS3() {
        return recPseudoDFS3;
    }

    public Rectangle getRecPseudoDFS4() {
        return recPseudoDFS4;
    }

    public Rectangle getRecPseudoDFS5() {
        return recPseudoDFS5;
    }

    public Rectangle getrecPseudoSearch1() {
        return recPseudoSearch1;
    }

    public Rectangle getrecPseudoSearch2() {
        return recPseudoSearch2;
    }

    public Rectangle getrecPseudoSearch3() {
        return recPseudoSearch3;
    }

    public Rectangle getrecPseudoSearch4() {
        return recPseudoSearch4;
    }

    public Rectangle getrecPseudoSearch5() {
        return recPseudoSearch5;
    }

    public Button getBtnOpsCreate() {
        return btnOpsCreate;
    }

    public Button getBtnOpsInsert() {
        return btnOpsInsert;
    }

    public Button getBtnOpsDelete() {
        return btnOpsDelete;
    }

    public Button getBtnOpsUpdate() {
        return btnOpsUpdate;
    }

    public Button getBtnOpsTraverse() {
        return btnOpsTraverse;
    }

    public StackPane getStackPaneInput() {
        return stackPaneInput;
    }

    public HBox gethBoxCreate() {
        return hBoxCreate;
    }

    public HBox gethBoxManual() {
        return hBoxManual;
    }

    public TextField getTfRootCreate() {
        return tfRootCreate;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public HBox gethBoxTraverse() {
        return hBoxTraverse;
    }

    public RadioButton getRadioBtnBFS() {
        return radioBtnBFS;
    }

    public RadioButton getRadioBtnDFS() {
        return radioBtnDFS;
    }

    public TextField getTfNodeTraverse() {
        return tfNodeTraverse;
    }

    public Button getBtnTraverse() {
        return btnTraverse;
    }

    public HBox gethBoxDelete() {
        return hBoxDelete;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public HBox gethBoxInsert() {
        return hBoxInsert;
    }

    public HBox gethBoxSearch() {
        return hBoxSearch;
    }

    public Button getBtnInsert() {
        return btnInsert;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public HBox gethBoxUpdate() {
        return hBoxUpdate;
    }

    public TextField getTfOldNodeUpdate() {
        return tfOldNodeUpdate;
    }

    public TextField getTfNewNodeUpdate() {
        return tfNewNodeUpdate;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public VBox getvBoxPseudo() {
        return vBoxPseudo;
    }

    public Rectangle getRecPseudoSearch1() {
        return recPseudoSearch1;
    }

    public Rectangle getRecPseudoSearch2() {
        return recPseudoSearch2;
    }

    public Rectangle getRecPseudoSearch3() {
        return recPseudoSearch3;
    }

    public Rectangle getRecPseudoSearch4() {
        return recPseudoSearch4;
    }

    public Rectangle getRecPseudoSearch5() {
        return recPseudoSearch5;
    }

    public Rectangle getRecPseudoSearch6() {
        return recPseudoSearch6;
    }

    public Rectangle getRecPseudoSearch7() {
        return recPseudoSearch7;
    }

    public Rectangle getRecPseudoInsert1() {
        return recPseudoInsert1;
    }

    public Rectangle getRecPseudoInsert2() {
        return recPseudoInsert2;
    }

    public Rectangle getRecPseudoInsert3() {
        return recPseudoInsert3;
    }

    public Rectangle getRecPseudoInsert4() {
        return recPseudoInsert4;
    }

    public Rectangle getRecPseudoInsert5() {
        return recPseudoInsert5;
    }

    public Rectangle getRecPseudoInsert6() {
        return recPseudoInsert6;
    }

    public Rectangle getRecPseudoInsert7() {
        return recPseudoInsert7;
    }

    public Rectangle getRecPseudoDelete1() {
        return recPseudoDelete1;
    }

    public Rectangle getRecPseudoDelete2() {
        return recPseudoDelete2;
    }

    public Rectangle getRecPseudoDelete3() {
        return recPseudoDelete3;
    }

    public Rectangle getRecPseudoDelete4() {
        return recPseudoDelete4;
    }

    public Rectangle getRecPseudoDelete5() {
        return recPseudoDelete5;
    }

    public Rectangle getRecPseudoDelete6() {
        return recPseudoDelete6;
    }

    public Rectangle getRecPseudoDelete7() {
        return recPseudoDelete7;
    }

    public Rectangle getRecPseudoUpdate1() {
        return recPseudoUpdate1;
    }

    public Rectangle getRecPseudoUpdate2() {
        return recPseudoUpdate2;
    }

    public Rectangle getRecPseudoUpdate3() {
        return recPseudoUpdate3;
    }

    public Rectangle getRecPseudoUpdate4() {
        return recPseudoUpdate4;
    }

    public Rectangle getRecPseudoUpdate5() {
        return recPseudoUpdate5;
    }

    public Rectangle getRecPseudoUpdate6() {
        return recPseudoUpdate6;
    }

    public Rectangle getRecPseudoUpdate7() {
        return recPseudoUpdate7;
    }

    public VBox getvBoxSearch() {
        return vBoxSearch;
    }

    public VBox getvBoxDelete() {
        return vBoxDelete;
    }

    public VBox getvBoxInsert() {
        return vBoxInsert;
    }

    public Label getMainLabel() {
        return mainLabel;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Button getForwardTraverseBtn() {
        return forwardTraverseBtn;
    }

    public Button getBackwardTraverseBtn() {
        return backwardTraverseBtn;
    }

    public Button getPauseTraverseBtn() {
        return pauseTraverseBtn;
    }

    public Button getContinueTraverseBtn() {
        return continueTraverseBtn;
    }

    public Button getOkTraverseBtn() {
        return okTraverseBtn;
    }

    public Button getForwardInsertBtn() {
        return forwardInsertBtn;
    }

    public Button getBackwardInsertBtn() {
        return backwardInsertBtn;
    }

    public Button getPauseInsertBtn() {
        return pauseInsertBtn;
    }

    public Button getContinueInsertBtn() {
        return continueInsertBtn;
    }

    public Button getOkInsertBtn() {
        return okInsertBtn;
    }

    public Button getForwardDeleteBtn() {
        return forwardDeleteBtn;
    }

    public Button getBackwardDeleteBtn() {
        return backwardDeleteBtn;
    }

    public Button getPauseDeleteBtn() {
        return pauseDeleteBtn;
    }

    public Button getContinueDeleteBtn() {
        return continueDeleteBtn;
    }

    public Button getOkDeleteBtn() {
        return okDeleteBtn;
    }

    public Button getForwardSearchBtn() {
        return forwardSearchBtn;
    }

    public Button getBackwardSearchBtn() {
        return backwardSearchBtn;
    }

    public Button getPauseSearchBtn() {
        return pauseSearchBtn;
    }

    public Button getContinueSearchBtn() {
        return continueSearchBtn;
    }

    public Button getOkSearchBtn() {
        return okSearchBtn;
    }

    public Button getForwardUpdateBtn() {
        return forwardUpdateBtn;
    }

    public Button getBackwardUpdateBtn() {
        return backwardUpdateBtn;
    }

    public Button getPauseUpdateBtn() {
        return pauseUpdateBtn;
    }

    public Button getContinueUpdateBtn() {
        return continueUpdateBtn;
    }

    public Button getOkUpdateBtn() {
        return okUpdateBtn;
    }

    public int getSearchID() {
        return SearchID;
    }

    public Rectangle getrecPseudoSearch6() {
        return recPseudoSearch6;
    }

    public Rectangle getrecPseudoSearch7() {
        return recPseudoSearch7;
    }

    public TextField getTfNodeDelete() {
        return tfNodeDelete;
    }

    public TextField getTfParentInsert() {
        return tfParentInsert;
    }

    public TextField getTfNodeInsert() {
        return tfNodeInsert;
    }

    public StackPane getStackPanePseudo() {
        return stackPanePseudo;
    }

    public Pane getScenePane() {
        return scenePane;
    }

    public TextField getTfNodeSearch() {
        return tfNodeSearch;
    }

    public VBox getvBoxBFS() {
        return vBoxBFS;
    }

    public VBox getvBoxDFS() {
        return vBoxDFS;
    }

    public RadioButton getRadioBtnManual() {
        return radioBtnManual;
    }

    public RadioButton getRadioBtnRandom() {
        return radioBtnRandom;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public ArrayList<UserAction> getHistory() {
        return history;
    }

    public VBox getvBoxInsertB() {
        return vBoxInsertB;
    }

    public VBox getvBoxDeleteB() {
        return vBoxDeleteB;
    }

    public void buildGUI(Node root) {
        scenePane.getChildren().add(root);
        ArrayList<Node> listNode = new ArrayList<Node>();
        listNode.add(root);

        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                listNode.addAll(tmp.getListOfChildren());
            }
            if (!tmp.equals(root)) {
                scenePane.getChildren().add(tmp);
                scenePane.getChildren().add(tmp.getParentLine());
            }
        }
    }

    public void rebuildTree() {
        Node root = this.getTreeDataStructure().getRootNode();
        if (root != null) {
            scenePane.getChildren().remove(root);
            ArrayList<Node> listNodeDel = new ArrayList<Node>(root.getListOfChildren());
            while (listNodeDel.size() > 0) {
                Node tmp = listNodeDel.remove(0);
                if (tmp.getListOfChildren().size() > 0) {
                    listNodeDel.addAll(tmp.getListOfChildren());
                }
                scenePane.getChildren().remove(tmp);
                scenePane.getChildren().remove(tmp.getParentLine());
            }

            scenePane.getChildren().add(root);
            ArrayList<Node> listNode = new ArrayList<Node>();
            listNode.add(root);
            while (listNode.size() > 0) {
                Node tmp = listNode.remove(0);
                if (tmp.getListOfChildren().size() > 0) {
                    ArrayList<Node> tmpListNode = new ArrayList<Node>(tmp.getListOfChildren());
                    tmp.getListOfChildren().removeAll(tmp.getListOfChildren());
                    for (Node childNode : tmpListNode) {
                        tmp.addChild(childNode);
                        listNode.add(childNode);
                    }
                }

                if (!tmp.equals(root)) {
                    scenePane.getChildren().add(tmp);
                    scenePane.getChildren().add(tmp.getParentLine());
                }
            }
        }
    }

    public void deleteSubtree(Node root) {
        scenePane.getChildren().remove(root);
        if (!root.equals(this.getTreeDataStructure().getRootNode())) {
            scenePane.getChildren().remove(root.getParentLine());
            root.getParentNode().getListOfChildren().remove(root);
        }

        ArrayList<Node> listNode = new ArrayList<Node>(root.getListOfChildren());
        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                listNode.addAll(tmp.getListOfChildren());
            }
            tmp.getParentNode().getListOfChildren().remove(tmp);
            tmp.setId(null);
            scenePane.getChildren().remove(tmp);
            scenePane.getChildren().remove(tmp.getParentLine());
        }
        if (root.equals(this.getTreeDataStructure().getRootNode())) {
            this.getTreeDataStructure().devastateRootNode();
        }
    }

    @FXML
    public void backPressed() throws IOException {
        // System.out.println("Back pressed"); for debugging
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/mainWindow.fxml"));
        MainWindowController mainController = new MainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setFullScreen(true);
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }

    @FXML
    public void helpPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/help.fxml"));
        HelpController helpController = new HelpController();
        loader.setController(helpController);
        Stage helpStage = new Stage();
        Scene scene = new Scene(loader.load());
        helpStage.setScene(scene);
        helpStage.setTitle("Help");
        helpStage.initOwner(menuStage);
        helpStage.showAndWait();
    }

    @FXML
    public void undoPressed() {
        if (history.size() > 0) {
            UserAction lastAction = history.get(history.size() - 1);
            lastAction.undo();
        }
    }

    @FXML
    public void redoPressed() {
        if (history.size() > 0) {
            UserAction lastAction = history.get(history.size() - 1);
            lastAction.redo();
        }
    }

    @FXML
    public void resetPressed() {
        if (this.getTreeDataStructure().getRootNode() != null) {
            this.deleteSubtree(this.getTreeDataStructure().getRootNode());
        }
    }

    @FXML
    public void mainLabelPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/mainWindow.fxml"));
        MainWindowController mainController = new MainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setFullScreen(true);
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }

    @FXML
    protected void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        mainLabel.setText(this.getTreeType());
    }

    @FXML
    protected void forwardSearchBtnPressed(ActionEvent e) {

        this.getTreeDataStructure().forwardSearchStep(SearchID);

    }

    @FXML
    protected void forwardDeleteBtnPressed(ActionEvent e) {

        this.getTreeDataStructure().forwardDeleteStep(Integer.parseInt(tfNodeDelete.getText()), scenePane);

    }

    @FXML
    protected void forwardInsertBtnPressed(ActionEvent e) {

        this.getTreeDataStructure().forwardInsertStep(Integer.parseInt(tfParentInsert.getText()),
                Integer.parseInt(tfNodeInsert.getText()), scenePane);

    }

    @FXML
    protected void forwardUpdateBtnPressed(ActionEvent e) {

        this.getTreeDataStructure().forwardUpdateStep(Integer.parseInt(tfOldNodeUpdate.getText()),
                Integer.parseInt(tfNewNodeUpdate.getText()));

    }

    @FXML
    protected void forwardTraverseBtnPressed(ActionEvent e) {
        if (algorithm.equals("BFS")) {
            this.getTreeDataStructure().forwardBFS1Step();
        } else {
            this.getTreeDataStructure().forwardDFS1Step();
        }
    }

    @FXML
    protected void backwardTraverseBtnPressed(ActionEvent e) {
        if (algorithm.equals("BFS")) {
            this.getTreeDataStructure().backwardBFS1Step();
        } else {
            this.getTreeDataStructure().backwardDFS1Step();
        }

    }

    @FXML
    protected void backwardDeleteBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().backwardDeleteStep(Integer.parseInt(tfNodeDelete.getText()), scenePane);
    }

    @FXML
    protected void backwardInsertBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().backwardInsertStep(Integer.parseInt(tfNodeInsert.getText()), scenePane);
    }

    @FXML
    protected void backwardUpdateBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().backwardUpdateStep(Integer.parseInt(tfOldNodeUpdate.getText()),
                Integer.parseInt(tfNewNodeUpdate.getText()));
    }

    @FXML
    protected void backwardSearchBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().backwardSearchStep();
    }

    @FXML
    protected void pauseTraverseBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().pause();
    }

    @FXML
    protected void continueTraverseBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().continueclick();
    }

    @FXML
    protected void okTraverseBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().okTraverse();
    }

    @FXML
    protected void pauseSearchBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().pause();
    }

    @FXML
    protected void continueSearchBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().continueclick();
    }

    @FXML
    protected void okSearchBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().okSearch();
    }

    @FXML
    protected void pauseUpdateBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().pause();
    }

    @FXML
    protected void continueUpdateBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().continueclick();
    }

    @FXML
    protected void okUpdateBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().okUpdate();
    }

    @FXML
    protected void pauseInsertBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().pause();
    }

    @FXML
    protected void continueInsertBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().continueclick();
    }

    @FXML
    protected void okInsertBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().okInsert();
    }

    @FXML
    protected void pauseDeleteBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().pause();
    }

    @FXML
    protected void continueDeleteBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().continueclick();
    }

    @FXML
    protected void okDeleteBtnPressed(ActionEvent e) {
        this.getTreeDataStructure().okDelete();
    }

    @FXML
    protected void btnCreatePressed(ActionEvent event) {
        this.resetPressed();
        CreatePressed createPressed;
        if (radioBtnManual.isSelected()) {
            createPressed = new CreatePressed(this, this.getTreeDataStructure(), scenePane, tfRootCreate.getText());
        } else {
            createPressed = new CreatePressed(this, this.getTreeDataStructure(), scenePane);
        }
        createPressed.run();
        history.add((UserAction) createPressed);
        tfRootCreate.clear();
    }

    @FXML
    protected void btnTraversePressed(ActionEvent event) {
        stackPanePseudo.setVisible(true);
        if (algorithm.equals("BFS")) {

            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(true);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxUpdate.setVisible(false);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);
        } else {

            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(true);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxUpdate.setVisible(false);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);
        }
        TraversePressed traversePressed = new TraversePressed(treeDataStructure, algorithm);
        traversePressed.run();
        this.getHistory().add(traversePressed);

    }

    @FXML
    protected void btnDeletePressed(ActionEvent event) throws TreeException {
        String delNodeVal = tfNodeDelete.getText();
        int intDelNodeVal = Integer.parseInt(delNodeVal);
        try {
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(true);
            vBoxUpdate.setVisible(false);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);
            this.getTreeDataStructure().checkDeleteNode(intDelNodeVal);
            DeletePressed deletePressed = new DeletePressed((GenericTree) this.getTreeDataStructure(), scenePane, this,
                    intDelNodeVal);
            deletePressed.run();
            ;
            history.add(deletePressed);

        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you want to delete does not exist.");
            alert.showAndWait();
        }

    }

    @FXML
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

            String node_val = tfNodeInsert.getText();
            String parent_val = tfParentInsert.getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);

            this.getTreeDataStructure().checkInsertNode(intParentVal, intNodeVal);

            this.getTreeDataStructure().startInsert(intParentVal, intNodeVal, scenePane);
            InsertPressed insertPressed = new InsertPressed(this.getTreeDataStructure(), this, this.getScenePane(),
                    intNodeVal, intParentVal);

            insertPressed.redo();

            this.getHistory().add(insertPressed);
        }

        catch (NodeExistedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like you have tried invalid insertion operation.");

            alert.showAndWait();
        }

        catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like you have tried invalid insertion operation.");

            alert.showAndWait();
        }
    }

    @FXML
    protected void btnUpdatePressed(ActionEvent event) throws TreeException {
        String new_val = tfNewNodeUpdate.getText();
        String old_val = tfOldNodeUpdate.getText();

        int intNewVal = Integer.parseInt(new_val);
        int intOldVal = Integer.parseInt(old_val);

        try {
            this.getTreeDataStructure().checkUpdateNode(intOldVal, intNewVal);
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(false);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxUpdate.setVisible(true);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);
            UpdatePressed updatePressed = new UpdatePressed((GenericTree) this.getTreeDataStructure(), this, scenePane,
                    intOldVal, intNewVal);
            updatePressed.run();

            history.add(updatePressed);

        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like old node does not exist.");

            alert.showAndWait();
        } catch (NodeExistedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like new node have existed.");

            alert.showAndWait();
        }
    }

    @FXML
    protected void btnSearchPressed(ActionEvent event) throws TreeException {
        String val_node = tfNodeSearch.getText();
        int intNodeVal = Integer.parseInt(val_node);
        SearchID = intNodeVal;

        try {
            this.getTreeDataStructure().checkNodeExisted(intNodeVal);
            stackPanePseudo.setVisible(true);
            vBoxSearch.setVisible(true);
            vBoxBFS.setVisible(false);
            vBoxDFS.setVisible(false);
            vBoxInsert.setVisible(false);
            vBoxDelete.setVisible(false);
            vBoxUpdate.setVisible(false);
            vBoxDeleteB.setVisible(false);
            vBoxInsertB.setVisible(false);

            SearchPressed searchPressed = new SearchPressed((GenericTree) this.getTreeDataStructure(), this, scenePane,
                    intNodeVal);
            searchPressed.run();
            this.getHistory().add(searchPressed);

        }

        catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you are searching for does not exist.");
            alert.showAndWait();
        }
    }

    @FXML // done
    void btnOpsInsertPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxInsert);

    }

    @FXML // done
    void btnOpsDeletePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
        setControl(hBoxDelete);
    }

    @FXML // done
    void btnOpsCreatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxCreate);

    }

    @FXML // done
    void btnOpsUpdatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxUpdate);
    }

    @FXML // done
    void btnOpsTraversePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxTraverse);
    }

    @FXML
    void btnOpsSearchPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxSearch);
    }

    @FXML
    void radioBtnRandomPressed(ActionEvent event) {
        hBoxManual.setVisible(false);
    }

    @FXML
    void radioBtnManualPressed(ActionEvent event) {
        hBoxManual.setVisible(true);
    }

    @FXML
    private void radioBtnBFSPressed(ActionEvent event) {
        this.algorithm = "BFS";
    }

    @FXML
    private void radioBtnDFSPressed(ActionEvent event) {
        this.algorithm = "DFS";
    }

    private void setControl(HBox hBoxOn) {
        ArrayList<HBox> setHBox = new ArrayList<HBox>(
                Arrays.asList(hBoxCreate, hBoxTraverse, hBoxInsert, hBoxDelete, hBoxUpdate, hBoxSearch));
        for (HBox hbox : setHBox) {
            if (hBoxOn.equals(hbox)) {
                hbox.setVisible(true);
            } else {
                hbox.setVisible(false);
            }
        }
    }

}
