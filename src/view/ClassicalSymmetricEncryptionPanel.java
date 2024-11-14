package view;

import controller.MainController;
import view.component.*;

import javax.swing.*;
import java.awt.*;

public class ClassicalSymmetricEncryptionPanel extends JPanel {

    private ClassicalOptionsPanel algorithmOptionsPanel;
    private CipherInfoPanel cipherInfoPanel;
    private MainController mainController;

    public ClassicalSymmetricEncryptionPanel(MainController mainController) {
        this.mainController = mainController;
        setLayout(new BorderLayout(10, 10));

        algorithmOptionsPanel = new ClassicalOptionsPanel(mainController);
        cipherInfoPanel = new CipherInfoPanel(mainController);

        // Cài đặt listener để nhận sự kiện thay đổi thuật toán
        algorithmOptionsPanel.setAlgorithmChangeListener(algorithm -> cipherInfoPanel.showKeyPanel(algorithm));


        // Đặt kích thước ưa thích cho các panel con
        algorithmOptionsPanel.setPreferredSize(new Dimension(400, 0));
        cipherInfoPanel.setPreferredSize(new Dimension(400, 0));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, algorithmOptionsPanel, cipherInfoPanel);
        splitPane.setContinuousLayout(true); // Hiển thị liên tục khi thay đổi kích thước
        splitPane.setDividerLocation(0.5); // Vị trí phân chia ban đầu
        splitPane.setResizeWeight(0.5); // Cân bằng chiều rộng cho cả hai bên

        // Thêm splitPane vào KeyOptionsPanel
        add(splitPane, BorderLayout.CENTER);

        algorithmOptionsPanel.setAlgorithmChangeListener(algorithm -> cipherInfoPanel.showKeyPanel(algorithm));
        algorithmOptionsPanel.setKeyGenerateListener(this::genKey);

    }

    public void genKey() {
        String selectedAlgorithm = algorithmOptionsPanel.getSelectedAlgorithm();
        String selectedLanguage = algorithmOptionsPanel.getSelectedLanguage();
        String selectedAlphabet = algorithmOptionsPanel.getSelectedAlphabet();

        String generatedKey = "";

        switch (selectedAlgorithm) {
            case "Hill":
                // Giả sử hillCipherPanel đã có phương thức genKey để tạo ma trận khóa
                generatedKey = "Hill cipher key generated!";
                break;
            case "Substitution":
                generatedKey = "Substitution cipher key generated!";
                break;
            case "Vigence":
                generatedKey = "Vigence cipher key generated!";
                break;
            case "Affine":
                generatedKey = "Affine cipher key generated!";
                break;
            case "Transposition":
                generatedKey = "Transposition cipher key generated!";
                break;
            default:
                generatedKey = "Algorithm not recognized";
                break;
        }

        // Hiển thị dialog với kết quả
        JOptionPane.showMessageDialog(this, generatedKey, "Key Generation", JOptionPane.INFORMATION_MESSAGE);
    }

}
