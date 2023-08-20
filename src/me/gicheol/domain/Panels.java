package me.gicheol.domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * 판넬 DTO
 */
public class Panels {

    // 상단 판넬
    private JPanel mainTopPanel = new JPanel(new GridLayout(5, 1));

    // 상단 텍스트 판넬
    private JPanel mainTopTextPanel = new JPanel(new GridLayout(1, 1));

    // 상단 버튼 판넬
    private JPanel mainTopButtonPanel = new JPanel(new GridLayout());

    // 중간 판넬
    private JPanel mainMiddlePanel = new JPanel(new BorderLayout());

    // 매출 input
    private JTextField mainSalesAmountField;

    // 매출 퍼센트 input
    private JTextField mainSalesAmountFeeField;

    // 업체명 input
    private JTextField companyField;

    // 매출 입력 버튼
    private JButton mainSalesAddButton;

    // 결과  버튼
    private JButton mainSubmitButton;

    // 선택 매출 목록 삭제 버튼
    private JButton mainSelectResultRemoveButton;

    // 매출 목록 전체 삭제 버튼
    private JButton mainAllResultRemoveButton;

    // 기록 전체 삭제 버튼
    private JButton mainAllRemoveButton;

    // 히스토리 보기 버튼
    private JButton mainHistoryButton;

    // 메시지 수정하기 버튼
    private JButton mainMessageUpdateButton;

    // 파일로 저장하기 버튼
    private JButton mainSaveFileButton;

    // 매출 목록
    private List salesAmountList = new List();

    // 결과 textarea
    private JTextArea mainResultTextArea;

    // 검증 결과 textarea
    private JTextArea mainResultTextAreaVerification;

    // 파일 저장 chooser
    private JFileChooser mainSaveFileChooser;

    // 히스토리 리스트
    private java.util.List<String> history = new ArrayList<>();

    // 검증 리스트
    private java.util.List<String> verificationHistory = new ArrayList<>();

    private JTextField datePickerText;


    /////  MessageUpdateDialogController /////
    // 변경하기 버튼
    private JButton messageUpdateDialogSubmitButton;

    // 나가기 버튼
    private JButton messageUpdateDialogCancelButton;

    // header 메시지 textarea
    private JTextArea headerMessageTextArea;

    // footer 메시지 textarea
    private JTextArea footerMessageTextArea;

    // header 메시지
    private String headerMessage;

    // footer 메시지
    private String footerMessage;
    //////////////////////////////////////////


    public JPanel getMainTopPanel() {
        return mainTopPanel;
    }

    public void setMainTopPanel(JPanel mainTopPanel) {
        this.mainTopPanel = mainTopPanel;
    }

    public JPanel getMainTopTextPanel() {
        return mainTopTextPanel;
    }

    public void setMainTopTextPanel(JPanel mainTopTextPanel) {
        this.mainTopTextPanel = mainTopTextPanel;
    }

    public JPanel getMainTopButtonPanel() {
        return mainTopButtonPanel;
    }

    public void setMainTopButtonPanel(JPanel mainTopButtonPanel) {
        this.mainTopButtonPanel = mainTopButtonPanel;
    }

    public JPanel getMainMiddlePanel() {
        return mainMiddlePanel;
    }

    public void setMainMiddlePanel(JPanel mainMiddlePanel) {
        this.mainMiddlePanel = mainMiddlePanel;
    }

    public JTextField getMainSalesAmountField() {
        return mainSalesAmountField;
    }

    public void setMainSalesAmountField(JTextField mainSalesAmountField) {
        this.mainSalesAmountField = mainSalesAmountField;
    }

    public JTextField getMainSalesAmountFeeField() {
        return mainSalesAmountFeeField;
    }

    public void setMainSalesAmountFeeField(JTextField mainSalesAmountFeeField) {
        this.mainSalesAmountFeeField = mainSalesAmountFeeField;
    }

    public JTextField getCompanyField() {
        return companyField;
    }

    public void setCompanyField(JTextField companyField) {
        this.companyField = companyField;
    }

    public JButton getMainSalesAddButton() {
        return mainSalesAddButton;
    }

    public void setMainSalesAddButton(JButton mainSalesAddButton) {
        this.mainSalesAddButton = mainSalesAddButton;
    }

    public JButton getMainSubmitButton() {
        return mainSubmitButton;
    }

