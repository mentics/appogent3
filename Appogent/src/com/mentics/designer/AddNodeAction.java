package com.mentics.designer;

import com.mentics.model.Modification;
import com.mentics.model.Modify;

public class AddNodeAction implements Modify<DataModel> {

    public static class AddedNode implements Modification<DataModel> {
        private DataModel model;
        private Item newItem;

        public AddedNode(DataModel model, Item newItem) {
            this.model = model;
            this.newItem = newItem;
        }

        @Override
        public DataModel newValue() {
            return model;
        }

        public Item getValueAdded() {
            return newItem;
        }
    }
    
    private Item item;


    public AddNodeAction(Item item) {
        this.item = item;
    }

    @Override
    public Modification<DataModel> apply(DataModel oldValue) {
        return new AddedNode(oldValue.withNewNode(item), item);
    }
}
