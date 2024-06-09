package screen.controller.operation;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CreatePressed implements UserAction {
    private boolean isRandom;
    private String rootId;
    private GenericTreeController genericTreeController;
    private Pane scenePane;
    private GenericTree genericTree;
    private String treeType;

    public CreatePressed(GenericTreeController genericTreeController, GenericTree genericTree, Pane scenePane,
            String rootId) {
        this.rootId = rootId;
        this.isRandom = false;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.genericTree = genericTree;
    }

    public CreatePressed(GenericTreeController genericTreeController, GenericTree genericTree, Pane scenePane) {
        this.isRandom = true;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.genericTree = genericTree;
    }

    @Override
    public void run() {
        if (this.isRandom) {

            Random randint = new Random();
            int numNodes = randint.nextInt(10);
            while (numNodes <= 0) {
                numNodes = randint.nextInt(10);
            }
            ArrayList<Integer> listValNodes = new ArrayList<Integer>();
            for (int i = 0; i < numNodes; i++) {
                int newVal = randint.nextInt(10);
                while (listValNodes.contains(newVal)) {
                    newVal = randint.nextInt(10);
                }
                listValNodes.add(newVal);
            }

            if (this.genericTreeController.getTreeDataStructure() instanceof BalancedTree) {
                System.out.println("Create Balanced Tree");

                Node root = new Node(listValNodes.get(0));
                this.rootId = String.valueOf(listValNodes.get(0));
                genericTree.setTreeController(genericTreeController);
                genericTree.setRootNode(root);
                scenePane.getChildren().add(root);

                for (int i = 1; i < listValNodes.size(); i++) {
                    Node childNode = root.addChild(listValNodes.get(i));
                    System.out.println(rootId + " " + childNode.getNodeId());
                    scenePane.getChildren().add(childNode.getParentLine());
                    scenePane.getChildren().add(childNode);
                    root = childNode;
                }

            }

            else if (this.genericTreeController.getTreeDataStructure() instanceof BinaryTree) {

                System.out.println("Create Binary Tree");

                Node root = new Node(listValNodes.get(0));
                this.rootId = String.valueOf(listValNodes.get(0));
                genericTree.setTreeController(genericTreeController);
                genericTree.setRootNode(root);
                scenePane.getChildren().add(root);
                HashMap<Integer, Integer> numChildNode = new HashMap<Integer, Integer>();

                for (int i = 0; i < numNodes; i++) {
                    numChildNode.put(i, 0);
                }

                for (int i = 1; i < numNodes; i++) {

                    ArrayList<Integer> possibleParent = new ArrayList<Integer>();
                    for (int j = 0; j < i; j++) {
                        if (numChildNode.get(j) < 2) {
                            possibleParent.add(j);
                        }
                    }

                    int parentDecision = possibleParent.get(randint.nextInt(possibleParent.size()));
                    Node childNode = genericTree.insertNode(listValNodes.get(parentDecision), listValNodes.get(i));
                    numChildNode.put(parentDecision, numChildNode.get(parentDecision) + 1);

                    scenePane.getChildren().add(childNode.getParentLine());
                    scenePane.getChildren().add(childNode);
                }
            }

            else if (this.genericTreeController.getTreeDataStructure() instanceof GenericTree) {

                System.out.println("Create Generic Tree");

                // set root node
                Node root = new Node(listValNodes.get(0));
                this.rootId = String.valueOf(listValNodes.get(0));
                genericTree.setTreeController(genericTreeController);
                genericTree.setRootNode(root);
                scenePane.getChildren().add(root);

                for (int i = 1; i < numNodes; i++) {
                    int parentDecision = randint.nextInt(i);
                    Node childNode = genericTree.insertNode(listValNodes.get(parentDecision), listValNodes.get(i));
                    scenePane.getChildren().add(childNode.getParentLine());
                    scenePane.getChildren().add(childNode);
                }
            }

        } else {
            int rootIdInt;
            if (rootId.equals("")) {
                rootIdInt = 1;
            } else {
                rootIdInt = Integer.parseInt(rootId);
            }
            genericTree.createTree(rootIdInt);
            genericTree.setTreeController(genericTreeController);
            Node root = genericTree.getRootNode();
            scenePane.getChildren().add(root);
        }
        System.out.println("Create operation.");
    }

    @Override
    public void undo() {
        this.genericTreeController.resetPressed();
        System.out.println("Create operation undo.");
    }

    public void redo() {

    }
}
