package me.gicheol.controller;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.listener.ButtonClickEventListenerFactory;
import me.gicheol.ui.DrawUI;

import javax.swing.*;

public class MessageUpdateDialogController extends JDialog {

    private final Panels panels;


    public MessageUpdateDialogController(Panels panels) {
        this.panels = panels;
    }


    public void callMessageUpdateDialog() {
        new DrawUI(panels).initMessageUpdateDialog(this);
        addListener();
    }

    private void addListener() {
        CommonUtils.addListener(null, this, panels, "MessageUpdateDialog", "Button");
    }

    private ButtonClickEventListenerFactory addButtonEventListener(String eventMethodName) {
        return new ButtonClickEventListenerFactory(panels, eventMethodName, this);
    }
}
