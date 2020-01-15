package com.fyy.alpay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 支付宝支付工具类封装
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 19:42
 */
public class AlPayUtil {
    private static AlipayClient alipayClient;

    static {
        /**
         * 支付宝的AlipayClient 构造函数的参数说明
         *URL 支付宝网关（固定） https://openapi.alipay.com/gateway.do
         *APPID APPID 即创建应用后生成 公司提供
         *APP_PRIVATE_KEY 开发者私钥，由开发者自己生成
         *FORMAT 参数返回格式，只支持 json
         *CHARSET 编码集，支持 GBK/UTF-8 开发者根据实际工程编码配置
         *ALIPAY_PUBLIC_KEY 支付宝公钥，由支付宝生成
         *SIGN_TYPE 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
         * */
        alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2017091608770636",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCh1qI8uo1qhrcePsa5JUAoYUX8HfPuBt7kc90aCP1M/v61/uzaU/lyGQeChKV3jdDTn2Lcq6kT5JBl3TLiaYHmO6cId1nQAIUxiT9zhB9crc4wAx8CVabMbcqUefs7Xsp+YhhUgU5X6GOS3emkUeL7RegBnL8vayfEBeUDgBxsk/K/VygBA8sapsEhnoOrB6bhMY4GaJrxb0kg9Ej8x4kpExLcxkT+UgcOiJvh6vpBZo5CJsiPQkFvSsNsWY2uSDudSL/KqpMxz+yPfVvZDt4fOfyi+CfYR43Jlo4tsT7joqH2JT06BH+KdJyc1D3Lqw7w/WdmZtmoLghH0kRZawrLAgMBAAECggEAYYtpm+rhQ7zQ8HTr+DogknYW5Z/0H5qai93d/Uw/yEHFqlJt1iZZKlE1upBS311l6beesdzxeuD/u7X4bokjV27K/YpaYsl9fl74FJslAApuRXgMH68aawsd2CIxsBYxPL3JZl3Np6SVJ7eDlJwakFMRRK+CeIVAoaDf6R01hKctkYnnE0wT+ffQNKWsISoEyiKVT3g5fur7iPOuDlDXsfi6Mm+e75wCXTmRRHmb8lPBAMLV+Kj5DFxg8dwNz81Fs4ZM2Aq0lBaTfy1H1zSlM1m42wcsMYDcgdEH9aq+OgqK+cny6umgs7/Alg7IgV/9b7AhKdvAqLy2ERUJtooj2QKBgQDeIoDW3HuTq7sBaBnu63f7icT2RM3fApfOiGM4UDtxPvc5dS5S//o3E8p+rbp21FfBeyLOJFd9dg/eu+ETA+63QMPw4Kq4AH/EA5AFohaOQ0IKFDjYyxfyD8ajA4USDwdiaW2/vmMeAtGSv+W5zWb9/t49LOTwzEW904+yOGcmhQKBgQC6guDZ0Ob4o9nx5XwZXEe2di4MupARHceGzmolyDvs3Qi/w+8QntrDvfqIJoqoxOG5NVi3jtjkqtJtMaPyxqNWTabWOOTLbrsqlvPUmeCl0j3FVFKAGcV7/b9XkLvh1DtnIe6rhhZCVB4e4bL/katpOTgulhmSMaWIaztGU0F1DwKBgQCTeobdn/6vuSlsMqhdFppPN1W8R0wDjt4o8iYlwibk9e//hswdsPN307zyQ/dzY2FsBIvEHx6zHkpFD6nMDSVVJzuv1gmiJjqtccwR4V5mT0MuG+TuElCwlkbD/ddAeRfm/6Ys0oNN7oMjkiI8LKH/alI0fXT2Zji7YhWaNpZNXQKBgEU6q0duWS1VdGJrcgLf0+aQO0uSPEN+MD+Dgrb/ee7TpJm5mpUqwb0CWWoMFE/MtJRQjtujdDJ8jZrmYBqPTLWOIS1G9PXl5idK3Lq/Wzlxrmf+gpj19+2sJEfWe0a5xkrjt3mHTd/U5VFFKXHfmiZ2jLoOEPPI5c6bLudNo/BVAoGBAMvwRxLO4xb11Ip4rnEHkw3Qn8lrddoC3/m7haHYZ5DyGe8wdCdEi6wyk5MvlNQdqdVg5bqV0AiotIBcd5Pemabun2WaB11h/6SSb6wKY4Fnz+H155zaEww4no9BTG9llqQV7H8AS77dN1bxhcpE/MGFoB9JFU0D+BwXAnth4z1u",
                "json","UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx7jJT+PSEM6ZiimTW0SGUfg4cJU04H/mQqkL2mk7KaHXFQqMh4US6xYkDlaEXzOOfxevuBqWOaB4/8TleO1CHZHXWHu9Xc+iYtJPNJGrxoGLM+6Cg9IafJTygRoaqdH0SoVMpxFdOpUftNdXHO+G0ZpS/7c1zpn8G64zN5J17IFrLcUlsEnSgOrJxsS2Q50b44er0KQlj76pehB2sTveHS2vdhqXzrv+oq99XtUKEY1a3nwDjXneI7YYKLHD9KU53pti/ibLDkOEjO4+DRowd+wfSwkmWGVL3X320mvCfrg/aMN71B/cyyhW0mQ4cxqh2UcnpxLm0v/+uC7dSCyAJwIDAQAB",
                "RSA2");
    }

    /**
     * 创建预支付链接
     *
     * @param bean 创建链接必要的参数对象
     * @return
     */
    public static String creatPrepay(AliPayBean bean) {
        // 1，创建请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        // 2设置传递的内容
        request.setBizContent(JSON.toJSONString(bean));
        // 3执行请求并获取详情对象
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            // 4 校验结果
            if (response.isSuccess()) {
                // 5返回预支付链接
                return response.getQrCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询支付宝的订单支付状态
     *
     * @param oid 订单号
     * @return
     */
    public static String queryPayStatus(String oid) {
        // 1 创建交易查询请求对象
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        // 2 设置订单号
        request.setBizContent("{out_trade_no:" + oid + "}");
        // 3执行请求并获取详情对象
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            // 4 校验结果
            if (response.isSuccess()) {
                return response.getTradeStatus();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取消订单
     *
     * @param oid 订单号
     * @return
     */
    public static String cancePay(String oid) {
        // 创建请求对象
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        // 2 设置订单号
        request.setBizContent("{out_trade_no:" + oid + "}");
        try {
            AlipayTradeCancelResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getAction();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退款订单
     *
     * @param bean 订单退款需要的必要参数的对象
     * @return
     */
    public static String refundPay(AlipayRefundBean bean) {
        // 创建请求对象
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        // 2 设置订单号
        request.setBizContent(JSON.toJSONString(bean));
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询退款状态
     * @param oid 订单号
     * @param rfid 退款号
     * @return
     */
    public static String queryRefundPay(String oid, String rfid) {
        // 创建请求对象
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        // 2 设置订单号 和 退款号
        request.setBizContent("{out_trade_no:" + oid + ",out_request_no:" + rfid + "}");
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getCode();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
