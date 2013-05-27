package com.mentics.designer;

import com.mentics.designer.AddNodeAction.AddedNode;
import com.mentics.designer.spi.EdgePainter;
import com.mentics.designer.spi.Layout;
import com.mentics.designer.spi.NodePainter;
import com.mentics.model.Modification;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import sodium.BehaviorSink;
import sodium.Event;
import sodium.Handler;
import sodium.Listener;

public class BasicLayout implements Layout {

    private NodePainter nodePainter;
    private EdgePainter edgePainter;
    private BehaviorSink<Point2D> mousePos;
    private Listener listen;

    public BasicLayout(final Group group, final BehaviorSink<Point2D> mousePos, Event<Modification<DataModel>> updates, final NodePainter nodePainter, EdgePainter edgePainter) {
        this.mousePos = mousePos;
        this.nodePainter = nodePainter;
        this.edgePainter = edgePainter;
        listen = updates.listen(new Handler<Modification<DataModel>>() {
            @Override
            public void run(final Modification<DataModel> mod) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (mod instanceof AddedNode) {
                            Item added = ((AddedNode) mod).getValueAdded();
                            if (added != null) {
                                // TODO: translate from mouse coords to world coords
                                Node newNode = nodePainter.createNode(added);
                                Point2D mouse = mousePos.sample();
                                newNode.setTranslateX(mouse.getX());
                                newNode.setTranslateY(mouse.getY());
                                //Random r = new Random();
                                //newNode.setTranslateZ(r.nextDouble() * 100);
                                group.getChildren().add(newNode);
                            }
                        }
                    }
                });
            }
        });
    }
}