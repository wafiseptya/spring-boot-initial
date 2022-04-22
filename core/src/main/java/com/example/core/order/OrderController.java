package com.example.core.order;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("api/v1/order")
@RestController
public class OrderController{
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public boolean createOrder(@RequestBody Order order){

        System.out.println(order.getPhone_brand());
        String phonePlatform;
        switch (order.getPhone_brand()){
            case "xiaomi":
                phonePlatform = "Android";
                break;
            case "samsung":
                phonePlatform = "Android";
                break;
            case "iphone":
                phonePlatform = "IOS";
                break;
            case "ipad":
                phonePlatform = "IOS";
                break;
            case "oppo":
                phonePlatform = "Android";
                break;
            default:
                return false;
        }
        System.out.println(phonePlatform);
        Integer duration;
        Integer price;
        switch (order.getDamage()){
            case "Mati total":
                duration = 120;
                price = 300000;
                break;
            case "Boot loop":
                duration = 90;
                price = 250000;
                break;
            case "Batre bocor":
                duration = 60;
                price = 100000;
                break;
            case "Layar retak":
                duration = 60;
                price = 200000;
                break;
            default:
                return false;
        }
        System.out.println(duration);

        String uri = "http://localhost:8070/api/v1/technician/"+phonePlatform;
        String uri_customer_create = "http://localhost:8070/api/v1/customer";
        String uri_customer_update = "http://localhost:8070/api/v1/customer/update";
        String uri_update = "http://localhost:8070/api/v1/technician/update";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        Integer techId = 0;
        try {
            result = result.substring(1, result.length() - 1);
            JSONObject jsonObject = new JSONObject(result);
            techId = jsonObject.getInt("id");
        }catch (JSONException err){
            System.out.println(err);
            return false;
        }
        // Send api to create customer order
        restTemplate = new RestTemplate();
        String reqBody = "{\"name\":\""+order.getName()+
                "\",\"email\":\""+order.getEmail()+
                "\",\"address\":\""+order.getAddress()+
                "\",\"phone_number\":\""+order.getPhone_number()+
                "\",\"phone_brand\":\""+order.getPhone_brand()+
                "\",\"damage\":\""+order.getDamage()+
                "\",\"price\":\""+price+
                "\",\"status\":\"on progress\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(reqBody);
        HttpEntity<String> entity = new HttpEntity<String>(reqBody,headers);

        String res = restTemplate.postForObject(uri_customer_create, entity, String.class);
        System.out.println(res);

        // Send api to update technician queue
        Integer finalTechId = techId;

        restTemplate = new RestTemplate();
        reqBody = "{\"id\":"+finalTechId+",\"queue\":1}";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<String>(reqBody,headers);

        res = restTemplate.postForObject(uri_update, entity, String.class);
        System.out.println(res);

        // async
        new Thread(() -> {

            try
            {
                //sleep until the fixing duration completed
                Thread.sleep(duration * 1000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            // send API to update queue
            RestTemplate restTemplate2 = new RestTemplate();
            String body = "{\"id\":"+finalTechId+",\"queue\":-1}";
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entitys = new HttpEntity<String>(body,header);
            String resp = restTemplate2.postForObject(uri_update, entitys, String.class);
            System.out.println(resp);


            // Send api to update customer order
            restTemplate2 = new RestTemplate();
            body = "{\"email\":\""+order.getEmail()+
                    "\",\"status\":\"finished\"}";
            header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            entitys = new HttpEntity<String>(body,header);

            resp = restTemplate2.postForObject(uri_customer_update, entitys, String.class);
            System.out.println(resp);

        }).start();
        return orderService.addOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }
}
