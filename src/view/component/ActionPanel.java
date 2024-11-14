package view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {

    private JButton encryptButton;
    private JButton decryptButton;

    private String encryptionMode = "text"; // Mặc định là chế độ mã hóa văn bản

    public ActionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        // Cài đặt màu sắc cho các nút
        encryptButton.setBackground(new Color(0, 128, 0));
        encryptButton.setForeground(Color.WHITE);
        encryptButton.setFocusPainted(false);

        decryptButton.setBackground(new Color(128, 0, 0));
        decryptButton.setForeground(Color.WHITE);
        decryptButton.setFocusPainted(false);

        add(encryptButton);
        add(decryptButton);

        // Thêm ActionListener cho các nút mã hóa/giải mã
        encryptButton.addActionListener(e -> handleEncryption());
        decryptButton.addActionListener(e -> handleDecryption());
    }
    public void setEncryptionMode(String mode) {
        this.encryptionMode = mode;
    }


    private void handleEncryption() {
        if ("text".equals(encryptionMode)) {
            // Thực hiện mã hóa văn bản
            System.out.println("Mã hóa văn bản");
        } else if ("file".equals(encryptionMode)) {
            // Thực hiện mã hóa tệp
            System.out.println("Mã hóa tệp");
        }
    }

    private void handleDecryption() {
        if ("text".equals(encryptionMode)) {
            // Thực hiện giải mã văn bản
            System.out.println("Giải mã văn bản");
        } else if ("file".equals(encryptionMode)) {
            // Thực hiện giải mã tệp
            System.out.println("Giải mã tệp");
        }
    }

    // Getter cho các nút
    public JButton getEncryptButton() {
        return encryptButton;
    }

    public JButton getDecryptButton() {
        return decryptButton;
    }
}