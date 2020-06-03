package com.crowd.foreground.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.MaintainOrderStatusExtParams;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.crowd.foreground.config.AlipayConfig;
import com.crowd.foreground.entity.Address;
import com.crowd.foreground.entity.Order;
import com.crowd.foreground.entity.PriceItem;
import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.OrderService;
import com.crowd.foreground.service.ProjectService;
import com.crowd.foreground.service.UserService;
import javafx.scene.input.DataFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private Subject subject;

    @RequestMapping("/project/pay_step1")
    public String payStep1(@RequestParam("priceItemId") Integer priceitemid,
                           Model model){
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        PriceItem priceItem=projectService.getPriceItemByItemId(priceitemid);
        String projectName=projectService.getProjectNameByItemId(priceitemid);
        model.addAttribute("user",user);
        model.addAttribute("projectName",projectName);
        model.addAttribute("priceItem",priceItem);
        return "pay-step-1";
    }

    @GetMapping("/project/pay_step2")
    public String payStep2(@RequestParam("priceItemId") Integer priceitemid,
                           Model model) throws Exception {
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //获取支持项信息
        PriceItem priceItem=projectService.getPriceItemByItemId(priceitemid);
        //获取当前支持项对应的项目名称
        String projectName=projectService.getProjectNameByItemId(priceitemid);
        //获取当前登录用户的地址
        List<Address> addressList=userService.getAddressByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("projectName",projectName);
        model.addAttribute("priceItem",priceItem);
        model.addAttribute("addressList",addressList);
        return "pay-step-2";
    }

    @PostMapping("/project/pay_step2")
    public String order(@RequestParam("priceItemId") Integer priceItemId,
                      @RequestParam("addressid") Integer addressId,
                      @RequestParam("remark") String remark,
                      Model model) throws Exception{
        //获取当前登录用户
        subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //获取支持项信息
        PriceItem priceItem=projectService.getPriceItemByItemId(priceItemId);
        //获取当前支持项对应的项目名称
        String projectName=projectService.getProjectNameByItemId(priceItemId);
        //时间戳作为订单号
        Long t=System.currentTimeMillis();

        model.addAttribute("orderID",t);
        model.addAttribute("user",user);

        //订单信息
        Integer userid=user.getId();
        Integer projectid=priceItem.getProjectId();
        Integer itemid=priceItem.getId();
        Date date=new Date();
        double money=priceItem.getMoney();
        Integer status=0;
        Integer addressid=addressId;
        String remark1=remark;
        //更新数据库订单
        Order order=new Order(t,userid,projectid,itemid,date,money,status,addressid,remark1,null);
        orderService.saveOrder(order);
        return "pay-step-3";
    }

    @RequestMapping("/project/pay_step3")
    public void pay(@RequestParam("orderId") Long orderId,
                    HttpServletResponse response)throws Exception{
        Order order=orderService.getOrderById(orderId);
        String money=String.valueOf(order.getMoney());
        String projectName=orderService.getProjectNameByOrderID(orderId);
        String orderid=String.valueOf(orderId);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(orderid.getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(String.valueOf(money).getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    //同步回调处理方法
    @GetMapping("/project/pay_step4")
    public String return_url(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException,Exception{
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if(verify_result){//验证成功
            //处理标记付款成功
            Order order =orderService.getOrderById(Long.valueOf(out_trade_no));
            Integer projectid=order.getProjectid();
            if(order.getPayno()==null){
                //更新订单状态为已支付
                orderService.updateOrderById(Long.valueOf(out_trade_no),1,trade_no);
                //更新项目的众筹信息
                projectService.updateProject(projectid,order.getMoney());
            }
            return "pay-step-4";//跳转到订单信息页面
        }else{
            //该页面可做页面美工编辑
            System.out.println("验证失败");
            return "index";//跳转到首页
        }
    }

}
