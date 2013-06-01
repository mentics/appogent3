package com.mentics.designer;

import com.mentics.model.Modification;
import com.mentics.model.Modify;
import com.mentics.model.graph.Node;

public class AddNodeAction implements Modify<DataModel> {

    public static class AddedNode implements Modification<DataModel> {
        private DataModel model;
        private Node newNode;

        public AddedNode(DataModel model, Node newNode) {
            this.model = model;
            this.newNode = newNode;
        }

        @Override
        public DataModel newValue() {
            return model;
        }

        public Node getValueAdded() {
            return newNode;
        }
    }
    
    private Node node;


    public AddNodeAction(Node node) {
        this.node = node;
    }

    @Override
    public Modification<DataModel> apply(DataModel oldValue) {
        return new AddedNode(oldValue.withNewNode(node), node);
    }
}
