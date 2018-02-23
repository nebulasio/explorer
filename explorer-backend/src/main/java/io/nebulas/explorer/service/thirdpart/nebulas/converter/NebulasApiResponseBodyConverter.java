package io.nebulas.explorer.service.thirdpart.nebulas.converter;

import com.alibaba.fastjson.JSON;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

public class NebulasApiResponseBodyConverter<T> implements Converter<ResponseBody, T> {

  private final Type type;

  public NebulasApiResponseBodyConverter(Type type){
    this.type = type;
  }


  @Override public T convert(ResponseBody value) throws IOException {
    return JSON.parseObject(value.string(),type);
  }
}
