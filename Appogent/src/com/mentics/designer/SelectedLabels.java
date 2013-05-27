/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mentics.designer;

import javafx.scene.control.Label;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author nordine
 */
public class SelectedLabels {

    private Set labels;
    private Set texts;

    public SelectedLabels() {
        labels = new LinkedHashSet();
        texts = new LinkedHashSet();
    }

    public Set getTexts() {
        return texts;
    }

    public void setTexts(Set texts) {
        this.texts = texts;
    }

    public Set getLabels() {
        if (labels == null) {
            labels = new LinkedHashSet();
        }
        return labels;
    }

    public void setLabels(Set labels) {
        this.labels = labels;
    }

    public void addToList(Label label) {
        if (texts.add(label.getText())) {
            labels.add(label);
            System.out.println("added "+label.getText());
        }
        else{
            System.out.println("not added "+label.getText());
        }
    }
}
