package io.github.hotspacode.neeza.server.standalone.service;

import io.github.hotspacode.neeza.core.cache.NeezaMockCache;
import io.github.hotspacode.neeza.transport.api.TransportServerStatus;
import io.github.hotspacode.neeza.transport.client.http.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class NoticeService {
    @Autowired
    private TransportClient transportClient;

    public void noticeClient(String methodDesc) {
        Set<String> methodClients = NeezaMockCache.getMethodClients(methodDesc);
        if (CollectionUtils.isEmpty(methodClients)) {
            return;
        }
        for (String methodClient : methodClients) {
            String[] split = methodClient.split(":");
            try {
                dataChangeNotice(methodDesc, split[0], split[1]);
            } catch (Exception e) {
                //客户端异常处理流程
            }
        }
    }

    private String dataChangeNotice(String methodDesc, String ip, String port) throws ExecutionException, InterruptedException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("body", methodDesc);
        paramMap.put("clientPort", TransportServerStatus.getRealPort() + "");

        CompletableFuture<String> mockDataCompletableFuture = transportClient.execute(ip, Integer.valueOf(port), "neeza/spy/mock/data/change", paramMap, false)
                .thenApply(json -> {
                    return json;
                });
        String responseStr = mockDataCompletableFuture.get();
        return responseStr;
    }
}