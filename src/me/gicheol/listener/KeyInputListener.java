package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.event.KeyEvent;


/**
 * 키보드 입력 이벤트
 */
public class KeyInputListener {

    private Panels panels;

    public KeyInputListener(Panels panels) {
        this.panels = panels;

    }


    /**
     * 매출 필드 키보드 입력 이벤트
     * 숫자 외 입력 불가 및 1000 단위 이상을 넘어 갈 경우 , 를 붙임
     * @param e
     */
     private void mainSalesAmountFieldInputEvent(KeyEvent e) {
         JTextField salesAmountField = panels.getMainSalesAmountField();
         salesAmountField.setText(CommonUtils.formattingFieldText(salesAmountField.getText()));
     }


    /**
     * 매출 수수료 키보드 입력 이벤트
     * 숫자 외 입력 불가 및 1000 단위 이상을 넘어 갈 경우 , 를 붙임
     * @param e
     */
     private void mainSalesAmountFeeFieldInputEvent(KeyEvent e) {
        JTextField salesAmountFeeField = panels.getMainSalesAmountFeeField();
        salesAmountFeeField.setText(CommonUtils.formattingFieldText(salesAmountFeeField.getText()));
     }

}
