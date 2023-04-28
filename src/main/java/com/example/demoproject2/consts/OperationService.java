package com.example.demoproject2.consts;

import java.util.Arrays;
import java.util.Optional;

public enum OperationService {
    USER_SERVICE,
    AGENT_SERVICE,
    CASHIER_SERVICE;

    public static Optional<OperationService> findByClassName(String simpleClassName) {
        return Arrays.stream(OperationService.values())
                .filter(value ->
                        simpleClassName.toLowerCase()
                                .contains(value.name().toLowerCase().replace("_", "")))
                .findAny();
    }
}
