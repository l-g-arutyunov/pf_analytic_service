package com.devlife.pf_sql_controller.eventPublisher;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectPublisher {
    public static final String PROJECT_BINDING = "project-out-0";
    public static final String EVENT = "event";
    private final StreamBridge streamBridge;

    public void sendMessage(ProjectDto projectDto, EventType event) {
        Message<ProjectDto> message = MessageBuilder.withPayload(projectDto).setHeader(EVENT, event.name()).build();
        streamBridge.send(PROJECT_BINDING, message);
    }
}
