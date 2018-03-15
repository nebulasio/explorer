package io.nebulas.explorer.service.thirdpart.nebulas.converter;

import io.nebulas.explorer.config.HttpApiHostConfig;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class NebulasApiConverterFactory extends Converter.Factory {

    private HttpApiHostConfig apiHost;

    private NebulasApiConverterFactory(HttpApiHostConfig apiHost) {
        this.apiHost = apiHost;
    }

    public static NebulasApiConverterFactory create(HttpApiHostConfig apiHost) {
        return new NebulasApiConverterFactory(apiHost);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new NebulasApiResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new NebulasApiRequestBodyConverter<>();
    }
}
