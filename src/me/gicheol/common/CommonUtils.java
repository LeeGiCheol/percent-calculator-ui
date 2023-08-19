package me.gicheol.common;

import me.gicheol.domain.Panels;
import me.gicheol.listener.ButtonClickEventListenerFactory;
import me.gicheol.listener.KeyInputListenerFactory;

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

public class CommonUtils {

    private final static String filePath = "/resources/message.txt";


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

    private static InputStreamReader getInputStreamReader(Path filePath) throws IOException {
        return new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8);
    }
    private static OutputStreamWriter getOutputStreamWriter(Path filePath) throws IOException {
        return new OutputStreamWriter(Files.newOutputStream(filePath), StandardCharsets.UTF_8);
    }

    private static Path getAbsolutePath() {
        return Paths.get(Paths.get("").toAbsolutePath() + CommonUtils.filePath);
    }

    private static Path getRelativePath(String filePath) {
        return Paths.get(filePath);
    }

    public static String addCommaFormat(String amount) {
        return amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")
                .split("\\.")[0];
    }
    public static String addCommaFormat(BigDecimal amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }

    public static String addCommaFormat(Integer amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }

    public static String addCommaFormat(Double amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }

    public static String addCommaFormat(Long amount) {
        return addCommaFormat(java.lang.String.valueOf(amount));
    }

    public static String addCommaWonFormat(Long amount) {
        return addCommaFormat(java.lang.String.valueOf(amount)) + "원";
    }
    public static String addCommaWonFormat(Double amount) {
        return addCommaFormat(amount) + "원";
    }

    public static String addCommaWonFormat(BigDecimal amount) {
        return addCommaFormat(amount.toString()) + "원";
    }



    public static BigDecimal getPercent(Long sumAmount, Long amountFee) {
        return BigDecimal.valueOf(sumAmount * amountFee / 100);
    }

    public static BigDecimal getPercent(String sumAmount, String amountFee) {
        return getPercent(Long.parseLong(sumAmount), Long.parseLong(amountFee));
    }
    public static BigDecimal getPercent(Long sumAmount, String amountFee) {
        return getPercent(sumAmount, Long.parseLong(amountFee));
    }
    public static BigDecimal getPercent(String sumAmount, Long amountFee) {
        return getPercent(Long.parseLong(sumAmount), amountFee);
    }


    public static BigDecimal getResultAmount(BigDecimal amountFee) {
        return amountFee.multiply(new BigDecimal("1.1"));
    }

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