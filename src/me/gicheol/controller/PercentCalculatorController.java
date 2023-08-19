package me.gicheol.controller;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.ui.DrawUI;

import javax.swing.*;


/**
 * 메인 퍼센트 계산기 컨트롤러
 */
public class PercentCalculatorController extends JFrame {


    /**
     * 퍼센트 계산기 호출
     */
    public void callMainFrame() {
        Panels panels = new Panels();
        CommonUtils.loadMessage(panels);

        DrawUI drawUI = new DrawUI(panels);
        drawUI.initMainPage(this);

        drawUI.addPanelToLayout(this, panels.getMainTopPanel(), panels.getMainMiddlePanel());

        addListener(panels);
    }


    /**
     * 퍼센트 계산기 이벤트 리스너 등록
     * @param panels
     */
    private void addListener(Panels panels) {
        CommonUtils.addListener(this, null, panels, "Main", "Field");
        CommonUtils.addListener(this, null, panels, "Main", "Button");
    }

}