package com.example.demoproject2.service.grpc.impl;

import com.example.demoproject2.proto.CreateLogRequest;
import com.example.demoproject2.proto.LogServiceGrpc;
import com.example.demoproject2.service.grpc.LogGrpcServiceClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogGrpcServiceClientImpl implements LogGrpcServiceClient {
    @Value("${grpc.server.port}")
    private Integer port;

    @Value("${grpc.server.host}")
    private String host;
    private LogServiceGrpc.LogServiceBlockingStub client;

    @PostConstruct
    private void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        this.client = LogServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void createLog(CreateLogRequest createLogRequest) {
        client.createLog(createLogRequest);
    }
}


