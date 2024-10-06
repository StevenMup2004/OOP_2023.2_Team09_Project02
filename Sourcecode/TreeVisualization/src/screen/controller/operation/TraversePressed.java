package screen.controller.operation;

import treedatastructure.GenericTree;

public class TraversePressed implements UserAction {

    private GenericTree TreeDataStructure;
    private String algorithm;

    public TraversePressed(GenericTree genericTree, String algorithm) {
        this.TreeDataStructure = genericTree;
        this.algorithm = algorithm;

    }

    @Override
    public void run() {
        if (algorithm.equals("BFS"))
        	
            this.getTreeDataStructure().startTraverseTreeBFS();
        
        else
            this.getTreeDataStructure().startTraverseTreeDFS();
    }

    private GenericTree getTreeDataStructure() {
        return TreeDataStructure;
    }

    @Override
    public void undo() {
        this.getTreeDataStructure().okTraverse();
    }

    @Override
    public void redo() {
        this.undo();
        this.run();
    }

}
