package br.com.santander.telemetria.core.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Wither @AllArgsConstructor @NoArgsConstructor
public class Dispositivo {

    private String apelido;
    private String idDispositivo;
    private String idFornecedor;
    private String imei;
    private String tipoDispositivo;
}
