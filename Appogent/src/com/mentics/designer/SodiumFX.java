package com.mentics.designer;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import sodium.Behavior;
import sodium.Event;
import sodium.EventSink;
import sodium.Handler;


public class SodiumFX {
    public static <A> void bind(final javafx.beans.property.Property<A> prop, Behavior<A> b) {
        b.changes().listen(new Handler<A>() {
            @Override
            public void run(A x) {
                prop.setValue(x);
            }
        });
        prop.setValue(b.sample());
    }

    public static <A extends javafx.event.Event> Event<A> eventFor(Node n, EventType<A> type) {
        final EventSink<A> ret = new EventSink<>();
        n.addEventHandler(type, new EventHandler<A>() {
            @Override
            public void handle(A x) {
                ret.send(x);
            }
        });
        return ret;
    }
}
