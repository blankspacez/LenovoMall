# LenovoMall
ouc 软工实践课设（后端）

前端：<https://github.com/blankspacez/LenovoMall_View>

# lenovoMall（参考联想商城）

## 项目简介

本项目前后端分离，前端基于`Vue`+`Vue-router`+`Vuex`+`Element-ui`+`Axios`，参考联想实现。后端基于`SpringBoot`+`Mysql`实现，还用到了`redis`和`rabbitMQ`。

前端包含了以下页面：首页、登录、注册、全部商品、商品详情页、关于我们、我的收藏、购物车、订单结算页面、我的订单、错误处理页面、秒杀商品页面。

实现了商品的展示、商品分类查询、关键字搜索商品、商品详细信息展示、登录、注册、用户购物车、订单结算、用户订单、用户收藏列表以及错误处理功能。

后端采取了MVC模式，基于Restful风格设计。

## 技术栈

- **前端：** `Vue`+`Vue-router`+`Vuex`+`Element-ui`+`Axios`

- **后端：** `SpringBoot`+`redis`+`rabbitMQ`

- **数据库：** `Mysql`

- **服务器：** `Nginx`

## 功能模块

### 登录

页面使用了element-ui的`Dialog`实现弹出对话框的效果，`登录`按钮设置在App.vue根组件，通过`vuex`中的`showLogin`状态控制登录框是否显示。

这样设计是为了既可以通过点击页面中的按钮登录，也可以是用户访问需要登录验证的页面或后端返回需要验证登录的提示后自动弹出登录框，减少了页面的跳转，简化用户操作。

用户输入的数据往往是不可靠的，所以本项目前后端都对登录信息进行了校验，前端基于element-ui的表单校验方式，自定义了校验规则进行校验。后端采用cookie + token进行校验。由于页面对于用户登录请求的响应要做到尽可能的快，我们采用了redis存储用户信息数据，并设置了数据的过期时间。

### 注册

页面同样使用了element-ui的`Dialog`实现弹出对话框的效果，`注册`按钮设置在App.vue根组件，通过父子组件传值控制注册框是否显示。

### 首页

首页主要是对商品的展示，有轮播图展示推荐的商品，分类别对热门商品进行展示。通过springBoot的controller类进行业务功能的实现。

### 全部商品

全部商品页面集成了全部商品展示、商品分类查询，以及根据关键字搜索商品结果展示。myBatis和pageHelper结合实现了分页查找的功能，关键字搜索则是通过数据库的模糊查询进行实现。

### 秒杀商品页

秒杀商品页展示了在固定时间段的特价商品。采用SpringBoot的@Scheduled注解设定定时任务定时将符合参与秒杀的商品查询出来再存⼊到Redis缓存，由于“秒杀”要求服务器快速响应以及严格按照用户的抢购顺序，我们还用到了rabbitMQ实现消息异步传输。

### 商品详情页

商品详情页主要是对某个商品的详细信息进行展示，用户可以在这里把喜欢的商品加入购物车或收藏列表。

### 我的购物车

购物车采用vuex实现。

### 订单结算

用户在购物车选择了准备购买的商品后，点击“去结算”按钮，会来到该页面。
用户在这里选择收货地址，确认订单的相关信息，然后确认购买。

### 我的收藏

用户在商品的详情页，可以通过点击加入 "喜欢" 按钮，把喜欢的商品加入到收藏列表。

### 我的订单

对用户的所有订单进行展示。


## 运行项目

```
1.安装前端vue项目开发环境所需的依赖包

npm install

2. 开启redis、rabbitMQ、Nginx服务

3. 通过maven导入相关依赖包，运行后端程序

4.在前端vue项目的命令行终端输入如下命令，即可成功运行项目 

npm run serve

 
