package com.jacoblucas.yo;

import lombok.Data;

@Data
class IotButtonEvent {
    private String serialNumber;
    private String batteryVoltage;
    private String clickType;
}
