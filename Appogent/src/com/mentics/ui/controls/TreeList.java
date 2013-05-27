package com.mentics.ui.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;


public class TreeList {
    private final class ActiveColumn implements EventHandler<MouseEvent> {
        private final ListView<GraphNode> view;
        private final int column;


        private ActiveColumn(ListView<GraphNode> view, int column) {
            this.view = view;
            this.column = column;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getX() > threshold(column)) {
                if (column < maxDepth) {
                    // System.out.println("selecting: "+index.value);
                    view.getSelectionModel().select(index.value);
                }
                view.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        setList(column, null);
                    }
                });
                view.setPrefWidth(collapsedSize());
                setList(column + 1, toObsList(view.getItems().get(index.value).getChildren()));
            }
        }
    }


    // Instance Fields //

    private final int maxDepth;

    private List<GraphNode> roots;

    // private List<ListView<GraphNode>> views;

    private HBox viewNode;

    private ListView<GraphNode> curList;

    private Value<Integer> index = new Value<Integer>();


    // Constructors //

    public TreeList(int maxDepth, List<GraphNode> roots) {
        this.maxDepth = maxDepth;
        this.roots = roots;

        viewNode = new HBox();
        // this.views = new ArrayList<>();
        for (int i = 0; i < maxDepth; i++) {
            ListView<GraphNode> view = new ListView<GraphNode>();
            // this.views.add(view);
            viewNode.getChildren().add(view);
            view.setPrefWidth(0);
            view.setVisible(false);
            // TODO: the following doesn't work
            view.setStyle(".list-cell:empty { visibility:hidden; }  .list-cell:odd:empty { -fx-background-color: white; }");

            view.setCellFactory(new Callback<ListView<GraphNode>, ListCell<GraphNode>>() {
                @Override
                public ListCell<GraphNode> call(ListView<GraphNode> list) {
                    final ListCell<GraphNode> cell = new TextFieldListCell<GraphNode>();
                    cell.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            index.value = cell.getIndex();
                        }
                    });
                    return cell;
                }
            });
        }
        
        viewNode.setPrefWidth(300);

        // this.views.get(0).setItems(topLevel);
        setList(0, toObsList(roots));
    }


    // Public Methods //

    public Parent getViewNode() {
        return viewNode;
    }


    // Local Methods //

    private void setList(final int column, ObservableList<GraphNode> items) {
        if (column >= maxDepth) {
            return;
        }
        final ListView<GraphNode> view = (ListView<GraphNode>) viewNode.getChildren().get(column);
        if (items != null) {
            view.setItems(items);
        }
        if (column < maxDepth - 1) {
            // If we're moving back to the left, clear on mouse move to the right.
            final ListView<GraphNode> nextView = (ListView<GraphNode>) viewNode.getChildren().get(column + 1);
            if (nextView.isVisible()) {
                nextView.setPrefWidth(0);
                nextView.setOnMouseMoved(null);
                nextView.setVisible(false);
            }
        }
        double p = viewNode.getWidth() - (column * collapsedSize());
        view.setPrefWidth(p > 0 ? p : -1);
        view.setVisible(true);
        curList = view;
        if (column < maxDepth - 1) {
            curList.setOnMouseMoved(new ActiveColumn(view, column));
        }
        view.requestFocus();
        view.getSelectionModel().clearSelection();
    }

    private ObservableList<GraphNode> toObsList(Iterable<GraphNode> nodes) {
        ObservableList<GraphNode> obsList = FXCollections.observableArrayList();
        for (GraphNode n : nodes) {
            obsList.add(n);
        }
        return obsList;
    }

    private double threshold(int column) {
        return viewNode.getWidth() / maxDepth;
    }

    private double collapsedSize() {
        return threshold(0) / 2;
    }
}
