package com.eugene.sumarry.designbeautiful.alert.one;

/**
 *
 * 创建了一个告警类：alert。
 * 内部实现逻辑：如果调用量超过了api规则的tps数或错误数量超过了api对应规则的数量，则会触发对应的告警。
 *
 * 试想一下，如果我们现在新增一个成功率的告警，该怎么做？
 * 首先：修改alert类的check方法参数定义，新增一个参数：successCount
 * 其次：在check方法内部，新增对成功率的计算和告警逻辑
 *
 * 针对更正方法的参数定义，则会导致我们所有用到这个方法的地方都得跟着更改
 * 另外，check方法内部因为新增了成功率的计算和告警逻辑，需要把之前的tps、错误数量告警都回归一遍
 * 没有做到对扩展开放，对修改关闭。
 *
 * @author muyang
 * @create 2023/11/24 21:09
 */
public class Entry {

    public static void main(String[] args) {
        // 创建一个告警入口
        Alert alert = new Alert(new Notification());
        // 执行check方法，200s内，请求了10000吃api，发生了100次错误。判断是否要触发告警
        alert.check("avnegerEug.trade.fullinfo.get", 10000L, 100L, 200L);
    }

}
