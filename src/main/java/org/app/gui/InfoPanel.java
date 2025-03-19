package org.app.gui;

import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.Translation;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private final JTextArea textArea;

    public InfoPanel(InstructionList instructionList, Translation translation) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setMaximumSize(new Dimension(300, Integer.MAX_VALUE));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(5, 5, 5, 5));

        populateTextArea(instructionList, translation);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void populateTextArea(InstructionList instructionList, Translation translation) {
        StringBuilder text = new StringBuilder();
        for (Instruction ins : instructionList) {
            text.append("Tra ").append((int) Math.floor(ins.getDistance()))
                    .append(" metri:\n")
                    .append(ins.getTurnDescription(translation))
                    .append("\n\n");
        }
        textArea.setText(text.toString());
    }
}
