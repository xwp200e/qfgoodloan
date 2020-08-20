package com.project.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PayController {
    @RequestMapping("/pay")
    public   void   pay (HttpServletRequest httpRequest,
                         HttpServletResponse httpResponse)   throws ServletException, IOException {
        AlipayClient alipayClient =  new DefaultAlipayClient( "\t\n" +
                "https://openapi.alipaydev.com/gateway.do" ,
                "2021000119609573", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSBuO2l/2iLILQ2/4iKDI3BwadTpbFm+Btd/uWJJkE/e9K1Yx4cORyAqtGZ2YP5c6441jOvtXHin1IgXuyqgEDh/cTbyeAdu0VWqD57hOSHkyYrbUpaLDyYNT972qmPrqIUXOy/qviEs+k3Bwo1MeOVgi7hAb3S8yUGCe8M0wFa1C2OIZAjZbqp+QfOVne4UYzargA0MulW98dOsty7BTP1kX3JHZW2SJk6WHPM+LYO34ymOJPiGYFzU7aV7YjodKGpuO87ERJNjl4jbnZv+5eQ4OD81iOq+/QzKeN5/0PGJM7wZEYcI48Mlvy+86eDdhMev/jbQiq0KCs4PCdi2y9AgMBAAECggEABPEqv8FBGj37Z2oqnalSmb4pISXJwIJYBBxtbVVrwFl/wb6cdG8NrDYe6dESCTOz3VoEZWrb/VbYip2nPBK42g+98vBuG9DqkTByBkNici2IVhp66R3b7zgLvVRe/aVd1sM68THhS1Nrp56zbUZWQqDyxrXfLntoPxLaNWpfBkfgSRGmP2uUrGuA8BhZfHOWEOF5wHxX5D2XhsC2hcImatoEIRKFZUsxLIYblgLajSS3ypmLgor3XicGF9WDXvoaP7UG7Ase+j+fdc+n80lqZv5SjXxSb9InpYtJVNNGrg0MA/dTOFTEVLtyGr19TuPCPV+7+u1xZFtElQ1Tc0pYgQKBgQDe7h7hppacyO4QYN7Me2/9FWDpEvipkZyFSmFbhMKd7XQeViFntZ3WdBAASPSV+xcylCFn8uIu4r9lRaNXBoc8kUR98VrNUXvHmhl3l341x0sfVCuOGUwMWR48Xv23zXV26OaM3NXliJtBYCGq2aC0wUCcHhmh3aE+eb4Z/zDi8QKBgQCnsFTRB6IEhPdN50tdgyyqtUpevvkZd/20RceD9XifKlritqK+UcFcId6tdOI1Fnld7xshP4I3Af/NjObJPiyuN/MPkZ9tk3mc3sl3PizAKQHoerO3g2MuhljoSO4FZEtiYbSPd9rBdYU7j+XoKsum/boeNkW+xi8LXhUpMAlOjQKBgQCCRw/e+LaNu1J8Wc/6GazRbtvqeT6+Aa1Bq70HeacuGaVBUT5GWDl8KOls8TWWLF1bHnwDPkp18ea97RtOcS848v/exdw1nofClsiJPgvwq524Gj011oy9rprf1vdYoYYizmplKW0KrXFlnCMwr+udlk4GrgR2tolTdFvm314XUQKBgA7i7oSoAtAX5cR6tgwVKw/0jCehGIgUSOeKEk46qnymy+64e/YdV+cP8q6/Y52G4ztdkLEA7FRpzwW2bn6LEHhe6ysEqHyXzQIKHSSC1oIwocS7r9yow1NMBfSJFOhRoQeY2OiroTXvcuZ4vdZAhlfKyo0IEGUBPwfpUEOZyhm5AoGBALqGBknLof3qo/NRGojaWUE6+9A62xKFrxrU+nelHMeG6Z96gcVhtLYaT/PsF18Es61bFjfjY5WNJsShqYLebMNCkD/4p8gWpqeVmJqtMHKTsXkgb98npaVt4dsGxnNaKQwWxx6FTbPB64cqzJI13uq5o6rJHeqza0RWZfIvrN7u", "json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlFs+KnxH618OANQVtxcx1Q9BCtC9aQlDlgLBBrGWm2vKQmqpYcJy0h+JmN+cYmW1lSEBhwX+8MY4UXBLK29P9ZFriTFrWKiuUFPSQFI8BQaExEukPZEqZGq1a1NXqOoMLL+X3fM18tqXeUJ6OfDyEjTxSBxn6BuBt0JJtiU09K74IJMw9Y9e/c/9/6eiEbdipwSq2l27Lw/nSKqbueVykcXcFTrymlZmFwEAnAo7w3tCAjy1efubiaWNtnX9z6bXLH/ONpzfNxNEd4mddaK620nFsV4emFj1ujHs/kQhDjad/WRrC9zWZtAn2DalKwbKwpZd+yVyRQL4V837vNzgGwIDAQAB", "RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest(); //创建API对应的request
        //支付宝回调
        alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
        alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址
        // alipayRequest.putOtherTextParam("app_auth_token", "201611BB8xxxxxxxxxxxxxxxxxxxedcecde6");//如果 ISV 代商家接入电脑网站支付能力，则需要传入 app_auth_token，使用第三方应用授权；自研开发模式请忽略
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"20230320010101001\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":1000001.44,"  +
                "    \"subject\":\"法拉利\","  +
                "    \"body\":\"定金\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset="  + "utf-8");
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
