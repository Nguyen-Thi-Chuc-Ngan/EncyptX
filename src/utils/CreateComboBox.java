package utils;

import javax.swing.*;
import java.awt.*;

public class CreateComboBox {
    public JPanel createAlgorithmSelectionPanel(JComboBox<String> algorithmComboBox) {
         // Lưu giá trị trả về vào algorithmComboBox

        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.X_AXIS));
        algorithmPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        algorithmPanel.add(new JLabel("Algorithm:"));
        algorithmPanel.add(Box.createHorizontalStrut(10));
        algorithmPanel.add(algorithmComboBox);

        algorithmPanel.revalidate();
        algorithmPanel.repaint();

        return algorithmPanel;
    }

    public JPanel createLanguageSelectionPanel(JComboBox<String> languageComboBox) {
        String[] languages = {"English", "Vietnamese"};
        languageComboBox = createComboBox(languages);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new BoxLayout(languagePanel, BoxLayout.X_AXIS));
        languagePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        languagePanel.add(new JLabel("Language:"));
        languagePanel.add(Box.createHorizontalStrut(10));
        languagePanel.add(languageComboBox);
        languagePanel.revalidate();
        languagePanel.repaint();

        return languagePanel;
    }

    public JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(150, 25));
        return comboBox;
    }
}
