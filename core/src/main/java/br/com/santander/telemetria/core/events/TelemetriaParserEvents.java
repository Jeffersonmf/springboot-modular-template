package br.com.santander.telemetria.core.events;

import br.com.santander.telemetria.core.utils.ETypeFormatter;
import org.jsonbuilder.JsonBuilder;
import org.jsonbuilder.implementations.gson.GsonAdapter;
import org.jsonbuilder.implementations.jackson.JacksonAdapter;

/**
 * @author Jefferson Marchetti Ferreira
 * <p>
 * Nessa classe teremos implementações relacionados a eventos e processamentos
 * reativos.
 */
public class TelemetriaParserEvents {

    private JsonBuilder json;
    private String jsonBody;

    public TelemetriaParserEvents(String jsonBody) {
        this(jsonBody, ETypeFormatter.Default);
    }

    public TelemetriaParserEvents(String jsonBody, ETypeFormatter eTypeFormatter) {

        this.jsonBody = (jsonBody == null
                || jsonBody == "") ? "" : jsonBody;

        switch (eTypeFormatter) {
            case JacksonParser:
                json = new JsonBuilder(new JacksonAdapter());
                break;
            case GSONParser:
                json = new JsonBuilder(new GsonAdapter());
                break;
            default:
                json = new JsonBuilder(new JacksonAdapter());
                break;
        }
    }

    public JsonBuilder buildAndParserToJson() {

        json.object("key1", "value1")
                .object("key2", "value2")
                .object("key3")
                .object("innerKey1", "value3")
                .build();

        return json;
    }

    public String ToString() {
        return json.toString();
    }
}
