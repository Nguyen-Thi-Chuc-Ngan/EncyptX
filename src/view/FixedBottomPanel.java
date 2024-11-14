package view;

import view.component.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FixedBottomPanel extends JPanel {
    private final JTabbedPane tabbedPane;
    InputPanel inputPanel;
    ResultPanel resultPanel;
    ActionPanel actionPanel;

    FileEncryptionPanel fileEncryptionPanel;

    public FixedBottomPanel() {
        setPreferredSize(new Dimension(1200, 375)); // Chiếm 50% chiều cao của frame
        setLayout(new BorderLayout());

        // Tạo InputPanel, ResultPanel và ActionPanel
        inputPanel = new InputPanel();
        resultPanel = new ResultPanel();
        actionPanel = new ActionPanel();

        // Tạo TextEncryptionPanel để chứa InputPanel và ResultPanel
        TextEncryptionPanel textEncryptionPanel = new TextEncryptionPanel(inputPanel, resultPanel);

        // Tạo FileEncryptionPanel cho mã hóa tệp
        fileEncryptionPanel = new FileEncryptionPanel();

        // Tạo tabbedPane và thêm các panel vào tab
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Text Encryption", textEncryptionPanel);
        tabbedPane.addTab("File Encryption", fileEncryptionPanel);

        // Thêm tabbedPane vào BorderLayout.CENTER và actionPanel vào BorderLayout.SOUTH
        add(tabbedPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);  // Thêm actionPanel vào phía dưới của FixedBottomPanel


        // Thêm ChangeListener để theo dõi sự thay đổi tab
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                // Cập nhật actionPanel để thay đổi hành động phù hợp với tab hiện tại
                if (selectedIndex == 0) {
                    actionPanel.setEncryptionMode("text"); // Chế độ mã hóa văn bản
                } else if (selectedIndex == 1) {
                    actionPanel.setEncryptionMode("file"); // Chế độ mã hóa tệp
                }
            }
        });

    }


}
