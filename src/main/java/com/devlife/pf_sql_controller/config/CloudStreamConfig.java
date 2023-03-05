package com.devlife.pf_sql_controller.config;

import com.devlife.pf_sql_controller.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.pf_sql_controller.service.eventService.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@Configuration
@RequiredArgsConstructor
public class CloudStreamConfig {
    private final ConsumerService<UserAsyncModel> userConsumerService;

    @Bean
    public Consumer<Message<UserAsyncModel>> userConsumer() {
        return userConsumerService::handle;
    }

}
