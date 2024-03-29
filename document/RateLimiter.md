# 限流需求

## 项目背景

* 公司创立初期，所有的功能都是大一统，放在一个工程里。随着业务的发展，发现有越来越多的功能需要进行切割，独立成一个个新的系统。这其中也会出现一些公共的基础服务，所有的项目都会依赖公共服务，我们可能会创立一个团队来做公共服务的维护和升级迭代。

## 需求背景

* 对于个公共服务平台来说，接口请求来自很多不同的系统。每个业务方使用基础服务的方式大不相同，有些可能是每天固定时间使用job来刷接口，有些可能恶意流量比较大，脉冲型的请求到公共服务占用系统资源，进而影响到其他业务。
* 因此，我们需要开发一个网关，来保护自己的系统，其中包含参数映射、鉴权、限流等基础功能。而**限流**就是我们本期要做的需求。

## 需求分析

* 一般来说，我们会先找一个应用场景，针对这个场景写一个demo程序，这样能够很直观地看到框架长什么样子。知道了框架长什么样子后，就相当于读书期间确定了考试的题目。针对明确的考题，去想解决方案，应该是我们最擅长的事情。
* 对于限流框架来说，一般有如下应用场景：
  * 首先，我们需要设置限流规则。为了做到在不修改代码的前提下修改规则，我们一般会把规则放在配置文件中（比如xml、yaml配置文件）。
  * 在集成了限流框架的应用启动的时候，限流框架会将限流规则，按照事先定义的语法解析并加载到内存中。在接收到请求之后，应用会将请求发送给限流框架，限流框架会告诉应用，这个接口是否允许继续处理，还是触发限流熔断。
* 结合上述的应用场景后，从使用的角度来说，限流框架主要包含两部分功能：限流配置（管理时逻辑）和限流规则匹配（运行时逻辑）。不过，作为通用的框架，除了功能性需求之外，**非功能需求也非常重要（有时候能决定一个框架的成败，比如：易用性、扩展性、灵活性、性能、容错等）**。
* 易用性方面：我们希望限流规则的配置、编程接口的使用都很简单。我们系统提供各种不同的限流算法，比如基于内存的单机限流算法、基于Redis的分布式限流算法，能够让使用者自由选择。同时，我们也希望能快速方便的集成到Spring框架中去。
* 扩展性和灵活性方面：我们希望能够灵活地扩展各种限流算法。同时，我们还希望支持不同的格式（JSON、YAML、XML等格式）、不同的数据源（本地文件配置或zookeeper集中配置等）的限流规则配置方式。
* 性能方面：因为每个接口请求都要被检查是否限流，因此，我们希望限流的规则校验逻辑处理要非常快，能尽可能的减少对接口请求本身响应时间的影响
* 容错性方面：希望限流框架出错，也不能影响服务本身的可用性。比如：分布式限流算法依赖redis，如果redis挂掉了，限流逻辑无法正常运行，这个时候业务接口也要能正常服务才行。

# 限流设计

## 限流规则





## 限流算法



## 限流模式



## 集成使用









