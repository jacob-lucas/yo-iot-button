package com.jacoblucas.yo;

import lombok.Data;

@Data
class IotButtonRequest {
    private String serialNumber;
    private String batteryVoltage;
    private String clickType;
}
