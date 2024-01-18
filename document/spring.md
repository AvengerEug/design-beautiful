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
  
  

