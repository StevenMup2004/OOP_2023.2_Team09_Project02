package screen.controller.operation;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.*;

public class InsertPressed implements UserAction {
    private static final Node NULL = null;
    private int nodeVal;
    private int parentval;

    private Node parent;
    private GenericTreeController genericTreeController;
    private GenericTree treeDataStructure;
    private Pane scenePane;

    public InsertPressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane,
            int nodeVal, int parentval) {
        this.treeDataStructure = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.nodeVal = nodeVal;
        this.parentval = parentval;
    }

    @Override
    public void run() {

        this.getTreeDataStructure().startInsert(parentval, nodeVal, scenePane);

    }

    public GenericTree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    @Override
    public void undo() {
        this.treeDataStructure.okInsert();
        scenePane.getChildren().clear();
        this.treeDataStructure.traverseNode = NULL;
        this.treeDataStructure.rootNode = this.treeDataStructure.intialTree;
        Node root = this.treeDataStructure.rootNode;
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
        System.out.println("Insert operation undo.");
    }

    @Override
    public void redo() {
        this.undo();
        System.out.println("OK insert SSSSSSSSSSS");
        this.treeDataStructure.okInsert();
        System.out.println("OK insert end");
        this.run();
    }

}
