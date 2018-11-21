package io.nebulas.explorer.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class DecodeUtil {

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    public static JSONObject decodeData(String data) {
        try {
            String dataStr = new String(DECODER.decode(data), "UTF-8");
            return JSONObject.parseObject(dataStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static boolean isContractTransfer(JSONObject data) {
        String func = data.getString("Function");
        return "transfer".equals(func);
    }

}
