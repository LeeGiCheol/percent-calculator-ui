package me.gicheol.controller;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.ui.DrawUI;

import javax.swing.*;

public class PercentCalculatorController extends JFrame {

    public PercentCalculatorController() {
        Panels panels = new Panels();
        CommonUtils.loadMessage(panels);

        DrawUI drawUI = new DrawUI(panels);
        drawUI.initMainPage(this);

        drawUI.addPanelToLayout(this, panels.getMainTopPanel(), panels.getMainMiddlePanel());

        addListener(panels);
    }


    private void addListener(Panels panels) {
        CommonUtils.addListener(this, null, panels, "Main", "Field");
        CommonUtils.addListener(this, null, panels, "Main", "Button");
    }

}