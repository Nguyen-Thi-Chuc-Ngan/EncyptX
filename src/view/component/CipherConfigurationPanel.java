package view.component;

import javax.swing.*;
import java.awt.*;

public class CipherConfigurationPanel extends JPanel {
    private ClassicalOptionsPanel algorithmOptionsPanel;
    private CipherInfoPanel cipherInfoPanel;

    public CipherConfigurationPanel(){
        setLayout(new BorderLayout(10, 10));

        algorithmOptionsPanel = new ClassicalOptionsPanel();
        cipherInfoPanel = new CipherInfoPanel();

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

    }



}
