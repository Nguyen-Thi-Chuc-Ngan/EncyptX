package view.component;

import controller.MainController;
import view.component.classical.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CipherInfoPanel extends JPanel {
    protected CardLayout cardLayout;
    protected JPanel hillKeyPanel;
    protected JPanel substitutionKeyPanel;
    protected JPanel vigenereKeyPanel;
    protected JPanel affineKeyPanel;
    protected JPanel transpositionKeyPanel;


    public CipherInfoPanel(MainController mainController) {
        cardLayout = new CardLayout();
        setBorder(new TitledBorder("Key Info"));
        setLayout(cardLayout);
        setPreferredSize(new Dimension(400, 0));

        // Khởi tạo các panel cho từng thuật toán
        hillKeyPanel = createHillKeyPanel();
        substitutionKeyPanel = createSubstitutionKeyPanel();
        vigenereKeyPanel = createVigenereKeyPanel();
        affineKeyPanel = createAffineKeyPanel();
        transpositionKeyPanel = createTranspositionKeyPanel();

        // Thêm từng panel vào CardLayout
        add(hillKeyPanel, "Hill");
        add(substitutionKeyPanel, "Substitution");
        add(vigenereKeyPanel, "Vigenère");
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

        SubstitutionCipherPanel substitutionCipherPanel = new SubstitutionCipherPanel(); // Create an instance of SubstitutionCipherPanel
        substitutionCipherPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(substitutionCipherPanel); // Add the SubstitutionCipherPanel instance to the panel

        return panel;
    }

    private JPanel createVigenereKeyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        VigenereCipherPanel vigenereCipherPanel = new VigenereCipherPanel();
        panel.add(vigenereCipherPanel, BorderLayout.CENTER); // Thêm VigenereCipherPanel vào panel chính
        return panel;
    }


    private JPanel createAffineKeyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));

        // Khởi tạo AffineCipherPanel
        AffineCipherPanel affineCipherPanel = new AffineCipherPanel();
        panel.add(affineCipherPanel, BorderLayout.CENTER);  // Thêm AffineCipherPanel vào panel

        return panel;
    }

    private JPanel createTranspositionKeyPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        TranspositionCipherPanel transpositionCipherPanel = new TranspositionCipherPanel();
        panel.add(transpositionCipherPanel, BorderLayout.CENTER);

        return panel;
    }

    public void showKeyPanel(String algorithm) {
        cardLayout.show(this, algorithm);
    }
}