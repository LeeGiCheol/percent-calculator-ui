package me.gicheol.domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Panels {

    private JPanel mainTopTextPanel = new JPanel(new GridLayout(1, 1));
    private JPanel mainTopButtonPanel = new JPanel(new GridLayout());
    private JPanel mainTopPanel = new JPanel(new GridLayout(5, 1));
    private JPanel mainMiddlePanel = new JPanel(new BorderLayout());
    private JTextField companyField;
    private JTextField mainSalesAmountField;
    private JTextField mainSalesAmountFeeField;
    private JButton mainSalesAddButton;
    private JButton mainResultRemoveButton;
    private JButton mainAllResultRemoveButton;
    private JButton mainAllRemoveButton;
    private JButton mainSubmitButton;
    private JButton mainHistoryButton;
    private JButton mainSaveFileButton;

    /////  MessageUpdateDialogController /////
    private JButton messageUpdateDialogSubmitButton;
    private JButton messageUpdateDialogCancelButton;
    private JTextArea headerMessageTextArea;
    private JTextArea footerMessageTextArea;
    //////////////////////////////////////////

    private JButton mainMessageUpdateButton;
    private JTextArea mainResultTextArea;
    private JTextArea mainResultTextAreaVerification;
    private JFileChooser mainSaveFileChooser;

    private List salesAmountList = new List();
    private java.util.List<String> history = new ArrayList<>();
    private java.util.List<String> verificationHistory = new ArrayList<>();
    private String headerMessage;
    private String footerMessage;


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

    public JPanel getMainTopPanel() {
        return mainTopPanel;
    }

    public void setMainTopPanel(JPanel mainTopPanel) {
        this.mainTopPanel = mainTopPanel;
    }

    public JPanel getMainMiddlePanel() {
        return mainMiddlePanel;
    }

    public void setMainMiddlePanel(JPanel mainMiddlePanel) {
        this.mainMiddlePanel = mainMiddlePanel;
    }

    public JTextField getCompanyField() {
        return companyField;
    }

    public void setCompanyField(JTextField companyField) {
        this.companyField = companyField;
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

    public JButton getMainSalesAddButton() {
        return mainSalesAddButton;
    }

    public void setMainSalesAddButton(JButton mainSalesAddButton) {
        this.mainSalesAddButton = mainSalesAddButton;
    }

    public JButton getMainResultRemoveButton() {
        return mainResultRemoveButton;
    }

    public void setMainResultRemoveButton(JButton mainResultRemoveButton) {
        this.mainResultRemoveButton = mainResultRemoveButton;
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

    public JButton getMainSubmitButton() {
        return mainSubmitButton;
    }

    public void setMainSubmitButton(JButton mainSubmitButton) {
        this.mainSubmitButton = mainSubmitButton;
    }

    public JButton getMainHistoryButton() {
        return mainHistoryButton;
    }

    public void setMainHistoryButton(JButton mainHistoryButton) {
        this.mainHistoryButton = mainHistoryButton;
    }

    public JButton getMainSaveFileButton() {
        return mainSaveFileButton;
    }

    public void setMainSaveFileButton(JButton mainSaveFileButton) {
        this.mainSaveFileButton = mainSaveFileButton;
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

    public JButton getMainMessageUpdateButton() {
        return mainMessageUpdateButton;
    }

    public void setMainMessageUpdateButton(JButton mainMessageUpdateButton) {
        this.mainMessageUpdateButton = mainMessageUpdateButton;
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

    public List getSalesAmountList() {
        return salesAmountList;
    }

    public void setSalesAmountList(List salesAmountList) {
        this.salesAmountList = salesAmountList;
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

}
