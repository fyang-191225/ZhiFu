package com.fyy.test;

import com.alipay.api.internal.util.file.IOUtils;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * java中访问接口
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 21:27
 */
public class Http_Main {
    public static void main(String[] args) throws IOException {
        // 1创建资源对象
        URL url = new URL("https://api.apiopen.top/musicRankings");
        // 2 创建链接对象
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        // 3 设置请求信息
        huc.setRequestMethod("GET");
        // 4 发起请求
        huc.connect();
        // 5.验证响应状态码
        if (huc.getResponseCode() == 200) {
            // 6 获取响应结果
            String json = new String(IOUtils.toByteArray(huc.getInputStream()));
            System.out.println(json);
        }
        // 7关闭
        huc.disconnect();
    }
}
