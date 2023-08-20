package me.gicheol.listener;

import me.gicheol.controller.CalendarController;
import me.gicheol.domain.FontFactory;
import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickListener extends MouseAdapter {

    private Panels panels;


    public MouseClickListener(Panels panels) {
        this.panels = panels;
    }


    /**
     * 마우스 클릭 이벤트
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (e.getSource() == panels.getStartCalendar()) {
            startCalendarEvent();
        } else {
            calendarDayClickEvent(e);
        }
    }


    /**
     * Calendar 생성 이벤트
     */
    private void startCalendarEvent() {
        if (!panels.isShowDatePicker()) {
            CalendarController calendarController = new CalendarController(panels);
            calendarController.callCalendar();
        }
    }


    /**
     * Calendar 날짜 클릭 이벤트 (클릭 효과)
     * @param e
     */
    private void calendarDayClickEvent(MouseEvent e) {
        JLabel mouseClick = (JLabel) e.getSource();
        if (panels.getSelectedDay() != mouseClick) {
            panels.getSelectedDay().setFont(FontFactory.getFont(Font.PLAIN, 5));
            mouseClick.setFont(FontFactory.BOLD_FONT_SIZE_20);
            panels.setSelectedDay(mouseClick);
        }
    }

}
