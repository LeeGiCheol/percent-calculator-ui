package me.gicheol.ui;

import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.*;

public class MessageUpdateDialog extends JDialog {

    private Panels panels;
    private JTextArea header;
    private JTextArea footer;

    public MessageUpdateDialog(JFrame frame, Panels panels) {
        super(frame, "");

        this.panels = panels;
    }

    public void call() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        header = new JTextArea();
        footer = new JTextArea();

        header.append(panels.getHeaderMessage());
        footer.append(panels.getFooterMessage());

        header.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        footer.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        footer.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(header); panel.add(footer);

        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        JButton submitButton = new JButton("수정하기");
        JButton cancelButton = new JButton("취소하기");

        footerPanel.add(submitButton); footerPanel.add(cancelButton);

        add(BorderLayout.CENTER, panel);
        add(BorderLayout.SOUTH, footerPanel);
        setSize(800, 800);

        submitButton.addActionListener(e -> {

        });

        cancelButton.addActionListener(e -> {
            setVisible(false);
        });
    }
}
