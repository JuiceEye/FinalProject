package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Objects;

public class Client {
    @DatabaseField(generatedId = true)
    int clientId;
    @DatabaseField(columnName = "clientFirstName")
    String clientFirstName;
    @DatabaseField(columnName = "clientLastName")
    String clientLastName;
    @DatabaseField(columnName = "clientEmail", unique = true)
    String clientEmail;
    @DatabaseField(columnName = "clientPassword", unique = true)
    String clientPassword;
    @DatabaseField(columnName = "clientPhoneNumber", unique = true)
    String clientPhoneNumber;
    private ClientRole ClientRole;
    private String token;
    private long tokenSettingTime;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTokenSettingTime() {
        return tokenSettingTime;
    }

    public void setTokenSettingTime(long tokenSettingTime) {
        this.tokenSettingTime = tokenSettingTime;
    }

    public com.github.JuiceEye.models.ClientRole getClientRole() {
        return ClientRole;
    }

    public void setClientRole(com.github.JuiceEye.models.ClientRole clientRole) {
        ClientRole = clientRole;
    }

    public Client() {
    }

    public Client(int clientId, String clientFirstName, String clientLastName, String clientEmail, String clientPassword, String clientPhone) {
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPassword = clientPassword;
        this.clientPhoneNumber = clientPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId &&
                Objects.equals(clientFirstName, client.clientFirstName) &&
                Objects.equals(clientLastName, client.clientLastName) &&
                Objects.equals(clientEmail, client.clientEmail) &&
                Objects.equals(clientPassword, client.clientPassword) &&
                Objects.equals(clientPhoneNumber, client.clientPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientFirstName, clientLastName, clientEmail, clientPassword, clientPhoneNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhoneNumber + '\'' +
                '}';
    }
}

/*done*/