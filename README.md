# wx-mp-subscribe
微信小程序订阅消息


### 前端
>ps：样式和位置大家可以根据自身喜好进行修改

* 把 subscribe-btn 复制到小程序components目录
* 在需要使用的页面的 json 文件里引用这个组件
```
//如：在page/index/index.wxml页面使用，page/index/index.json里面引用
{
  "usingComponents": {
    "subscribe-btn":"/components/subscribe-btn/subscribe-btn"
  }
}
```
* 在 page/index/index.wxml 上放置订阅按钮即可
```
<subscribe-btn"></subscribe-btn>
```

### 后端
> codeplus-svr为后端服务项目  
> 后端服务使用java来实现，数据库用sqlite，相关运行说明在项目根目录  
> ps: 后端项目为springboot项目，过于庞大，建议参照逻辑使用自己熟悉的较轻量的语言来实现  
