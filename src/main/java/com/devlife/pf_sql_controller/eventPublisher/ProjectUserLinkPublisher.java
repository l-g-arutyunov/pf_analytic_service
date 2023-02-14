package com.devlife.pf_sql_controller.eventPublisher;

import com.devlife.pf_sql_controller.dto.asyncMessageModel.ProjectUserLinkAsyncModel;
import com.devlife.pf_sql_controller.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectUserLinkPublisher {
    public static final String PROJECT_BINDING = "projectUserLink-out-0:";
    public static final String EVENT = "event";
    private final StreamBridge streamBridge;

    public void sendMessage(ProjectUserLinkAsyncModel projectLinkAsyncModel, EventType event) {
        Message<ProjectUserLinkAsyncModel> message = MessageBuilder.withPayload(projectLinkAsyncModel).setHeader(EVENT, event.name()).build();
        streamBridge.send(PROJECT_BINDING, message);
    }
}
