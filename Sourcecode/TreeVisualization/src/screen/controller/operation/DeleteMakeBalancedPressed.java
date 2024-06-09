package screen.controller.operation;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.GenericTree;
import treedatastructure.Node;

public class DeleteMakeBalancedPressed implements UserAction {
    private static final Node NULL = null;
    private GenericTreeController genericTreeController;
    private GenericTree TreeDataStructure;
    private Node oldRootNode;
    private Pane scenePane;
    private int delId;

    public DeleteMakeBalancedPressed(GenericTreeController genericTreeController, GenericTree genericTree, int delId,
            Pane scenePane) {
        this.genericTreeController = genericTreeController;
        this.TreeDataStructure = genericTree;
        this.oldRootNode = genericTree.getRootNode();
        this.delId = delId;
        this.scenePane = scenePane;
    }

    @Override
    public void run() {
        this.getTreeDataStructure().startDelete(delId, scenePane);

    }

    private GenericTree getTreeDataStructure() {
        return this.TreeDataStructure;
    }

    @Override
    public void undo() {
        scenePane.getChildren().clear();
        this.TreeDataStructure.traverseNode = NULL;
        this.TreeDataStructure.rootNode = this.TreeDataStructure.intialTree;
        Node root = this.TreeDataStructure.rootNode;
        scenePane.getChildren().add(root);

        ArrayList<Node> listNode = new ArrayList<>();
        listNode.add(root);
        while (!listNode.isEmpty()) {
            Node tmp = listNode.remove(0);
            if (!tmp.getListOfChildren().isEmpty()) {
                ArrayList<Node> tmpListNode = new ArrayList<>(tmp.getListOfChildren());
                for (Node childNode : tmpListNode) {
                    listNode.add(childNode);
                }
            }
            if (!tmp.equals(root)) {
                scenePane.getChildren().add(tmp);
                scenePane.getChildren().add(tmp.getParentLine());
            }
        }
        System.out.println("Delete operation undo.");
    }

    @Override
    public void redo() {

        this.undo();
        this.TreeDataStructure.okDelete();
        this.run();

    }
}
