package treedatastructure;

import exception.*;
import screen.controller.BalancedBinaryTreeController;
import screen.controller.BalancedTreeController;
import screen.controller.GenericTreeController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.*;
import javafx.animation.Animation.Status;
import java.util.ArrayList;

public class GenericTree {
    private static final Node NULL = null;
    public boolean isTimelineRunning = true;
    Node copyTree = new Node(0);
    public Node rootNode;
    ArrayList<Node> queue1 = new ArrayList<Node>();

    protected double timesleep = 1;
    protected Timeline timeline;

    protected Color recColor1 = Color.web("#99f28a");
    protected Color recColor2 = Color.BLUE;

    protected Color NOT_VISIT_COLOR = Color.WHITE;
    protected Color VISIT_COLOR = Color.BLUE;

    protected ArrayList<Node> queue;
    private ArrayList<Node> stack;

    public Node traverseNode;
    public Node intialTree = new Node(0);

    protected GenericTreeController treeController;

    public void setTreeController(GenericTreeController treeController) {
        this.treeController = treeController;
    }

    public GenericTreeController getTreeController() {
        return this.treeController;
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public void devastateRootNode() {
        this.rootNode = null;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void createTree(int rootId) {
        this.rootNode = new Node(rootId);
        System.out.println(rootId + "");
    }

    public void createTree() {
        int id = 1;
        this.rootNode = new Node(id);
    }

    public void setRootNode(Node newRoot) {
        if (newRoot == null) {
            this.rootNode = null;
            return;
        }
        this.rootNode = newRoot;
        this.rootNode.setDepth(0);
        this.rootNode.setParentNode(null);

        if (this.rootNode.getNumChildren() > 0) { // nếu là 1 subtree
            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(this.rootNode);

            Node tmp;
            while (queue.size() > 0) {
                tmp = queue.remove(0); // lấy node đầu tiên của queue

                if (tmp.getNumChildren() > 0) {
                    for (Node n : tmp.getListOfChildren()) {
                        n.setDepth(tmp.getDepth() + 1);
                        queue.add(n);
                    }
                }
            }
        }
    }

    public void continueclick() {
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void traverseTree(String algorithm) throws NoneAlgorithmSpecifiedException {
        if (!algorithm.equals("BFS") & !algorithm.equals("DFS")) {
            throw new NoneAlgorithmSpecifiedException("The algorithm should be BFS or DFS!");
        }
        if (algorithm.equals("BFS")) {
            startTraverseTreeBFS();
        } else {
            startTraverseTreeDFS();
        }
    }

    public Node searchNode(int searchId) {
        ArrayList<Node> queue = new ArrayList<Node>();
        if (this.getRootNode().getNodeId() == searchId) {
            return this.getRootNode();
        }
        queue.add(this.getRootNode());
        Node tmp;
        while (queue.size() > 0) {
            tmp = queue.remove(0); // lấy node đầu tiên của queue
            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.getNodeId() == searchId) {
                        return n;
                    }
                    queue.add(n);
                }
            }
        }
        return null;
    }

    public ArrayList<Node> getPathToRoot(Node node) {
        ArrayList<Node> list_node = new ArrayList<Node>();
        while (!node.equals(this.rootNode)) {
            list_node.add(node);
            node = node.getParentNode();
        }
        return list_node;
    }

    public Node insertNode(int parentId, int childId) {
        Node parent = searchNode(parentId);
        Node childNode = parent.addChild(childId);
        // this.addData(childNode);
        return childNode;
    }

    public void updateNode(int oldId, int newId) {
        Node oldNode = searchNode(oldId);
        oldNode.updateId(newId);
    }

    public void printBFS() {
        if (this.rootNode == null) {
            System.out.println("Root = null");
            return;
        }
        queue = new ArrayList<Node>();
        queue.add(this.rootNode);
        while (queue.size() > 0) {
            Node tmp = queue.remove(0);
            System.out.print("Node " + tmp.getNodeId() + " (" + tmp.getDepth() + ") " + "child: ");
            if (tmp.getNumChildren() > 0) {
                for (Node child : tmp.getListOfChildren()) {
                    System.out.print(child.getNodeId() + " ");
                    queue.add(child);
                }
            }
            System.out.println("");

        }
    }

