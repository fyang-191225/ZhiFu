package com.fyy.alpay;

import lombok.Data;

/**
 * 创建链接必要的参数
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 20:00
 */
@Data
public class AliPayBean {
    private String out_trade_no; // 订单号 64 位以内
    private double total_amount;// 金额 单位元， 小数点两位
    private String subject;  // 标题
    private String body; // 描述信息可省略

    public AliPayBean() {
    }

    public AliPayBean(String out_trade_no, double total_amount, String subject, String body) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
        this.body = body;
    }
}
