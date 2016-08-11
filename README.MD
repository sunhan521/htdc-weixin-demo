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



## jssdk
#### 后台请求
``` java

    @RequestMapping("/ticket")
    @ResponseBody
    public String getTsapiTicket() throws Exception {
        return wxMpService.getJsapiTicket();
    }

    @RequestMapping("/sign")
    @ResponseBody
    public String getJsSdkSign(String url) throws Exception {
        return new Gson().toJson(wxMpService.createJsapiSignature(url));
    }
    
```

#### js代码

``` 

$(".jssdk").on("click", function () {
    wx.scanQRCode({
        desc: 'scanQRCode desc'
    });
});
$.ajax({
    url: "http://eca3681.ngrok.natapp.cn/ticket",
    success: function (data) {
        $.get("http://eca3681.ngrok.natapp.cn/sign",
                {
                    url: location.href.split('#')[0]
                },
                function (sign) {
                    console.log("sign:" + sign)
                    wx.config({
                        debug: true,
                        appId: sign.appid,
                        timestamp: sign.timestamp,
                        nonceStr: sign.noncestr,
                        signature: sign.signature,
                        jsApiList: [
                            'scanQRCode'
                        ]
                    });
                },
                'json'
        );
    }
});
```

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