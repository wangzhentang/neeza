package io.github.hotspacode.neeza.base.api;

public interface INeezaSerialization {

    String CHARSET_NAME = "UTF-8";


    byte[] serialize(Object data);

    <T> T deserialize(byte[] data, Class<T> clz);

}
