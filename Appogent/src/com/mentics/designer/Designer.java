package com.mentics.designer;

import com.mentics.designer.plugins.BasicEdgePainter;
import com.mentics.designer.plugins.BasicLayout;
import com.mentics.designer.plugins.BasicNodePainter;
import com.mentics.designer.spi.Layout;
import com.mentics.javafx.DraggablePathNature;
import com.mentics.javafx.KeyEventProperty;
import com.mentics.javafx.Natures;
import com.mentics.javafx.ZoomableNature;
import com.mentics.model.ModelManager;
import com.mentics.model.graph.Node;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.agilewiki.jactor.concurrent.JAThreadFactory;
import org.agilewiki.jactor.concurrent.JAThreadManager;
import org.pcollections.IntTreePMap;
import sodium.BehaviorSink;

import java.io.File;
import java.util.Iterator;

//import static com.mentics.designer.plugins.BasicNodePainter.selectedLabels;

public class Designer extends Application {

    public static int counter = 0;
    BehaviorSink<Point2D> mousePos = new BehaviorSink<>(new Point2D(0, 0));
    private KeyEventProperty keyProp;
    private ModelManager<DataModel> mgr;
    private BasicNodePainter basicNodePainter;
    private BasicEdgePainter basicEdgePainter;
    private final Group group;

    public Designer() {
        group = new Group();
    }

    public static class Point {

        public double x;
        public double y;
    }

    public static void main(String[] args) {
        launch(args);
        threads.close();
    }

    private static JAThreadManager threads = new JAThreadManager();

    static {
        threads.start(2, new JAThreadFactory());
    }


    @Override
    public void start(Stage stage) throws Exception {
        mgr = new ModelManager<>(new DataModel("<New Project>", IntTreePMap.<Node>empty()), threads);

        stage.setScene(createScene());
        stage.setTitle("Designer");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }

    private Scene createScene() {
        // BorderPane root = new BorderPane();
        // root.setCenter(createPane());
        Pane root = createPane();

        Text projectName = new Text();
        root.getChildren().add(projectName);
        projectName.setTranslateY(projectName.getBoundsInLocal().getHeight());

        SodiumFX.bind(projectName.textProperty(), mgr.value.map(DataModel.TO_PROJECT_NAME));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("./test.css");
        System.out.println(new File("test.css").getAbsolutePath());
        System.out.println(new File("test.css").exists());
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
//                Label text;
//                switch (keyEvent.getCode()) {
//                    case S:
//                        text = (Label) getNode("LEFT");
//                        if (text != null) {
//                            text.getStyleClass().add("activenode");
//                            Label Oldtext = basicNodePainter.currentLabel.get();
//                            if (Oldtext != null) {
//                                Oldtext.getStyleClass().remove("activenode");
//                            }
//                            basicNodePainter.currentLabel.set(text);
//                        }
//                        keyEvent.consume();
//                        break;
//                    case D:
//                        text = (Label) getNode("RIGHT");
//                        if (text != null) {
//                            text.getStyleClass().add("activenode");
//                            Label Oldtext = basicNodePainter.currentLabel.get();
//                            if (Oldtext != null) {
//                                Oldtext.getStyleClass().remove("activenode");
//                            }
//                            basicNodePainter.currentLabel.set(text);
//                        }
//                        keyEvent.consume();
//                        break;
//                    case A:
//                        text = (Label) getNode("TOP");
//                        if (text != null) {
//                            text.getStyleClass().add("activenode");
//                            Label Oldtext = basicNodePainter.currentLabel.get();
//                            if (Oldtext != null) {
//                                Oldtext.getStyleClass().remove("activenode");
//                            }
//                            basicNodePainter.currentLabel.set(text);
//                        }
//                        keyEvent.consume();
//                        break;
//                    case W:
//                        text = (Label) getNode("BOTTOM");
//                        if (text != null) {
//                            text.getStyleClass().add("activenode");
//                            Label Oldtext = basicNodePainter.currentLabel.get();
//                            if (Oldtext != null) {
//                                Oldtext.getStyleClass().remove("activenode");
//                            }
//                            basicNodePainter.currentLabel.set(text);
//                        }
//                        keyEvent.consume();
//                        break;
//                    case M://Multi selection
//                        BasicNodePainter.selection = 1;
//                        break;
//                    case L://deselet selection
//                        BasicNodePainter.selection = 0;
//                        break;
//                    case F://Linking Labels
//                        drawLines();
//                        break;
//                    default:
//                }
            }

