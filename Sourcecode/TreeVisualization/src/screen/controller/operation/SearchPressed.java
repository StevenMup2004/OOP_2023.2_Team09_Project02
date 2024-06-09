package screen.controller.operation;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.GenericTree;

public class SearchPressed implements UserAction {
    private int intNodeVal;
    private GenericTreeController genericTreeController;
    private GenericTree TreeDataStructure;
    private Pane scenePane;

    public SearchPressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane,
            int nodeVal) {
        this.TreeDataStructure = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.intNodeVal = nodeVal;
    }

    @Override
    public void run() {
        this.getTreeDataStructure().startSearch(intNodeVal);
    }

    private GenericTree getTreeDataStructure() {
        return TreeDataStructure;
    }

    @Override
    public void undo() {
        // do nothing
        System.out.println("Search operation undo.");
    }

    @Override
    public void redo() {

        this.TreeDataStructure.okSearch();
        this.run();
    }

}
