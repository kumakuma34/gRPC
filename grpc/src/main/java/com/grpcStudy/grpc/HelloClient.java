package com.grpcStudy.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloClient {
    private static final Logger logger = Logger.getLogger(HelloClient.class.getName());
    private ManagedChannel channel;
    private HelloServiceGrpc.HelloServiceBlockingStub blockingStub;
    private HelloServiceGrpc.HelloServiceStub asyncStub;
    
    public HelloClient(String host, int port){
        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext());
        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
        asyncStub = HelloServiceGrpc.newStub(channel);
    }
    public HelloClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public HelloResponse sayHello(HelloRequest helloRequest)
    {
        HelloResponse helloResponse=null;

        try
        {
            helloResponse= blockingStub.hello(helloRequest);
        }
        catch (StatusRuntimeException e)
        {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return helloResponse;
    }

}
