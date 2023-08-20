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
     * 1. 숫자 외 입력 불가
     * 2. 한 자리 이상 입력 시 % 를 붙임
     * 3. 1000 단위 이상을 넘어 갈 경우 , 를 붙임
     * 4. backspace 입력 시 (keyCode == 8) 숫자만 제거 후 다시 포맷팅
     * @param e
     */
     private void mainSalesAmountFeeFieldInputEvent(KeyEvent e) {
         JTextField salesAmountFeeField = panels.getMainSalesAmountFeeField();
         String text = salesAmountFeeField.getText();
         if (e.getKeyCode() == 8) {
             text = text.replaceAll(",", "");
             text = text.replaceAll("%", "");

             if (text.length() > 1) {
                 text = text.substring(0, text.length() - 1);
                 salesAmountFeeField.setText(CommonUtils.formattingFeeFieldText(text));
             } else {
                 salesAmountFeeField.setText("");
             }
         } else {
             salesAmountFeeField.setText(CommonUtils.formattingFeeFieldText(text));
         }
     }


    /**
     * 날짜 입력 키보드 입력 이벤트
     * 아직 사용하지 않음
     * @param e
     */
     private void mainDatePickerFieldInputEvent(KeyEvent e) {}

}
