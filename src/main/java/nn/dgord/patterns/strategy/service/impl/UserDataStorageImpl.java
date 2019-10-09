package nn.dgord.patterns.strategy.service.impl;

import nn.dgord.patterns.strategy.domain.info.user.UserInfo;
import nn.dgord.patterns.strategy.service.DataStorageService;

import java.util.List;
import java.util.UUID;

public class UserDataStorageImpl implements DataStorageService<UserInfo, UUID> {

    @Override
    public List<UserInfo> getAllEntityData() {
        return null;
    }

    @Override
    public UserInfo getEntityData(UUID entityIdentifier) {
        return null;
    }
}
