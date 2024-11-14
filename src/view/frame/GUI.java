package view.frame;

import controller.MainController;
import custom.Theme;
import view.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame{

    private JTabbedPane tabbedPane;

    private FixedBottomPanel fixedBottomPanel;
    private MainController mainController;
    public GUI(MainController mainController) {
        Theme theme = new Theme();
        theme.setup();
        setTitle("Tool mã hóa");
        init();
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void init() {
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Khởi tạo tabbedPane và thêm các tab
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Symmetric", new SymmetricEncryptionPanel());
        tabbedPane.addTab("Classical Symmetric", new ClassicalSymmetricEncryptionPanel(mainController));
        tabbedPane.addTab("Asymmetric", new AsymmetricEncryptionPanel());
        tabbedPane.addTab("Hash", new HashPanel());
        tabbedPane.addTab("Digital Signature", new DigitalSignaturePanel());
        tabbedPane.addTab("Instructions For Use", new InstructionsForUse());
        tabbedPane.addTab("Creator Information", new CreatorInformation());

        // Khởi tạo fixedBottomPanel từ class mới
        fixedBottomPanel = new FixedBottomPanel();

        // Thêm ChangeListener để ẩn hoặc hiển thị FixedBottomPanel khi thay đổi tab
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();

                // Kiểm tra nếu tab được chọn là 3 tab cuối ("Instructions For Use" hoặc "Creator Information")
                if (selectedIndex >= 4) {
                    // Ẩn FixedBottomPanel
                    fixedBottomPanel.setVisible(false);
                } else {
                    // Hiển thị FixedBottomPanel
                    fixedBottomPanel.setVisible(true);
                }
            }
        });

        // Thêm tabbedPane vào contentPanel
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        contentPanel.add(fixedBottomPanel, BorderLayout.SOUTH);

        add(contentPanel);
    }

}
