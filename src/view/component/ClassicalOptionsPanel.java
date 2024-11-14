package view.component;

import view.component.classical.HillCipherPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ClassicalOptionsPanel extends JPanel {

    protected JComboBox<String> algorithmComboBox;
    protected JComboBox<String> languageComboBox;
    protected JComboBox<String> alphabetComboBox;

    protected JButton loadKeyButton;
    protected JButton saveKeyButton;
    protected JButton generateKeyButton;

    private HillCipherPanel hillCipherPanel;

    private AlgorithmChangeListener listener;
    private KeyGenerateListener keyGenerateListener;

    public ClassicalOptionsPanel() {
        // Sử dụng GridBagLayout để tự động căn chỉnh khi phóng to
        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Algorithm Options"));
        setPreferredSize(new Dimension(400, 0)); // Độ rộng của bảng chọn
        GridBagConstraints gbc = new GridBagConstraints();

        hillCipherPanel = new HillCipherPanel();
        // Giảm khoảng cách giữa các phần tử
        gbc.insets = new Insets(5, 20, 5, 20); // Khoảng cách giữa các thành phần (top, left, bottom, right)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Đảm bảo các thành phần chiếm toàn bộ chiều ngang
        gbc.weightx = 1.0; // Cung cấp không gian cho các thành phần chiếm toàn bộ chiều ngang

        // Tạo các thành phần giao diện
        algorithmComboBox = new JComboBox<>(new String[]{"Hill", "Substitution", "Vigenère", "Affine", "Transposition"});
        languageComboBox = new JComboBox<>(new String[]{"English", "Vietnamese"});
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

        // Tạo các nút và thêm vào panel
        loadKeyButton = new JButton("Load Key");
        saveKeyButton = new JButton("Save Key");
        generateKeyButton = new JButton("Generate");

        // Thêm các nút vào panel dưới cùng theo hàng ngang và căn phải
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Căn phải các nút
        buttonPanel.add(loadKeyButton);
        buttonPanel.add(saveKeyButton);
        buttonPanel.add(generateKeyButton);

        // Đặt vị trí các nút dưới các ComboBox
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; // Chiếm toàn bộ chiều rộng
        gbc.weightx = 1.0; // Chiếm toàn bộ chiều rộng
        add(buttonPanel, gbc);

        languageComboBox.addActionListener(e -> updateLanguage());

        algorithmComboBox.addActionListener(e -> {
            if (listener != null) {
                listener.onAlgorithmChanged((String) algorithmComboBox.getSelectedItem());
            }
        });

        generateKeyButton.addActionListener(e -> {
            if (keyGenerateListener != null) {
                keyGenerateListener.onGenerateKey();
            }
        });


    }

    // Tạo interface để giao tiếp với CipherConfigurationPanel
    public interface AlgorithmChangeListener {
        void onAlgorithmChanged(String algorithm);
    }

    public void setAlgorithmChangeListener(AlgorithmChangeListener listener) {
        this.listener = listener;
    }

    public interface KeyGenerateListener {
        void onGenerateKey();
    }
    public void setKeyGenerateListener(KeyGenerateListener listener) {
        this.keyGenerateListener = listener;
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
    public String getSelectedAlgorithm() {
        return (String) algorithmComboBox.getSelectedItem();
    }

    public String getSelectedLanguage() {
        return (String) languageComboBox.getSelectedItem();
    }

    public String getSelectedAlphabet() {
        return (String) alphabetComboBox.getSelectedItem();
    }
}
