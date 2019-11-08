package io.github.hotspacode.neeza.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.hotspacode.neeza.core.NeezaMockConstant;

public class NeezaMockConfig {
    public static void init(){
        System.getProperties().setProperty(NeezaMockConstant.SIMPLE_MOCK_VM_PACKAGE_NAME, "top/moxingwang/simplemock/test");
        System.getProperties().setProperty(NeezaMockConstant.SIMPLE_MOCK_VM_SERVER_URL, "http://localhost:8080/neeza/mock/string/");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}