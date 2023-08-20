package me.gicheol.common;

import me.gicheol.domain.Panels;
import me.gicheol.listener.listenerfactory.ButtonClickEventListenerFactory;
import me.gicheol.listener.listenerfactory.KeyInputListenerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 공통 유틸리티
 */
public class CommonUtils {

    /**
     * 메시지 저장 파일 경로
     */
    private final static String filePath = "/resources/message.txt";


    /**
     * 메시지 불러오기
     * @param panels
     */
    public static void loadMessage(Panels panels) {
        try (BufferedReader br = new BufferedReader(getInputStreamReader(getAbsolutePath()))) {
            int lineNum = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                if (lineNum == 0) {
                    panels.setHeaderMessage(line.split(":")[1]);
                } else if (lineNum == 1) {
                    panels.setFooterMessage(line.split(":")[1]);
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.out.println("READ_ERROR " + e.getMessage());
        }
    }


    /**
     * 메시지 저장
     * @param panels
     */
    public static void saveMessage(Panels panels) {
        try (BufferedWriter bw = new BufferedWriter(getOutputStreamWriter(getAbsolutePath()))) {
            String writeMessage =
                    "HEADER:" +
                    panels.getHeaderMessage() +
                    "\n" +
                    "FOOTER:" +
                    panels.getFooterMessage();
            bw.write(writeMessage);
            bw.flush();
        } catch (IOException e) {
            System.out.println("WRITE_ERROR " + e.getMessage());
        }
    }


    /**
     * 히스토리 파일 저장
     * @param panels
     * @param filePath
     */
    public static void saveFile(Panels panels, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(getOutputStreamWriter(getRelativePath(filePath)))) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < panels.getHistory().size(); i++) {
                result.append(java.lang.String.format("[%d] ", i+1)).append("\n").append(panels.getHistory().get(i)).append("\n\n");
            }

