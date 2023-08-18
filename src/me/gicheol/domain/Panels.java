package me.gicheol.domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Panels {

    private JPanel topPanel = new JPanel(new GridLayout(1, 1));
    private JPanel middlePanel = new JPanel(new GridLayout(1, 1));
    private JPanel resultPanel = new JPanel(new BorderLayout());
    private JTextField companyField;
    private JTextField salesAmountField;
    private JTextField salesAmountFeeField;
    private JButton salesAddButton;
    private JButton removeButton;
    private JButton allRemoveButton;
    private JButton submitButton;
    private JButton historyButton;
    private JButton messageUpdateButton;
    private JTextArea resultTextArea;
    private JTextArea resultTextAreaVerification;

    private List salesAmountList = new List();
    private java.util.List<String> history = new ArrayList<>();
    private java.util.List<String> verificationHistory = new ArrayList<>();
    private String headerMessage;
    private String footerMessage;


    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getMiddlePanel() {
        return middlePanel;
    }

    public void setMiddlePanel(JPanel middlePanel) {
        this.middlePanel = middlePanel;
    }

    public JPanel getResultPanel() {
        return resultPanel;
    }

    public void setResultPanel(JPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public JTextField getCompanyField() {
        return companyField;
    }

    public void setCompanyField(JTextField companyField) {
        this.companyField = companyField;
    }

    public JTextField getSalesAmountField() {
        return salesAmountField;
    }

    public void setSalesAmountField(JTextField salesAmountField) {
        this.salesAmountField = salesAmountField;
    }

    public JTextField getSalesAmountFeeField() {
        return salesAmountFeeField;
    }

    public void setSalesAmountFeeField(JTextField salesAmountFeeField) {
        this.salesAmountFeeField = salesAmountFeeField;
    }

    public JButton getSalesAddButton() {
        return salesAddButton;
    }

    public void setSalesAddButton(JButton salesAddButton) {
        this.salesAddButton = salesAddButton;
    }

    public List getSalesAmountList() {
        return salesAmountList;
    }

    public void setSalesAmountList(List salesAmountList) {
        this.salesAmountList = salesAmountList;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(JButton removeButton) {
        this.removeButton = removeButton;
    }

    public JButton getAllRemoveButton() {
        return allRemoveButton;
    }

    public void setAllRemoveButton(JButton allRemoveButton) {
        this.allRemoveButton = allRemoveButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JButton getHistoryButton() {
        return historyButton;
    }

    public void setHistoryButton(JButton historyButton) {
        this.historyButton = historyButton;
    }

    public JButton getMessageUpdateButton() {
        return messageUpdateButton;
    }

    public void setMessageUpdateButton(JButton messageUpdateButton) {
        this.messageUpdateButton = messageUpdateButton;
    }

    public JTextArea getResultTextArea() {
        return resultTextArea;
    }

    public void setResultTextArea(JTextArea resultTextArea) {
        this.resultTextArea = resultTextArea;
    }

    public JTextArea getResultTextAreaVerification() {
        return resultTextAreaVerification;
    }

    public void setResultTextAreaVerification(JTextArea resultTextAreaVerification) {
        this.resultTextAreaVerification = resultTextAreaVerification;
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
