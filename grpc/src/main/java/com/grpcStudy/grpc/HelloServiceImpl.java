package com.grpcStudy.grpc;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase{

    @Override
    public void hello(HelloRequest helloRequest, StreamObserver<HelloResponse> responseStreamObserver){
        String greeting = new StringBuilder()
                .append("hello, ")
                .append(helloRequest.getFirstName())
                .append(" ")
                .append(helloRequest.getLastName())
                .toString();

        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseStreamObserver.onNext(helloResponse);
        responseStreamObserver.onCompleted();
    }

}
