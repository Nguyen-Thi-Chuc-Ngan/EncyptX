package controller;

import view.component.InputPanel;
import view.component.ResultPanel;

import javax.swing.*;

public class SymmetricEncryptionController {


    public void encryptText(InputPanel inputPanel, ResultPanel resultPanel) {
        String text = inputPanel.getTextArea().getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter text to encrypt.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String encryptedText = "Encrypted: " + text; // Replace with actual encryption if needed
        resultPanel.getEncryptResultTextArea().setText(encryptedText);
    }

    public void decryptText(ResultPanel resultPanel) {
        String encryptedText = resultPanel.getEncryptResultTextArea().getText();
        if (encryptedText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please encrypt text first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String decryptedText = encryptedText.replace("Encrypted: ", ""); // Replace with actual decryption if needed
        resultPanel.getDecryptResultTextArea().setText(decryptedText);
    }

    public void decryptFile(String inputFile, String outputDir) {
    }

    public void encryptFile(String inputFile, String outputDir) {
    }
}
