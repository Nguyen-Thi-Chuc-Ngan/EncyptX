package view.component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KeyInfoPanel extends JPanel {

    private JTextField keyField;
    private JTextField ivField;
    private JComboBox<String> ivSizeComboBox;
    protected JComboBox<String> keySizeComboBox;  // Thêm ComboBox cho key size
    private JButton loadKeyButton;
    private JButton saveKeyButton;
    private JButton generateKeyButton;

    public KeyInfoPanel() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible alignment
        setBorder(new TitledBorder("Key Info"));
        setPreferredSize(new Dimension(400, 0)); // Set the preferred size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ensure components take full width of grid cell
        gbc.weightx = 1.0; // Allow components to grow horizontally

        // Create UI components
        keyField = new JTextField(40);  // 3 hàng, 30 cột


        ivField = new JTextField(40);  // IV input field
        ivSizeComboBox = new JComboBox<>(new String[] {"128", "256"}); // IV size options
        keySizeComboBox = new JComboBox<>(new String[] {"128", "192", "256"}); // Key size options
        loadKeyButton = new JButton("Load Key");
        saveKeyButton = new JButton("Save Key");
        generateKeyButton = new JButton("Generate");

        // Adding Key Size field (Now placed first)
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(new JLabel("Key Size: "), gbc);  // Adding Key Size label
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        add(keySizeComboBox, gbc);  // Adding the key size combo box

        // Adding Key field (Now placed second)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        add(new JLabel("Key: "), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        add(keyField, gbc);

        // Adding IV Size field
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        add(new JLabel("IV Size: "), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        add(ivSizeComboBox, gbc);

        // Adding IV field
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        add(new JLabel("IV: "), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        add(ivField, gbc);

        // Button panel for Load, Save, Generate
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loadKeyButton);
        buttonPanel.add(saveKeyButton);
        buttonPanel.add(generateKeyButton);

        // Ensure the button panel spans across all columns
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(buttonPanel, gbc);


    }
}
