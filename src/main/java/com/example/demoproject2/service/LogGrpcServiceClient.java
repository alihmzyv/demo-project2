package com.example.demoproject2.service;

import com.example.demoproject2.proto.CreateLogRequest;
import com.example.demoproject2.proto.LogResponse;

public interface LogGrpcServiceClient {
    LogResponse createLog(CreateLogRequest createLogRequest);
}
