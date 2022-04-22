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
        return orderService.addOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrder(){

        String uri = "http://localhost:8070/api/v1/technician";
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
        }
        Integer finalTechId = techId;

        restTemplate = new RestTemplate();
        String reqBody = "{\"id\":"+finalTechId+",\"queue\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reqBody,headers);

        String res = restTemplate.postForObject(uri_update, entity, String.class);
        System.out.println(res);

        new Thread(() -> {

            try
            {
                Thread.sleep(10000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            RestTemplate restTemplate2 = new RestTemplate();
            String body = "{\"id\":"+finalTechId+",\"queue\":-1}";
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entitys = new HttpEntity<String>(body,header);
            String resp = restTemplate2.postForObject(uri_update, entitys, String.class);
            System.out.println(resp);

        }).start();
        return orderService.getAllOrder();
    }
}