            private void drawLines() {
//                if (selectedLabels.get().getLabels() != null) {
//                    Iterator labels = selectedLabels.get().getLabels().iterator();
//                    Label first = (Label) labels.next();
//                    Path path = new Path();
//                    MoveTo moveTo = new MoveTo(first.getTranslateX(), first.getTranslateY());
//                    path.getElements().add(moveTo);
//                    //Iterator labels2 = selectedLabels.get().getLabels().iterator();
//                    while (labels.hasNext()) {
//                        Label l = (Label) labels.next();
//                        QuadCurveTo quadCurveTo = new QuadCurveTo();//l.getTranslateX(), l.getTranslateY());
//                        quadCurveTo.setX(l.getTranslateX());
//                        quadCurveTo.setY(l.getTranslateY());
//                        quadCurveTo.setControlX(l.getTranslateX() + 15);
//                        quadCurveTo.setControlY(l.getTranslateY() + 15);
//                        path.getElements().add(quadCurveTo);
//                        path.setStroke(javafx.scene.paint.Color.BLUE);
//                        path.setStrokeWidth(2);
//                        DraggablePathNature.addTo(l, l, path, getLabelInSelectedLabels(l), MouseButton.PRIMARY);
//                    }
//                    group.getChildren().add(path);
//                }
            }
        });

        return scene;
    }

    Layout layout;

    private Pane createPane() {
        final Pane pane = new Pane();

        pane.getChildren().add(group);
        ZoomableNature.addTo(group);
        Natures.makeMouseFocusable(pane);
        basicNodePainter = new BasicNodePainter();
        basicEdgePainter = new BasicEdgePainter();
        layout = new BasicLayout(group, mousePos, mgr.updates, basicNodePainter, basicEdgePainter);

        pane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ev) {
                if (ev.getCode() == KeyCode.INSERT) {
                    String name = "Node#" + (counter++);
                    mgr.send(new AddNodeAction(new Node(++counter, name)));
                }
            }
        });

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                mousePos.send(group.parentToLocal(ev.getX(), ev.getY()));
            }
        });

        return pane;
    }

    public javafx.scene.Node getNode(String direction) {
        return findNearestNode(direction);
    }

    public javafx.scene.Node findNearestNode(String code) {
//        javafx.scene.Node selectedNode = BasicNodePainter.currentLabel.get();
//        double x = selectedNode.getTranslateX();
//        double y = selectedNode.getTranslateY();
//        Point2D point2D = new Point2D(x, y);
//        double MAX_DISTANCE;
//        MAX_DISTANCE = 10000;
        javafx.scene.Node returnedNode = null;
//        for (javafx.scene.Node PTmp : group.getChildren()) {
//            double tmpDistance = Math.sqrt(Math.pow(PTmp.getTranslateX() - point2D.getX(), 2) + Math.pow(PTmp.getTranslateY() - point2D.getY(), 2));
//            if (MAX_DISTANCE > tmpDistance && isInDirection(code, point2D, PTmp)) {
//                MAX_DISTANCE = tmpDistance;
//                returnedNode = PTmp;
//            }
//        }
        return returnedNode;

    }

    public boolean isInDirection(String direction, Point2D currentPosition, javafx.scene.Node node) {
        boolean ret = false;
        switch (direction) {
            case "LEFT":
                ret = node.getTranslateX() - currentPosition.getX() < 0;
                break;
            case "RIGHT":
                ret = node.getTranslateX() - currentPosition.getX() > 0;
                break;
            case "TOP":
                ret = node.getTranslateY() - currentPosition.getY() < 0;
                break;
            case "BOTTOM":
                ret = node.getTranslateY() - currentPosition.getY() > 0;
                break;
            default:
        }
        return ret;
    }

    public int getLabelInSelectedLabels(Label text) {
//        Iterator labels = selectedLabels.get().getLabels().iterator();
        int i = 0;
//        while (labels.hasNext()) {
//            Label l = (Label) labels.next();
//            if (l.getText().equals(text.getText())) {
//                System.err.println("trouvé" + l.getText() + " i= " + i);
//                break;
//            }
//            i++;
//            System.out.println("i = " + i);
//        }
        return i;
    }
}
