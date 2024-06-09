package treedatastructure;

import exception.NodeFullChildrenException;
import exception.TreeException;

public class BinaryTree extends GenericTree {
    public static final int MAX_CHILDREN = 2;

    @Override
    public void checkInsertNode(int parentId, int childId) throws TreeException {
        super.checkInsertNode(parentId, childId);
        Node parent = searchNode(parentId);

        if (this.checkFullChildrenNode(parent)) {
            throw new NodeFullChildrenException("The parent already has 2 children! Can't add!");
        }
    }

    public Node insertNode(int parentId, int childId) {
        Node parent = searchNode(parentId);
        return parent.addChild(childId);
    }

    public boolean checkFullChildrenNode(Node node) {
        return node.getNumChildren() == MAX_CHILDREN;
    }

    public boolean checkFullChildrenNode(int nodeVal) {
        Node node = this.searchNode(nodeVal);
        return node.getNumChildren() == MAX_CHILDREN;
    }

}
