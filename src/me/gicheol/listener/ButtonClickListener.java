package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ButtonClickListener implements ActionListener {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d");
    private Panels panels;


    public ButtonClickListener(Panels panels) {
        this.panels = panels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panels.getSalesAddButton()) {
            salesAddButtonClickEvent();
        } else if (e.getSource() == panels.getRemoveButton()) {
            removeButtonClickEvent();
        } else if (e.getSource() == panels.getAllRemoveButton()) {
            allRemoveButtonClickEvent();
        } else if (e.getSource() == panels.getSubmitButton()) {
            submitButtonClickEvent();
        } else if (e.getSource() == panels.getHistoryButton()) {
            historyButtonClickEvent();
        } else if (e.getSource() == panels.getMessageUpdateButton()) {
            messageUpdateButtonClickEvent();
        }
    }

    private void salesAddButtonClickEvent() {
        String text = panels.getSalesAmountField().getText();

        if (text.length() == 0) {
            JOptionPane.showMessageDialog(null, "매출을 입력하세요.");
            return;
        }

        if (!text.matches("^[0-9|,]*$")) {
            text = text.replaceAll("[^0-9|,]", "");
        }

        panels.getSalesAmountList().add(text + "원");

        JTextField salesAmountField = panels.getSalesAmountField();
        salesAmountField.setText("");

        panels.setSalesAmountField(salesAmountField);
    }

    private void submitButtonClickEvent() {
        textAreaRemove();

        if (panels.getSalesAmountList().getItemCount() == 0) {
            return;
        }

        String amountFee = panels.getSalesAmountFeeField().getText().replaceAll(",", "");
        if (amountFee.equals("")) {
            JOptionPane.showMessageDialog(null, "퍼센트를 입력하세요.");
        }



        String companyField = "";
        if (!panels.getCompanyField().getText().equals("")) {
            companyField = panels.getCompanyField().getText() + "\n";
        }

        String nowDate = LocalDate.now().format(dateFormat);
        
        String headerMent = "방송 고생 많으셨습니다 ! 계약된 방송건이 모두 종료되어 수수료정산 관련 안내 드립니다!";
        String footerMent = "세금계산거 발행금액으로, 메일로 발행해드렸습니다! 하기 계좌번호로 입금 부탁드리겠습니다.";

        StringBuilder amount = new StringBuilder();


        List amountList = panels.getSalesAmountList();
        Long sumAmount = 0L;
        StringBuilder amountSB = new StringBuilder();

        for (String item : amountList.getItems()) {
            sumAmount += Long.parseLong(item.substring(0, item.length()-1).replaceAll(",", ""));

            amountSB.append("ㄴ매출: ")
                    .append(item).append("\n");

            amount.append(item).append("|");
        }



        BigDecimal percent = CommonUtils.getPercent(sumAmount, amountFee);
        BigDecimal resultAmount = CommonUtils.getResultAmount(percent);

        StringBuilder result = resultMessage(companyField, headerMent, nowDate, sumAmount, amountFee, percent, resultAmount, footerMent);

        panels.getResultTextArea().append(result.toString());
        panels.getHistory().add(result.toString());

        String amountAppend = amountAppend(amount);

        StringBuilder verification = verificationMessage(companyField, nowDate, amountSB, amountAppend, sumAmount, amountFee, percent, resultAmount);

        panels.getResultTextAreaVerification().append(verification.toString());
        panels.getVerificationHistory().add(verification.toString());
    }


    private static StringBuilder resultMessage(String companyField, String headerMent, String nowDate, Long sumAmount, String amountFee, BigDecimal percent, BigDecimal resultAmount, String footerMent) {
        return new StringBuilder()
            .append(companyField)
            .append("\n")
            .append(headerMent)
            .append("\n\n")
            .append(nowDate)
            .append("\n")
            .append("ㄴ매출: ")
            .append(sumAmount)
            .append("\n\n")
            .append("총 - ")
            .append(CommonUtils.addCommaWonFormat(sumAmount))
            .append("\n\n")
            .append(CommonUtils.addCommaFormat(amountFee))
            .append("% ")
            .append("매출 수수료 정산 금액 - ")
            .append(CommonUtils.addCommaWonFormat(percent))
            .append("\n")
            .append("세금계산서 발행금액 - ")
            .append(CommonUtils.addCommaWonFormat(resultAmount))
            .append("\n\n")
            .append(footerMent)
        ;
    }

    private static StringBuilder verificationMessage(String companyField, String nowDate, StringBuilder amountSB, String amountAppend, Long sumAmount, String amountFee, BigDecimal percent, BigDecimal resultAmount) {
        return new StringBuilder()
                .append(companyField)
                .append("[검증]")
                .append("\n\n")
                .append(nowDate)
                .append("\n")
                .append(amountSB)
                .append("합계 - ")
                .append(amountAppend)
                .append(" = ")
                .append(CommonUtils.addCommaWonFormat(sumAmount))
                .append("\n\n")
                .append(CommonUtils.addCommaFormat(amountFee))
                .append("% ")
                .append("매출 수수료 정산 금액 - ")
                .append(CommonUtils.addCommaFormat(sumAmount) + " x " + amountFee + " / " + 100)
                .append(" = ")
                .append(CommonUtils.addCommaWonFormat(percent))
                .append("\n")
                .append("세금계산서 발행금액 - ")
                .append(CommonUtils.addCommaFormat(percent) + " x " + 1.1 + " = " + CommonUtils.addCommaWonFormat(resultAmount))
        ;
    }

    private String amountAppend(StringBuilder amount) {
        String[] split = amount.toString().split("\\|");
        String result = "";
        for (String s : split) {
            result += s + " + ";
        }

        return result.substring(0, result.length() - 2);
    }

    private void removeButtonClickEvent() {
        List amountList = panels.getSalesAmountList();
        String selectedItem = amountList.getSelectedItem();
        if (selectedItem == null) return;

        amountList.remove(selectedItem);
    }

    private void allRemoveButtonClickEvent() {
        List amountList = panels.getSalesAmountList();
        if (amountList.getItemCount() == 0) return;

        amountList.removeAll();
    }

    private void historyButtonClickEvent() {
        textAreaRemove();

        java.util.List<String> history = panels.getHistory();

        if (history.size() > 0) {
            historyCall(panels.getResultTextArea(), history, "히스토리");
        }

        java.util.List<String> verificationHistory = panels.getVerificationHistory();

        if (verificationHistory.size() > 0) {
            historyCall(panels.getResultTextAreaVerification(), verificationHistory, "검증 히스토리");
        }

    }

    private void messageUpdateButtonClickEvent() {

    }

    private void historyCall(JTextArea textArea, java.util.List<String> history, String name) {
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n");
        for (int i = 0; i < history.size(); i++) {
            result.append(String.format("[%d] ", i+1)).append("\n").append(history.get(i)).append("\n\n");
        }

        textArea.append(result.toString());
    }

    private void textAreaRemove() {
        panels.getResultTextArea().selectAll();
        panels.getResultTextArea().replaceSelection("");
        panels.getResultTextAreaVerification().selectAll();
        panels.getResultTextAreaVerification().replaceSelection("");
    }

}
