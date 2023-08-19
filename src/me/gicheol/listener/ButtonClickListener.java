package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.controller.MessageUpdateDialogController;
import me.gicheol.controller.PercentCalculatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ButtonClickListener {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d");

    private final Panels panels;

    public ButtonClickListener(Panels panels) {
        this.panels = panels;
    }

    private void mainSalesAddButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        String text = panels.getMainSalesAmountField().getText();

        if (text.length() == 0) {
            JOptionPane.showMessageDialog(null, "매출을 입력하세요.");
            return;
        }

        if (!text.matches("^[0-9|,]*$")) {
            text = text.replaceAll("[^0-9|,]", "");
        }

        panels.getSalesAmountList().add(text + "원");

        JTextField salesAmountField = panels.getMainSalesAmountField();
        salesAmountField.setText("");

        panels.setMainSalesAmountField(salesAmountField);
    }

    private void mainSubmitButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        textAreaRemove();

        if (panels.getSalesAmountList().getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "매출을 입력하세요.");
            return;
        }

        String amountFee = panels.getMainSalesAmountFeeField().getText().replaceAll(",", "");
        if (amountFee.equals("")) {
            JOptionPane.showMessageDialog(null, "퍼센트를 입력하세요.");
        }


        String nowDate = LocalDate.now().format(dateFormat);

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

        StringBuilder result = resultMessage(nowDate, amountSB, sumAmount, amountFee, percent, resultAmount);

        panels.getMainResultTextArea().append(result.toString());
        panels.getHistory().add(result.toString());

        String amountAppend = amountAppend(amount);

        StringBuilder verification = verificationMessage(nowDate, amountSB, amountAppend, sumAmount, amountFee, percent, resultAmount);

        panels.getMainResultTextAreaVerification().append(verification.toString());
        panels.getVerificationHistory().add(verification.toString());
        panels.getMainHistoryButton().setText("히스토리 보기");
    }


    private StringBuilder resultMessage(String nowDate, StringBuilder amountSB, Long sumAmount, String amountFee, BigDecimal percent, BigDecimal resultAmount) {
        String companyField = panels.getCompanyField().getText().equals("") ?
                "" : panels.getCompanyField().getText() + "\n";

        return new StringBuilder()
                .append(companyField)
                .append(panels.getHeaderMessage())
                .append("\n\n")
                .append(nowDate)
                .append("\n")
                .append(amountSB)
                .append("\n")
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
                .append(panels.getFooterMessage())
        ;
    }

    private StringBuilder verificationMessage(String nowDate, StringBuilder amountSB, String amountAppend, Long sumAmount, String amountFee, BigDecimal percent, BigDecimal resultAmount) {
        String companyField = panels.getCompanyField().getText().equals("") ?
                "" : panels.getCompanyField().getText() + "\n";

        return new StringBuilder()
                .append(companyField)
                .append(nowDate)
                .append("\n")
                .append(amountSB)
                .append("\n")
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
        StringBuilder result = new StringBuilder();
        for (String s : split) {
            result.append(s).append(" + ");
        }

        return result.substring(0, result.length() - 2);
    }

    private void mainResultRemoveButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        List amountList = panels.getSalesAmountList();
        String selectedItem = amountList.getSelectedItem();
        if (selectedItem == null) return;

        amountList.remove(selectedItem);
    }

    private void mainAllResultRemoveButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        List amountList = panels.getSalesAmountList();
        if (amountList.getItemCount() == 0) return;

        amountList.removeAll();
    }

    private void mainAllRemoveButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        textAreaRemove();
        panels.getHistory().clear();
        panels.getVerificationHistory().clear();
        panels.getSalesAmountList().removeAll();

        panels.getCompanyField().selectAll();
        panels.getCompanyField().replaceSelection("");
        panels.getMainSalesAmountField().selectAll();
        panels.getMainSalesAmountField().replaceSelection("");
        panels.getMainSalesAmountFeeField().selectAll();
        panels.getMainSalesAmountFeeField().replaceSelection("");
    }

    private void mainHistoryButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        textAreaRemove();

        java.util.List<String> history = panels.getHistory();
        java.util.List<String> verificationHistory = panels.getVerificationHistory();

        if (panels.getMainHistoryButton().getText().equals("히스토리 보기")) {
            if (history.size() > 0) {
                historyCall(panels.getMainResultTextArea(), history, "히스토리");
            }

            if (verificationHistory.size() > 0) {
                historyCall(panels.getMainResultTextAreaVerification(), verificationHistory, "검증 히스토리");
            }

            if (history.size() > 0 && verificationHistory.size() > 0) {
                panels.getMainHistoryButton().setText("결과 보기");
            }
        } else if (panels.getMainHistoryButton().getText().equals("결과 보기")) {
            if (history.size() > 0 && verificationHistory.size() > 0) {
                panels.getMainResultTextArea().setText(history.get(history.size()-1));
                panels.getMainResultTextAreaVerification().setText(verificationHistory.get(verificationHistory.size()-1));
                panels.getMainHistoryButton().setText("히스토리 보기");
            }
        }

    }

    private void mainMessageUpdateButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        new MessageUpdateDialogController(panels).callMessageUpdateDialog();
    }

    private void messageUpdateDialogSubmitButtonClickEvent(MessageUpdateDialogController dialogUI, ActionEvent e) {
        dialogUI.setVisible(false);
        panels.setHeaderMessage(panels.getHeaderMessageTextArea().getText());
        panels.setFooterMessage(panels.getFooterMessageTextArea().getText());
        CommonUtils.saveMessage(panels);
    }

    private void messageUpdateDialogCancelButtonClickEvent(MessageUpdateDialogController dialogUI, ActionEvent e) {
        dialogUI.setVisible(false);
    }

    private void mainSaveFileButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        if (panels.getMainSaveFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            if (panels.getHistory().size() > 0) {
                String filePath = panels.getMainSaveFileChooser().getSelectedFile().toString();
                String fileExtension = panels.getMainSaveFileChooser().getFileFilter().getDescription();
                String fileFullPath = filePath + "." + fileExtension;

                CommonUtils.saveFile(panels, fileFullPath);
            } else {
                JOptionPane.showMessageDialog(null, "매출을 입력하세요.");
            }
        }
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
        panels.getMainResultTextArea().selectAll();
        panels.getMainResultTextArea().replaceSelection("");
        panels.getMainResultTextAreaVerification().selectAll();
        panels.getMainResultTextAreaVerification().replaceSelection("");
    }
}
