package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputListener extends KeyAdapter {

    private final Panels panels;

    public KeyInputListener(Panels panels) {
        this.panels = panels;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if (e.getKeyCode() == 10) {
            panels.getSalesAddButton().doClick();
        }

        if (e.getSource() == panels.getSalesAmountField()) {
            JTextField salesAmountField = panels.getSalesAmountField();
            salesAmountField.setText(formattingFieldText(salesAmountField.getText()));
        } else if (e.getSource() == panels.getSalesAmountFeeField()) {
            JTextField salesAmountFeeField = panels.getSalesAmountFeeField();
            salesAmountFeeField.setText(formattingFieldText(salesAmountFeeField.getText()));
        }
    }

    private String formattingFieldText(String fieldText) {
        if (fieldText.isEmpty()) {
            return "";
        }

        char text = fieldText.charAt(fieldText.length() - 1);

        if (!Character.isDigit(text)) {
            return fieldText.substring(0, fieldText.length() - 1);
        } else if (fieldText.length() >= 4) {
            String amount = fieldText.replaceAll(",", "");
            return CommonUtils.addCommaFormat(amount);
        } else {
            return fieldText;
        }
    }

}