            bw.write(result.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("WRITE_ERROR " + e.getMessage());
        }
    }


    /**
     * InputStreamReader 생성
     * @param filePath
     * @return
     * @throws IOException
     */
    private static InputStreamReader getInputStreamReader(Path filePath) throws IOException {
        return new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8);
    }


    /**
     * OutputStreamReader 생성
     * @param filePath
     * @return
     * @throws IOException
     */
    private static OutputStreamWriter getOutputStreamWriter(Path filePath) throws IOException {
        return new OutputStreamWriter(Files.newOutputStream(filePath), StandardCharsets.UTF_8);
    }


    /**
     * 상대경로 생성
     * @return
     */
    private static Path getAbsolutePath() {
        return Paths.get(Paths.get("").toAbsolutePath() + CommonUtils.filePath);
    }


    /**
     * 절대경로 생성
     * @param filePath
     * @return
     */
    private static Path getRelativePath(String filePath) {
        return Paths.get(filePath);
    }


    /**
     * String 숫자 포맷에 콤마 추가
     * @param amount
     * @return
     */
    public static String addCommaFormat(String amount) {
        return amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")
                .split("\\.")[0];
    }


    /**
     * BigDecimal 숫자 포맷에 콤마 추가
     * @param amount
     * @return
     */
    public static String addCommaFormat(BigDecimal amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }


    /**
     * Long 숫자 포맷에 콤마 추가
     * @param amount
     * @return
     */
    public static String addCommaFormat(Long amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }


    /**
     * Integer 숫자 포맷에 콤마 추가
     * @param amount
     * @return
     */
    public static String addCommaFormat(Integer amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }


    /**
     * Double 숫자 포맷에 콤마 추가
     * @param amount
     * @return
     */
    public static String addCommaFormat(Double amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }


    /**
     * BigDecimal 숫자 포맷에 콤마와 원 글자 추가
     * @param amount
     * @return
     */
    public static String addCommaWonFormat(BigDecimal amount) {
        return addCommaFormat(amount.toString()) + "원";
    }


    /**
     * Long 숫자 포맷에 콤마와 원 글자 추가
     * @param amount
     * @return
     */
    public static String addCommaWonFormat(Long amount) {
        return addCommaFormat(java.lang.String.valueOf(amount)) + "원";
    }


    /**
     * Double 숫자 포맷에 콤마와 원 글자 추가
     * @param amount
     * @return
     */
    public static String addCommaWonFormat(Double amount) {
        return addCommaFormat(amount) + "원";
    }


    /**
     * 매출 수수료 정산 금액 구하기
     * @param sumAmount
     * @param amountFee
     * @return
     */
    public static BigDecimal calculateFeeAmount(Long sumAmount, Long amountFee) {
        return BigDecimal.valueOf(sumAmount * amountFee / 100);
    }


    /**
     * 매출 수수료 정산 금액 구하기
     * @param sumAmount
     * @param amountFee
     * @return
     */
    public static BigDecimal calculateFeeAmount(String sumAmount, String amountFee) {
        return calculateFeeAmount(Long.parseLong(sumAmount), Long.parseLong(amountFee));
    }


    /**
     * 매출 수수료 정산 금액 구하기
     * @param sumAmount
     * @param amountFee
     * @return
     */
    public static BigDecimal calculateFeeAmount(Long sumAmount, String amountFee) {
        return calculateFeeAmount(sumAmount, Long.parseLong(amountFee));
    }


    /**
     * 매출 수수료 정산 금액 구하기
     * @param sumAmount
     * @param amountFee
     * @return
     */
    public static BigDecimal calculateFeeAmount(String sumAmount, Long amountFee) {
        return calculateFeeAmount(Long.parseLong(sumAmount), amountFee);
    }


    /**
     * 부가세 계산
     * @param amountFee
     * @return
     */
    public static BigDecimal calculateVAT(BigDecimal amountFee) {
        return amountFee.multiply(new BigDecimal("1.1"));
    }


    /**
     * 금액 포맷팅
     * @param fieldText
     * @return
     */
    public static String formattingFieldText(String fieldText) {
        if (fieldText.isEmpty()) {
            return "";
        }

        char text = fieldText.charAt(fieldText.length() - 1);

        if (!Character.isDigit(text)) {
            return fieldText.substring(0, fieldText.length() - 1);
        } else if (fieldText.length() >= 4) {
            String amount = fieldText.replaceAll(",", "");
            return CommonUtils.addCommaFormat(amount);
        } else {
            return fieldText;
        }
    }

    public static String formattingFeeFieldText(String textField) {
        String replaceFeeFieldText = textField;
        if (textField.contains("%")) {
            replaceFeeFieldText = textField.replaceAll("%", "");
        }
        return formattingFieldText(replaceFeeFieldText) + "%";
    }


    /**
     * 파라미터가 없는 이벤트 메서드 호출
     * @param callClazz
     * @param panels
     * @param eventMethodName
     */
    public static void callEventMethod(Class<?> callClazz, Panels panels, String eventMethodName) {
        try {
            Object clazz = callClazz.getConstructor(panels.getClass()).newInstance(panels);
            Method method = clazz.getClass().getDeclaredMethod(eventMethodName);
            method.setAccessible(true);
            method.invoke(clazz);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * 파라미터가 있는 이벤트 메서드 호출
     * @param callClazz
     * @param panels
     * @param eventMethodName
     * @param params
     * @param paramType
     */
    public static void callEventMethod(Class<?> callClazz, Panels panels, String eventMethodName, Object[] params, Class<?>[] paramType) {
        try {
            Object clazz = callClazz.getConstructor(panels.getClass()).newInstance(panels);
            Method method = clazz.getClass().getDeclaredMethod(eventMethodName, paramType);
            method.setAccessible(true);
            method.invoke(clazz, params);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * 이벤트 리스너 추가
     * Panels에 추가되어 있는 변수 중
     * uiName 를 포함하고, eventType으로 끝나는 변수를 찾기 위해
     * getter Method를 통해 조회 후 Invoke
     *
     * @param frame
     * @param dialog
     * @param panels
     * @param uiName (Main: PercentCalculator, MessageUpdateDialog: MessageUpdateDialog)
     * @param eventType (Field, Button)
     */
    public static void addListener(JFrame frame, JDialog dialog, Panels panels, String uiName, String eventType) {
        Method[] panelMethods = panels.getClass().getDeclaredMethods();

        for (Method panelMethod : panelMethods) {
            String methodName = panelMethod.getName();
            if (methodName.startsWith("get") && methodName.contains(uiName) &&
                    methodName.lastIndexOf(eventType) == methodName.length() - eventType.length()) {

                if (methodName.contains(eventType)) {
                    try {
                        panelMethod.setAccessible(false);
                        String eventNameAppend = "";
                        if (eventType.equals("Button")) {
                            eventNameAppend = "ClickEvent";
                            String eventMethodName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4) + eventNameAppend;
                            AbstractButton component = (AbstractButton) panelMethod.invoke(panels);
                            if (frame != null) {
                                component.addActionListener(new ButtonClickEventListenerFactory(panels, eventMethodName, frame));
                            } else if (dialog != null) {
                                component.addActionListener(new ButtonClickEventListenerFactory(panels, eventMethodName, dialog));
                            }
                        } else if (eventType.equals("Field")) {
                            eventNameAppend = "InputEvent";
                            String eventMethodName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4) + eventNameAppend;
                            Component component = (Component) panelMethod.invoke(panels);
                            component.addKeyListener(new KeyInputListenerFactory(panels, eventMethodName));
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}