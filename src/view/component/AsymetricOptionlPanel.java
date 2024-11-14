package view.component;

import javax.swing.*;
import java.awt.*;

public class AsymetricOptionlPanel extends JPanel {

    private AlgorithmAsymetricPanel algorithmAsymetricPanel;
    private KeyInfoPanel keyInfoPanel;
    public AsymetricOptionlPanel(){
        setLayout(new BorderLayout(10, 10));

        // Tạo panel tùy chọn thuật toán và thông tin khóa
        algorithmAsymetricPanel = new AlgorithmAsymetricPanel();
        keyInfoPanel = new KeyInfoPanel();

        // Đặt kích thước ưa thích cho các panel con
        algorithmAsymetricPanel.setPreferredSize(new Dimension(400, 0));
        keyInfoPanel.setPreferredSize(new Dimension(400, 0));

        // Tạo JSplitPane để chia hai panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, algorithmAsymetricPanel, keyInfoPanel);
        splitPane.setContinuousLayout(true); // Hiển thị liên tục khi thay đổi kích thước
        splitPane.setDividerLocation(0.5); // Vị trí phân chia ban đầu
        splitPane.setResizeWeight(0.5); // Cân bằng chiều rộng cho cả hai bên

        // Thêm splitPane vào KeyOptionsPanel
        add(splitPane, BorderLayout.CENTER);


    }
}
