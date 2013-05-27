package com.mentics.designer.spi;

import com.mentics.designer.Item;
import javafx.scene.Node;

public interface NodePainter {
    Node createNode(Item added);
}
