package view.component;

import javax.swing.*;
import java.awt.*;

public class KeyOptionsPanel extends JPanel {

    private AlgorithmOptionsPanel algorithmOptionsPanel;
    private KeyInfoPanel keyInfoPanel;


    public KeyOptionsPanel() {
        setLayout(new BorderLayout(10, 10));

        // Tạo panel tùy chọn thuật toán và thông tin khóa
        algorithmOptionsPanel = new AlgorithmOptionsPanel();
        keyInfoPanel = new KeyInfoPanel();

        // Đặt kích thước ưa thích cho các panel con
        algorithmOptionsPanel.setPreferredSize(new Dimension(400, 0));
        keyInfoPanel.setPreferredSize(new Dimension(400, 0));

        // Tạo JSplitPane để chia hai panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, algorithmOptionsPanel, keyInfoPanel);
        splitPane.setContinuousLayout(true); // Hiển thị liên tục khi thay đổi kích thước
        splitPane.setDividerLocation(0.5); // Vị trí phân chia ban đầu
        splitPane.setResizeWeight(0.5); // Cân bằng chiều rộng cho cả hai bên

        // Thêm splitPane vào KeyOptionsPanel
        add(splitPane, BorderLayout.CENTER);

        // Thêm action listener cho algorithmComboBox
        algorithmOptionsPanel.algorithmComboBox.addActionListener(e -> updateOptions());
        algorithmOptionsPanel.modeComboBox.addActionListener(e -> updatePaddingOptions());
    }
    // Hàm cập nhật các options dựa trên thuật toán
    // Hàm cập nhật các options dựa trên thuật toán
    public void updateOptions() {
        String algorithm = (String) algorithmOptionsPanel.algorithmComboBox.getSelectedItem();

        // Cập nhật key size combo box
        keyInfoPanel.keySizeComboBox.removeAllItems();
        algorithmOptionsPanel.modeComboBox.removeAllItems();

        if ("DES".equals(algorithm)) {
            keyInfoPanel.keySizeComboBox.addItem("56");
            keyInfoPanel.keySizeComboBox.setEnabled(false); // Key size không thể thay đổi cho DES

            // Cập nhật các chế độ cho DES
            algorithmOptionsPanel.modeComboBox.addItem("ECB");
            algorithmOptionsPanel.modeComboBox.addItem("CBC");
            algorithmOptionsPanel.modeComboBox.addItem("CFB");
            algorithmOptionsPanel.modeComboBox.addItem("OFB");
            algorithmOptionsPanel.modeComboBox.addItem("CTR");
        } else if ("AES".equals(algorithm)) {
            keyInfoPanel.keySizeComboBox.addItem("128");
            keyInfoPanel.keySizeComboBox.addItem("192");
            keyInfoPanel.keySizeComboBox.addItem("256");
            keyInfoPanel.keySizeComboBox.setEnabled(true); // Cho phép thay đổi key size cho AES

            // Cập nhật các chế độ cho AES
            algorithmOptionsPanel.modeComboBox.addItem("ECB");
            algorithmOptionsPanel.modeComboBox.addItem("CBC");
            algorithmOptionsPanel.modeComboBox.addItem("CFB");
            algorithmOptionsPanel.modeComboBox.addItem("OFB");
            algorithmOptionsPanel.modeComboBox.addItem("CTR");
            algorithmOptionsPanel.modeComboBox.addItem("CTS");
            algorithmOptionsPanel.modeComboBox.addItem("GCM");
        }
    }

    // Hàm cập nhật padding options dựa trên thuật toán và chế độ
    public void updatePaddingOptions() {
        algorithmOptionsPanel.paddingComboBox.removeAllItems();
        String algorithm = (String) algorithmOptionsPanel.algorithmComboBox.getSelectedItem();
        String mode = (String) algorithmOptionsPanel.modeComboBox.getSelectedItem();

        if ("AES".equals(algorithm)) {
            if ("CTR".equals(mode) || "CTS".equals(mode) || "GCM".equals(mode)) {
                // Các chế độ này không cần padding
                algorithmOptionsPanel.paddingComboBox.addItem("NoPadding");
            } else {
                // Các chế độ khác yêu cầu padding
                algorithmOptionsPanel.paddingComboBox.addItem("NoPadding");
                algorithmOptionsPanel.paddingComboBox.addItem("PKCS5Padding");
                algorithmOptionsPanel.paddingComboBox.addItem("ISO10126Padding");
            }
        } else if ("DES".equals(algorithm)) {
            // DES chỉ sử dụng PKCS5Padding hoặc NoPadding
            algorithmOptionsPanel.paddingComboBox.addItem("PKCS5Padding");
            algorithmOptionsPanel.paddingComboBox.addItem("NoPadding");
        }
    }

}
