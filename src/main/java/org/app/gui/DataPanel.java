package org.app.gui;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {

    JLabel distance;
    JLabel time;

    public DataPanel(double distance, double time) {
        this.distance = new JLabel("Distance: " + Math.floor(distance/1000) + " km   ");
        this.time = new JLabel("Time: " + Math.floor(time/60000) + " min");

        add(this.distance, BorderLayout.WEST);
        add(this.time, BorderLayout.EAST);
    }
}
