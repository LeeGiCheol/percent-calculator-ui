package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.controller.CalendarController;
import me.gicheol.domain.Panels;
import me.gicheol.controller.MessageUpdateDialogController;
import me.gicheol.controller.PercentCalculatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * 버튼 클릭 이벤트
 */
public class ButtonClickListener {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d");
    private final Panels panels;

    public ButtonClickListener(Panels panels) {
        this.panels = panels;
    }


    /**
     * 매출 입력 버튼 클릭 이벤트
     * @param frame
     * @param e
     */
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


    /**
     * 결과 버튼 클릭 이벤트
     * @param frame
     * @param e
     */
    private void mainSubmitButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        textAreaRemove();

        if (panels.getSalesAmountList().getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "매출을 입력하세요.");
            return;
        }

        String amountFee = panels.getMainSalesAmountFeeField().getText().replaceAll(",", "").replaceAll("%", "");
        if (amountFee.equals("")) {
            JOptionPane.showMessageDialog(null, "퍼센트를 입력하세요.");
        }

        List amountList = panels.getSalesAmountList();
        Long sumAmount = 0L;
        StringBuilder amountListMessageAppendSales = new StringBuilder();
        StringBuilder amountListMessage = new StringBuilder();

        for (String item : amountList.getItems()) {
            sumAmount += Long.parseLong(item.substring(0, item.length()-1).replaceAll(",", ""));

            amountListMessageAppendSales.append("ㄴ매출: ")
                    .append(item).append("\n");

            amountListMessage.append(item).append("|");
        }

        BigDecimal feeAmount = CommonUtils.calculateFeeAmount(sumAmount, amountFee);
        BigDecimal vat = CommonUtils.calculateVAT(feeAmount);

        StringBuilder resultMessage = resultMessage(amountListMessageAppendSales, sumAmount, amountFee, feeAmount, vat);

        panels.getMainResultTextArea().append(resultMessage.toString());
        panels.getHistory().add(resultMessage.toString());

        String verificationAmountMessage = verificationAmountMessage(amountListMessage);

        StringBuilder verification = verificationMessage(amountListMessageAppendSales, verificationAmountMessage, sumAmount, amountFee, feeAmount, vat);

        panels.getMainResultTextAreaVerification().append(verification.toString());
        panels.getVerificationHistory().add(verification.toString());

        if (!panels.getMainHistoryButton().getText().equals("히스토리 보기")) {
            historyButtonToggle();
        }
    }


    /**
     * 히스토리 보기 버튼 toggle
     * toggle 시 결과보기
     */
    private void historyButtonToggle() {
        java.util.List<String> history = panels.getHistory();
        java.util.List<String> verificationHistory = panels.getVerificationHistory();

        if (panels.getMainHistoryButton().getText().equals("히스토리 보기")) {
            if (history.size() > 0) {
                historyMessageCall(panels.getMainResultTextArea(), history, "히스토리");
            }

            if (verificationHistory.size() > 0) {
                historyMessageCall(panels.getMainResultTextAreaVerification(), verificationHistory, "검증 히스토리");
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


    /**
     * 결과 메시지 생성
     * @param amountSB
     * @param sumAmount
     * @param amountFee
     * @param feeAmount
     * @param vat
     * @return
     */
    private StringBuilder resultMessage(StringBuilder amountSB, Long sumAmount, String amountFee, BigDecimal feeAmount, BigDecimal vat) {
        String companyField = panels.getCompanyField().getText().equals("") ?
                "" : panels.getCompanyField().getText() + "\n";


        return new StringBuilder()
                .append(companyField)
                .append(panels.getHeaderMessage())
                .append("\n\n")
                .append(inputDateIsNullNowDate())
                .append("\n")
                .append(amountSB)
                .append("\n")
                .append("총 - ")
                .append(CommonUtils.addCommaWonFormat(sumAmount))
                .append("\n\n")
                .append(CommonUtils.addCommaFormat(amountFee))
                .append("% ")
                .append("매출 수수료 정산 금액 - ")
                .append(CommonUtils.addCommaWonFormat(feeAmount))
                .append("\n")
                .append("세금계산서 발행금액 - ")
                .append(CommonUtils.addCommaWonFormat(vat))
                .append("\n\n")
                .append(panels.getFooterMessage())
        ;
    }


    /**
     * 검증 결과 메시지 생성
     * @param amountSB
     * @param amountAppend
     * @param sumAmount
     * @param amountFee
     * @param feeAmount
     * @param vat
     * @return
     */
    private StringBuilder verificationMessage(StringBuilder amountSB, String amountAppend, Long sumAmount, String amountFee, BigDecimal feeAmount, BigDecimal vat) {
        String companyField = panels.getCompanyField().getText().equals("") ?
                "" : panels.getCompanyField().getText() + "\n";

        return new StringBuilder()
                .append(companyField)
                .append(inputDateIsNullNowDate())
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
                .append(CommonUtils.addCommaWonFormat(feeAmount))
                .append("\n")
                .append("세금계산서 발행금액 - ")
                .append(CommonUtils.addCommaFormat(feeAmount) + " x " + 1.1 + " = " + CommonUtils.addCommaWonFormat(vat))
        ;
    }


    /**
     * 날짜를 작성했다면 작성한 날짜, 아니면 오늘 날짜를 반환
     * @return
     */
    private String inputDateIsNullNowDate() {
        if (panels.getMainDatePickerField().getText().equals("")) {
            return LocalDate.now().format(dateFormat);
        } else {
            return panels.getMainDatePickerField().getText();
        }
    }


    /**
     * 히스토리 메시지 생성
     * @param textArea
     * @param history
     * @param name
     */
    private void historyMessageCall(JTextArea textArea, java.util.List<String> history, String name) {
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n");
        for (int i = 0; i < history.size(); i++) {
            result.append(String.format("[%d] ", i+1)).append("\n").append(history.get(i)).append("\n\n");
        }

        textArea.append(result.toString());
    }


    /**
     * 검증용 합계 메시지 생성
     * @param amount
     * @return
     */
    private String verificationAmountMessage(StringBuilder amount) {
        String[] split = amount.toString().split("\\|");
        StringBuilder result = new StringBuilder();
        for (String s : split) {
            result.append(s).append(" + ");
        }

        return result.substring(0, result.length() - 2);
    }


    /**
     * 선택 매출 목록 삭제 버튼 클릭 이벤트 (매출 목록 리스트 중 선택 된 1개 매출 삭제)
     * @param frame
     * @param e
     */
    private void mainSelectResultRemoveButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        List amountList = panels.getSalesAmountList();
        String selectedItem = amountList.getSelectedItem();
        if (selectedItem == null) return;

        amountList.remove(selectedItem);
    }


    /**
     * 매출 목록 전체 삭제 버튼 클릭 이벤트 (매출 목록 전체 삭제)
     * @param frame
     * @param e
     */
    private void mainAllResultRemoveButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        List amountList = panels.getSalesAmountList();
        if (amountList.getItemCount() == 0) return;

        amountList.removeAll();
    }


    /**
     * 기록 전체 삭제 버튼 클릭 이벤트 (Field 값, 매출 목록, 결과, 검증 결과, 히스토리 모두 삭제)
     * @param frame
     * @param e
     */
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
        panels.getMainDatePickerField().selectAll();
        panels.getMainDatePickerField().replaceSelection("");
    }


    /**
     * 히스토리 보기 버튼 클릭 이벤트
     * @param frame
     * @param e
     */
    private void mainHistoryButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        textAreaRemove();
        historyButtonToggle();
    }


    /**
     * 메시지 수정하기 버튼 클릭 이벤트
     * @param frame
     * @param e
     */
    private void mainMessageUpdateButtonClickEvent(PercentCalculatorController frame, ActionEvent e) {
        new MessageUpdateDialogController(panels).callMessageUpdateDialog();
    }


    /**
     * 파일로 저장하기 버튼 클릭 이벤트
     * @param frame
     * @param e
     */
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


    /**
     * 변경하기 버튼 클릭 이벤트 (MessageUpdateDialog)
     * @param dialogUI
     * @param e
     */
    private void messageUpdateDialogSubmitButtonClickEvent(MessageUpdateDialogController dialogUI, ActionEvent e) {
        dialogUI.setVisible(false);
        panels.setHeaderMessage(panels.getHeaderMessageTextArea().getText());
        panels.setFooterMessage(panels.getFooterMessageTextArea().getText());
        CommonUtils.saveMessage(panels);
    }


    /**
     * 나가기 버튼 클릭 이벤트 (MessageUpdateDialog)
     * @param dialogUI
     * @param e
     */
    private void messageUpdateDialogCancelButtonClickEvent(MessageUpdateDialogController dialogUI, ActionEvent e) {
        dialogUI.setVisible(false);
    }


    /**
     * 결과, 검증 결과 내용 삭제
     */
    private void textAreaRemove() {
        panels.getMainResultTextArea().selectAll();
        panels.getMainResultTextArea().replaceSelection("");
        panels.getMainResultTextAreaVerification().selectAll();
        panels.getMainResultTextAreaVerification().replaceSelection("");
    }


    /**
     * Calendar 이전 달 버튼 클릭 이벤트
     * @param e
     */
    private void lastMonthClickEvent(ActionEvent e) {
        Integer year = (Integer) panels.getYearCombo().getSelectedItem();
        Integer month = (Integer) panels.getMonthCombo().getSelectedItem();

        if (month == 1) {
            year--; month = 12;
        } else {
            month--;
        }

        panels.getYearCombo().setSelectedItem(year);
        panels.getMonthCombo().setSelectedItem(month);
    }


    /**
     * Calendar 다음 달 버튼 클릭 이벤트
     * @param e
     */
    private void nextMonthClickEvent(ActionEvent e) {
        Integer year = (Integer) panels.getYearCombo().getSelectedItem();
        Integer month = (Integer) panels.getMonthCombo().getSelectedItem();

        if (year == 12){
            year++; month = 1;
        } else {
            month++;
        }

        panels.getYearCombo().setSelectedItem(year);
        panels.getMonthCombo().setSelectedItem(month);
    }


    /**
     * Calendar 날짜 선택 버튼 클릭 이벤트
     * @param e
     */
    private void calendarSubmitClickEvent(CalendarController frame, ActionEvent e) {
        String selectMonth = panels.getMonthCombo().getSelectedItem().toString();
        String selectDay= panels.getSelectedDay().getText();

        if (selectMonth.length() == 1) {
            selectMonth = "0" + selectMonth;
        }
        if (selectDay.length() == 1) {
            selectDay = "0" + selectDay;
        }

        if (selectMonth.length() == 0 || selectDay.length() == 0) {
            return;
        }

        panels.getMainDatePickerField().setText(selectMonth + "/" + selectDay);
        panels.setShowDatePicker(false);

        frame.setVisible(false);
    }


    /**
     * Calendar 이전 달, 다음 달 버튼 클릭 후 이벤트
     * @param e
     */
    private void comboActionEvent(ActionEvent e) {
        CommonUtils.createDayStart(panels);
    }


}
