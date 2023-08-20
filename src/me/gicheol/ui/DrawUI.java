package me.gicheol.ui;

import me.gicheol.domain.FontFactory;
import me.gicheol.domain.Panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static me.gicheol.domain.CustomComponent.*;


/**
 * UI 생성
 */
public class DrawUI {

    private final Panels panels;
    private final java.util.List<String> layoutLocation;
    private final String faviconPath = "/resources/favicon.ico";

    public DrawUI(Panels panels) {
        this.panels = panels;
        this.layoutLocation = setLayoutLocation();
    }


    /**
     * 메인 페이지 생성
     * @param jFrame
     */
    public void initMainPage(JFrame jFrame) {
        setLookAndFeel("Nimbus");

        setDefaultPanels();
        initTopPanel();
        initMiddlePanel();

        setFaviconImage(jFrame);

        jFrame.setSize(2000,1200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }


    /**
     * UI 타입 설정
     * @param uiType
     */
    private void setLookAndFeel(String uiType) {
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


    /**
     * Frame 파비콘 설정
     * TODO: 현재 설정 안되는데 원인 파악 필요
     * @param jframe
     */
    private void setFaviconImage(JFrame jframe) {
        ImageIcon favicon = getImageIconOrigin("/favicon.png");
        jframe.setIconImage(favicon.getImage());
    }


    private ImageIcon getImageIconOrigin(String imagePath) {
        return new ImageIcon(getClass().getResource(imagePath));
    }

    /**
     * 이미지 가져오기
     * @param imagePath
     * @return
     */
    private ImageIcon getImageIcon(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }


    /**
     * 판넬 기본 설정
     * @return
     */
    public Panels setDefaultPanels() {
        JTextField salesAmountField = createJTextField(FontFactory.PLAIN_FONT_SIZE_20);
        JTextField salesAmountFeeField = createJTextField(FontFactory.PLAIN_FONT_SIZE_20);
        JTextField companyField = createJTextField(FontFactory.PLAIN_FONT_SIZE_20);
        JTextField datePickerText = createJTextField(FontFactory.PLAIN_FONT_SIZE_20);
        JLabel startCalendar = new JLabel();
        startCalendar.setIcon(getImageIcon("/calendar.png", 30, 30));

        panels.setMainSalesAmountField(salesAmountField);
        panels.setMainSalesAmountFeeField(salesAmountFeeField);
        panels.setCompanyField(companyField);
        panels.setMainDatePickerField(datePickerText);
        panels.setStartCalendar(startCalendar);

        JFileChooser saveFileChooser = new JFileChooser();
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        saveFileChooser.setMultiSelectionEnabled(false);
        panels.setMainSaveFileChooser(saveFileChooser);

        return panels;
    }


    /**
     * 판넬 기본 레이아웃 등록
     * @return
     */
    public java.util.List<String> setLayoutLocation() {
        java.util.List<String> location = new ArrayList<>();
        location.add(BorderLayout.NORTH);
        location.add(BorderLayout.CENTER);
        location.add(BorderLayout.EAST);
        return location;
    }


    /**
     * 판넬 레이아웃 설정
     * @param jFrame
     * @param panels
     */
    public void addPanelToLayout(JFrame jFrame, JPanel... panels) {
        for (int i = 0; i < panels.length; i++) {
            jFrame.add(layoutLocation.get(i), panels[i]);
        }
    }


    /**
     * 상단 판넬 설정
     * topText: 매출, 매출 수수료, 업체명
     * topCalendar: 날짜 입력, Calendar
     * topButton: 매출 입력, 결과
     * buttonRow(topButton에 포함): 선택 매출 목록 삭제, 매출 목록 전체 삭제, 기록 전체 삭제, 히스토리 보기, 메시지 수정하기
     * saveFileButton(판넬 없음): 파일로 저장하기
     * topPanelCombination: 위 판넬, 버튼 합치기
     * resultLabel: 매출 목록, 결과, 검증
     */
    public void initTopPanel() {
        initTopText();
        initTopCalendar();
        initTopButton();
        initButtonRow();
        initSaveFileButton();
        initTopPanelCombination();
        initResultLabel();
    }


    /**
     * 상단 텍스트 필드 판넬 설정
     */
    private void initTopText() {
        JLabel amountLabel = createJLabel(" 매출 :", FontFactory.BOLD_FONT_SIZE_20);
        JTextField salesAmountField = panels.getMainSalesAmountField();
        JLabel salesAmountFee = createJLabel(" 매출 수수료 :", FontFactory.BOLD_FONT_SIZE_20);
        JTextField salesAmountFeeField = panels.getMainSalesAmountFeeField();
        JLabel companyLabel = createJLabel(" 업체명 :", FontFactory.BOLD_FONT_SIZE_20);
        JTextField companyField = panels.getCompanyField();

        JPanel topTextPanel = this.panels.getMainTopTextPanel();
        topTextPanel.add(amountLabel);
        topTextPanel.add(salesAmountField);
        topTextPanel.add(salesAmountFee);
        topTextPanel.add(salesAmountFeeField);
        topTextPanel.add(companyLabel);
        topTextPanel.add(companyField);

        panels.setMainSalesAmountField(salesAmountField);
        panels.setMainSalesAmountFeeField(salesAmountFeeField);
        panels.setCompanyField(companyField);
    }


    /**
     * 상단 Calendar 관련 설정
     */
    private void initTopCalendar() {
        JPanel topTextPanel = panels.getMainTopTextPanel();

        JLabel datePickerLabel = createJLabel(" 날짜 입력 :", FontFactory.BOLD_FONT_SIZE_20);

        JLabel startCalendar = panels.getStartCalendar();

        topTextPanel.add(datePickerLabel);
        topTextPanel.add(panels.getMainDatePickerField());
        topTextPanel.add(startCalendar);
    }


    /**
     * 상단 버튼 판넬 설정 (최 상단)
     */
    private void initTopButton() {
        JButton salesAddButton = createJButton("매출 입력", FontFactory.BOLD_FONT_SIZE_20);
        JButton submitButton = createJButton("결과", FontFactory.BOLD_FONT_SIZE_20);

        JPanel topTextPanel = this.panels.getMainTopTextPanel();

        topTextPanel.add(salesAddButton);
        topTextPanel.add(submitButton);

        this.panels.setMainSalesAddButton(salesAddButton);
        this.panels.setMainSubmitButton(submitButton);
    }


    /**
     * 상단 버튼 Row 설정 (상단 두 번째)
     */
    private void initButtonRow() {
        JButton selectResultRemoveButton = createJButton("선택 매출 목록 삭제", FontFactory.PLAIN_FONT_SIZE_20);
        JButton allResultRemoveButton = createJButton("매출 목록 전체 삭제", FontFactory.PLAIN_FONT_SIZE_20);
        JButton allRemoveButton = createJButton("기록 전체 삭제", FontFactory.PLAIN_FONT_SIZE_20);
        JButton historyButton = createJButton("히스토리 보기", FontFactory.PLAIN_FONT_SIZE_20);
        JButton messageUpdateButton = createJButton("메시지 수정하기", FontFactory.PLAIN_FONT_SIZE_20);

        JPanel topButtonPanel = this.panels.getMainTopButtonPanel();
        topButtonPanel.add(selectResultRemoveButton);
        topButtonPanel.add(allResultRemoveButton);
        topButtonPanel.add(allRemoveButton);
        topButtonPanel.add(historyButton);
        topButtonPanel.add(messageUpdateButton);

        this.panels.setMainSelectResultRemoveButton(selectResultRemoveButton);
        this.panels.setMainAllResultRemoveButton(allResultRemoveButton);
        this.panels.setMainAllRemoveButton(allRemoveButton);
        this.panels.setMainHistoryButton(historyButton);
        this.panels.setMainMessageUpdateButton(messageUpdateButton);
    }


    /**
     * 파일로 저장하기 버튼 설정 (세 번째)
     */
    private void initSaveFileButton() {
        JButton saveFileButton = createJButton("파일로 저장하기", FontFactory.PLAIN_FONT_SIZE_20);
        this.panels.setMainSaveFileButton(saveFileButton);
    }


    /**
     * topPanel 합치기
     */
    private void initTopPanelCombination() {
        JPanel topPanel = this.panels.getMainTopPanel();

        topPanel.add(this.panels.getMainTopTextPanel());
        topPanel.add(this.panels.getMainTopButtonPanel());
        topPanel.add(this.panels.getMainSaveFileButton());
        topPanel.add(new JPanel(new GridLayout(1, 1)));
    }


    /**
     * 매출 목록, 결과, 검증 라벨 설정
     */
    private void initResultLabel() {
        JPanel resultLabelPanel = new JPanel(new GridLayout(1, 1));

        JLabel amountListLabel = createJLabel("매출 목록", FontFactory.BOLD_FONT_SIZE_20);
        JLabel resultTextAreaLabel = createJLabel("결과", FontFactory.BOLD_FONT_SIZE_20);
        JLabel resultTextAreaVerificationLabel = createJLabel("검증", FontFactory.BOLD_FONT_SIZE_20);

        resultLabelPanel.add(amountListLabel);
        resultLabelPanel.add(resultTextAreaLabel);
        resultLabelPanel.add(resultTextAreaVerificationLabel);

        this.panels.getMainTopPanel().add(resultLabelPanel);
    }


    /**
     * 중간 판넬 설정
     * textAreaPanel: 매출 목록(List), 결과, 검증
     */
    public void initMiddlePanel() {
        this.panels.getMainMiddlePanel().add(initTextAreaPanel());
    }


    /**
     * textArea 설정
     * 매출 목록은 List
     * @return
     */
    private JPanel initTextAreaPanel() {
        JPanel textAreaPanel = new JPanel(new GridLayout(1, 1));

        List amountList = this.panels.getSalesAmountList();
        amountList.setFont(FontFactory.PLAIN_FONT_SIZE_20);

        JTextArea resultTextArea = createJTextArea(FontFactory.PLAIN_FONT_SIZE_20, Color.GRAY);
        JTextArea resultTextAreaVerification = createJTextArea(FontFactory.PLAIN_FONT_SIZE_20, Color.GRAY);
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


    /**
     * 메시지 수정하기 팝업 생성
     * MessageUpdateDialog
     * @param jDialog
     */
    public void initMessageUpdateDialog(JDialog jDialog) {
        jDialog.add(BorderLayout.BEFORE_FIRST_LINE, messageUpdateDialogTopLabel());
        jDialog.add(BorderLayout.CENTER, messageUpdateDialogMessagePanel());
        jDialog.add(BorderLayout.SOUTH, messageUpdateFooterPanel());
        jDialog.setSize(1200, 500);
        jDialog.setVisible(true);
    }


    /**
     * 메시지 수정하기 팝업 상단 판넬: 상단 메시지(Label), 하단 메시지(Label)
     * @return
     */
    private JPanel messageUpdateDialogTopLabel() {
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        JLabel headerLabel = createJLabel("상단 메시지", FontFactory.PLAIN_FONT_SIZE_20);
        JLabel footerLabel = createJLabel("하단 메시지", FontFactory.PLAIN_FONT_SIZE_20);

        labelPanel.add(headerLabel);
        labelPanel.add(footerLabel);

        return labelPanel;
    }


    /**
     * 메시지 수정하기 팝업 메시지 판넬: 상단 메시지(TextArea), 하단 메시지(TextArea)
     * @return
     */
    private JPanel messageUpdateDialogMessagePanel() {
        JPanel messagePanel = new JPanel(new GridLayout(1, 2));
        JTextArea header = createJTextArea(FontFactory.getFont(Font.PLAIN, 15), Color.GRAY);
        JTextArea footer = createJTextArea(FontFactory.getFont(Font.PLAIN, 15), Color.GRAY);

        header.append(panels.getHeaderMessage());
        footer.append(panels.getFooterMessage());


        JScrollPane headerScrollPane = new JScrollPane(header);
        JScrollPane footerScrollPane = new JScrollPane(footer);

        messagePanel.add(headerScrollPane);
        messagePanel.add(footerScrollPane);

        panels.setHeaderMessageTextArea(header);
        panels.setFooterMessageTextArea(footer);

        return messagePanel;
    }


    /**
     * 메시지 수정하기 팝업 하단 판넬: 변경하기, 나가기
     * @return
     */
    private JPanel messageUpdateFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        JButton submitButton = createJButton("변경하기", FontFactory.PLAIN_FONT_SIZE_20);
        JButton cancelButton = createJButton("나가기", FontFactory.PLAIN_FONT_SIZE_20);

        footerPanel.add(submitButton);
        footerPanel.add(cancelButton);

        panels.setMessageUpdateDialogSubmitButton(submitButton);
        panels.setMessageUpdateDialogCancelButton(cancelButton);

        return footerPanel;
    }

}
