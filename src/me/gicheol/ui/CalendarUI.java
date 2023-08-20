package me.gicheol.ui;

import me.gicheol.domain.Panels;

import javax.swing.*;
import java.awt.*;

public class CalendarUI {

    private JFrame frame;
    private Panels panels;


    public CalendarUI(JFrame frame, Panels panels) {
        this.frame = frame;
        this.panels = panels;
    }


    /**
     * Calendar 호출
     * @param year
     * @param month
     */
    public void initCalendar(int year, int month) {
        initTopPanel(year, month);
        initDaysPanel();
    }


    /**
     * 상단 패널 생성
     * @param year
     * @param month
     * @return
     */
    private JPanel initTopPanel(int year, int month) {
        JPanel topPanel = new JPanel();

        frame.add(BorderLayout.NORTH, topPanel);
        topPanel.setLayout(new FlowLayout());
        topPanel.setSize(300,400);

        JButton lastMonth = new JButton("◀");
        topPanel.add(lastMonth);


        JComboBox<Integer> yearCombo = getIntegerJComboBox(year - 5, year + 5, year);
        topPanel.add(yearCombo);

        JLabel yearLabel = new JLabel("년 ");
        topPanel.add(yearLabel);

        JComboBox<Integer> monthCombo = getIntegerJComboBox(1, 12, month);
        topPanel.add(monthCombo);

        JLabel monthLabel = new JLabel("월");
        JButton nextMonth = new JButton("▶");
        JButton submitButton = new JButton("선택");
        topPanel.add(monthLabel);
        topPanel.add(nextMonth);
        topPanel.add(submitButton);
        topPanel.setBackground(new Color(0,210,180));

        panels.setYearCombo(yearCombo);
        panels.setMonthCombo(monthCombo);
        panels.setSelectedDay(new JLabel());
        panels.setNextMonth(nextMonth);
        panels.setLastMonth(lastMonth);
        panels.setCalendarSubmitButton(submitButton);

        return topPanel;
    }


    /**
     * 달력 패널 생성
     */
    private void initDaysPanel() {
        JPanel center = new JPanel(new BorderLayout());
        frame.add("Center", center);

        JPanel cntNorth = new JPanel(new GridLayout(0,7));
        center.add("North",cntNorth);

        String[] days = {"일","월","화","수","목","금","토"};
        for(int i = 0; i < days.length; i++) {
            JLabel dayOfWeek = new JLabel(days[i], JLabel.CENTER);
            if (i == 0) {
                dayOfWeek.setForeground(Color.red);
            } else if (i == 6)  {
                dayOfWeek.setForeground(Color.blue);
            }
            cntNorth.add(dayOfWeek);
        }

        JPanel cntCenter = new JPanel(new GridLayout(0,7));
        panels.setCntCenter(cntCenter);

        center.add("Center",cntCenter);
    }


    /**
     * 프레임 기본 설정
     */
    public void setDefaultSetup() {
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    /**
     * Integer JComboBox 생성
     * @param start
     * @param end
     * @param date
     * @return
     */
    private JComboBox<Integer> getIntegerJComboBox(int start, int end, int date) {
        DefaultComboBoxModel<Integer> model = createComboBoxModel(start, end);
        JComboBox<Integer> combo = new JComboBox<>();
        combo.setModel(model);
        combo.setSelectedItem(date);
        return combo;
    }


    /**
     * ComboBox 생성
     * @param start
     * @param end
     * @return
     */
    private DefaultComboBoxModel<Integer> createComboBoxModel(int start, int end) {
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (int i = start; i <= end; i++) {
            model.addElement(i);
        }
        return model;
    }

}

