package com.github.JuiceEye.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.JuiceEye.models.Client;

import java.io.IOException;

public class ClientSerializer extends StdSerializer<Client> {
    public ClientSerializer() {
        super(Client.class);
    }

    @Override
    public void serialize(Client client, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("clientId", String.valueOf(client.getClientId()));
        jsonGenerator.writeStringField("clientFirstName", client.getClientFirstName());
        jsonGenerator.writeStringField("clientLastName", client.getClientLastName());
        jsonGenerator.writeStringField("clientEmail", client.getClientEmail());
        jsonGenerator.writeStringField("clientPhoneNumber", client.getClientPhoneNumber());
        jsonGenerator.writeEndObject();
    }
}

/*done*/
