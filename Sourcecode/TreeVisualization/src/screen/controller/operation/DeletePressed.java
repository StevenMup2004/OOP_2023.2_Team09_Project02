package screen.controller.operation;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.GenericTree;
import treedatastructure.Node;
import java.util.ArrayList;
import java.util.HashMap;

public class DeletePressed implements UserAction {

    private static final Node NULL = null;
    private GenericTreeController genericTreeController;
    private int intDelNodeVal;
    private Node parentDelNode;
    private GenericTree treeDataStructure;
    private Pane scenePane;
    private HashMap<Integer, ArrayList<Integer>> listDelNode = new HashMap<>();

    public DeletePressed(GenericTree genericTree, Pane scenePane, GenericTreeController genericTreeController,
            int intDelNodeVal) {
        this.genericTreeController = genericTreeController;
        this.treeDataStructure = genericTree;
        this.intDelNodeVal = intDelNodeVal;
        this.scenePane = scenePane;
    }

    @Override
    public void run() {
        System.out.println("Delete Operation");
        this.getTreeDataStructure().startDelete(intDelNodeVal, scenePane);
    }

    private GenericTree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    @Override
    public void undo() {

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
        System.out.println("Delete operation undo.");
        System.out.println("Root");
        System.out.println(this.treeDataStructure.rootNode.getNodeId());

    }

    @Override
    public void redo() {

        this.undo();
        this.treeDataStructure.okDelete();
        this.run();

    }

}
