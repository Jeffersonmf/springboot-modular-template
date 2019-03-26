package br.com.santander.telemetria.core.domain;

import lombok.*;
import lombok.experimental.Wither;

@Wither @AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class TelemetriaLite {

    private String banco;
    private String agencia;
    private String conta;
    private String nomeUsuario;
    private String apelidoCelular;
    private String imei;
    private String ipCliente;
    private String plataforma;
    private String versaoApp;
    private String versaoSOcelular;
    private String cnpj;
    private String idUsuario;
    private String nomeEmpresa;
    private String pernumper;

    @Override
    public String toString() {
        return "Telemetria [Banco=" + banco + ", Agencia=" + agencia + "]";
    }
}
