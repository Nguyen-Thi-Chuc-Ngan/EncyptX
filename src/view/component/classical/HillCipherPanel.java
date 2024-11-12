package view.component.classical;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.security.SecureRandom;

import static model.HillCipher.getMod;

public class HillCipherPanel extends JPanel {
    private JComboBox<String> matrixSizeComboBox;
    private JPanel matrixPanel;
    private JLabel errorLabel;
    private JTextField[][] matrixFields;
    public int[][] keyMatrix;
    String selectedSize;
    public boolean isKeyGenerated = false;
    String selectedLanguage;

    public HillCipherPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        String[] matrixSizes = {"2x2", "3x3", "4x4"};
        matrixSizeComboBox = new JComboBox<>(matrixSizes);
        matrixSizeComboBox.addActionListener(e -> updateMatrixPanel());
        add(matrixSizeComboBox);

        matrixPanel = new JPanel();
        matrixPanel.setPreferredSize(new Dimension(590, 150));
        add(matrixPanel);

        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        updateMatrixPanel();
    }

    // Update matrix panel based on selected matrix size
    public void updateMatrixPanel() {
        selectedSize = (String) matrixSizeComboBox.getSelectedItem();
        int size = Integer.parseInt(selectedSize.substring(0, 1));
        keyMatrix = new int[size][size];

        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(size, size));
        matrixFields = new JTextField[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField textField = new JTextField(5);
                textField.setHorizontalAlignment(JTextField.CENTER);
                matrixFields[i][j] = textField;
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    public void removeUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }

                    public void changedUpdate(DocumentEvent e) {
                        validateInput(textField);
                    }
                });
                matrixPanel.add(textField);
            }
        }
        matrixPanel.revalidate();
        matrixPanel.repaint();
    }

    // Validate if input is a valid integer
    private void validateInput(JTextField textField) {
        String text = textField.getText().trim();
        try {
            if (!text.isEmpty() && !text.matches("-?\\d+")) {
                showError("Vui lòng nhập số nguyên hợp lệ.", textField);
            } else {
                textField.setBackground(Color.WHITE);
                updateKeyMatrix();
            }
        } catch (NumberFormatException e) {
            showError("Vui lòng nhập số hợp lệ.", textField);
        }
    }

    private void showError(String message, JTextField textField) {
        JOptionPane.showMessageDialog(this, message, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        textField.setBackground(Color.PINK);
    }

    // Update key matrix when input is valid
    private void updateKeyMatrix() {
        boolean valid = true;
        int size = keyMatrix.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String value = matrixFields[i][j].getText().trim();
                try {
                    keyMatrix[i][j] = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    valid = false;
                    break;
                }
            }
        }
        if (valid && isValidKeyMatrix(keyMatrix, selectedLanguage)) {
            isKeyGenerated = true;
            errorLabel.setText("");
        } else {
            errorLabel.setText("Vui lòng nhập ma trận khóa hợp lệ.");
            isKeyGenerated = false;
        }
    }

    // Generate random key matrix
    public void genKey(String selectedLanguage) {
        int size = keyMatrix.length;
        SecureRandom random = new SecureRandom();

        do {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    keyMatrix[i][j] = random.nextInt(getMod(selectedLanguage));
                    matrixFields[i][j].setText(String.valueOf(keyMatrix[i][j]));
                }
            }
        } while (!isValidKeyMatrix(keyMatrix, selectedLanguage));

        isKeyGenerated = true;
    }

    private boolean isValidKeyMatrix(int[][] matrix, String selectedLanguage) {
        int det = determinant(matrix, selectedLanguage) % getMod(selectedLanguage);
        if (det < 0) det += getMod(selectedLanguage);
        return gcd(det, getMod(selectedLanguage)) == 1;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static int determinant(int[][] matrix, String selectedLanguage) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];

        int det = 0;
        for (int i = 0; i < n; i++) {
            int[][] minor = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                int colIndex = 0;
                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    minor[j - 1][colIndex++] = matrix[j][k];
                }
            }
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor, selectedLanguage);
        }
        return (det % getMod(selectedLanguage) + getMod(selectedLanguage)) % getMod(selectedLanguage);
    }
}
