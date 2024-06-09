package screen.controller.operation;

import javafx.scene.layout.Pane;
import screen.controller.GenericTreeController;
import treedatastructure.GenericTree;
import treedatastructure.Node;

public class UpdatePressed implements UserAction {
    private int intOldVal;
    private int intNewVal;
    private GenericTreeController genericTreeController;
    private GenericTree TreeDataStructure;
    private Pane scenePane;

    public UpdatePressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane,
            int intOldVal, int intNewVal) {
        this.TreeDataStructure = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.intNewVal = intNewVal;
        this.intOldVal = intOldVal;
    }

    @Override
    public void run() {
        this.getTreeDataStructure().startUpdate(intOldVal, intNewVal);
        System.out.println("Update operation.");
    }

    private GenericTree getTreeDataStructure() {
        return TreeDataStructure;
    }

    @Override
    public void undo() {
        Node nodeObject = TreeDataStructure.searchNode(intNewVal);
        nodeObject.getTfId().setText(String.valueOf(intOldVal));
        nodeObject.updateId(intOldVal);
        System.out.println("Update operation undo.");
    }

    @Override
    public void redo() {
        this.undo();
        this.TreeDataStructure.okUpdate();
        this.run();
    }

}
