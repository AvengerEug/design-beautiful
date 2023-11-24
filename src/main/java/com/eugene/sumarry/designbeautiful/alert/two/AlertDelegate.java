package com.eugene.sumarry.designbeautiful.alert.two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muyang
 * @create 2023/11/24 21:21
 */
public class AlertDelegate {

    private List<AbstractAlertHandler> alertHandlers;

    public void initBeans() {
        Notification notification = new Notification();

        alertHandlers = new ArrayList<AbstractAlertHandler>();
        alertHandlers.add(new ErrorCountAlertHandler(notification));
        alertHandlers.add(new SuccessCountAlertHandler(notification));
        alertHandlers.add(new ErrorCountAlertHandler(notification));

    }

    public void check(AlertRequest alertRequest) {
        for (AbstractAlertHandler alertHandler : alertHandlers) {
            alertHandler.process(alertRequest);
        }
    }





}
