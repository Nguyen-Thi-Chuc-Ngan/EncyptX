package view.component.classical;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AffineCipherPanel extends JPanel {
    private JTextField keyAField; // Khóa 'a'
    private JTextField keyBField; // Khóa 'b'
    private JComboBox<String> caseStrategyCombo; // ComboBox cho Case Strategy
    private JComboBox<String> foreignCharsCombo; // ComboBox cho Foreign Chars

    public AffineCipherPanel() {
        // Set layout cho panel
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Thêm khoảng cách viền cho panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // khoảng cách giữa các thành phần

        // Tạo các thành phần
        keyAField = new JTextField(20); // Ô nhập cho khóa 'a'
        keyBField = new JTextField(20); // Ô nhập cho khóa 'b'
        caseStrategyCombo = new JComboBox<>(new String[]{"Ignore Case", "Uppercase", "Lowercase"});  // Lựa chọn chế độ chữ hoa/chữ thường
        foreignCharsCombo = new JComboBox<>(new String[]{"Maintain case", "Ignore case", "Strict (A ≠ a)"});  // Lựa chọn cho các ký tự đặc biệt

        // Cấu hình và thêm các thành phần vào panel
        addLabeledComponent("Enter 'a' (1-25, must have inverse mod 26):", keyAField, gbc, 0);
        addLabeledComponent("Enter 'b' (0-25):", keyBField, gbc, 1);
        addLabeledComponent("Case Strategy:", caseStrategyCombo, gbc, 2);
        addLabeledComponent("Foreign Chars:", foreignCharsCombo, gbc, 3);
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

    public void generateKeys() {
        Random rand = new Random();

        // Sinh khóa 'a' sao cho gcd(a, alphabetSize) = 1
        int a;
        do {
            a = rand.nextInt(25) + 1; // Sinh giá trị a trong khoảng từ 1 đến 25
        } while (gcd(a, 26) != 1); // Kiểm tra tính hợp lệ của 'a'

        // Sinh khóa 'b' trong phạm vi 0 đến 25
        int b = rand.nextInt(26);

        // Cập nhật giá trị khóa 'a' và 'b' vào các trường nhập liệu
        keyAField.setText(String.valueOf(a));
        keyBField.setText(String.valueOf(b));
    }

    // Mã hóa văn bản theo thuật toán Affine Cipher
    public String encrypt(String plaintext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            StringBuilder ciphertext = new StringBuilder();
            for (char c : plaintext.toCharArray()) {
                int index = alphabet.indexOf(c);
                if (index != -1) {
                    // Mã hóa theo công thức Affine: E(x) = (a * x + b) % 26
                    int encryptedIndex = (a * index + b) % alphabet.length();
                    ciphertext.append(alphabet.charAt(encryptedIndex));
                } else {
                    // Nếu ký tự không nằm trong bảng chữ cái, giữ nguyên
                    ciphertext.append(c);
                }
            }
            return ciphertext.toString();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for keys.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    // Giải mã văn bản
    public String decrypt(String ciphertext, String alphabet) {
        try {
            int a = Integer.parseInt(keyAField.getText());
            int b = Integer.parseInt(keyBField.getText());

            // Tính nghịch đảo của 'a' modulo 26
            int aInverse = modInverse(a, 26);

            StringBuilder plaintext = new StringBuilder();
            for (char c : ciphertext.toCharArray()) {
                int index = alphabet.indexOf(c);
                if (index != -1) {
                    // Giải mã theo công thức Affine: D(x) = a_inv * (x - b) % 26
                    int decryptedIndex = (aInverse * (index - b + 26)) % alphabet.length();
                    plaintext.append(alphabet.charAt(decryptedIndex));
                } else {
                    // Nếu ký tự không nằm trong bảng chữ cái, giữ nguyên
                    plaintext.append(c);
                }
            }
            return plaintext.toString();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for keys.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    // Tính ước chung lớn nhất (GCD)
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Tính nghịch đảo mod 26
    private int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy nghịch đảo
    }

    // Các phương thức getter
    public String getKeyA() {
        return keyAField.getText();
    }

    public String getKeyB() {
        return keyBField.getText();
    }

    public String getCaseStrategy() {
        return (String) caseStrategyCombo.getSelectedItem();
    }

    public String getForeignCharsOption() {
        return (String) foreignCharsCombo.getSelectedItem();
    }
}
