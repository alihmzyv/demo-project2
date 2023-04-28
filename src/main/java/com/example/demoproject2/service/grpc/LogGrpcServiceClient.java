package com.example.demoproject2.service.grpc;

import com.example.demoproject2.proto.CreateLogRequest;

public interface LogGrpcServiceClient {
    void createLog(CreateLogRequest createLogRequest);
}
