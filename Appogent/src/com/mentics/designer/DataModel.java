package com.mentics.designer;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mentics.model.graph.Node;
import org.pcollections.IntTreePMap;
import sodium.Lambda1;

@DefaultSerializer(DataModel.DataModelSerializer.class)
public class DataModel {
    public static class DataModelSerializer extends Serializer<DataModel> {
        public void write(Kryo kryo, Output output, DataModel obj) {
            output.writeString(obj.projectName);
            output.writeString(obj.projectPath);
            kryo.writeObject(output, obj.items);
        }

        public DataModel read(Kryo kryo, Input input, Class<DataModel> type) {
            String projectName = input.readString();
            String projectPath = input.readString();
            IntTreePMap<Node> nodes = kryo.readObject(input, IntTreePMap.class);
            return new DataModel(projectName, projectPath, nodes);
        }
    }


    public static final Lambda1<DataModel, String> TO_PROJECT_NAME = new Lambda1<DataModel, String>() {
        @Override
        public String apply(DataModel model) {
            return model.projectName;
        }
    };


    // Instance Fields //

    public final String projectName;

    public final String projectPath;

    public final IntTreePMap<Node> items;


    // Constructors //

    public DataModel(String projectName, String projectPath, IntTreePMap<Node> items) {
        this.projectName = projectName;
        this.projectPath = projectPath;
        this.items = items;
    }

    public DataModel withNewNode(Node node) {
        // TODO: this cast to int is not safe. I think we'll move to a different data structure at some point and resolve this then.
        return new DataModel(projectName, projectPath, items.plus((int) node.id, node));
    }
}
