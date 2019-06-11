package ind.ck.controller;

import ind.ck.task.TaskTest;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/greet")
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Map<String, Object> helloWorld(HttpRequest request ){
        Map<String, Object> results = new HashMap<>(16);
        results.put("hello", "Hello World!");
        return results;
    }
}
