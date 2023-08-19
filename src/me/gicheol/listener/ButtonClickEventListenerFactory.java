package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonClickEventListenerFactory implements ActionListener {

    private final Panels panels;
    private String eventMethodName;
    private Object[] params = new Object[1];
    private Class<?>[] paramTypes = new Class<?>[1];


    public ButtonClickEventListenerFactory(Panels panels) {
        this.panels = panels;
    }

    public ButtonClickEventListenerFactory(Panels panels, String eventMethodName) {
        this.panels = panels;
        this.eventMethodName = eventMethodName;
    }

    public ButtonClickEventListenerFactory(Panels panels, String eventMethodName, Object... reqParams) {
        this.panels = panels;
        this.eventMethodName = eventMethodName;

        int length = params.length;
        if (reqParams.length != 0) {
            params = Arrays.copyOf(params, length +1);
            paramTypes = Arrays.copyOf(paramTypes, paramTypes.length+1);
        }

        for (int i = 0; i < length; i++) {
            params[i] = reqParams[i];
            paramTypes[i] = params[i].getClass();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (Arrays.stream(params).noneMatch(e::equals)) {
            params[params.length-1] = e;
            paramTypes[paramTypes.length-1] = e.getClass();
        }

        CommonUtils.callEventMethod(ButtonClickListener.class, panels, eventMethodName, params, paramTypes);
    }

}
