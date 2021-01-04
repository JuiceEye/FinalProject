package com.github.JuiceEye.services;

import com.github.JuiceEye.config.Constants;
import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.exceptions.InvalidLoginCredentialsException;
import com.github.JuiceEye.models.Client;

import com.github.JuiceEye.models.ClientRole;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class ClientService implements Handler {

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +  //part before @
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public Dao<Client, Integer> clientDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Client.class);
    }

    @Override
    public void create(Object objectClient) throws SQLException {
        clientDao().create((Client) objectClient);
    }

    @Override
    public Object read(int id) throws SQLException {
        return clientDao().queryForId(id);
    }

    @Override
    public void update(Object objectClient) throws SQLException {
        clientDao().update((Client) objectClient);
    }

    @Override
    public void delete(int id) throws SQLException {
        clientDao().delete(clientDao().queryForId(id));
    }

    @Override
    public List<?> getAll() throws SQLException {
        return clientDao().queryForAll();
    }

    public Client defineClient(Context context) throws SQLException, InvalidLoginCredentialsException {
        List<Client> clientList;
        QueryBuilder<Client, Integer> queryBuilder = clientDao().queryBuilder();
        queryBuilder.where().eq("clientUsername", context.basicAuthCredentials().getUsername());
        PreparedQuery<Client> preparedQuery = queryBuilder.prepare();
        clientList = clientDao().query(preparedQuery);
        ClientRole role = ClientRole.UNDEFINED;
        Random random = new Random();
        long elapsedTime = System.currentTimeMillis() - clientList.get(0).getTokenSettingTime();
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedMinutes = elapsedSeconds / 60;
        if (elapsedMinutes >= 60) {
            if (BCrypt.checkpw(context.basicAuthCredentials().getPassword(), clientList.get(0).getClientPassword())) {
                String token = "";
                for (int i = 0; i < 20; i++) {
                    token = token.concat(String.valueOf(Constants.ALPHABET.charAt(random.nextInt(Constants.ALPHABET.length()))));
                }
                clientList.get(0).setToken(token);
                clientList.get(0).setTokenSettingTime(System.currentTimeMillis());
                return clientList.get(0);
            } else {
                throw new InvalidLoginCredentialsException("Invalid login credentials");
            }
        } else {
            if (context.header("Token").equals(clientList.get(0).getToken())) {
                return clientList.get(0);
            } else {
                throw new InvalidLoginCredentialsException("Invalid login credentials");
            }
        }
    }
}

/*done*/
