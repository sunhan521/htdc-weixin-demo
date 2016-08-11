package com.htdc.weixin.config;

import com.htdc.weixin.util.Constants;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunhan on 6/8/2016.
 */
@Configuration
public class WxMpConfig {
    protected static final String APP_ID = Constants.getConfig("weixin.appId");
    protected static final String APP_SECRET = Constants.getConfig("weixin.secret");
    protected static final String TOKEN = Constants.getConfig("weixin.token");
    protected static final String AES_KEY = Constants.getConfig("weixin.aesKey");
    protected static final String REDIRECT_URI = Constants.getConfig("weixin.oauth2redirectUri");


    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(WxMpConfig.APP_ID);
        configStorage.setSecret(WxMpConfig.APP_SECRET);
        configStorage.setToken(WxMpConfig.TOKEN);
        configStorage.setAesKey(WxMpConfig.AES_KEY);
        configStorage.setOauth2redirectUri(WxMpConfig.REDIRECT_URI);
        return configStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

}
