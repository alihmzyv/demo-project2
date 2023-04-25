package com.example.demoproject2.service.impl;

import com.example.demoproject2.proto.CreateLogRequest;
import com.example.demoproject2.proto.LogResponse;
import com.example.demoproject2.proto.LogServiceGrpc;
import com.example.demoproject2.service.LogGrpcServiceClient;
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
    public LogResponse createLog(CreateLogRequest createLogRequest) {
        return client.createLog(createLogRequest);
    }
}


