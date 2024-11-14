package view;

import controller.SymmetricEncryptionController;
import view.component.*;

import javax.swing.*;
import java.awt.*;

public class SymmetricEncryptionPanel extends JPanel {

    private final SymmetricEncryptionController controller;
    private final JTabbedPane tabbedPane;


    public SymmetricEncryptionPanel() {
        setLayout(new BorderLayout(10, 10));

        controller = new SymmetricEncryptionController();

        // Tạo KeyOptionsPanel ở trên
        KeyOptionsPanel keyOptionsPanel = new KeyOptionsPanel();


        // Tạo InputPanel, ResultPanel và ActionPanel ở dưới
        InputPanel inputPanel = new InputPanel();
        ResultPanel resultPanel = new ResultPanel();
        ActionPanel actionPanel = new ActionPanel();

        // Tạo TextEncryptionPanel để chứa InputPanel và ResultPanel
        TextEncryptionPanel textEncryptionPanel = new TextEncryptionPanel(inputPanel, resultPanel);

        // Tạo FileEncryptionPanel cho mã hóa tệp
        FileEncryptionPanel fileEncryptionPanel = new FileEncryptionPanel();

        tabbedPane = new JTabbedPane();

        // Thêm các panel vào tabbedPane
        tabbedPane.addTab("Text Encryption", textEncryptionPanel);
        tabbedPane.addTab("File Encryption", fileEncryptionPanel);

        // JSplitPane để chia KeyOptionsPanel ở trên và bottomPanel ở dưới
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, keyOptionsPanel, tabbedPane);
        mainSplitPane.setDividerLocation(0.5);
        mainSplitPane.setResizeWeight(0.5);

        // Thêm mainSplitPane vào SymmetricEncryptionPanel
        add(mainSplitPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        actionPanel.addEncryptListener(e -> {
            // Kiểm tra tab hiện tại
            if (tabbedPane.getSelectedIndex() == 0) {  // Tab "Text Encryption"
                // Mã hóa văn bản
                controller.encryptText(inputPanel, resultPanel);
            } else if (tabbedPane.getSelectedIndex() == 1) {  // Tab "File Encryption"
                String inputFile = fileEncryptionPanel.getInputFilePath();
                String outputDir = fileEncryptionPanel.getOutputDirPath();

                // Kiểm tra nếu các file và thư mục không hợp lệ
                if (inputFile.isEmpty() || outputDir.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select both input file and output directory.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Gọi phương thức mã hóa tệp trong controller với các tham số
                controller.encryptFile(inputFile, outputDir);
            }
        });

        actionPanel.addDecryptListener(e -> {
            // Kiểm tra tab hiện tại
            if (tabbedPane.getSelectedIndex() == 0) {  // Tab "Text Encryption"
                // Giải mã văn bản
                controller.decryptText(resultPanel);
            } else if (tabbedPane.getSelectedIndex() == 1) {  // Tab "File Encryption"
                // Giải mã tệp
                // Giải mã tệp
                String inputFile = fileEncryptionPanel.getInputFilePath();
                String outputDir = fileEncryptionPanel.getOutputDirPath();

                // Kiểm tra nếu các file và thư mục không hợp lệ
                if (inputFile.isEmpty() || outputDir.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please select both input file and output directory.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Gọi phương thức giải mã tệp trong controller với các tham số
                controller.decryptFile(inputFile, outputDir);// Gọi phương thức giải mã tệp trong FileEncryptionPanel
            }
        });
    }
}
