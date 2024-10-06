package treedatastructure;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import exception.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BalancedTree extends GenericTree {
    private int MAX_DIFF_DISTANCE;
    private int change = 0;

    public BalancedTree() {
        this.MAX_DIFF_DISTANCE = 2;
    }

    public BalancedTree(int MAX_DIFF_DISTANCE) {
        this.MAX_DIFF_DISTANCE = MAX_DIFF_DISTANCE;
    }

    public void forwardInsertStep(int parentId, int childID, Pane scenePane) {
        
    
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);

            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);


            traverseNode = queue.remove(0);
        }
        System.out.println(traverseNode.getNodeId());
        System.out.println(traverseNode.getState());
        if (traverseNode.getState() == 1) { // TH1
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event -> {
                	 this.getTreeController().getRecPseudoInsertB2().setFill(recColor2);
                     this.getTreeController().getRecPseudoInsertB3().setFill(recColor2);
                     this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
                     
                     this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);

                     this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
                     this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
                     this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);



                });

                pause.play();

      
               
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth());
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == parentId) {
                    traverseNode.setState(5);
                    timeline.stop();
                    isTimelineRunning = false;
                }
                traverseNode.setState(3);
            }
        } else if (traverseNode.getState() == 2) {

            this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor2);
            
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);


            if (traverseNode.getNodeId() == parentId) {
            	 Node childNode = new Node(childID);
                 System.out.println(childNode.getNodeId());
                 traverseNode.addChild(childNode);

                 scenePane.getChildren().add(childNode.getParentLine());
                 scenePane.getChildren().add(childNode);
                 traverseNode.setState(5);
                 timeline.stop();
                 isTimelineRunning = false;
                 return;

            }
            else traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu

            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor2);

            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor2);

            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();
            }
        }
    }

    public void backwardInsertStep(int Id, Pane scenePane) {
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
        } else if (traverseNode.getState() == 2) { // State 2
            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoInsertB1().setFill(recColor2);
                this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
            } else {
                this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
                this.getTreeController().getRecPseudoInsertB6().setFill(recColor2);
                this.getTreeController().getRecPseudoInsertB7().setFill(recColor2);

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
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
        } else if (traverseNode.getState() == 5) {
            scenePane.getChildren().remove(traverseNode.getTheLastChild().getParentLine());
            scenePane.getChildren().remove(traverseNode.getTheLastChild());
            traverseNode.getListOfChildren().remove(traverseNode.getTheLastChild());
            rebuildTree(scenePane);
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoInsertB1().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB2().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB3().setFill(recColor2);
            this.getTreeController().getRecPseudoInsertB4().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB5().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB6().setFill(recColor1);
            this.getTreeController().getRecPseudoInsertB7().setFill(recColor1);
        }
    }

    public int getMaxDiffDistance() {
        return this.MAX_DIFF_DISTANCE;
    }

    public boolean isBalanced() {
        boolean balance = true;
        Node rootNode = this.getRootNode();
        ArrayList<Node> listOfLeaves = new ArrayList<Node>();

        // 1. Lấy danh sách các Leaf: O(N) với N = số node của cây
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.isLeaf()) {
            listOfLeaves.add(rootNode);
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0) {
            tmp = queue.remove(0); // lấy node đầu tiên của queue
            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.isLeaf()) {
                        listOfLeaves.add(n);
                    }
                    queue.add(n);
                }
            }
        }

        // 2. Check xem có balance không ? O(M^2) với M là số lá của cây
        int diff_distance;
        int numberOfLeaves = listOfLeaves.size();
        System.out.println(numberOfLeaves);
        for (int i = 0; i < numberOfLeaves; i++) {
            for (int j = i + 1; j < numberOfLeaves; j++) {
                diff_distance = Math.abs(listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth());
                System.out.println(
                        listOfLeaves.get(i).getNodeId() + " " + listOfLeaves.get(j).getNodeId() + " " + diff_distance);
                if (diff_distance > MAX_DIFF_DISTANCE) {
                    balance = false;
                }
            }
        }
        return balance;
    }

    @Override
    public void checkInsertNode(int parentId, int childId) throws TreeException {
        super.checkInsertNode(parentId, childId);

        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        tmpBalancedTree.insertNode(parentId, childId);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

    public BalancedTree copyBalanceTree(BalancedTree oldTree) {
        ArrayList<Node> oldQueue = new ArrayList<Node>();
        BalancedTree newTree = new BalancedTree(this.getMaxDiffDistance());
        oldQueue.add(oldTree.getRootNode());
        Node newRoot = new Node(oldTree.getRootNode().getNodeId());
        newTree.setRootNode(newRoot);
        while (oldQueue.size() > 0) {
            Node tmp = oldQueue.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                for (Node childNode : tmp.getListOfChildren()) {
                    Node newChild = newTree.insertNode(tmp.getNodeId(), childNode.getNodeId());
                    oldQueue.add(childNode);
                }
            }
        }
        return newTree;
    }

    @Override
    public void checkDeleteNode(int oldNode) throws TreeException {
        super.checkDeleteNode(oldNode);
        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        System.out.println("hello " + tmpBalancedTree.getRootNode().getNodeId());
        tmpBalancedTree.deleteNode(oldNode);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

    public Node findMinDepthLeaf() { // đổi tên từ makeBalance -> makeBalanceInsert
        ArrayList<Node> queue = new ArrayList<Node>();
        HashMap<Integer, Integer> depthLeaf = new HashMap<Integer, Integer>();

        int minDepth = 100;
        Node minNode = new Node(0);
        Node newRoot = new Node(this.getRootNode().getNodeId());
        queue.add(this.getRootNode());
        while (queue.size() > 0) {
            Node tmp = queue.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                queue.addAll(tmp.getListOfChildren());
            }
            if (tmp.getNumChildren() == 0) {
                if (minDepth > tmp.getDepth()) {
                    minDepth = tmp.getDepth();
                    minNode = tmp;
                }
            }
        }
        return minNode;
    }

    public void makeBalanceDelete(int delId, Pane scenePane) {
        BalancedTree copiedTree;
        copiedTree = this.copyTreeHao();

        // 1. Delete trên cây copiedTree
        ArrayList<Node> queue = new ArrayList<Node>();
        if (copiedTree.getRootNode().getNodeId() == delId) {
            copiedTree.setRootNode(null);
            return;
        }
        queue.add(copiedTree.getRootNode());
        Node tmp = null; // đây là node cha của node bị delete. Đây chính là nguyên nhân gây ra
                         // unbalanced
        boolean kdDel = false;
        while (queue.size() > 0) {
            tmp = queue.remove(0);
            if (tmp.getNumChildren() > 0) {
                for (int i = 0; i < tmp.getNumChildren(); i++) {
                    if (tmp.getListOfChildren().get(i).getNodeId() == delId) {
                        tmp.getListOfChildren().remove(i);
                        kdDel = true;
                        break;
                    }
                    queue.add(tmp.getListOfChildren().get(i));
                }
            }
            if (kdDel) {
                break;
            }
        }
        System.out.println("1. Delete node tren cây copiedTree (Done)");
        copiedTree.printBFS();
        System.out.println("tmp.id = " + tmp.getNodeId());

        // 2. Copy tree từ cây gốc
        System.out.println("Bắt đầu bước 2. của hàm makeBalanceDelete; tmp==null: ");
        // 2.1. Make balanced cho cây copiedTree
        System.out.println(
                "Trước khi make balance (xoay cây) cho cây copiedTree, cây copiedTree: " + copiedTree.isBalanced());
        copiedTree.printBFS();

        Node node = nodeMakeUnbalanced(tmp);
        while (true) {
            if (node != null) {
                tmp = copiedTree.makeBalanced(tmp.getNodeId());
                if (copiedTree.isBalanced()) {
                    break;
                } else {
                    node = nodeMakeUnbalanced(tmp);
                }
            } else if (tmp.isRootNode()) {
                break;
            } else if (!tmp.isRootNode()) {
                tmp = tmp.getParentNode();
                node = nodeMakeUnbalanced(tmp);
            }

        }
        System.out.println(
                "Sau khi make balance (xoay cây) cho cây copiedTree, cây copiedTree: " + copiedTree.isBalanced());
        copiedTree.printBFS();

        // 3. Copy từ cây copiedTree đã cân bằng sang cây this và hiển thị cây this lên
        // GUI
        this.setRootNode(null);

        this.createTree(copiedTree.getRootNode().getNodeId());
        Node root = this.getRootNode();
        scenePane.getChildren().add(root);
        queue = new ArrayList<Node>();

        queue.add(copiedTree.getRootNode());
        Node tmp_child;
        while (queue.size() > 0) {
            tmp = queue.remove(0);
            if (tmp.getNumChildren() > 0) {
                for (Node child : tmp.getListOfChildren()) {
                    tmp_child = this.insertNode(tmp.getNodeId(), child.getNodeId());
                    scenePane.getChildren().add(tmp_child);
                    scenePane.getChildren().add(tmp_child.getParentLine());
                    queue.add(child);
                }

            }
        }

    }

    public BalancedTree copyTreeHao() {
        BalancedTree copiedTree = new BalancedTree(this.MAX_DIFF_DISTANCE);
        if (this.getRootNode() == null) {
            System.out.println("COPY: this có root = null, copy root = null!");
            copiedTree.setRootNode(null);
            return copiedTree;
        }
        ArrayList<Node> queue = new ArrayList<Node>();
        copiedTree.setRootNode(new Node(this.getRootNode().getNodeId()));
        queue.add(this.getRootNode());
        Node this_tmp;
        Node copy_tmp;
        while (queue.size() > 0) {
            this_tmp = queue.remove(0);
            copy_tmp = copiedTree.searchNode(this_tmp.getNodeId());
            if (this_tmp.getNumChildren() > 0) {
                for (Node child : this_tmp.getListOfChildren()) {
                    copy_tmp.addChild_NO_GUI(child.getNodeId());
                    queue.add(child);
                }
            }
        }
        return copiedTree;
    }

    public Node makeBalanced(int fromId) {
        Node tmp = searchNode(fromId);

        Node node = nodeMakeUnbalanced(tmp);

        /*
         * Đến đây, ta thu được:
         * - tmp: node bị mất cân bằng
         * - nodes: 2 node làm mất cân bằng
         */
        Node biggerNode = node;
        Node ancestorBigger = null;

        for (Node n : tmp.getListOfChildren()) {
            if (n.isAncestor(biggerNode)) {
                ancestorBigger = n;
                break;
            }

        }

        Node secondAncestorBigger = null;
        for (Node n : ancestorBigger.getListOfChildren()) {
            if (n.isAncestor(biggerNode)) {
                secondAncestorBigger = n;
                break;
            }
        }

        /*
         * Bắt đầu xoay cây
         */
        for (int i = 0; i < ancestorBigger.getNumChildren(); i++) {
            if (ancestorBigger.getListOfChildren().get(i).equals(secondAncestorBigger)) {
                continue;
            }
            tmp.addChildmakeBalanceDel(ancestorBigger.getListOfChildren().remove(i));
        }

        tmp.getListOfChildren().remove(ancestorBigger);
        if (tmp.getParentNode() == null) { // nếu tmp là rootNode
            this.setRootNode(ancestorBigger);
            ancestorBigger.addChildmakeBalanceDel(tmp);
            return ancestorBigger;
        }
        tmp.getParentNode().addChildmakeBalanceDel(ancestorBigger);
        tmp.getParentNode().getListOfChildren().remove(tmp);
        ancestorBigger.addChildmakeBalanceDel(tmp);
        return ancestorBigger;
    }

    public Node nodeMakeUnbalanced(Node root) {
        Node node = null;
        ArrayList<Node> queue = new ArrayList<Node>();
        ArrayList<Node> listOfLeaves = new ArrayList<Node>();

        if (root.isLeaf()) {
            return node;
        }
        queue.add(root);
        Node tmp;
        while (queue.size() > 0) {
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.isLeaf()) {
                        listOfLeaves.add(n);
                    }
                    queue.add(n);
                }
            }
        }

        for (int i = 0; i < listOfLeaves.size(); i++) {
            for (int j = i; j < listOfLeaves.size(); j++) {
                int diff = listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth();
                if (Math.abs(diff) > MAX_DIFF_DISTANCE) {
                    if (diff > 0) {
                        node = listOfLeaves.get(i);
                    } else {
                        node = listOfLeaves.get(j);
                    }

                    return node; // node có depth lớn hơn.
                }
            }
        }
        return node;
    }

    @Override
    public void forwardDeleteStep(int Id, Pane scenePane) {
        if (traverseNode == null) {
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            traverseNode = queue.remove(0);
        }
        if (traverseNode.getState() == 1) { // TH1
            // Nếu đã được add vào queue và đc remove ra (xong state1) thì tiếp state2
            // (Sys.print)
            try { // duyệt

                traverseNode.getCircle().setFill(VISIT_COLOR);
                traverseNode.setState(2);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.43));
                pause.setOnFinished(event -> {
                	 this.getTreeController().getRecPseudoDeleteB2().setFill(recColor2);
                     this.getTreeController().getRecPseudoDeleteB3().setFill(recColor2);
                     this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
                     this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
                     this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
                     this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
                     this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
                });

                pause.play();

                
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println("Error");
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
                if (traverseNode.getNodeId() == Id) {

                    copyTree = copyTree.copyNode(this.getRootNode());
                    scenePane.getChildren().clear();
                    makeBalanceDelete(Id, scenePane);

                    traverseNode.setState(5);
                    timeline.stop();
                } else
                    traverseNode.setState(3);

            }
        } else if (traverseNode.getState() == 2) {
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            if (traverseNode.getNodeId() == Id) {
                if (change == 0) {
                    copyTree = copyTree.copyNode(this.getRootNode());
                    change = 1;
                }
                traverseNode.setState(5);
                scenePane.getChildren().clear();
                makeBalanceDelete(Id, scenePane);
                timeline.stop();
                return;

            } else
                traverseNode.setState(3);

        }

        else if (traverseNode.getState() == 3) { // TH2
            // thay đổi màu
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(4);
        } else if (traverseNode.getState() == 4) { // TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();

            }
        }
    }

    @Override
    public void backwardDeleteStep(int Id, Pane scenePane) {
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
                System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(),
                        traverseNode.getState());
                backwardDeleteStep(Id, scenePane);

            }
        } else if (traverseNode.getState() == 2) { // State 2

            if (traverseNode.equals(rootNode)) {
                this.getTreeController().getRecPseudoDeleteB1().setFill(recColor2);
                this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
                this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
                this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
                this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
                this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
                this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            } else {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.43));
                pause.setOnFinished(event -> {
                    this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
                    this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
                    this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
                    this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
                    this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
                    this.getTreeController().getRecPseudoDeleteB6().setFill(recColor2);
                    this.getTreeController().getRecPseudoDeleteB7().setFill(recColor2);

                });

                pause.play();

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
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        } else if (traverseNode.getState() == 3) {
            traverseNode.setState(2);
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        } else if (traverseNode.getState() == 4) { // State 3
            // Change colors
            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            if (traverseNode.getNumChildren() > 0) { // Remove children if any
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(3);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        } else if (traverseNode.getState() == 5) {
            System.out.println("\nSTATE 5");
            scenePane.getChildren().clear();
            this.rootNode = copyTree;
            Node root = copyTree;
            scenePane.getChildren().add(root);

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
            traverseNode = searchNode(Id);
            if (traverseNode != null) {
                traverseNode.setState(2);
            } else
                System.out.print("ERROR NULL");

            this.getTreeController().getRecPseudoDeleteB1().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB2().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB3().setFill(recColor2);
            this.getTreeController().getRecPseudoDeleteB4().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB5().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB6().setFill(recColor1);
            this.getTreeController().getRecPseudoDeleteB7().setFill(recColor1);
            System.out.printf("\nChange to Node %d with State %d\n", traverseNode.getNodeId(), traverseNode.getState());
        }
    }

}
