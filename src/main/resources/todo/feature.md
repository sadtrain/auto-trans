## 服务器部署
 - 自动部署功能
## 消息功能
 - @所有人功能
 - 新用户欢迎功能
 - 先做一个群作为自动转链群，自动回复

## 动态配置
- 可以在页面新建一个QQ作为接收者
- 配置每个QQ要发送到的群
- 按群暂停某个QQ的发送
- 

## 消息队列化
 - 来自三分线报，垃圾佬的群消息都流进消息池，入池的时候可以去重。
    1. 来自同一个群的消息，不做去重
    2. 结合上下文，例如第一步、第二步、第三步这种。
    3. 如果三分线报的消息入池，则垃圾佬的同一个（步）消息不入池。
    4. 同群消息不去重可以避免 凑单款没了要换凑单款的这种重复消息不被发送
 - 3-5个小号从消息池消费消息，消费内容都是一样的。引入kafka，多客户端消费。已消费的消息依然保留30分钟，
 - 新客户端引入时，可以选择消费历史的30分钟消息，是否必要？？ 后期加入管理员功能，如果新客户端加入，则将最近30分钟消息列出，管理员选择是否要发送
 - 去重策略最好有多重模式，双11时候切换为激进模式，(不控制消息速度)

## 服务器部署
 - 先试用狗云的服务器，如果不报错可以先将就用着。本机也一直挂着。kafka保证分布式部署数据不重复
 - 至少保留一个国内服务器，引入eureka注册中心，自己电脑关机时候可以自动切换到其他云服务器

## 公众号
 - 用户给公众号发消息，会自动给用户返回券和返现。
 - 需要接入微信支付系统，保留每个用户的余额，并给提现功能
 - 自动跟单，定时任务。跟单成功时，才把用户的返现置为true。

## more
 - 管理员功能，APP控制。消息密集时人工选择。描述性消息
