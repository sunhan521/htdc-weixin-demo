package com.htdc.weixin.controller;

import com.google.gson.Gson;
import com.htdc.weixin.interceptor.OAuthRequired;
import com.htdc.weixin.util.Constants;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    protected WxMpConfigStorage configStorage;
    @Autowired
    protected WxMpService wxMpService;

    @RequestMapping("/hello")
    @OAuthRequired
    public String hello(ModelMap modelMap, HttpSession session) {
        WxMpUser wxMpUser = (WxMpUser) session.getAttribute("wxMpUser");
        String openId = wxMpUser.getOpenId();
        modelMap.put("openId", openId);
        modelMap.put("wxMpUser", new Gson().toJson(wxMpUser));
        return "hello";
    }

    @RequestMapping("/world")
    @OAuthRequired
    public String world(ModelMap modelMap, HttpSession session) {
        WxMpUser wxMpUser = (WxMpUser) session.getAttribute("wxMpUser");
        String openId = wxMpUser.getOpenId();
        modelMap.put("openId", openId);
        modelMap.put("wxMpUser", new Gson().toJson(wxMpUser));
        return "world";
    }

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
}