package me.gicheol.listener.listenerfactory;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.listener.KeyInputListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;


/**
 * 키보드 입력 이벤트 리스너 생성
 */
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


    /**
     * 키보드 입력 이벤트 발생
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if (e.getKeyCode() == 10) {
            panels.getMainSalesAddButton().doClick();
        }

        // 이벤트가 아직 발생하지 않은 경우 이벤트 내용 전달
        if (Arrays.stream(params).noneMatch(e::equals)) {
            params[params.length-1] = e;
            paramTypes[paramTypes.length-1] = e.getClass();
        }

        CommonUtils.callEventMethod(KeyInputListener.class, panels, eventMethodName, params, paramTypes);
    }



}
