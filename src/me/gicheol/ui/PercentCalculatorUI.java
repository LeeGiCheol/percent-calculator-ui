package me.gicheol.ui;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.listener.ButtonClickListener;
import me.gicheol.listener.KeyInputListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.BorderLayout.NORTH;

public class PercentCalculatorUI extends JFrame {

    private final java.util.List<String> layoutLocation;


    public PercentCalculatorUI() {
        Panels panels = new Panels();
        layoutLocation = setLayoutLocation();
        CommonUtils.loadMessage(panels);

        ImageIcon favicon = new ImageIcon("resources/favicon.ico");
        setIconImage(favicon.getImage());

        setLookAndFeel();

        setSize(2000,1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel topPanel = panels.getTopPanel();

        JLabel amountLabel = new JLabel(" 매출 :", JLabel.LEFT);
        amountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField salesAmountField = new JTextField(20);
        salesAmountField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

        topPanel.add(amountLabel);
        topPanel.add(salesAmountField);

        JLabel salesAmountFee = new JLabel(" 매출 수수료 :", JLabel.LEFT);
        salesAmountFee.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        JTextField salesAmountFeeField = new JTextField(20);
        salesAmountFeeField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

        JLabel salesAmountFeePercent = new JLabel("%", JLabel.LEFT);
        salesAmountFeePercent.setFont(new Font("맑은 고딕", Font.PLAIN, 30));

        topPanel.add(salesAmountFee);
        topPanel.add(salesAmountFeeField);
        topPanel.add(salesAmountFeePercent);

        JLabel companyLabel = new JLabel(" 업체명 :", JLabel.LEFT);
        companyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JTextField companyField = new JTextField(20);
        companyField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

        topPanel.add(companyLabel);
        topPanel.add(companyField);

        JButton salesAddButton = new JButton("입력");
        JButton removeButton = new JButton("삭제");
        JButton allRemoveButton = new JButton("전체 삭제");
        JButton submitButton = new JButton("결과");
        JButton historyButton = new JButton("히스토리 보기");
        JButton messageUpdateButton = new JButton("메시지 수정하기");
        salesAddButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        removeButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        allRemoveButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        submitButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        historyButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        messageUpdateButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));


        topPanel.add(salesAddButton);
        topPanel.add(removeButton);
        topPanel.add(allRemoveButton);
        topPanel.add(submitButton);
        topPanel.add(historyButton);
        topPanel.add(messageUpdateButton);

        JPanel middlePanel = panels.getMiddlePanel();
        List amountList = panels.getSalesAmountList();
        amountList.setFont(new Font("맑은 고딕", Font.PLAIN, 30));

        middlePanel.add(amountList);

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        resultTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane resultTextAreaScrollPane = new JScrollPane(resultTextArea);

        middlePanel.add(resultTextAreaScrollPane);

        JTextArea resultTextAreaVerification = new JTextArea();
        resultTextAreaVerification.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        resultTextAreaVerification.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane resultTextAreaVerificationScrollPane = new JScrollPane(resultTextAreaVerification);

        JPanel resultPanel = panels.getResultPanel();
        resultPanel.add(resultTextAreaVerificationScrollPane);

        middlePanel.add(resultPanel);
        addPanelToLayout(topPanel, middlePanel);

        setPanels(panels, salesAmountField, salesAmountFeeField, companyField, salesAddButton, removeButton, allRemoveButton, submitButton, historyButton, messageUpdateButton, middlePanel, resultTextArea, resultTextAreaVerification);
        addListener(panels);

        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    private void setPanels(Panels panels, JTextField salesAmountField, JTextField salesAmountFeeField, JTextField companyField, JButton salesAddButton, JButton removeButton, JButton allRemoveButton, JButton submitButton, JButton historyButton, JButton messageUpdateButton, JPanel middlePanel, JTextArea resultTextArea, JTextArea resultTextAreaVerification) {
        panels.setMiddlePanel(middlePanel);
        panels.setSalesAmountField(salesAmountField);
        panels.setSalesAmountFeeField(salesAmountFeeField);
        panels.setCompanyField(companyField);
        panels.setSalesAddButton(salesAddButton);
        panels.setRemoveButton(removeButton);
        panels.setSubmitButton(submitButton);
        panels.setResultTextArea(resultTextArea);
        panels.setResultTextAreaVerification(resultTextAreaVerification);
        panels.setAllRemoveButton(allRemoveButton);
        panels.setHistoryButton(historyButton);
        panels.setMessageUpdateButton(messageUpdateButton);
    }

    private void addPanelToLayout(JPanel... panels) {
        for (int i = 0; i < panels.length; i++) {
            add(layoutLocation.get(i), panels[i]);
        }
    }

    private void addListener(Panels panels) {
        panels.getSalesAmountField().addKeyListener(new KeyInputListener(panels));
        panels.getSalesAmountFeeField().addKeyListener(new KeyInputListener(panels));
        panels.getSalesAddButton().addActionListener(new ButtonClickListener(panels));
        panels.getRemoveButton().addActionListener(new ButtonClickListener(panels));
        panels.getAllRemoveButton().addActionListener(new ButtonClickListener(panels));
        panels.getSubmitButton().addActionListener(new ButtonClickListener(panels));
        panels.getHistoryButton().addActionListener(new ButtonClickListener(panels));
        panels.getMessageUpdateButton().addActionListener(new ButtonClickListener(panels));
    }

    private java.util.List<String> setLayoutLocation() {
        java.util.List<String> location = new ArrayList<>();
        location.add(NORTH);
        location.add(BorderLayout.CENTER);
        location.add(BorderLayout.CENTER);
        location.add(BorderLayout.EAST);
        return location;
    }



}