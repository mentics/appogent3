package com.mentics.designer;

import com.mentics.designer.spi.NodePainter;
import com.mentics.javafx.DraggableItemNature;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Path;

public class BasicNodePainter implements NodePainter {

    public static int selection = 0;
    final static ObjectProperty<Label> currentLabel = new SimpleObjectProperty<>();
    final static ObjectProperty<SelectedLabels> selectedLabels = new SimpleObjectProperty<>();
    private SelectedLabels labels = new SelectedLabels();

    @Override
    public Node createNode(Item item) {
        final Label text = new Label(item.toString());
        final Path path = new Path();
        text.getStyleClass().add("node");
        text.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
            @Override
            public void handle(Event ev) {
                text.getStyleClass().add("selected");
                currentLabel.set(text);
            }
        });

        text.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<Event>() {
            @Override
            public void handle(Event ev) {
                if (selection == 0) {
                    text.getStyleClass().add("activenode");
                    Label Oldtext = currentLabel.get();
                    if (Oldtext != null) {
                        Oldtext.getStyleClass().remove("activenode");
                        if (text.getText().equals(Oldtext.getText())) {
                            text.getStyleClass().add("activenode");
                        }
                    }
                    currentLabel.set(text);
                    //deselect
                    if (selectedLabels.get() != null && !selectedLabels.get().getLabels().isEmpty()) {
                        for (Object o : selectedLabels.get().getLabels()) {
                            Label l = (Label) o;
                            l.getStyleClass().remove("activenode");
                            System.out.println("removing style");
                        }
                        selectedLabels.get().getLabels().clear();
                    } else {
                        System.out.println("is null*********");
                        selectedLabels.set(labels);
                    }
                } else {
                    text.getStyleClass().add("activenode");
                    if (selectedLabels.get().getLabels() != null) {
                        selectedLabels.get().addToList(text);
                    } else {
                        selectedLabels.get().addToList(text);
                    }
                }
            }
        });
        DraggableItemNature.addTo(text, text, MouseButton.PRIMARY);
        return text;
    }
   
    
}
