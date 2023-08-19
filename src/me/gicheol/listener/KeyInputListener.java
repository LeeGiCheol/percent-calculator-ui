package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class KeyInputListener {

    private Panels panels;

    public KeyInputListener(Panels panels) {
        this.panels = panels;

    }

     private void mainSalesAmountFieldInputEvent(KeyEvent e) {
         JTextField salesAmountField = panels.getMainSalesAmountField();
         salesAmountField.setText(CommonUtils.formattingFieldText(salesAmountField.getText()));
     }

     private void mainSalesAmountFeeFieldInputEvent(KeyEvent e) {
        JTextField salesAmountFeeField = panels.getMainSalesAmountFeeField();
        salesAmountFeeField.setText(CommonUtils.formattingFieldText(salesAmountFeeField.getText()));
     }

}
