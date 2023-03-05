package com.devlife.pf_sql_controller.service.eventService;

import com.devlife.pf_sql_controller.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.enums.EventType;
import com.devlife.pf_sql_controller.exception.UserAlreadyExistsException;
import com.devlife.pf_sql_controller.exception.UserNotFoundException;
import com.devlife.pf_sql_controller.mapper.UserMapper;
import com.devlife.pf_sql_controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserConsumerService implements ConsumerService<UserAsyncModel> {
    public static final String EVENT = "event";
    public final UserRepository userRepository;
    public final UserMapper userMapper;

    @Override
    public void handle(Message<UserAsyncModel> message) {
        validate(message);

        final MessageHeaders headers = message.getHeaders();
        final UserAsyncModel userAsyncModel = message.getPayload();
        final EventType event = EventType.getByName(headers.get(EVENT, String.class));

        final Optional<User> userOpt = userRepository.findByExternalId(userAsyncModel.getId());

        if (event == EventType.CREATE) {
            if (userOpt.isPresent()) {
                throw new UserAlreadyExistsException(userAsyncModel.getId());
            }
            userRepository.save(userMapper.convertToEntity(userAsyncModel));
        }

        if (event == EventType.UPDATE) {
            User existsUser = userOpt.orElseThrow(() -> new UserNotFoundException(Objects.toString(userAsyncModel.getId())));
            final User userEntity = userMapper.convertToEntity(userAsyncModel);
            userEntity.setId(existsUser.getId());
            userRepository.save(userEntity);
    }

}

    private void validate(Message<UserAsyncModel> message) {
        Objects.requireNonNull(message.getHeaders().get(EVENT, String.class), "header \"event\" must be");

        UserAsyncModel payload = message.getPayload();
        Objects.requireNonNull(payload.getId(), "user id is require");
        Objects.requireNonNull(payload.getProfile(), "profile is require");
        Objects.requireNonNull(payload.getProfile().getNickname(), "nickname is require");
    }
}
