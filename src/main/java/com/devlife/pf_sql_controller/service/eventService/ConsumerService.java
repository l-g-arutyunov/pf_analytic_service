package com.devlife.pf_sql_controller.service.eventService;

import org.springframework.messaging.Message;

public interface ConsumerService<T> {
    void handle(Message<T> message);
}
