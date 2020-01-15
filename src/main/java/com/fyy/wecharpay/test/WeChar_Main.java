package com.fyy.wecharpay.test;

import com.fyy.common.util.QrcodeUtil;
import com.fyy.wecharpay.PayCommonUtil;

/**
 * 微信支付测试
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 22:05
 */
public class WeChar_Main {
    public static void main(String[] args) throws Exception {
        // 创建预支付链接 返回待支付链接
        // 参数 1 价格 单位分
        String url = PayCommonUtil.weixin_pay("1", "花生米","2020100820");
        if (url!=null) {
            boolean r = QrcodeUtil.encode(url,"png",400,"weixin_ceshi.png");
            System.out.println(r);
        }
    }
}
