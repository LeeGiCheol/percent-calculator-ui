package me.gicheol.controller;

import me.gicheol.common.CommonUtils;
import me.gicheol.domain.Panels;
import me.gicheol.ui.DrawUI;

import javax.swing.*;


/**
 * 메시지 수정 팝업 컨트롤러
 */
public class MessageUpdateDialogController extends JDialog {

    private final Panels panels;


    public MessageUpdateDialogController(Panels panels) {
        this.panels = panels;
    }


    /**
     * 메시지 수정 팝업 호출
     */
    public void callMessageUpdateDialog() {
        new DrawUI(panels).initMessageUpdateDialog(this);
        addListener();
    }


    /**
     * 메시지 수정 팝업 이벤트 리스너 등록
     */
    private void addListener() {
        CommonUtils.addListener(null, this, panels, "MessageUpdateDialog", "Button");
    }

}
