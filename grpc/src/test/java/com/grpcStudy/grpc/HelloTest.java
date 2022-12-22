package com.grpcStudy.grpc;

import org.junit.Assert;
import org.junit.Test;

public class HelloTest {
    @Test
    public void sayHello_Test(){
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setFirstName("hyunsoo")
                .setLastName("Jang")
                .build();

        HelloClient helloWorldClient = new HelloClient("localhost", 8080);

        HelloResponse response = helloWorldClient.sayHello(helloRequest);

        Assert.assertEquals(response.getGreeting(), "Hi, hyunsoo Jang");
    }
}
