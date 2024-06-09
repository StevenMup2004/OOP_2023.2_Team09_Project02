package screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private ChoiceBox<String> ChoiceBox;
    private String[] tree = { "Overview", "Usage", "Generic Tree", "Binary Tree", "Balance Tree",
            "Balance Binary Tree" };
    @FXML
    private Text title;
    @FXML
    private TextArea contentTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBox.getItems().addAll(tree);
        ChoiceBox.setOnAction(this::getTree);
    }

    public void getTree(ActionEvent event) {
        String myTree = ChoiceBox.getValue();
        title.setText(myTree);

        String content;
        switch (myTree) {
            case "Overview":
                content = "This project involves designing a program to display and explain the basic operations on \n types of trees: generic tree, binary tree, balanced tree, and balanced binary tree.\n"
                        +
                        " The user will be able to interact with the trees through a graphical user interface (GUI),\n"
                        +
                        " and they can choose to visualize various operations like create, insert, delete, update,\n traverse, and search.";
                break;
            case "Usage":
                content = "- Start the application and navigate to the main menu.\n" +
                        "- Choose the type of tree you want to work with: generic tree,\n binary tree, balanced tree, or balanced binary tree.\n"
                        +
                        "- Read the help menu to understand the basic usage and aim of the project.\n" +
                        "- Perform operations on the selected tree:\n" +
                        " + Create: Create a new empty tree.\n" +
                        " + Insert: Add a new node with a specified value as a child of a specified parent node.\n" +
                        " + Delete: Delete a node with a specified value from the tree.\n" +
                        " + Update: Change the value of a node from its current value to a new value.\n" +
                        " + Traverse: Traverse all nodes in the tree using the Depth-First Search (DFS)\n or Breadth-First Search (BFS) algorithm, highlighting the current node at each step of traversal.\n"
                        +
                        " + Search: Search for a specific value in the tree.\n" +
                        "- During the execution of an operation, the code panel will show pseudo code or actual code,\n and the progress bar will indicate the execution progress.\n"
                        +
                        "- Use the controls in the bottom bar to pause, continue, go backward, or forward during the\n execution of an operation.\n"
                        +
                        "- Undo or redo operations using the respective options in the bottom bar.\n" +
                        "- Use the \"Back\" button to return to the main menu at any time.";
                break;
            case "Generic Tree":
                content = "- A tree is a nonlinear hierarchical data structure\n" +
                        "that consists of nodes connected by edges and contains no cycles.\n"
                        + "- It does not impose any specific ordering or constraint on the child nodes.\n"
                        + "- It provides a flexible way to represent hierarchical relationships among elements.";
                break;
            case "Binary Tree":
                content = "- A binary tree is a tree data structure in which each node has at most two child nodes,\n referred to as the left child and the right child.\n"
                        + "- It follows the constraint that the left child node's value is less than the parent node's\n value, and the right child node's value is greater than or equal to the parent node's value.\n"
                        + "- Binary trees are commonly used for efficient searching, sorting, and traversal algorithms.";
                break;
            case "Balance Tree":
                content = "- A balanced tree is a tree where each leaf node is “not more than a certain distance” \n away from the root than any other leaf.\n"
                        + "- It ensures that the tree remains balanced, which helps in maintaining efficient search,\n insertion, and deletion operations.\n"
                        + "- Popular examples of balanced trees include AVL trees and red-black trees.";
                break;
            case "Balance Binary Tree":
                content = "- A balanced binary tree combines the properties of both binary trees and balanced trees.\n"
                        + "- It provides the benefits of both efficient searching and maintaining balance for better\n performance.";
                break;
            default:
                content = "No information available for the selected tree.";
                break;
        }

        contentTextArea.setText(content);

    }
}