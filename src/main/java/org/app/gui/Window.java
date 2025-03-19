package org.app.gui;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        init();
    }

    private void init(){
        setSize(800, 600);
        setTitle("MapViewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
