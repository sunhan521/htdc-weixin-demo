<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">

    <script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js" type="text/javascript"></script>
    <script type="text/javascript">
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
    </script>
</head>
<body>
this is a page.

<br>
openId:${openId}
wxMpUser:${wxMpUser}

<a href="javascript:;" style="font-size:15px;" class="jssdk">aaa</a>
</body>
</html>
