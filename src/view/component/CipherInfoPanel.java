package view.component;

import view.component.classical.HillCipherPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CipherInfoPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel hillKeyPanel;
    private JPanel substitutionKeyPanel;
    private JPanel vigenceKeyPanel;
    private JPanel affineKeyPanel;
    private JPanel transpositionKeyPanel;


    public CipherInfoPanel() {
        cardLayout = new CardLayout();
        setBorder(new TitledBorder("Key Info"));
        setLayout(cardLayout);

        // Khởi tạo các panel cho từng thuật toán
        hillKeyPanel = createHillKeyPanel();
        substitutionKeyPanel = createSubstitutionKeyPanel();
        vigenceKeyPanel = createVigenceKeyPanel();
        affineKeyPanel = createAffineKeyPanel();
        transpositionKeyPanel = createTranspositionKeyPanel();

        // Thêm từng panel vào CardLayout
        add(hillKeyPanel, "Hill");
        add(substitutionKeyPanel, "Substitution");
        add(vigenceKeyPanel, "Vigence");
        add(affineKeyPanel, "Affine");
        add(transpositionKeyPanel, "Transposition");
    }

    private JPanel createHillKeyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

        JLabel titleLabel = new JLabel("Enter Hill Cipher Key:"); // Create label
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align label to the left
        panel.add(titleLabel); // Add title label

        HillCipherPanel hillCipherPanel = new HillCipherPanel(); // Create an instance of HillCipherPanel
        hillCipherPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(hillCipherPanel); // Add the HillCipherPanel instance to the panel

        return panel;
    }

    private JPanel createSubstitutionKeyPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Substitution Cipher Key:"));
        panel.add(new JTextField(10));
        return panel;
    }

    private JPanel createVigenceKeyPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Vigence Cipher Key:"));
        panel.add(new JTextField(10));
        return panel;
    }

    private JPanel createAffineKeyPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Affine Cipher Key:"));
        panel.add(new JTextField(10));
        return panel;
    }

    private JPanel createTranspositionKeyPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Transposition Cipher Key:"));
        panel.add(new JTextField(10));
        return panel;
    }

    public void showKeyPanel(String algorithm) {
        cardLayout.show(this, algorithm);
    }
}