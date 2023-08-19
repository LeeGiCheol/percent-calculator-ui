package me.gicheol.listener;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class KeyInputListenerFactory extends KeyAdapter {

    private Panels panels;
    private String eventMethodName;

    private Object[] params = new Object[1];
    private Class<?>[] paramTypes = new Class<?>[1];

    public KeyInputListenerFactory(Panels panels, String eventMethodName) {
        super();
        this.panels = panels;
        this.eventMethodName = eventMethodName;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if (e.getKeyCode() == 10) {
            panels.getMainSalesAddButton().doClick();
        }

        if (Arrays.stream(params).noneMatch(e::equals)) {
            params[params.length-1] = e;
            paramTypes[paramTypes.length-1] = e.getClass();
        }

        CommonUtils.callEventMethod(KeyInputListener.class, panels, eventMethodName, params, paramTypes);
    }



}
