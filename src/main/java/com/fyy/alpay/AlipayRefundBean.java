package com.fyy.alpay;

import lombok.Data;

/**
 * 订单退款需要的必要参数
 *
 * @author fyy
 * @date 2020/1/15 0015 下午 20:23
 */
@Data
public class AlipayRefundBean {
    private String out_trade_no; // 订单号 64 位以内
    private double refund_amount; // 退款金额 单位元
    private String out_request_no;// //64位 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
}
