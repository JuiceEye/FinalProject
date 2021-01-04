package com.github.JuiceEye.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.JuiceEye.exceptions.InvalidEmailException;
import com.github.JuiceEye.exceptions.InvalidPhoneNumberException;
import com.github.JuiceEye.models.Client;
import com.github.JuiceEye.services.ClientService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class ClientDeserializer extends StdDeserializer<Client> {

    public ClientDeserializer() {
        super(Client.class);
    }

    @Override
    public Client deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = p.getCodec().readTree(p);
//      int ClientId = Integer.valueOf(root.get("clientId").asText());
        String firstName = root.get("clientFirstName").asText();
        String lastName = root.get("clientLastName").asText();
        String clientEmail = null;
        //Хэширование пароля
        String password = BCrypt.hashpw(root.get("clientPassword").asText(), BCrypt.gensalt());
        String clientPhoneNumber = null;
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber number = null;

        //Проверка валидности номера
        try {
            number = phoneNumberUtil.parse(root.get("clientPhoneNumber").asText(), Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        if ((phoneNumberUtil.isPossibleNumber(number))) {
            clientPhoneNumber = root.get("clientPhoneNumber").asText();
        } else {
            throw new InvalidPhoneNumberException("Invalid phone number");
        }

        //Проверка валидности почты
        String email = root.get("clientEmail").asText();
        if (ClientService.isValid(email)) {
            clientEmail = root.get("clientEmail").asText();
        } else {
            throw new InvalidEmailException("Invalid email");
        }
        return new Client(/*userId*/0, firstName, lastName, clientEmail, password, clientPhoneNumber);
    }
}

/*done*/