    public void setMainSubmitButton(JButton mainSubmitButton) {
        this.mainSubmitButton = mainSubmitButton;
    }

    public JButton getMainSelectResultRemoveButton() {
        return mainSelectResultRemoveButton;
    }

    public void setMainSelectResultRemoveButton(JButton mainSelectResultRemoveButton) {
        this.mainSelectResultRemoveButton = mainSelectResultRemoveButton;
    }

    public JButton getMainAllResultRemoveButton() {
        return mainAllResultRemoveButton;
    }

    public void setMainAllResultRemoveButton(JButton mainAllResultRemoveButton) {
        this.mainAllResultRemoveButton = mainAllResultRemoveButton;
    }

    public JButton getMainAllRemoveButton() {
        return mainAllRemoveButton;
    }

    public void setMainAllRemoveButton(JButton mainAllRemoveButton) {
        this.mainAllRemoveButton = mainAllRemoveButton;
    }

    public JButton getMainHistoryButton() {
        return mainHistoryButton;
    }

    public void setMainHistoryButton(JButton mainHistoryButton) {
        this.mainHistoryButton = mainHistoryButton;
    }

    public JButton getMainMessageUpdateButton() {
        return mainMessageUpdateButton;
    }

    public void setMainMessageUpdateButton(JButton mainMessageUpdateButton) {
        this.mainMessageUpdateButton = mainMessageUpdateButton;
    }

    public JButton getMainSaveFileButton() {
        return mainSaveFileButton;
    }

    public void setMainSaveFileButton(JButton mainSaveFileButton) {
        this.mainSaveFileButton = mainSaveFileButton;
    }

    public List getSalesAmountList() {
        return salesAmountList;
    }

    public void setSalesAmountList(List salesAmountList) {
        this.salesAmountList = salesAmountList;
    }

    public JTextArea getMainResultTextArea() {
        return mainResultTextArea;
    }

    public void setMainResultTextArea(JTextArea mainResultTextArea) {
        this.mainResultTextArea = mainResultTextArea;
    }

    public JTextArea getMainResultTextAreaVerification() {
        return mainResultTextAreaVerification;
    }

    public void setMainResultTextAreaVerification(JTextArea mainResultTextAreaVerification) {
        this.mainResultTextAreaVerification = mainResultTextAreaVerification;
    }

    public JFileChooser getMainSaveFileChooser() {
        return mainSaveFileChooser;
    }

    public void setMainSaveFileChooser(JFileChooser mainSaveFileChooser) {
        this.mainSaveFileChooser = mainSaveFileChooser;
    }

    public java.util.List<String> getHistory() {
        return history;
    }

    public void setHistory(java.util.List<String> history) {
        this.history = history;
    }

    public java.util.List<String> getVerificationHistory() {
        return verificationHistory;
    }

    public void setVerificationHistory(java.util.List<String> verificationHistory) {
        this.verificationHistory = verificationHistory;
    }

    public JButton getMessageUpdateDialogSubmitButton() {
        return messageUpdateDialogSubmitButton;
    }

    public void setMessageUpdateDialogSubmitButton(JButton messageUpdateDialogSubmitButton) {
        this.messageUpdateDialogSubmitButton = messageUpdateDialogSubmitButton;
    }

    public JButton getMessageUpdateDialogCancelButton() {
        return messageUpdateDialogCancelButton;
    }

    public void setMessageUpdateDialogCancelButton(JButton messageUpdateDialogCancelButton) {
        this.messageUpdateDialogCancelButton = messageUpdateDialogCancelButton;
    }

    public JTextArea getHeaderMessageTextArea() {
        return headerMessageTextArea;
    }

    public void setHeaderMessageTextArea(JTextArea headerMessageTextArea) {
        this.headerMessageTextArea = headerMessageTextArea;
    }

    public JTextArea getFooterMessageTextArea() {
        return footerMessageTextArea;
    }

    public void setFooterMessageTextArea(JTextArea footerMessageTextArea) {
        this.footerMessageTextArea = footerMessageTextArea;
    }

    public String getHeaderMessage() {
        return headerMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public String getFooterMessage() {
        return footerMessage;
    }

    public void setFooterMessage(String footerMessage) {
        this.footerMessage = footerMessage;
    }

    public JTextField getDatePickerText() {
        return datePickerText;
    }

    public void setDatePickerText(JTextField datePickerText) {
        this.datePickerText = datePickerText;
    }
}