package io.github.hotspacode.neeza.spy.transport;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.base.util.NeezaConstant;
import io.github.hotspacode.neeza.base.util.StringUtil;
import io.github.hotspacode.neeza.core.util.IpUtil;
import io.github.hotspacode.neeza.core.util.PidUtil;
import io.github.hotspacode.neeza.spy.ClassReader;
import io.github.hotspacode.neeza.spy.NeezaServer;
import io.github.hotspacode.neeza.transport.api.HeartbeatSender;
import io.github.hotspacode.neeza.transport.api.TransportServerStatus;
import io.github.hotspacode.neeza.transport.api.config.TransportConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class NettyHttpHeartbeatSender implements HeartbeatSender {
    private final CloseableHttpClient client;

    private final int timeoutMs = 3000;
    private final int INTERVAL_MILLISECONDS = 3000;

    private boolean mockClassesNoticed = false;


    private final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(timeoutMs)
            .setConnectTimeout(timeoutMs)
            .setSocketTimeout(timeoutMs)
            .build();


    public NettyHttpHeartbeatSender() {
        this.client = HttpClients.createDefault();
    }

    @Override
    public boolean sendHeartbeat() throws Exception {
        if (StringUtil.isEmpty(NeezaServer.getServerIp())) {
            return false;
        }
        int realPort = TransportServerStatus.getRealPort();
        if (realPort == 0) {
            return false;
        }
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme("http")
                    .setHost(NeezaServer.getServerIp())
                    .setPort(NeezaServer.getServerPort())
                    .setPath(TransportConfig.SERVER_HEARTBEAT_URL)
                    .setParameter("appName", NeezaServer.getAppName())
                    .setParameter("version", NeezaConstant.VERSION)
                    .setParameter("ip", IpUtil.getIp())
                    .setParameter("port",  String.valueOf(realPort))
                    .setParameter("pid", String.valueOf(PidUtil.getPid()));

            uriBuilder.setParameter("pulledMethods", JSON.toJSONString(NeezaServer.getPullMethod().keySet()));
            if (!mockClassesNoticed) {
                uriBuilder.setParameter("mockClasses", JSON.toJSONString(ClassReader.getMockClasses()));
            }

            HttpGet request = new HttpGet(uriBuilder.build());
            request.setConfig(requestConfig);
            CloseableHttpResponse response = client.execute(request);
            response.close();
            mockClassesNoticed = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long intervalMilliseconds() {
        return INTERVAL_MILLISECONDS;
    }
}
