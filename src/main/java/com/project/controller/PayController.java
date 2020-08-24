package com.project.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.project.pojo.Alipay;
import com.project.pojo.Company;
import com.project.pojo.Loaned;
import com.project.service.ICompanyService;
import com.project.service.ILoanedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
public class PayController {

    @Resource
    private ILoanedService loanedService;
    @Resource
    private ICompanyService companyService;

    @RequestMapping("/pay")
    public   void   pay (HttpServletRequest httpRequest,
                         HttpServletResponse httpResponse, Alipay alipay)   throws ServletException, IOException {
//        Alipay alipay = new Alipay();
//        alipay.setPrice(1000);
//        alipay.setSubject("mashaladi");
//        alipay.setSubject_id("123");
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

        AlipayClient alipayClient =  new DefaultAlipayClient( "\t\n" +
                "https://openapi.alipaydev.com/gateway.do" ,
                "2021000119631264", "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCdpfDTQpcvGvQ5Jm5uTzAS6yozCGalmYU1pYdEtDSPfFqkQkTCRRFO7OgA16TAy+bFLTuGEVDlYzfEPeNYXgP4xjopoguh8Me4t9NSQTsO5yMEoGKeEqjJQEUx0uZoJH4WQ35YFXk52Vsz7LEbPJjikMFJgMR6CeK/9+X1fJoUwhafgJTbG1gz+fqGObRTdUrbzwbcQC62Ea6kBZVyOoP35723bu8SZ2MNKgY+gPVK9gO+OKhQxhxSe9FReprTBay7KV2xpq29PRpnN9UlWa3t4M+44ap/f11VrNsm0lDBIrtRNpuHLpOihmiV1WMZAvyz2O8Fk4CvE5t6KViQJ4lRAgMBAAECggEBAJj40WOdxSffRsZB5XxCeMqSyML2Tuw3uMvikAx5tFra9vVWnHPKI05rkYBnv6WY9Ze1GYIxQUck7JMO3pD7wWzfpMaLSDBefN5f+b1iMJV4cUFnA+qQ/pkyNTJwjETXzEV8PpfoVlZs9BPBg+WC+nM2tWfGfGBTQWS3frujqbujQuJ96260oNfuw7daKT91jycNIkGa/bADNCcFq+MIH07tNg2ov89tjEiDKGqikSNgDK3nzME9ayV8BePVW5aODeSCOchTfkbfQ4uCmzSYf8VjTXkdHRvgxNZf5wDWroVD/qjerk8NriN1FsoWIUwjkoeb8Y09Rv+1AzmAerqvBwUCgYEA7Dg/MFOcwssBfXBcyD0SSMBWoK/d8EUWGjmeV2upp5GIPrNswcVyQkuIiu+PH1qS4ypEwt5F5uKAUuWUt5cdgK7Rsz2Z4gfaO/nq7QsraD4TDPTaeqrWwngw9MeUY01wpPOKGoAWAKWGWMJibxgNtdGnNrLVAtTcIZb4Vd7GdxcCgYEAqtli0lMzoPcctl6Fgw6aHcG24RR8n1uPFcsimR49iaZC4NSJD5Qyd5mz/uXljfQWAzxLGJ7bs3aYhNfHcLjd9RKFcZwQNw7I2QNwR97YNymuul7lAzspuBtHKsKOAuqpglVDU5FTvNZ+lNfadjwfdV3PeX60UF4BuwkkWFU1w9cCgYB3uQpNtBL/p2RZ71kXI9zggptlVb1myZ0qLYnTRP33sAsdvFKi4y/F6gKKaGBhw8Yu3H19RqqnGqVvXmzBoBWT4wblkZMsvE4fl8s52S9m5g9Bwdf51ByE8+XcKktLCTTOZaDZTZHZQguVcO/y3n4UaO4XCwxrIvdvDtVOsFpmgQKBgQCX9kuDsCWX4rRTMbenXnGOTiMQCFzgkVuvksLvVop9yLhrqh9PEA3je38yFeIcZSK/Nf3FpGbeNXprjlIC3SvvM84lwx5ZGnljLT/NbVT7m8OiWY1xVkpBS2H9kpcl2Pt0xcngpr7IlDB6y5Ow3sFMb2h/NEfFxBfd+L+vIft0eQKBgQDFwL4371msxnDK5A8MvXlc1kkyzOJM47OBK8+pPzn82XreRXydqCz0STtqikxWMVafQ+UHfbE6GhLWiSJ30WhAOHd6mDheDekArYapeAAYfEFsmWrzvL9Pp+yb3TsTuS/8/KlnNj7whx9wkaOUIIOitbZ7aM0NgmmXJLY7+9JFqQ==", "json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnaXw00KXLxr0OSZubk8wEusqMwhmpZmFNaWHRLQ0j3xapEJEwkURTuzoANekwMvmxS07hhFQ5WM3xD3jWF4D+MY6KaILofDHuLfTUkE7DucjBKBinhKoyUBFMdLmaCR+FkN+WBV5OdlbM+yxGzyY4pDBSYDEegniv/fl9XyaFMIWn4CU2xtYM/n6hjm0U3VK288G3EAuthGupAWVcjqD9+e9t27vEmdjDSoGPoD1SvYDvjioUMYcUnvRUXqa0wWsuyldsaatvT0aZzfVJVmt7eDPuOGqf39dVazbJtJQwSK7UTabhy6TooZoldVjGQL8s9jvBZOArxObeilYkCeJUQIDAQAB", "RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new AlipayTradePagePayRequest(); //创建API对应的request
        //支付宝回调
        alipayRequest.setReturnUrl( "http://www.baidu.com" );
        alipayRequest.setNotifyUrl( "http://[240e:bf:d205:f365:197f:d409:3244:27da]:8080/returlCallBack" ); //在公共参数中设置回跳和通知地址
        // alipayRequest.putOtherTextParam("app_auth_token", "201611BB8xxxxxxxxxxxxxxxxxxxedcecde6");//如果 ISV 代商家接入电脑网站支付能力，则需要传入 app_auth_token，使用第三方应用授权；自研开发模式请忽略
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"" + uuid + "\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":" + alipay.getPrice() + ","  +
                "    \"subject\":\"" + alipay.getSubject() + "\","  +
                "    \"body\":\"" + alipay.getSubject_id() + "\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"sys_service_provider_id\":\"" + alipay.getSubject_id() + "\""  +
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

    @RequestMapping("/returlCallBack")
    public String returlCallBack(HttpServletRequest request , HttpServletResponse httpResp) throws AlipayApiException {
        Map<String, String> paramsMap = convertRequestParamsToMap(request);
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4KI4xMgIJsSWsAUp45FitkCleoj5ZCYAIOdKY/UYHBwTwttXCEe1ADFCB8VrIT1IdobupdBA/yjhCSLd+zeH2EvpdpMtHBxaipM8CXW+JciJIRc10JpY3E1qWWvmLMF5FSQyWzkiOqnIgKt9mIIlB6TGXZb/WSXnrVCHcE1N0/gQsqddqBBdum+ejm0HywXms2zXE2uclgTJnxM5DZ3D/FywfEGtjiaFB2SQaUMHpOb0O0XrYwkzQj/zcGUcnmIGEifik4Ek66DJiMzTZjbqn55Fr34G7V4MuDN7eTSkta4lTyTi94/2wNw7aAMaxZFqpREH4qdxp1l7yuS5mjIM6wIDAQAB", "utf-8", "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println("成功");
            int money = (int) Double.parseDouble(paramsMap.get("invoice_amount"));
            int loid = Integer.parseInt(paramsMap.get("body"));
            System.out.println("贷款记录ID" + loid + "金额" + money);


            Loaned loanedServiceByloid = loanedService.findByloid(loid);
            loanedServiceByloid.setRmoney(loanedServiceByloid.getRmoney() - money);
            System.out.println(loanedServiceByloid);
            if (loanedServiceByloid.getRmoney() <= 0) {
                loanedServiceByloid.setLstatus(true);
                // 信用加1


            }
            boolean b = loanedService.updateLoaned(loanedServiceByloid);
            System.out.println("修改"+b);

            // 公司账户加钱!
            Company company = companyService.findByid(1);
            company.setAccount(company.getAccount() + money);
            companyService.updateCredit(company);
            System.out.println("公司现金！" + company.getAccount());

            return "success";
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            System.out.println("失败");
            return "fail";
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }


}
