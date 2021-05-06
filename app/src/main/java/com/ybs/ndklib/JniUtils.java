package com.ybs.ndklib;

import com.ybs.base_module.base.util.Loger;

public class JniUtils {



    static {
        System.loadLibrary("ybs-sign-tool");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String signData(String data, String productModel);

    public static String getDeviceToken(String deviceTag, String timestamp, String productionModelSn) {
        String data = "assemblyTime=" + timestamp + "&deviceTag=" + deviceTag + "&productionModelSn=" + productionModelSn + "&timestamp=" + timestamp;
//        String data = "deviceTag=" + deviceTag + "&productionModelSn=" + productionModelSn + "&timestamp=" + timestamp;
        String signToken = new JniUtils().signData(data, productionModelSn);
        Loger.d("signToken = "+signToken);
        return signToken;
    }
}
