package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public JacksonConfig() throws Exception {

        objectMapper = new ObjectMapper()
            .setDateFormat(new RFC3339DateFormat())
                /*
            .registerModule(new JodaModule() {
                {
                    addSerializer(DateTime.class, new StdSerializer<DateTime>(DateTime.class) {
                        @Override
                        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
                            jgen.writeString(ISO8601DateFormatISODateTimeFormat.dateTimeNoMillis().print(value));
                        }
                    });
                    addSerializer(LocalDate.class, new StdSerializer<LocalDate>(LocalDate.class) {
                        @Override
                        public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
                            jgen.writeString(ISODateTimeFormat.date().print(value));
                        }
                    });

                }
            })*/;
    }

    public ObjectMapper getContext(Class<?> arg0) {
        return objectMapper;
    }
}