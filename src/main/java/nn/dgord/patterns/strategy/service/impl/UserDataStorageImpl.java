package nn.dgord.patterns.strategy.service.impl;

import nn.dgord.patterns.strategy.domain.User;
import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.exception.UserNotFoundException;
import nn.dgord.patterns.strategy.service.DataStorageService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static nn.dgord.patterns.strategy.utils.Constants.USER_ID_FIRST;
import static nn.dgord.patterns.strategy.utils.Constants.USER_ID_SECOND;

public class UserDataStorageImpl implements DataStorageService<UserInfo, UUID> {
    private static List<User> usersData;

    static {
         usersData = new ArrayList<>() {{
            add(User.builder().userInfo(UserInfo.builder()
                    .lastVisit(LocalDateTime.of(2019, Month.SEPTEMBER, 3, 12, 0))
                    .password("root_1")
                    .userId(USER_ID_FIRST)
                    .username("jack_123gmail")
                    .balance(100_000L)
                    .build()).build());
            add(User.builder()
                    .userInfo(UserInfo.builder()
                            .username("jessieVentura1983")
                            .password("123admin")
                            .userId(USER_ID_SECOND)
                            .lastVisit(LocalDateTime.now())
                            .isActive(true)
                            .balance(30_000L)
                            .build())
                    .build());
        }};
    }

    @Override
    public List<UserInfo> getAllEntityData() {
        System.out.println("Received request to get entities...");
        return usersData.stream()
                .map(User::getUserInfo).collect(Collectors.toList());
    }

    @Override
    public UserInfo getEntityData(UUID entityIdentifier) {
        System.out.println("Received request to get user with id: " + entityIdentifier);
        return usersData.stream()
                .filter(user -> user.getUserInfo().getUserId().equals(entityIdentifier))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s doesn't exist.",
                        entityIdentifier)))
                .getUserInfo();
    }

    public List<Map<String, Long>> getListOfUsersWithBalance() {
        System.out.println("Received request to get users with their balances...");
        return usersData.stream()
                .map(User::getUserInfo)
                .map(userInfo -> Map.of(userInfo.getUsername(), userInfo.getBalance()))
                .collect(Collectors.toList());
    }
}
