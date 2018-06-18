package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

    @GetMapping("/say-hello")
    public @ResponseBody String myFirstSpringView() {
        return "Hello from Spring!";
    }

    @GetMapping("/say-hello/{name}")
    public @ResponseBody String sayHello(@PathVariable String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/flights/from/{from}/to/{to}")
    public @ResponseBody String getFlights(
        @PathVariable String from,
        @PathVariable String to
    ) {
        return String.format("Finding flights from %s to %s...", from, to);
    }

    @GetMapping("/say-goodbye")
    public @ResponseBody String mySecondSpringView() {
        return "Goodbye World!";
    }

    @GetMapping("/increment/{n}")
    public @ResponseBody String increment(@PathVariable int n) {
        return String.format("%d + 1 = %d", n, n + 1);
    }

}
