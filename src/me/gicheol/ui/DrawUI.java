package me.gicheol.ui;

import me.gicheol.domain.FontFactory;
import me.gicheol.domain.Panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;



public class DrawUI {

    private final Panels panels;
    private final java.util.List<String> layoutLocation;

    public DrawUI(Panels panels) {
        this.panels = panels;
        this.layoutLocation = setLayoutLocation();
    }

    public void initMainPage(JFrame jFrame) {
        setLookAndFeel("Nimbus");

        setDefaultPanels();
        initTopPanel();
        initMiddlePanel();

        setFaviconImage(jFrame, "/resources/favicon.ico");

        jFrame.setSize(2000,1200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    public void setLookAndFeel(String uiType) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals(uiType)) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("LOOK_AND_FEEL_ERROR");
        }
    }

    public void setFaviconImage(JFrame jframe, String filePath) {
        Path path = Paths.get(Paths.get("").toAbsolutePath() + filePath);
        ImageIcon favicon = new ImageIcon(path.toString());
        jframe.setIconImage(favicon.getImage());
    }

    public java.util.List<String> setLayoutLocation() {
        java.util.List<String> location = new ArrayList<>();
        location.add(BorderLayout.NORTH);
        location.add(BorderLayout.CENTER);
        location.add(BorderLayout.EAST);
        return location;
    }

    public void addPanelToLayout(JFrame jFrame, JPanel... panels) {
        for (int i = 0; i < panels.length; i++) {
            jFrame.add(layoutLocation.get(i), panels[i]);
        }
    }

    public void initTopPanel() {
        initTopText();
        initTopButton();
        initButtonRow();
        initSaveFileButton();

        initTopPanelCombination();

        initResultLabel();
    }

    public void initMiddlePanel() {
        this.panels.getMainMiddlePanel().add(initTextAreaPanel());
    }

    private void initTopText() {
        JLabel amountLabel = new JLabel(" 매출 :", JLabel.LEFT);
        JTextField salesAmountField = this.panels.getMainSalesAmountField();
        JLabel salesAmountFee = new JLabel(" 매출 수수료 :", JLabel.LEFT);
        JTextField salesAmountFeeField = this.panels.getMainSalesAmountFeeField();
        JLabel salesAmountFeePercentLabel = new JLabel("%", JLabel.LEFT);
        JLabel companyLabel = new JLabel(" 업체명 :", JLabel.LEFT);
        JTextField companyField = this.panels.getCompanyField();

        amountLabel.setFont(FontFactory.BOLD_FONT_SIZE_20);
        salesAmountField.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        salesAmountFee.setFont(FontFactory.BOLD_FONT_SIZE_20);
        salesAmountFeeField.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        salesAmountFeePercentLabel.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        companyLabel.setFont(FontFactory.BOLD_FONT_SIZE_20);
        companyField.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        JPanel topTextPanel = this.panels.getMainTopTextPanel();
        topTextPanel.add(amountLabel);
        topTextPanel.add(salesAmountField);
        topTextPanel.add(salesAmountFee);
        topTextPanel.add(salesAmountFeeField);
        topTextPanel.add(salesAmountFeePercentLabel);
        topTextPanel.add(companyLabel);
        topTextPanel.add(companyField);
    }

    private void initTopButton() {
        JButton salesAddButton = new JButton("매출 입력");
        JButton submitButton = new JButton("결과");

        salesAddButton.setFont(FontFactory.BOLD_FONT_SIZE_20);
        submitButton.setFont(FontFactory.BOLD_FONT_SIZE_20);

        JPanel topTextPanel = this.panels.getMainTopTextPanel();
        topTextPanel.add(salesAddButton);
        topTextPanel.add(submitButton);

        this.panels.setMainSalesAddButton(salesAddButton);
        this.panels.setMainSubmitButton(submitButton);
    }

    private void initButtonRow() {
        JButton resultRemoveButton = new JButton("선택 매출 목록 삭제");
        JButton allResultRemoveButton = new JButton("매출 목록 전체 삭제");
        JButton allRemoveButton = new JButton("기록 전체 삭제");
        JButton historyButton = new JButton("히스토리 보기");
        JButton messageUpdateButton = new JButton("메시지 수정하기");

        resultRemoveButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        allRemoveButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        allResultRemoveButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        historyButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        messageUpdateButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        JPanel topButtonPanel = this.panels.getMainTopButtonPanel();
        topButtonPanel.add(resultRemoveButton);
        topButtonPanel.add(allResultRemoveButton);
        topButtonPanel.add(allRemoveButton);
        topButtonPanel.add(historyButton);
        topButtonPanel.add(messageUpdateButton);

        this.panels.setMainResultRemoveButton(resultRemoveButton);
        this.panels.setMainAllResultRemoveButton(allResultRemoveButton);
        this.panels.setMainAllRemoveButton(allRemoveButton);
        this.panels.setMainHistoryButton(historyButton);
        this.panels.setMainMessageUpdateButton(messageUpdateButton);
    }

    private void initSaveFileButton() {
        JButton saveFileButton = new JButton("파일로 저장하기");
        saveFileButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        this.panels.setMainSaveFileButton(saveFileButton);
    }


    private void initResultLabel() {
        JPanel resultLabelPanel = new JPanel(new GridLayout(1, 1));

        JLabel amountListLabel = new JLabel("매출 목록");
        JLabel resultTextAreaLabel = new JLabel("결과");
        JLabel resultTextAreaVerificationLabel = new JLabel("검증");

        resultTextAreaVerificationLabel.setFont(FontFactory.BOLD_FONT_SIZE_20);
        resultTextAreaLabel.setFont(FontFactory.BOLD_FONT_SIZE_20);
        amountListLabel.setFont(FontFactory.BOLD_FONT_SIZE_20);

        resultLabelPanel.add(amountListLabel);
        resultLabelPanel.add(resultTextAreaLabel);
        resultLabelPanel.add(resultTextAreaVerificationLabel);

        this.panels.getMainTopPanel().add(resultLabelPanel);
    }

    private void initTopPanelCombination() {
        JPanel topPanel = this.panels.getMainTopPanel();

        topPanel.add(this.panels.getMainTopTextPanel());
        topPanel.add(this.panels.getMainTopButtonPanel());
        topPanel.add(this.panels.getMainSaveFileButton());
        topPanel.add(new JPanel(new GridLayout(1, 1)));
    }


    private JPanel initTextAreaPanel() {
        JPanel textAreaPanel = new JPanel(new GridLayout(1, 1));

        List amountList = this.panels.getSalesAmountList();
        amountList.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        JTextArea resultTextArea = getTextAreaScrollPane();
        JTextArea resultTextAreaVerification = getTextAreaScrollPane();
        JScrollPane resultTextAreaScrollPane = new JScrollPane(resultTextArea);
        JScrollPane resultTextAreaVerificationScrollPane = new JScrollPane(resultTextAreaVerification);

        JPanel verificationPanel = new JPanel(new BorderLayout());
        verificationPanel.add(resultTextAreaVerificationScrollPane);

        textAreaPanel.add(amountList);
        textAreaPanel.add(resultTextAreaScrollPane);
        textAreaPanel.add(verificationPanel);

        this.panels.setMainResultTextArea(resultTextArea);
        this.panels.setMainResultTextAreaVerification(resultTextAreaVerification);

        return textAreaPanel;
    }

    private static JTextArea getTextAreaScrollPane() {
        JTextArea textArea = new JTextArea();
        textArea.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return textArea;
    }

    public Panels setDefaultPanels() {
        panels.setMainSalesAmountField(new JTextField());
        panels.setMainSalesAmountFeeField(new JTextField());
        panels.setCompanyField(new JTextField());

        JFileChooser saveFileChooser = new JFileChooser();
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        saveFileChooser.setMultiSelectionEnabled(false);
        panels.setMainSaveFileChooser(saveFileChooser);

        return panels;
    }


    public void initMessageUpdateDialog(JDialog jDialog) {
        jDialog.add(BorderLayout.BEFORE_FIRST_LINE, messageUpdateDialogTopLabel());
        jDialog.add(BorderLayout.CENTER, messageUpdateDialogMessagePanel());
        jDialog.add(BorderLayout.SOUTH, messageUpdateFooterPanel());
        jDialog.setSize(1200, 500);
        jDialog.setVisible(true);
    }

    private JPanel messageUpdateDialogTopLabel() {
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        JLabel headerLabel = new JLabel("상단 메시지");
        JLabel footerLabel = new JLabel("하단 메시지");

        headerLabel.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        footerLabel.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        labelPanel.add(headerLabel);
        labelPanel.add(footerLabel);

        return labelPanel;
    }

    private JPanel messageUpdateDialogMessagePanel() {
        JPanel messagePanel = new JPanel(new GridLayout(1, 2));
        JTextArea header = new JTextArea();
        JTextArea footer = new JTextArea();

        header.append(panels.getHeaderMessage());
        footer.append(panels.getFooterMessage());

        header.setFont(FontFactory.getFont(Font.PLAIN, 15));
        header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        footer.setFont(FontFactory.getFont(Font.PLAIN, 15));
        footer.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane headerScrollPane = new JScrollPane(header);
        JScrollPane footerScrollPane = new JScrollPane(footer);

        messagePanel.add(headerScrollPane);
        messagePanel.add(footerScrollPane);

        panels.setHeaderMessageTextArea(header);
        panels.setFooterMessageTextArea(footer);

        return messagePanel;
    }

    private JPanel messageUpdateFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        JButton submitButton = new JButton("변경하기");
        JButton cancelButton = new JButton("나가기");
        submitButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);
        cancelButton.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        footerPanel.add(submitButton);
        footerPanel.add(cancelButton);

        panels.setMessageUpdateDialogSubmitButton(submitButton);
        panels.setMessageUpdateDialogCancelButton(cancelButton);

        return footerPanel;
    }

}
