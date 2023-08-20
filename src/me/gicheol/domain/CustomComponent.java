package me.gicheol.domain;

import javax.swing.*;
import java.awt.*;


/**
 * Component 생성
 */
public class CustomComponent {


    /**
     * JLabel Font 설정 후 반환
     * @param font
     * @return
     */
    public static JLabel createJLabel(Font font) {
        JLabel jLabel = new JLabel();
        jLabel.setFont(font);
        return jLabel;
    }


    /**
     * JLabel Font 설정, Text 설정 후 반환
     * @param text
     * @param font
     * @return
     */
    public static JLabel createJLabel(String text, Font font) {
        JLabel jLabel = new JLabel(text);
        jLabel.setFont(font);
        return jLabel;
    }


    /**
     * JTextField Font 설정 후 반환
     * @param font
     * @return
     */
    public static JTextField createJTextField(Font font) {
        JTextField jTextField = new JTextField();
        jTextField.setFont(font);
        return jTextField;
    }


    /**
     * JTextField Font 설정, Text 설정 후 반환
     * @param text
     * @param font
     * @return
     */
    public static JTextField createJTextField(String text, Font font) {
        JTextField jTextField = new JTextField(text);
        jTextField.setFont(font);
        return jTextField;
    }


    /**
     * JButton Font 설정 후 반환
     * @param font
     * @return
     */
    public static JButton createJButton(Font font) {
        JButton jButton = new JButton();
        jButton.setFont(font);
        return jButton;
    }


    /**
     * JButton Font 설정, Text 설정 후 반환
     * @param text
     * @param font
     * @return
     */
    public static JButton createJButton(String text, Font font) {
        JButton jButton = new JButton(text);
        jButton.setFont(font);
        return jButton;
    }


    /**
     * JTextArea Font 설정 후 반환
     * @param font
     * @return
     */
    public static  JTextArea createJTextArea(Font font) {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(font);
        return jTextArea;
    }


    /**
     * JTextArea Font 설정, Border 설정 후 반환
     * @param font
     * @param color
     * @return
     */
    public static  JTextArea createJTextArea(Font font, Color color) {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(font);
        jTextArea.setBorder(BorderFactory.createLineBorder(color));
        return jTextArea;
    }

}
