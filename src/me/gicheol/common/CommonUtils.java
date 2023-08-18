package me.gicheol.common;

import me.gicheol.domain.Panels;
import me.gicheol.domain.Status;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonUtils {

    public static void loadMessage(Panels panels) {
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath() + "/resources/message.txt"))) {
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

    public static void saveMessage(Panels panels, Status status) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.get("").toAbsolutePath() + "/resources/message.txt"))) {
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

    public static String addCommaFormat(String amount) {
        return amount.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")
                .split("\\.")[0];
    }
    public static String addCommaFormat(BigDecimal amount) {
        return addCommaFormat(String.valueOf(amount));
    }

    public static String addCommaFormat(Integer amount) {
        return addCommaFormat(String.valueOf(amount));
    }

    public static String addCommaFormat(Double amount) {
        return addCommaFormat(String.valueOf(amount));
    }

    public static String addCommaFormat(Long amount) {
        return addCommaFormat(String.valueOf(amount));
    }

    public static String addCommaWonFormat(Long amount) {
        return addCommaFormat(String.valueOf(amount)) + "원";
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
}
