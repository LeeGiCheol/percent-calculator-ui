package me.gicheol.listener;

import me.gicheol.domain.Panels;
import me.gicheol.ui.CalendarUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickListener extends MouseAdapter {

    private Panels panels;


    public MouseClickListener(Panels panels) {
        this.panels = panels;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        CalendarUI calendarUI = new CalendarUI(panels);
        calendarUI.callCalendar();
    }

}
