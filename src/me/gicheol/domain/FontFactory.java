package me.gicheol.domain;

import java.awt.*;

public class FontFactory {

    public static final Font BOLD_FONT_SIZE_20 = new Font("맑은 고딕", Font.BOLD, 20);
    public static final Font PLAIN_FONT_SIZE_20 = new Font("맑은 고딕", Font.PLAIN, 20);
    public static final Font PLAIN_FONT_SIZE_30 = new Font("맑은 고딕", Font.PLAIN, 30);

    public static Font getFont(int fontWeight, int fontSize) {
        return new Font("맑은 고딕", fontWeight, fontSize);
    }

}
