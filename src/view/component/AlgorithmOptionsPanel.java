package view.component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AlgorithmOptionsPanel extends JPanel {

    protected JComboBox<String> algorithmComboBox;
    protected JComboBox<String> languageComboBox;
    protected JComboBox<String> modeComboBox;
    protected JComboBox<String> paddingComboBox;
    protected JComboBox<String> alphabetComboBox;
    KeyOptionsPanel keyOptionsPanel;

    public AlgorithmOptionsPanel() {
        // Sử dụng GridBagLayout để tự động căn chỉnh khi phóng to
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Algorithm Options"));
        setPreferredSize(new Dimension(400, 0)); // Độ rộng của bảng chọn
        GridBagConstraints gbc = new GridBagConstraints();

        // Giảm khoảng cách giữa các phần tử
        gbc.insets = new Insets(5, 20, 5, 20); // Khoảng cách giữa các thành phần (top, left, bottom, right)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Đảm bảo các thành phần chiếm toàn bộ chiều ngang
        gbc.weightx = 1.0; // Cung cấp không gian cho các thành phần chiếm toàn bộ chiều ngang

        // Tạo các thành phần giao diện
        algorithmComboBox = new JComboBox<>(new String[]{"AES", "DES"});
        languageComboBox = new JComboBox<>(new String[]{"English", "Vietnamese"});
        modeComboBox = new JComboBox<>(new String[]{"ECB", "CBC", "PCBC", "CFB", "OFB", "CTR", "CTS", "GCM"});
        paddingComboBox = new JComboBox<>(new String[]{"NoPadding", "PKCS5Padding","ISO10126Padding"});
        alphabetComboBox = new JComboBox<>(new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"}); // Các giá trị bảng chữ cái


        // Thuật toán
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        gbc.weightx = 0.1; // Chiếm 10% chiều rộng
        add(new JLabel("Algorithm: "), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 2; // Chiếm 90% chiều rộng
        gbc.weightx = 0.9; // Chiếm 90% chiều rộng
        algorithmComboBox.setPreferredSize(new Dimension(200, algorithmComboBox.getPreferredSize().height)); // Chiều rộng ComboBox
        add(algorithmComboBox, gbc);

        // Chế độ
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.weightx = 0.1; // Chiếm 10% chiều rộng
        add(new JLabel("Mode: "), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9; // Chiếm 90% chiều rộng
        modeComboBox.setPreferredSize(new Dimension(200, modeComboBox.getPreferredSize().height)); // Chiều rộng ComboBox
        add(modeComboBox, gbc);

        // Padding
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.weightx = 0.1; // Chiếm 10% chiều rộng
        add(new JLabel("Padding: "), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9; // Chiếm 90% chiều rộng
        paddingComboBox.setPreferredSize(new Dimension(200, paddingComboBox.getPreferredSize().height)); // Chiều rộng ComboBox
        add(paddingComboBox, gbc);

        // Độ dài khóa (Language)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.weightx = 0.1; // Chiếm 10% chiều rộng
        add(new JLabel("Language: "), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9; // Chiếm 90% chiều rộng
        languageComboBox.setPreferredSize(new Dimension(200, languageComboBox.getPreferredSize().height)); // Chiều rộng ComboBox
        add(languageComboBox, gbc);

        // Alphabet
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        gbc.weightx = 0.1; // Chiếm 10% chiều rộng
        add(new JLabel("Alphabet: "), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 0.9; // Chiếm 90% chiều rộng
        alphabetComboBox.setPreferredSize(new Dimension(200, alphabetComboBox.getPreferredSize().height)); // Chiều rộng ComboBox
        add(alphabetComboBox, gbc);

        languageComboBox.addActionListener(e -> updateLanguage());
    }

    public void updateLanguage() {
        String language = (String) languageComboBox.getSelectedItem();

        if ("English".equals(language)) {
            // Bảng chữ cái tiếng Anh
            alphabetComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                    "abcdefghijklmnopqrstuvwxyz",
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

            }));
        } else if ("Vietnamese".equals(language)) {
            // Bảng chữ cái tiếng Việt
            alphabetComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "AĂÂBCDĐEÊFGHIJKLMNOÔƠPQRSTUƯVWXYZ",  // Chữ cái tiếng Việt hoa
                    "aăâbcdeêfghijklmnoôơpqrstuưvwxyz"   // Chữ cái tiếng Việt thường
            }));
        }
    }
}
