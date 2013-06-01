package com.mentics.designer.spi;

import com.mentics.model.graph.Node;

public interface NodePainter {
    javafx.scene.Node createNode(Node added);
}
