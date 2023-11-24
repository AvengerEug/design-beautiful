package com.eugene.sumarry.designbeautiful.alert.two;

/**
 *
 * 在告警功能的版本1中，我们要新增一个功能需要修改方法的定义以及会改动到原有方法。
 * 那我们如何来做到对扩展开放，对修改关闭呢？
 * 第一：我们可以把check方法的入参封装成一个request
 * 第二：我们可以把每一个告警的逻辑抽象成一个个的handler，后续新增告警逻辑，新增handler即可（移除各种if逻辑）
 * 第三：我们新增一个delegate类用来委托执行所有相关的告警逻辑入口，并在内部维护告警类相关的引用
 *
 *
 *
 * @author muyang
 * @create 2023/11/24 21:18
 */
public class Entry {

    public static void main(String[] args) {
        // 初始化所有的告警处理器，如果有新增，则直接修改initBeans方法即可
        AlertDelegate alertDelegate = new AlertDelegate();
        alertDelegate.initBeans();

        // 如果要新增新的告警逻辑，需要新增参数时，只需要在AlertRequest类中添加参数即可
        AlertRequest alertRequest = new AlertRequest(500L, 200L, 300L);
        alertDelegate.check(alertRequest);

        // 代码写完后，可能有人会说，就算新增了告警规则，不也修改了AlertDelegate的initBeans方法么？不也在AlertRequest类中的代码吗（新增了属性）
        
    }

}
