package com.mentics.designer;

import org.pcollections.IntTreePMap;
import sodium.Lambda1;

public class DataModel {
    public static final Lambda1<DataModel, String> TO_PROJECT_NAME = new Lambda1<DataModel, String>() {
        @Override
        public String apply(DataModel model) {
            return model.projectName;
        }
    };

    
    // Instance Fields //
    
    public final String projectName;

    public final IntTreePMap<Item> items;

    
    // Constructors //
    
    public DataModel(String projectName, IntTreePMap<Item> items) {
        this.projectName = projectName;
        this.items = items;
    }

    public DataModel withNewNode(Item item) {
        return new DataModel(projectName, items.plus(item.id, item));
    }
}
