package io.github.hotspacode.neeza.spy.service;

import com.alibaba.fastjson.JSON;
import io.github.hotspacode.neeza.core.NeezaMockConstant;
import io.github.hotspacode.neeza.core.service.serialization.FastJSONSerialization;
import io.github.hotspacode.neeza.deputy.api.IMockSpyService;
import io.github.hotspacode.neeza.deputy.api.INeezaSerialization;
import io.github.hotspacode.neeza.deputy.dto.MockData;
import io.github.hotspacode.neeza.deputy.dto.MockTransport;

public class MockSpyService implements IMockSpyService {
    private static FastJSONSerialization neezaSerialization = new FastJSONSerialization();


    @Override
    public MockTransport transport(String targetClassName, String targetMethodName) {
        MockTransport mockTransport = new MockTransport(false);


        try {
            //调用mock server
            String mockUrl = System.getProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL) + targetClassName + "." + targetMethodName;
            String responseStr = org.apache.http.util.EntityUtils.toString(org.apache.http.impl.client.HttpClients.createDefault().execute(new org.apache.http.client.methods.HttpGet(mockUrl)).getEntity(), "UTF-8");

            //todo 全局参数mock报错是否支持继续

            MockData mockData = neezaSerialization.deserialize(responseStr.getBytes(), MockData.class);


            if (MockData.Type.NONE.equals(mockData.getType())) {
                mockTransport.setMocked(false);
            } else {
                mockTransport.setMocked(true);

                if (MockData.Type.ReturnBody.equals(mockData.getType())) {
                    mockTransport.setResponse(mockData.getBody());
                } else if (MockData.Type.VoidReturn.equals(mockData.getType())) {
                    mockTransport.setReturnVoid(true);
                } else if (MockData.Type.ReturnNull.equals(mockData.getType())) {
                    mockTransport.setReturnNull(true);
                }
            }
        } catch (Exception e) {

        }

        return mockTransport;
    }
}
