# 充血模型
* 数据和方法封装在一个类中，比如模型中所有的字段抽象都是通过类中封装的方法来操作。要想操作这个类的数据，只能通过暴露出来的方法来操作。比如想对一个虚拟钱包做转账操作时，按照贫血模式开发，我们会把双方钱包entity对象找出来，然后再做加减法， 得到一个值，最后再把这个值set到entity更新到数据库中。如果按照充血模型来做的话，我们会先把双方的钱包entity转换成充血模型的BO对象，然后在对相应的钱包账号做相关的业务操作，比如有一个方法叫加钱，此方法内部会更新bo对象的balance字段。