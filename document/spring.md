# 总结下spring用到了哪些设计模式

## 适配器模式

* 在springmvc中，我们可以通过@Controller、实现Controller接口、继承HttpServlet 类来定义一个Controller。最终springmvc的dispatcherServlet接收到请求时，会用到一个HandlerMapping类来映射对应的handler。但是，每一个handler处理请求的细节不一样。对于@Controller注解而言，我们只需要添加对应@GetMapping注解就可以了。对于实现Controller接口而言，则需要实现内部的HandleRequest方法。对于继承HttpServlet类而言，则需要重写Service方法。

* 如果我们不做任何处理时，代码可能会这么写：

  ```java
  Handler handler = handlerMapping.get(URL);
  if (handler instanceof Controller) {
    ((Controller)handler).handleRequest(...);
  } else if (handler instanceof Servlet) {
    ((Servlet)handler).service(...);
  } else if (hanlder 对应通过注解来定义的Controller) {
    反射调用方法...
  }
  ```

* 可以发现，有很多if逻辑。如果我们加上适配器模式的话，代码可能写成下面这样子：

  ```java
  // 先定义一个适配器接口
  public interface HandlerAdapter {
      boolean supports(Object val1);
      
      ModelAndView handler(HttpServletRequest var1， HttpServletResponse val2);
  }
  
  // 对应实现Controller接口的Controller
  public class SimpleControllerHandlerAdapter implements HandlerAdapter {
      public boolean supports(Object handler) {
          return handler instance of Controller;
      }
      
      public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) {
          return ((Controller)handler).handleRequest(request, response)
      }
  }
  
  // 对应继承HttpServlet接口的Controller
  public class SimpleHttpServletAdapter implements HandlerAdapter {
      public boolean supports(Object handler) {
          return handler instance of HttpServlet;
      }
      
      public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) {
          ((Servlet)handler).service(...);
          return new ModelAndView();
      }
  }
  
  
  // 使用方式
  // 使用URL获取适配器
  HandlerAdapter handlerAdapter = handlerMapping.get(URL);
  // 调用适配器的handler方法即可，就没有烦人的if-else代码了
  handlerAdapter.handler();
  
  ```
  

## 策略模式

在Spring AOP中有两种实现动态代理的方式，分别是jdk的proxy和cglib。**在策略模式中，策略的创建一般是通过工厂方法来实现的**。对应spring的源码中，AopProxyFactory是一个工厂类接口，DefaultAopProxyFactory是一个默认的工厂类，用来创建AopProxy对象。代码如下所示：

```java
// 策略类的定义
public interface AopProxy {
  Object getProxy();
  Object getProxy(ClassLoader var1);
}


// 创建策略类的工厂定义
public Interface AopProxyFactory {
    AopProxy createAopProxy(AdviseSupport var1) throws AopConfigException;
}

// aop代理类的创建工厂
public class DefaultAopProxyFactory implements AopProxyFactory {
    public DefaultAopProxyFactory() {
    }
    
    public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
        // 创建jdk模式的AopProxy
        if (!config.isOptimize() && !config.isProxyTargetClass() && !this.hasNoUserSuppliedProxyInterfaces(config)) {
          return new JdkDynamicAopProxy(config);
        } else {
          Class<?> targetClass = config.getTargetClass();
          if (targetClass == null) {
            throw new AopConfigException("TargetSource cannot determine target class: Either an interface or a target is required for proxy creation.");
          } else {
            // 有cglib，再根据类是否实现接口来确定是使用cglib还是jdk代理类
            return (AopProxy)(!targetClass.isInterface() && !Proxy.isProxyClass(targetClass) ? new ObjenesisCglibAopProxy(config) : new JdkDynamicAopProxy(config));
          }
        }
      }
	}

  //用来判断用哪个动态代理实现方式
  private boolean hasNoUserSuppliedProxyInterfaces(AdvisedSupport config) {
    Class<?>[] ifcs = config.getProxiedInterfaces();
  }
   
    
}


```

* 策略模式的典型应用场景，一般是通过环境变量、状态值、计算结果等动态地决定使用哪个策略。对应到Spring的源码中，我们可以参看刚刚给出的DefaultAopProxyFactory类中的createAopProxy函数的代码实现，其中hasNoUserSuppliedProxyInterfaces方法和其他的一些判断条件决定是使用哪种策略生成代理类

