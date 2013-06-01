package com.mentics.designer;

import com.mentics.model.graph.Node;
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

    public final IntTreePMap<Node> items;


    // Constructors //

    public DataModel(String projectName, IntTreePMap<Node> items) {
        this.projectName = projectName;
        this.items = items;
    }

    public DataModel withNewNode(Node node) {
        return new DataModel(projectName, items.plus(node.id, node));
    }
}
