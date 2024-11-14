package view;

import view.component.AsymetricOptionlPanel;

import javax.swing.*;
import java.awt.*;

public class AsymmetricEncryptionPanel extends JPanel {
    public AsymmetricEncryptionPanel(){
        setLayout(new BorderLayout(10, 10));

        AsymetricOptionlPanel asymetricOptionlPanel = new AsymetricOptionlPanel();
        add(asymetricOptionlPanel);
    }
}
