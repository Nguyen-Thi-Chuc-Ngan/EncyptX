package view.component;

import javax.swing.*;
import java.awt.*;

public class TextEncryptionPanel extends JPanel {

    public TextEncryptionPanel(InputPanel inputPanel, ResultPanel resultPanel) {
        setLayout(new BorderLayout(10, 10));

        // JSplitPane cho InputPanel và ResultPanel
        JSplitPane inputResultSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, resultPanel);
        inputResultSplitPane.setDividerLocation(0.5);
        inputResultSplitPane.setResizeWeight(0.5);

        add(inputResultSplitPane, BorderLayout.CENTER);
    }
}
