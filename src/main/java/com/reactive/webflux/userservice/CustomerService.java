package com.reactive.webflux.userservice;

import com.reactive.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CustomerService {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<User> getCustomers()  {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerService::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new User( "customerid"+i,"customer",i+1))
                .collect(Collectors.toList());
    }


    public Flux<User> getCustomersStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new User( "customerid"+i,"customer",i+1));
    }


    public Flux<User> getCustomerList()  {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new User( "customerid"+i,"customer",i+1));
    }


    public List<User> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<User> customers = getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }



    public Flux<User> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<User> customers = getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
}

