package me.gicheol;


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;

public class JDatePickerEx extends JFrame {
    public JDatePickerEx() {
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        add(datePicker);
        setBounds(300,300,400,400);
        setVisible(true);

//
//        String[] date = planeDTO.getDate().split("-");
//        int dateY = Integer.parseInt(date[0]);
//        int dateM = Integer.parseInt(date[1]) - 1;
//        int dateD = Integer.parseInt(date[2]);
//        model.setDate(dateY, dateM, dateD);
//        model.setSelected(true);
    }

    public static void main(String[] args) {
        new JDatePickerEx();
    }
}
