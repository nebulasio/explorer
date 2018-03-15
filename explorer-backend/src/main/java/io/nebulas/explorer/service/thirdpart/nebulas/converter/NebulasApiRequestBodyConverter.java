package io.nebulas.explorer.service.thirdpart.nebulas.converter;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;

public class NebulasApiRequestBodyConverter<T> implements Converter<T, RequestBody> {
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(JSON_MEDIA_TYPE, JSON.toJSONString(value));
    }
}
