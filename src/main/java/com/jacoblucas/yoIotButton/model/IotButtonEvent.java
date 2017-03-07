package com.jacoblucas.yoIotButton.model;

import lombok.Data;

@Data
public class IotButtonEvent {
    private String serialNumber;
    private String batteryVoltage;
    private String clickType;
}
