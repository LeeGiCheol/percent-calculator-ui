package me.gicheol.main;

import me.gicheol.controller.PercentCalculatorController;


/**
 * 퍼센트 계산기 메인 호출
 */
public class Main {

    public static void main(String[] args) {
        PercentCalculatorController percentCalculatorController = new PercentCalculatorController();
        percentCalculatorController.callMainFrame();
    }

}
