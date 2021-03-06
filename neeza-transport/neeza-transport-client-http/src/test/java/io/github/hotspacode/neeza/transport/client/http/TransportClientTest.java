package io.github.hotspacode.neeza.transport.client.http;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * @author moxingwang
 */
public class TransportClientTest {
    public static void main(String[] args) {

    }

    @Test
    public void testClient() throws ExecutionException, InterruptedException {
        TransportClient transportClient = new TransportClient();
        CompletableFuture<Object> objectCompletableFuture = transportClient.execute("neeza/mock/string/io.github.hotspacode.neeza.test.mock.CommonMessageService.public java.lang.String io.github.hotspacode.neeza.test.mock.CommonMessageService.getString(java.lang.String,java.lang.String)", false).thenApply(json -> {
            System.out.println(json);
            return json;
        });

        try {
            Object o = objectCompletableFuture.get();

            System.out.printf(o.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

