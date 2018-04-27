package io.nebulas.explorer.config.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.service.thirdpart.coinmarketcap.CoinMarketCapApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.converter.NebulasApiConverterFactory;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Configuration
public class HttpApiConfig {

    @Bean
    @Scope
    public OkHttpClient createDefaultHttpClient() {
        return httpClient(50, 5, true);
    }

    @Scope
    @Bean
    public NebulasApiService createNebulasApiService(@Autowired YAMLConfig yamlConfig, OkHttpClient httpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(yamlConfig.getApiHost().getNebulas())
                .addConverterFactory(NebulasApiConverterFactory.create(yamlConfig.getApiHost()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(NebulasApiService.class);
    }

    @Scope
    @Bean
    public CoinMarketCapApiService createCoinMarketCapApiService(@Autowired YAMLConfig yamlConfig, OkHttpClient httpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(yamlConfig.getApiHost().getCoinmarketcap())
                .addConverterFactory(new ConverterFactoryWithSerialization<>())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(CoinMarketCapApiService.class);
    }

    private OkHttpClient httpClient(int maxIdleConnections, long keepAliveDuration, boolean log) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, MINUTES))
                .connectTimeout(1, MINUTES)
                .readTimeout(2, MINUTES)
                .writeTimeout(1, MINUTES);
        if (log) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    public static class ConverterFactoryWithSerialization<T extends BaseRequest> extends Converter.Factory {
        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return (Converter<T, RequestBody>) t -> {
                SerializeConfig jsonConfig = new SerializeConfig();
                FormBody.Builder formBuilder = new FormBody.Builder();
                JSONObject jsonMap = (JSONObject) JSON.parse(JSON.toJSONString(t, jsonConfig));
                jsonMap.forEach((key, value) -> formBuilder.add(key, JSON.toJSONString(value, jsonConfig)));
                return formBuilder.build();
            };
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type instanceof BaseResponse) {
                return new BaseResponseConverter(type);
            } else {
                return new ResponseBodyConverter<>(type);
            }
        }

        public class BaseResponseConverter implements Converter<ResponseBody, BaseResponse> {
            Type ty;

            BaseResponseConverter(Type type) {
                ty = type;
            }

            @Override
            public BaseResponse convert(ResponseBody value) throws IOException {
                String msg = value.string();
                ParserConfig config = new ParserConfig();
                config.setAsmEnable(false);
                return JSON.parseObject(msg, ty, config);
            }
        }

        public class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {

            private final Type type;

            public ResponseBodyConverter(Type type) {
                this.type = type;
            }

            @Override
            public T convert(ResponseBody responseBody) throws IOException {
                String responseJson = responseBody.string();
                return JSONObject.parseObject(responseJson, type);
            }
        }
    }
}
