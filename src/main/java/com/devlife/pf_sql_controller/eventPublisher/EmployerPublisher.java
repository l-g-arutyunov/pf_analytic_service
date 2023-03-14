package com.devlife.pf_sql_controller.eventPublisher;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.enums.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class EmployerPublisher {
    public static final String EMPLOYER_BINDING = "employer-out-0";
    public static final String EVENT = "event";
    private final StreamBridge streamBridge;

    public void sendMessage(EmployerDto employerDto, EventType event) {
        Message<EmployerDto> message = MessageBuilder.withPayload(employerDto).setHeader(EVENT, event.name()).build();
        streamBridge.send(EMPLOYER_BINDING, message);
    }
}
