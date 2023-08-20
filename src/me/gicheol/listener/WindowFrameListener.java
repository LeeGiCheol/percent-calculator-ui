package me.gicheol.listener;

import me.gicheol.domain.Panels;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowFrameListener implements WindowListener {

    private Panels panels;

    public WindowFrameListener(Panels panels) {
        this.panels = panels;
    }


    public void windowOpened(WindowEvent e) {
        panels.setShowDatePicker(true);
    }
    public void windowClosing(WindowEvent e) {
        panels.setShowDatePicker(false);
    }
    public void windowClosed(WindowEvent e) {
        panels.setShowDatePicker(false);
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

}
