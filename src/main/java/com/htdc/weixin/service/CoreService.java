package com.htdc.weixin.service;

import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.List;

/**
 * Created by FirenzesEagle on 2016/5/30 0030.
 * Email:liumingbo2008@gmail.com
 */
public interface CoreService {

    /**
     * HttpGet请求
     *
     * @param urlWithParams
     * @throws Exception
     */
    public void requestGet(String urlWithParams) throws IOException;

    /**
     * HttpPost请求
     *
     * @param url
     * @param params
     * @throws ClientProtocolException
     * @throws IOException
     */
    public void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException;

    /**
     * 刷新消息路由器
     */
    public void refreshRouter();

    /**
     * 路由消息
     *
     * @param inMessage
     * @return
     */
    public WxMpXmlOutMessage route(WxMpXmlMessage inMessage);

    /**
     * 通过openid获得基本用户信息
     *
     * @param openid
     * @param lang
     * @return
     */
    public WxMpUser getUserInfo(String openid, String lang);

}