    public void deleteSubtree(Node root, Pane scenePane) {
        scenePane.getChildren().remove(root);
        if (!root.equals(this.getRootNode())) {
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
        if (root.equals(this.getRootNode())) {
            this.devastateRootNode();
        }
    }

    public void rebuildTree(Pane scenePane) {
        Node root = this.getRootNode();
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

    protected void deleteNode(int rootId) {
        Node root = this.searchNode(rootId);
        if (!root.equals(this.getRootNode())) {
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
        }
    }

    public void checkNodeExisted(int searchId) throws TreeException {
        Node tmp = searchNode(searchId);
        if (tmp == null) {
            throw new NodeNotExistsException("Node does not exist.");
        }
    }

    public void checkNodeNotExisted(int searchId) throws TreeException {
        Node tmp = searchNode(searchId);
        if (tmp != null) {
            throw new NodeExistedException("Node does exist.");
        }
    }

    public void checkInsertNode(int parentId, int childId) throws TreeException {
        checkNodeExisted(parentId);
        checkNodeNotExisted(childId);
    }

    public void checkUpdateNode(int oldId, int newId) throws TreeException {

        checkNodeExisted(oldId);

        checkNodeNotExisted(newId);

    }

    public void checkDeleteNode(int delId) throws TreeException {
        this.checkNodeExisted(delId);
    }

    public void forwardBFS1Step() {
        System.out.println("BF1 operation");
        if (traverseNode == null) {
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);

            traverseNode = queue.remove(0);
        }
        if (traverseNode.getState() == 1) { // TH1
            // Nếu đã được add vào queue và đc remove ra (xong state1) thì tiếp state2
            // (Sys.print)
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(2);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
            }
        } else if (traverseNode.getState() == 2) { // TH2
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();

            }
        }
    }

    public void backwardBFS1Step() {
        if (traverseNode == null) {
            return;
        }
        if (traverseNode.getState() == 1) {
            queue.add(0, traverseNode);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);

            /*
             * TH0: nó ko có bố mẹ (nó là root)
             */
            if (traverseNode.isRootNode()) {
                traverseNode = null;
                this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
                this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
                this.getTreeController().getRecPseudoBFS1().setFill(recColor2);
                return;
            }

            boolean traverseNodeIsFirstChild;
            try {
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            /*
             * TH1: nó có anh em ở trước
             */
            if (!traverseNodeIsFirstChild) {
                traverseNode = traverseNode.getLeftSibling();
                return;
            }

            /*
             * TH2: nó ko có anh em ở trước (nó là con đầu)
             */

            // TH2.1: Nếu cha là rootNode
            Node parent = traverseNode.getParentNode();
            if (parent.isRootNode()) {
                traverseNode = rootNode;
            }

            // TH2.2: Nếu cha nó ko phải rootNode
            else {
                Node leftSblingOfParent = parent.getLeftSibling();
                /*
                 * Nếu cha của nó có leftSibling
                 */
                if (leftSblingOfParent != null) {
                    /*
                     * Nếu leftSibling đó có con
                     */
                    if (leftSblingOfParent.getNumChildren() > 0) {
                        traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                    }

                    /*
                     * Nếu leftSibling đó ko có con
                     */
                    else {
                        traverseNode = parent.getParentNode().getTheLastChild();
                    }
                }
                /*
                 * Nếu cha nó là con đầu của ông nó
                 */
                else {
                    Node grandpa = parent.getParentNode();
                    traverseNode = grandpa.getTheLastChild();
                }
            }
        }

        else if (traverseNode.getState() == 2) {
            try { // duyệt
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(1);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print
                                                                                                                    // node
                                                                                                                    // tmp
                traverseNode.setState(1);
            }
        } else if (traverseNode.getState() == 3) {
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(2);
        }
    }

    /*
     * Các method cho traverse DFS
     */
    public void forwardDFS1Step() {
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoDFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);
            traverseNode = stack.remove(stack.size() - 1);
        }

        if (traverseNode.getState() == 1) {
            try {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(2);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
            }
        }

        else if (traverseNode.getState() == 2) {
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) {
                for (int i = traverseNode.getNumChildren() - 1; i >= 0; i--) {
                    stack.add(traverseNode.getListOfChildren().get(i));
                    traverseNode.getListOfChildren().get(i).setState(1);
                }
            }
            traverseNode.setState(3);
        }

        else if (traverseNode.getState() == 3) {
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);

            if (stack.size() > 0) {
                traverseNode = stack.remove(stack.size() - 1);
            } else {
                timeline.stop();
            }
        }
    }

    public void backwardDFS1Step() {
        if (traverseNode == null) {
            return;
        }

        if (traverseNode.getState() == 1) {
            stack.add(traverseNode);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);

            if (traverseNode.isRootNode()) {
                traverseNode = null;
                this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
                this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
                this.getTreeController().getRecPseudoDFS1().setFill(recColor2);
                return;
            }

            boolean traverseNodeIsFisrtChild;
            try {
                traverseNodeIsFisrtChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (traverseNodeIsFisrtChild) {
                traverseNode = traverseNode.getParentNode();
                return;
            }

            Node leftSibling = traverseNode.getLeftSibling();
            traverseNode = leftSibling;
            while (traverseNode.getNumChildren() > 0) {
                traverseNode = traverseNode.getListOfChildren().get(traverseNode.getNumChildren() - 1);
            }
        }

        else if (traverseNode.getState() == 2) {
            traverseNode.setState(1);

            try {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward " + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId()); // print node tmp

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward " + traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print
                                                                                                            // node tmp

            }

        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);

            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);

            if (traverseNode.getNumChildren() > 0) {
                for (int i = traverseNode.getNumChildren() - 1; i >= 0; i--) {
                    stack.remove(traverseNode.getListOfChildren().get(i));
                    traverseNode.getListOfChildren().get(i).setState(0);
                }
            }
        }
    }

    public void forwardSearchStep(int Id) {
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoSearch2().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch1().setFill(recColor1);

            traverseNode = queue.remove(0);
        }

        if (traverseNode.getState() == 1) { // TH1
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.43));
                pause.setOnFinished(event -> {

                    this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
                    this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
                    this.getTreeController().getRecPseudoSearch4().setFill(recColor2);
                    this.getTreeController().getRecPseudoSearch5().setFill(recColor2);

                });

                pause.play();
                if (traverseNode.getNodeId() == Id) {
                    timeline.stop();
                }
                ;

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == Id) {
                    traverseNode.setState(5);
                    timeline.stop();
                }
                traverseNode.setState(3);
            }
        } else if (traverseNode.getState() == 2) {

            this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch4().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch5().setFill(recColor2);

            if (traverseNode.getNodeId() == Id) {
                timeline.stop();
            }
            traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu

            this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch6().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch7().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch2().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor2);

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();
            }
        }
    }

    public void backwardSearchStep() {
        System.out.println(traverseNode.getNodeId());
        System.out.println(traverseNode.getState());

        if (traverseNode == null) {
            return;
        }
        if (traverseNode == rootNode && traverseNode.getState() == 0)
            return;

        if (traverseNode.getState() == 1) { // State 1

            traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
            queue.add(0, traverseNode);
            // Change colors

            // Handle case when node is root
            if (traverseNode.isRootNode()) {
                traverseNode = null;
                return;
            }

            boolean traverseNodeIsFirstChild;
            try {
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (!traverseNodeIsFirstChild) { // Node has previous siblings
                traverseNode = traverseNode.getLeftSibling();
                return;
            } else { // Node is the first child
                Node parent = traverseNode.getParentNode();
                if (parent.isRootNode()) { // Parent is root
                    traverseNode = rootNode;
                } else { // Parent is not root
                    Node leftSiblingOfParent = parent.getLeftSibling();
                    if (leftSiblingOfParent != null) {
                        if (leftSiblingOfParent.getNumChildren() > 0) { // Left sibling has children
                            traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                        } else { // Left sibling has no children
                            traverseNode = parent.getParentNode().getTheLastChild();
                        }
                    } else { // Parent is the first child of its parent
                        Node grandpa = parent.getParentNode();
                        traverseNode = grandpa.getTheLastChild();
                    }
                }
            }
            backwardSearchStep();
        } else if (traverseNode.getState() == 2) { // State 2
            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoSearch1().setFill(recColor2);
                this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
            } else {
                this.getTreeController().getRecPseudoSearch1().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
                this.getTreeController().getRecPseudoSearch6().setFill(recColor2);
                this.getTreeController().getRecPseudoSearch7().setFill(recColor2);

            }
            try { // Traverse
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId());
                traverseNode.setState(1);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(1);
            }
        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoSearch1().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch2().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoSearch1().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch4().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch5().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
        } else if (traverseNode.getState() == 5) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoSearch1().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch2().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch3().setFill(recColor2);
            this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
            this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
        }
    }

    public void forwardUpdateStep(int OldId, int NewId) {
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoUpdate2().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);

            traverseNode = queue.remove(0);
        }

        if (traverseNode.getState() == 1) { // TH1
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.43));
                pause.setOnFinished(event -> {

                    this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
                    this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
                    this.getTreeController().getRecPseudoUpdate4().setFill(recColor2);
                    this.getTreeController().getRecPseudoUpdate5().setFill(recColor2);

                });

                pause.play();
                if (traverseNode.getNodeId() == OldId) {
                    traverseNode.updateId(NewId);
                    traverseNode.setState(5);
                    timeline.stop();
                }

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == OldId) {
                    traverseNode.updateId(NewId);
                    timeline.stop();
                }
                traverseNode.setState(3);
            }
        } else if (traverseNode.getState() == 2) {

            this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate4().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate5().setFill(recColor2);

            if (traverseNode.getNodeId() == OldId) {
                traverseNode.updateId(NewId);
                timeline.stop();
            }
            traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu

            this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate6().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate7().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate2().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor2);

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();
            }
        }
    }

    public void backwardUpdateStep(int OldId, int NewId) {
        System.out.println(traverseNode.getNodeId());
        System.out.println(traverseNode.getState());

        if (traverseNode == null) {
            return;
        }
        if (traverseNode == rootNode && traverseNode.getState() == 0)
            return;

        if (traverseNode.getState() == 1) { // State 1

            traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
            queue.add(0, traverseNode);
            // Change colors

            // Handle case when node is root
            if (traverseNode.isRootNode()) {
                traverseNode = null;
                return;
            }

            boolean traverseNodeIsFirstChild;
            try {
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (!traverseNodeIsFirstChild) { // Node has previous siblings
                traverseNode = traverseNode.getLeftSibling();
                return;
            } else { // Node is the first child
                Node parent = traverseNode.getParentNode();
                if (parent.isRootNode()) { // Parent is root
                    traverseNode = rootNode;
                } else { // Parent is not root
                    Node leftSiblingOfParent = parent.getLeftSibling();
                    if (leftSiblingOfParent != null) {
                        if (leftSiblingOfParent.getNumChildren() > 0) { // Left sibling has children
                            traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                        } else { // Left sibling has no children
                            traverseNode = parent.getParentNode().getTheLastChild();
                        }
                    } else { // Parent is the first child of its parent
                        Node grandpa = parent.getParentNode();
                        traverseNode = grandpa.getTheLastChild();
                    }
                }
            }
            backwardUpdateStep(OldId, NewId);
        } else if (traverseNode.getState() == 2) { // State 2
            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoUpdate1().setFill(recColor2);
                this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
            } else {
                this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
                this.getTreeController().getRecPseudoUpdate6().setFill(recColor2);
                this.getTreeController().getRecPseudoUpdate7().setFill(recColor2);

            }
            try { // Traverse
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId());
                traverseNode.setState(1);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(1);
            }
        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate2().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate4().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate5().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
        } else if (traverseNode.getState() == 5) {
            traverseNode.setState(2);

            traverseNode.updateId(OldId);
            System.out.println("Color");
            System.out.println(OldId);
            this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate2().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate3().setFill(recColor2);
            this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
            this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
        }
    }

    public void forwardDeleteStep(int Id, Pane scenePane) {
        System.out.println("Delete FF Operation");
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoDelete2().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
            traverseNode = queue.remove(0);
        }
        System.out.println("Delete node");
        System.out.println(Id);
        if (traverseNode.getState() == 1) { // TH1
            // Nếu đã được add vào queue và đc remove ra (xong state1) thì tiếp state2
            // (Sys.print)
            try { // duyệt

                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.43));
                pause.setOnFinished(event -> {

                    this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
                    this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
                    this.getTreeController().getRecPseudoDelete4().setFill(recColor2);
                    this.getTreeController().getRecPseudoDelete5().setFill(recColor2);

                });

                pause.play();
                copyTree = copyTree.copyNode(this.getRootNode());
                if (traverseNode.getNodeId() == Id) {
                    System.out.println("Start Equal");
                    if (!traverseNode.equals(this.getRootNode())) {
                        traverseNode.getParentNode().getListOfChildren().remove(traverseNode);
                    }
                    deleteSubtree(traverseNode, scenePane);
                    rebuildTree(scenePane);
                    traverseNode.setState(5);
                    timeline.stop();
                } else
                    traverseNode.setState(3);

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println("Error");
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == Id) {
                    copyTree = copyTree.copyNode(this.getRootNode());

                    if (!traverseNode.equals(this.getRootNode())) {
                        traverseNode.getParentNode().getListOfChildren().remove(traverseNode);
                    }
                    deleteSubtree(traverseNode, scenePane);
                    rebuildTree(scenePane);
                    traverseNode.setState(5);
                    timeline.stop();

                }
                traverseNode.setState(3);

            }
        } else if (traverseNode.getState() == 2) {
            this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete4().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor2);
            if (traverseNode.getNodeId() == Id) {
                copyTree = copyTree.copyTree(rootNode);
                if (!traverseNode.equals(this.getRootNode())) {
                    traverseNode.getParentNode().getListOfChildren().remove(traverseNode);
                }

                deleteSubtree(traverseNode, scenePane);
                rebuildTree(scenePane);
                traverseNode.setState(5);
                timeline.stop();
            }
            traverseNode.setState(3);

        }

        else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu
            this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete6().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor2);
            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete2().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor2);
            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();

            }
        }
    }

    public void backwardDeleteStep(int Id, Pane scenePane) {
        System.out.println(traverseNode.getNodeId());
        System.out.println(traverseNode.getState());

        if (traverseNode == null) {
            return;
        }
        if (traverseNode == rootNode && traverseNode.getState() == 0)
            return;

        if (traverseNode.getState() == 1) { // State 1

            traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
            queue.add(0, traverseNode);
            // Change colors

            // Handle case when node is root
            if (traverseNode.isRootNode()) {
                traverseNode = null;
                return;
            }

            boolean traverseNodeIsFirstChild;
            try {
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (!traverseNodeIsFirstChild) { // Node has previous siblings
                traverseNode = traverseNode.getLeftSibling();
                return;
            } else { // Node is the first child
                Node parent = traverseNode.getParentNode();
                if (parent.isRootNode()) { // Parent is root
                    traverseNode = rootNode;
                } else { // Parent is not root
                    Node leftSiblingOfParent = parent.getLeftSibling();
                    if (leftSiblingOfParent != null) {
                        if (leftSiblingOfParent.getNumChildren() > 0) { // Left sibling has children
                            traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                        } else { // Left sibling has no children
                            traverseNode = parent.getParentNode().getTheLastChild();
                        }
                    } else { // Parent is the first child of its parent
                        Node grandpa = parent.getParentNode();
                        traverseNode = grandpa.getTheLastChild();
                    }
                }
            }
            backwardDeleteStep(Id, scenePane);
        } else if (traverseNode.getState() == 2) { // State 2
            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoDelete1().setFill(recColor2);
                this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
            } else {
                this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
                this.getTreeController().getRecPseudoDelete6().setFill(recColor2);
                this.getTreeController().getRecPseudoDelete7().setFill(recColor2);

            }
            try { // Traverse
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId());
                traverseNode.setState(1);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(1);
            }
        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete2().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete4().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
        } else if (traverseNode.getState() == 5) {
            Node root = this.getRootNode();
            if (root != null) {
                scenePane.getChildren().remove(root);
                ArrayList<Node> listNodeDel = new ArrayList<>(root.getListOfChildren());
                while (!listNodeDel.isEmpty()) {
                    Node tmp = listNodeDel.remove(0);
                    if (!tmp.getListOfChildren().isEmpty()) {
                        listNodeDel.addAll(tmp.getListOfChildren());
                    }
                    if (!tmp.equals(root)) {
                        scenePane.getChildren().remove(tmp);
                        scenePane.getChildren().remove(tmp.getParentLine());
                    }
                }
                rootNode = copyTree;
                scenePane.getChildren().add(rootNode);

                ArrayList<Node> listNode = new ArrayList<>();
                listNode.add(rootNode);
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
                if (traverseNode.equals(rootNode))
                    System.out.println("Yes");
                traverseNode = searchNode(Id);
                if (traverseNode.equals(rootNode))
                    System.out.println("Yes");
                if (traverseNode != null) {
                    traverseNode.setState(2);
                }
            }

            this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete2().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor2);
            this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
        }
    }

    public void forwardInsertStep(int parentId, int childID, Pane scenePane) {

        if (traverseNode == null) {
            this.getTreeController().getRecPseudoInsert2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert1().setFill(recColor1);

            traverseNode = queue.remove(0);
        }

        if (traverseNode.getState() == 1) { // TH1
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {

                    this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
                    this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
                    this.getTreeController().getRecPseudoInsert4().setFill(recColor2);
                    this.getTreeController().getRecPseudoInsert5().setFill(recColor2);

                });

                pause.play();

                if (traverseNode.getNodeId() == parentId) {
                    Node childNode = new Node(childID);
                    traverseNode.addChild(childNode);

                    scenePane.getChildren().add(childNode.getParentLine());
                    scenePane.getChildren().add(childNode);
                    traverseNode.setState(5);
                    timeline.stop();
                    isTimelineRunning = false;

                } else
                    traverseNode.setState(3);

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == parentId) {
                    traverseNode.setState(5);
                    timeline.stop();
                    isTimelineRunning = false;
                } else
                    traverseNode.setState(3);
            }
        } else if (traverseNode.getState() == 2) {

            this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert4().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor2);

            if (traverseNode.getNodeId() == parentId) {
                Node childNode = new Node(childID);
                traverseNode.addChild(childNode);
                scenePane.getChildren().add(childNode.getParentLine());
                scenePane.getChildren().add(childNode);
                traverseNode.setState(5);
                timeline.stop();
                isTimelineRunning = false;

            } else
                traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu

            this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert6().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor2);

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();
            }
        }
    }

    public void backwardInsertStep(int Id, Pane scenePane) {

        if (traverseNode == null) {
            return;
        }
        if (traverseNode == rootNode && traverseNode.getState() == 0)
            return;
        System.out.printf("\nNode %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        if (traverseNode.getState() == 1) { // State 1

            traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
            queue.add(0, traverseNode);
            // Change colors

            // Handle case when node is root
            if (traverseNode.isRootNode()) {
                traverseNode = null;
                return;
            }

            boolean traverseNodeIsFirstChild;
            try {
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            } catch (NodeNotExistsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (!traverseNodeIsFirstChild) { // Node has previous siblings
                traverseNode = traverseNode.getLeftSibling();
                return;
            } else { // Node is the first child
                Node parent = traverseNode.getParentNode();
                if (parent.isRootNode()) { // Parent is root
                    traverseNode = rootNode;
                } else { // Parent is not root
                    Node leftSiblingOfParent = parent.getLeftSibling();
                    if (leftSiblingOfParent != null) {
                        if (leftSiblingOfParent.getNumChildren() > 0) { // Left sibling has children
                            traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                        } else { // Left sibling has no children
                            traverseNode = parent.getParentNode().getTheLastChild();
                        }
                    } else { // Parent is the first child of its parent
                        Node grandpa = parent.getParentNode();
                        traverseNode = grandpa.getTheLastChild();
                    }
                }
            }
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());

            backwardInsertStep(Id, scenePane);
        } else if (traverseNode.getState() == 2) { // State 2
            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoInsert1().setFill(recColor2);
                this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            } else {
                this.getTreeController().getRecPseudoInsert1().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
                this.getTreeController().getRecPseudoInsert6().setFill(recColor2);
                this.getTreeController().getRecPseudoInsert7().setFill(recColor2);

            }
            try { // Traverse
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " "
                        + traverseNode.getParentNode().getNodeId());
                traverseNode.setState(1);
                System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(),
                        traverseNode.getState());

            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(1);
            }
        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoInsert1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());

        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoInsert1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert4().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        } else if (traverseNode.getState() == 5) {
            scenePane.getChildren().remove(traverseNode.getTheLastChild().getParentLine());
            scenePane.getChildren().remove(traverseNode.getTheLastChild());
            traverseNode.getListOfChildren().remove(traverseNode.getTheLastChild());
            rebuildTree(scenePane);
            traverseNode.setState(2);

            isTimelineRunning = true;
            this.getTreeController().getRecPseudoInsert1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        }
    }

    public void okSearch() {
        timeline.stop();
        queue = null;
        traverseNode = null;
        // 1. Set lại state=0 và colorCircle = NOT_VISIT_COLOR
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size() > 0) {
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    queue.add(n);
                }
            }
        }

        queue = null;
        // 2. Set lại màu của các pseudoCode
        this.getTreeController().getRecPseudoSearch1().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch2().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch3().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch4().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch5().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch6().setFill(recColor1);
        this.getTreeController().getRecPseudoSearch7().setFill(recColor1);
        // 3. set visible = false cho cái stackPane
        this.getTreeController().getvBoxSearch().setVisible(false);
    }

    public void okUpdate() {
        timeline.stop();
        queue = null;
        traverseNode = null;
        // 1. Set lại state=0 và colorCircle = NOT_VISIT_COLOR
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size() > 0) {
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    queue.add(n);
                }
            }
        }

        queue = null;
        // 2. Set lại màu của các pseudoCode
        this.getTreeController().getRecPseudoUpdate1().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate2().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate3().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate4().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate5().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate6().setFill(recColor1);
        this.getTreeController().getRecPseudoUpdate7().setFill(recColor1);
        // 3. set visible = false cho cái stackPane
        this.getTreeController().getvBoxSearch().setVisible(false);
    }

    public void okDelete() {
        timeline.stop();
        queue = null;
        traverseNode = null;
        // 1. Set lại state=0 và colorCircle = NOT_VISIT_COLOR
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size() > 0) {
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    queue.add(n);
                }
            }
        }

        queue = null;
        // 2. Set lại màu của các pseudoCode

        if (this.getTreeController() instanceof BalancedTreeController) {
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            this.getTreeController().getvBoxDeleteB().setVisible(false);
        } else {
            this.getTreeController().getRecPseudoDelete1().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete2().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete3().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete4().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete5().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete6().setFill(recColor1);
            this.getTreeController().getRecPseudoDelete7().setFill(recColor1);
            this.getTreeController().getvBoxDelete().setVisible(false);
        }
        // 3. set visible = false cho cái stackPane

    }

    public void startTraverseTreeDFS() {
        stack = new ArrayList<Node>();

        this.getTreeController().getRecPseudoDFS1().setFill(recColor2);
        this.treeController.getvBoxSearch().setVisible(false);
        this.treeController.getvBoxBFS().setVisible(false);
        this.treeController.getvBoxDFS().setVisible(true);
        this.treeController.getvBoxDelete().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(false);
        this.treeController.getvBoxDeleteB().setVisible(false);
        this.treeController.getvBoxInsertB().setVisible(false);
        this.treeController.getvBoxInsert().setVisible(false);
        stack.add(rootNode);
        rootNode.setState(1);
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardDFS1Step();
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void okTraverse() {
        timeline.stop();
        // 0. Empty cái stack/queue và empty cái traverse node = null
        String algo = this.getTreeController().getAlgorithm();
        if (algo.equals("BFS")) {
            queue = null;
        } else {
            stack = null;
        }
        traverseNode = null;
        // 1. Set lại state=0 và colorCircle = NOT_VISIT_COLOR
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size() > 0) {
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    queue.add(n);
                }
            }
        }

        queue = null;
        // 2. Set lại màu của các pseudoCode
        if (algo.equals("BFS")) {
            this.getTreeController().getRecPseudoBFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
        } else {
            this.getTreeController().getRecPseudoDFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
        }
        // 3. set visible = false cho cái stackPane
        this.getTreeController().getStackPanePseudo().setVisible(false);
        if (algo.equals("BFS")) {
            this.getTreeController().getvBoxBFS().setVisible(false);
        }
        if (algo.equals("DFS")) {
            this.getTreeController().getvBoxDFS().setVisible(false);
        }

    }

    public void okInsert() {
        this.getTimeline().stop();
        System.out.println("okInsert Operation");
        traverseNode = null;
        
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size() > 0) {
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    queue.add(n);
                }
            }
        }
        queue = null;
        // 2. Set lại màu của các pseudoCode
        if (this.getTreeController() instanceof BalancedTreeController) {
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
            this.getTreeController().getvBoxInsertB().setVisible(false);
        } else {
            this.getTreeController().getRecPseudoInsert1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsert7().setFill(recColor1);
            this.getTreeController().getvBoxInsert().setVisible(false);
        }
        // 3. set visible = false cho cái stackPane

        System.out.println("okInsert ENDDDD Operation");
    }

    public void startTraverseTreeBFS() {
        queue = new ArrayList<Node>();

        queue.add(rootNode);
        this.getTreeController().getRecPseudoBFS1().setFill(recColor2);
        this.treeController.getvBoxSearch().setVisible(false);
        this.treeController.getvBoxBFS().setVisible(true);
        this.treeController.getvBoxDFS().setVisible(false);
        this.treeController.getvBoxDelete().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(false);
        this.treeController.getvBoxDeleteB().setVisible(false);
        this.treeController.getvBoxInsertB().setVisible(false);
        this.treeController.getvBoxInsert().setVisible(false);
        rootNode.setState(1);

        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardBFS1Step();
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void startUpdate(int OldId, int NewId) {
        queue = new ArrayList<Node>();

        queue.add(rootNode);
        rootNode.setState(1);
        this.treeController.getvBoxSearch().setVisible(false);
        this.treeController.getvBoxBFS().setVisible(false);
        this.treeController.getvBoxDFS().setVisible(false);
        this.treeController.getvBoxDelete().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(true);
        this.treeController.getvBoxDeleteB().setVisible(false);
        this.treeController.getvBoxInsertB().setVisible(false);
        this.treeController.getvBoxInsert().setVisible(false);
        this.getTreeController().getRecPseudoUpdate1().setFill(recColor2);
        rootNode.setState(1);
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardUpdateStep(OldId, NewId);
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void startSearch(int id) {
        queue = new ArrayList<Node>();

        queue.add(rootNode);
        rootNode.setState(1);
        this.treeController.getvBoxSearch().setVisible(true);
        this.treeController.getvBoxBFS().setVisible(false);
        this.treeController.getvBoxDFS().setVisible(false);
        this.treeController.getvBoxDelete().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(false);
        this.treeController.getvBoxDeleteB().setVisible(false);
        this.treeController.getvBoxInsertB().setVisible(false);
        this.treeController.getvBoxInsert().setVisible(false);
        this.getTreeController().getrecPseudoSearch1().setFill(recColor2);
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardSearchStep(id);
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void startInsert(int parentId, int childId, Pane scenePane) {
        intialTree = intialTree.copyNode(this.getRootNode());
        isTimelineRunning = true;
        queue = new ArrayList<Node>();
        queue.add(rootNode);

        this.treeController.getvBoxSearch().setVisible(false);
        this.treeController.getvBoxBFS().setVisible(false);
        this.treeController.getvBoxDFS().setVisible(false);
        this.treeController.getvBoxDelete().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(false);
        this.treeController.getvBoxDeleteB().setVisible(false);
        if ((this.getTreeController() instanceof BalancedTreeController)
                || (this.getTreeController() instanceof BalancedBinaryTreeController)) {
            this.treeController.getvBoxInsertB().setVisible(true);
            this.treeController.getvBoxInsert().setVisible(false);
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor2);
            System.out.println("Balance Binary Tree");
        } else {
            this.treeController.getvBoxInsertB().setVisible(false);
            this.treeController.getvBoxInsert().setVisible(true);
            this.getTreeController().getRecPseudoInsert1().setFill(recColor2);

        }
        rootNode.setState(1);

        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isTimelineRunning) {
                    forwardInsertStep(parentId, childId, scenePane);
                } else
                    return;
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void startDelete(int Id, Pane scenePane) {
        System.out.println("Delete Operation");
        traverseNode = null;
        intialTree = intialTree.copyNode(this.getRootNode());
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        this.treeController.getvBoxSearch().setVisible(false);
        this.treeController.getvBoxBFS().setVisible(false);
        this.treeController.getvBoxDFS().setVisible(false);
        this.treeController.getvBoxUpdate().setVisible(false);
        this.treeController.getvBoxInsertB().setVisible(false);
        this.treeController.getvBoxInsert().setVisible(false);
        this.getTreeController().getRecPseudoDelete1().setFill(recColor2);
        if ((this.getTreeController() instanceof BalancedTreeController)
                || (this.getTreeController() instanceof BalancedBinaryTreeController)) {

            this.treeController.getvBoxDelete().setVisible(false);
            this.treeController.getvBoxDeleteB().setVisible(true);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor2);
        } else {
            this.treeController.getvBoxDelete().setVisible(true);
            this.treeController.getvBoxDeleteB().setVisible(false);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor2);
        }
        rootNode.setState(1);

        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                forwardDeleteStep(Id, scenePane);
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

}
