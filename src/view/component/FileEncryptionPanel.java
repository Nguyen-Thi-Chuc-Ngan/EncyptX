package view.component;

import controller.SymmetricEncryptionController;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import java.io.File;

public class FileEncryptionPanel extends JPanel {

    private JLabel inputFileLabel;
    protected JTextField inputFilePath;
    private JButton browseInputButton;

    private JLabel outputDirLabel;
    protected JTextField outputDirPath;
    private JButton browseOutputButton;


    public FileEncryptionPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Adding padding around the panel

        // Initialize UI components
        inputFileLabel = new JLabel("Select file to encrypt:");
        inputFilePath = new JTextField(70);
        inputFilePath.setEditable(false);  // Read-only, user cannot type
        browseInputButton = new JButton("Browse");

        outputDirLabel = new JLabel("Select directory to save encrypted file:");
        outputDirPath = new JTextField(70);
        outputDirPath.setEditable(false);  // Read-only, user cannot type
        browseOutputButton = new JButton("Browse");

        // Create drag-and-drop area for file
        JPanel dragDropPanel = createDragDropPanel();

        // Configure "Browse" buttons to allow users to select input/output files
        browseInputButton.addActionListener(e -> browseFile(true));
        browseOutputButton.addActionListener(e -> browseFile(false));

        // Create a container panel that uses BoxLayout to make both panels fill equally
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS)); // Horizontal layout for input
        inputPanel.add(inputFileLabel);
        inputPanel.add(Box.createHorizontalStrut(110));  // Add space between label and text field
        inputPanel.add(inputFilePath);
        inputPanel.add(Box.createHorizontalStrut(20));  // Add space between text field and browse button
        inputPanel.add(browseInputButton);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.X_AXIS)); // Horizontal layout for output
        outputPanel.add(outputDirLabel);
        outputPanel.add(Box.createHorizontalStrut(20));  // Add space between label and text field
        outputPanel.add(outputDirPath);
        outputPanel.add(Box.createHorizontalStrut(20));  // Add space between text field and browse button
        outputPanel.add(browseOutputButton);

        // Create a container panel that uses GridLayout for equal distribution of space
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(inputPanel);
        containerPanel.add(Box.createVerticalStrut(10)); // Add space between panels
        containerPanel.add(outputPanel);

        // Add components to the panel
        add(dragDropPanel); // Add drag-and-drop panel
        add(Box.createVerticalStrut(30));
        add(containerPanel); // Add the container with equal size panels
    }

    private JPanel createDragDropPanel() {
        JPanel dragDropPanel = new JPanel(new BorderLayout());
        JLabel dragLabel = new JLabel("Drag and drop a file here");
        dragLabel.setHorizontalAlignment(JLabel.CENTER);
        dragLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        dragLabel.setPreferredSize(new Dimension(400, 90));
        dragLabel.setBackground(new Color(240, 240, 240)); // Light background color for the drag area
        dragLabel.setOpaque(true); // Ensure the drag area has a background color

        dragDropPanel.add(dragLabel, BorderLayout.CENTER);

        // Handle drag-and-drop events
        dragLabel.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferSupport support) {
                try {
                    java.util.List<File> files = (java.util.List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (!files.isEmpty()) {
                        inputFilePath.setText(files.get(0).getAbsolutePath());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        return dragDropPanel;
    }

    private void browseFile(boolean isInput) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(isInput ? JFileChooser.FILES_ONLY : JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (isInput) {
                inputFilePath.setText(selectedFile.getAbsolutePath());
            } else {
                outputDirPath.setText(selectedFile.getAbsolutePath());
            }
        }


    }
    // Getter methods to access the file paths
    public String getInputFilePath() {
        return inputFilePath.getText().trim();
    }

    public String getOutputDirPath() {
        return outputDirPath.getText().trim();
    }

}
