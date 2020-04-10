package nl.han.oose.dea.persistence;

import java.util.UUID;

public class TokenGenerator {
    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
