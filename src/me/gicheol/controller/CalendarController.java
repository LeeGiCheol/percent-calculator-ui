package me.gicheol.controller;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.listener.WindowFrameListener;
import me.gicheol.listener.listenerfactory.ButtonClickEventListenerFactory;
import me.gicheol.ui.CalendarUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarController extends JFrame {

    private Panels panels;

    public CalendarController(Panels panels) throws HeadlessException {
        this.panels = panels;
    }


    /**
     * Calendar 호출
     */
    public void callCalendar() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        CalendarUI ui = new CalendarUI(this, panels);
        ui.initCalendar(year, month);

        CommonUtils.dayPrint(panels, year, month);
        addListener();

        ui.setDefaultSetup();
    }

    /**
     * Listener 등록
     */
    private void addListener() {
        panels.getYearCombo().addActionListener(new ButtonClickEventListenerFactory(panels, "comboActionEvent"));
        panels.getMonthCombo().addActionListener(new ButtonClickEventListenerFactory(panels, "comboActionEvent"));
        panels.getLastMonth().addActionListener(new ButtonClickEventListenerFactory(panels, "lastMonthClickEvent"));
        panels.getNextMonth().addActionListener(new ButtonClickEventListenerFactory(panels, "nextMonthClickEvent"));
        panels.getCalendarSubmitButton().addActionListener(new ButtonClickEventListenerFactory(panels, "calendarSubmitClickEvent", this));
        addWindowListener(new WindowFrameListener(panels));
    }
}
