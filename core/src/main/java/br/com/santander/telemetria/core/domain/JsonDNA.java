package br.com.santander.telemetria.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.util.List;
import java.util.Optional;

@Wither @AllArgsConstructor @NoArgsConstructor
public class JsonDNA {

    private String CellTowerId;
    private String Compromised;
    private String DeviceModel;
    private String DeviceSystemName;
    private String DeviceSystemVersion;
    private String Emulator;
    private Optional<List<GeoLocationInfo>> GeoLocationInfo;
    private String HardwareID;
    private String Languages;
    private String LocationAreaCode;
    private String MCC;
    private String MNC;
    private String MultitaskingSupported;
    private String OS_ID;
    private String otherId;
    private String PhoneNumber;
    private String RSA_ApplicationKey;
    private String SDK_VERSION;
    private String SIM_ID;
    private String ScreenSize;
    private String WiFiMacAddress;
    private WiFiNetworksData WiFiNetworksData;
}
