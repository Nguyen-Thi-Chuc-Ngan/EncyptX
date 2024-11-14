package view.component.classical;

import javax.swing.*;
import java.awt.*;

public class SubstitutionCipherPanel extends JPanel {
    private JTextField ciphertextAlphabetField;        // Ô nhập Ciphertext Alphabet
    private JComboBox<String> caseStrategyCombo;       // ComboBox cho Case Strategy
    private JComboBox<String> foreignCharsCombo;       // ComboBox cho Foreign Chars

    public SubstitutionCipherPanel() {
        // Set layout cho panel
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Thêm khoảng cách viền cho panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // khoảng cách giữa các thành phần

        // Tạo các thành phần
        ciphertextAlphabetField = new JTextField( 20);  // Ô nhập cho bảng chữ cái mã hóa
        caseStrategyCombo = new JComboBox<>(new String[]{"Uppercase", "Lowercase", "Ignore Case"});  // Lựa chọn chế độ chữ hoa/chữ thường
        foreignCharsCombo = new JComboBox<>(new String[]{"Maintain case", "Ignore case", "Strict (A ≠ a)"});                      // Lựa chọn cho các ký tự đặc biệt

        // Cấu hình và thêm các thành phần vào panel
        addLabeledComponent("Ciphertext Alphabet:", ciphertextAlphabetField, gbc, 0);
        addLabeledComponent("Case Strategy:", caseStrategyCombo, gbc, 1);
        addLabeledComponent("Foreign Chars:", foreignCharsCombo, gbc, 2);
    }

    // Phương thức để tạo nhãn và thành phần với tỷ lệ 20-80
    private void addLabeledComponent(String labelText, JComponent component, GridBagConstraints gbc, int row) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = row;

        // Tỷ lệ 20% cho label
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        JLabel label = new JLabel(labelText);
        add(label, gbc);

        // Tỷ lệ 80% cho component
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        add(component, gbc);
    }

    // Các phương thức getter
    public String getCiphertextAlphabet() {
        return ciphertextAlphabetField.getText();
    }

    public String getCaseStrategy() {
        return (String) caseStrategyCombo.getSelectedItem();
    }

    public String getForeignCharsOption() {
        return (String) foreignCharsCombo.getSelectedItem();
    }
}
