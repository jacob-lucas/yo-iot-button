package com.jacoblucas.yoIotButton.model;

import lombok.Data;

@Data
public class IotButtonEvent {
    private String serialNumber;
    private String batteryVoltage;
    private String clickType;

    public boolean isSingleClick() {
        return clickType.equals("SINGLE");
    }

    public boolean isDoubleClick() {
        return clickType.equals("DOUBLE");
    }

    public boolean isLongClick() {
        return clickType.equals("LONG");
    }
}
