package com.fyy.alpay.test;

import com.fyy.alpay.AlPayUtil;
import com.fyy.alpay.AliPayBean;
import com.fyy.alpay.AlipayRefundBean;
import com.fyy.common.util.QrcodeUtil;

/**
 * 测试
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 20:38
 */
public class Test1 {
    public static void main(String[] args) {
//        String str = "二维码测试！！！！";
//        Boolean png = QrcodeUtil.encode(str, "png", 400, "ceshi1.png");
//        System.out.println("生成二维码：" + png);
        // 支付
        AliPayBean aliPayBean = new AliPayBean("2020205946", 0.01, "红包", "意思意思");
        String s = AlPayUtil.creatPrepay(aliPayBean);
        System.out.println(s);
        if (s != null) {
            boolean r = QrcodeUtil.encode(s, "png", 400, "ceshi2.png");
            System.out.println(r);
        }
        // 退款
        AlipayRefundBean bean = new AlipayRefundBean();
        bean.setOut_request_no("2020205946");
        bean.setOut_trade_no("2020205946");
        bean.setRefund_amount(0.01);
        System.out.println("发起退款" + AlPayUtil.refundPay(bean));
        // 查询退款状态
        System.out.println("查询退款状态" + AlPayUtil.queryRefundPay(bean.getOut_request_no(), bean.getOut_trade_no()));
    }
}
