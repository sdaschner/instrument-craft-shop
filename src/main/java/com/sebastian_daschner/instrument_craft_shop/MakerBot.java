package com.sebastian_daschner.instrument_craft_shop;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MakerBot {

    private Client client;
    private WebTarget target;

    @PostConstruct
    private void initClient() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        target = client.target("http://maker-bot:9080/maker-bot/resources/jobs");
    }

    public void startPrintJob(InstrumentType type) {
        JsonObject entity = buildEntity(type);
        Response response = sendRequest(entity);
        validateResponse(response);
    }

    private JsonObject buildEntity(InstrumentType type) {
        return Json.createObjectBuilder()
                .add("instrument", type.name().toLowerCase())
                .build();
    }

    private Response sendRequest(JsonObject entity) {
        try {
            return target.request().post(Entity.json(entity));
        } catch (Exception e) {
            throw new IllegalStateException("Could not print instrument, reason: " + e.getMessage(), e);
        }
    }

    private void validateResponse(Response response) {
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL)
            throw new IllegalStateException("Could not print instrument, status: " + response.getStatus());
    }

    @PreDestroy
    private void closeClient() {
        client.close();
    }

}
