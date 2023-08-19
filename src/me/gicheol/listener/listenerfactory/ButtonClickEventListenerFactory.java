package me.gicheol.listener.listenerfactory;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.listener.ButtonClickListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


/**
 * 버튼 클릭 이벤트 리스너 생성
 */
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


    /**
     * 버튼 클릭 이벤트 발생
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 이벤트가 아직 발생하지 않은 경우 이벤트 내용 전달
        if (Arrays.stream(params).noneMatch(e::equals)) {
            params[params.length-1] = e;
            paramTypes[paramTypes.length-1] = e.getClass();
        }

        CommonUtils.callEventMethod(ButtonClickListener.class, panels, eventMethodName, params, paramTypes);
    }

}
