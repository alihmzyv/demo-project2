package com.example.demoproject2.aspect;

import com.example.demoproject2.consts.OperationService;
import com.example.demoproject2.consts.OperationType;
import com.example.demoproject2.model.dto.log.LogJsonDto;
import com.example.demoproject2.proto.CreateLogRequest;
import com.example.demoproject2.service.grpc.LogGrpcServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.example.demoproject2.consts.OperationType.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
@Aspect
public class ServiceLogAspect {
    ObjectMapper objectMapper;
    LogGrpcServiceClient logGrpcServiceClient;

    @Pointcut("execution(public * com.example.demoproject2.service.impl.*.create*(..))")
    private void forCreate() {}

    @Pointcut("execution(public * com.example.demoproject2.service.impl.*.update*(..))")
    private void forUpdate() {}

    @Pointcut("execution(public * com.example.demoproject2.service.impl.*.delete*(..))")
    private void forDelete() {}

    @SneakyThrows
    @AfterReturning(pointcut = "forCreate()", returning = "result")
    public void afterCreateAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, CREATE);
        logGrpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    @AfterReturning(pointcut = "forUpdate()", returning = "result")
    public void afterUpdateAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, UPDATE);
        logGrpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    @AfterReturning(pointcut = "forDelete()", returning = "result")
    public void afterDeleteAdvice(JoinPoint joinPoint, Object result) {
        CreateLogRequest logRequest = createLogRequest(joinPoint, result, DELETE);
        logGrpcServiceClient.createLog(logRequest);
    }

    @SneakyThrows
    private CreateLogRequest createLogRequest(JoinPoint joinPoint, Object result, OperationType operationType) {
        Object[] args = joinPoint.getArgs();
        LogJsonDto logJson = LogJsonDto.builder()
                .args(args)
                .result(result)
                .build();
        String simpleClassName = joinPoint.getTarget().getClass().getSimpleName();
        log.info("simple class name: {}", simpleClassName);
        int operation_service = OperationService.findByClassName(simpleClassName)
                .map(Enum::ordinal)
                .orElseThrow(() -> new IllegalArgumentException("Operation service not found")); //not possible
        String username = (String) args[0];
        return CreateLogRequest.newBuilder()
                .setUsername(username)
                .setOperationService(operation_service)
                .setOperationType(operationType.ordinal())
                .setJson(objectMapper.writeValueAsString(logJson))
                .build();
    }
}
