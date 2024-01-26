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

## 组合模式

* 组合模式主要是能应用到树形结构的一组数据上，树中的数据分为叶子和中心节点两类。以spring的CacheManager接口为例，实现它的有EhCacheManager、SimpleCacheManager、RedisCacheManager、CompositeCacheManager。其中CompositeCacheManager表示中间节点，其余的表示叶子节点。代码如下所示：

  ```java
  // CacheManager接口定义，提供两个行为：根据名字获取缓存对象和获取u所有的缓存管理器
  public interface CacheManager {
    // 根据名字获取缓存对象
    Cache getCache(String var1);
    // 获取所有的缓存管理器
    Collection<String> getCacheNames();
  }
  
  // 缓存管理器，管理的是所有的CacheManager对象
  public class CompositeCacheManager implements CacheManager, InitializingBean {
    // 管理的CacheManager对象集合
    private final List<CacheManager> cacheManagers = new ArrayList();
    private boolean fallbackToNoOpCache = false;
  
    public CompositeCacheManager() {
    }
  
    public CompositeCacheManager(CacheManager... cacheManagers) {
      this.setCacheManagers(Arrays.asList(cacheManagers));
    }
  
    // 注册cacheManager对象
    public void setCacheManagers(Collection<CacheManager> cacheManagers) {
      this.cacheManagers.addAll(cacheManagers);
    }
  
    public void setFallbackToNoOpCache(boolean fallbackToNoOpCache) {
      this.fallbackToNoOpCache = fallbackToNoOpCache;
    }
  
    public void afterPropertiesSet() {
      if (this.fallbackToNoOpCache) {
        this.cacheManagers.add(new NoOpCacheManager());
      }
  
    }
  
    /**
     * 根据名字，从所有的缓存管理器中获取一遍对应的缓存。
     * 用到了递归。这里也可以用for，效果是一样的。
     */
    @Override
    public Cache getCache(String name) {
      Iterator var2 = this.cacheManagers.iterator();
  
      Cache cache;
      do {
        if (!var2.hasNext()) {
          return null;
        }
  
        CacheManager cacheManager = (CacheManager)var2.next();
        cache = cacheManager.getCache(name);
      } while(cache == null);
  
      return cache;
    }
  
    @Override
    public Collection<String> getCacheNames() {
      Set<String> names = new LinkedHashSet();
      Iterator var2 = this.cacheManagers.iterator();
  
      while(var2.hasNext()) {
        CacheManager manager = (CacheManager)var2.next();
        names.addAll(manager.getCacheNames());
      }
  
      return Collections.unmodifiableSet(names);
    }
  }
  ```

  

## 装饰器模式

* 在使用缓存的场景中，一般都是配合数据库来使用的。如果写缓存成功，但数据库事务回滚了，那缓存中就会有脏数据。为了解决这个问题，我们需要将缓存的操作与数据库的操作放在同一个事务中，要么都成功，要么都失败。

* 为了实现这样的功能，spring中使用了装饰器的设计模式，TransactionAwareCacheDecorator增加了对事务的支持，在事务提交、回滚的时候分别对cache的数据做处理。TransactionAwareCacheDecorator实现了cache接口，并且将所有的操作都委托给targetCache实现，对其中的写操作添加了事务功能。代码如下所示：

  ```java
  public class TransactionAwareCacheDecorator implements Cache {
    private final Cache targetCache;
  
    public TransactionAwareCacheDecorator(Cache targetCache) {
      Assert.notNull(targetCache, "Target Cache must not be null");
      this.targetCache = targetCache;
    }
  
    public Cache getTargetCache() {
      return this.targetCache;
    }
  
    public String getName() {
      return this.targetCache.getName();
    }
  
    public Object getNativeCache() {
      return this.targetCache.getNativeCache();
    }
  
    public ValueWrapper get(Object key) {
      return this.targetCache.get(key);
    }
  
    public <T> T get(Object key, Class<T> type) {
      return this.targetCache.get(key, type);
    }
  
    public <T> T get(Object key, Callable<T> valueLoader) {
      return this.targetCache.get(key, valueLoader);
    }
  
    /**
     * 我们一般是操作数据库，然后put缓存。
     * 但是这里的put方法会先检测当前是否有事务操作，如果有，则注册一个回调函数，在事务提交后，会执行afterCommit方法，即put数据到缓存中
     * 
     */
    public void put(final Object key, final Object value) {
      if (TransactionSynchronizationManager.isSynchronizationActive()) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
          public void afterCommit() {
            TransactionAwareCacheDecorator.this.targetCache.put(key, value);
          }
        });
      } else {
        this.targetCache.put(key, value);
      }
    }
    
    public ValueWrapper putIfAbsent(Object key, Object value) {
      return this.targetCache.putIfAbsent(key, value);
    }
  
    /**
     * 移除缓存数据。一般是删除数据库时，要移除数据。
     * 但是这里的移除数据方法会先检测当前是否有事务操作，如果有，则注册一个回调函数，在事务提交后，会执行afterCommit方法，即从缓存中移除数据
     */
    public void evict(final Object key) {
      if (TransactionSynchronizationManager.isSynchronizationActive()) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
          public void afterCommit() {
            TransactionAwareCacheDecorator.this.targetCache.evict(key);
          }
        });
      } else {
        this.targetCache.evict(key);
      }
  
    }
  
    public void clear() {
      if (TransactionSynchronizationManager.isSynchronizationActive()) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
          public void afterCommit() {
            TransactionAwareCacheDecorator.this.targetCache.clear();
          }
        });
      } else {
        this.targetCache.clear();
      }
    }
  }
  ```

* 额外思考：为什么要用装饰器模式来实现这个功能？我们直接根据操作数据库方法返回值来判断，数据库操作是否成功，进而再做相关的处理。

  * 如果要这么实现的话，代码肯定是不够优雅的，后续不好维护和迭代。通知，我们还得感知到spring事务的钩子函数，提交事务后，再返回true，回滚事务时，则返回false。但是我们既然都感知到spring事务的钩子函数了，那为什么不直接在钩子函数里操作缓存呢？

