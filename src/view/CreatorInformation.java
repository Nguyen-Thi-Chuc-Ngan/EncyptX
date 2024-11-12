package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorInformation extends JPanel {
    public CreatorInformation() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title label for the tool information
        JLabel titleLabel = new JLabel("Tool Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 0, 128));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Information panel for the developer and tool details
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add tool details
        JTextArea infoText = new JTextArea();
        infoText.setText("Tool Name: Encryption Tool\n" +
                "Version: 1.0\n" +
                "Developer: Nguyen Thi Chuc Ngan\n" +
                "Email: 21130451@st.hcmuaf.edu.vn\n\n" +
                "Faculty: Information Technology\n" +
                "Class: DH21DTC\n" +
                "University: University of Agriculture and Forestry, HCMC\n" +
                "Course: Information Systems Security\n\n" +
                "Thank you for using our tool!");
        infoText.setEditable(false);
        infoText.setFont(new Font("Arial", Font.PLAIN, 16));
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        infoText.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(infoText);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        infoPanel.add(scrollPane);

        // Add contact links for the developer
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setBackground(Color.WHITE);
        linkPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email button
        JButton emailButton = new JButton("Contact via Email");
        emailButton.setForeground(Color.BLUE);
        emailButton.setBorderPainted(false);
        emailButton.setContentAreaFilled(false);
        emailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon emailIcon = new ImageIcon(getClass().getResource("/image/email_icon.png"));
        Image image = emailIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        emailButton.setIcon(new ImageIcon(image));
        emailButton.setText(" Contact via Email");
        emailButton.setFont(new Font("Arial", Font.PLAIN, 16));
        emailButton.setPreferredSize(new Dimension(200, 40));
        emailButton.setFocusPainted(false);
        emailButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().mail(new java.net.URI("mailto:21130451@st.hcmuaf.edu.vn?subject=Question about Encryption Tool"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        emailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                emailButton.setForeground(Color.RED);
                emailButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailButton.setForeground(Color.BLUE);
                emailButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
            }
        });

        linkPanel.add(emailButton);

        // GitHub button
        JButton githubButton = new JButton("GitHub");
        githubButton.setForeground(Color.BLUE);
        githubButton.setFont(new Font("Arial", Font.PLAIN, 16));
        githubButton.setBorderPainted(false);
        githubButton.setContentAreaFilled(false);
        githubButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        githubButton.setPreferredSize(new Dimension(200, 40));
        githubButton.setFocusPainted(false);
        githubButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        githubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://github.com/Nguyen-Thi-Chuc-Ngan/SafeDataEncryptor.git"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        linkPanel.add(githubButton);

        // Feedback button
        JButton feedbackButton = new JButton("Rate the Tool");
        feedbackButton.setForeground(Color.GREEN);
        feedbackButton.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackButton.setBorderPainted(false);
        feedbackButton.setContentAreaFilled(false);
        feedbackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feedbackButton.setPreferredSize(new Dimension(200, 40));
        feedbackButton.setFocusPainted(false);
        feedbackButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for your feedback!");
            }
        });

        linkPanel.add(feedbackButton);

        // More Info button
        JButton moreInfoButton = new JButton("Learn More");
        moreInfoButton.setForeground(Color.BLUE);
        moreInfoButton.setFont(new Font("Arial", Font.PLAIN, 16));
        moreInfoButton.setBorderPainted(false);
        moreInfoButton.setContentAreaFilled(false);
        moreInfoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        moreInfoButton.setPreferredSize(new Dimension(200, 40));
        moreInfoButton.setFocusPainted(false);
        moreInfoButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
        moreInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Development History: \nVersion 1.0...");
            }
        });

        linkPanel.add(moreInfoButton);

        infoPanel.add(linkPanel);

        add(infoPanel, BorderLayout.CENTER);
    }
}
