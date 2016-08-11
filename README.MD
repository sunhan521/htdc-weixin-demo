## 引入第三方的微信SDK
https://github.com/chanjarster/weixin-java-tools

## 更新overrride.properties
``` bash
weixin.appId=
weixin.secret=
weixin.token=
weixin.aesKey=
weixin.oauth2redirectUri=http://eca3681.ngrok.natapp.cn
```

## 微信后台：
1. 修改SDK相关配置，SDK域名，以及JSSDK的可信任域名

## 新增@OAuthRequired 注解
在需要获取用户信息的Controller上面，添加注解

``` java
@RequestMapping("/hello")
    @OAuthRequired
    public String hello(ModelMap modelMap, HttpSession session) {
        WxMpUser wxMpUser = (WxMpUser) session.getAttribute("wxMpUser");
        String openId = wxMpUser.getOpenId();
        modelMap.put("openId", openId);
        modelMap.put("wxMpUser", new Gson().toJson(wxMpUser));
        return "hello";
    }
```