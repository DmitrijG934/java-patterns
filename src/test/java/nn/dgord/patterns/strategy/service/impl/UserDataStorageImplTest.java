package nn.dgord.patterns.strategy.service.impl;

import nn.dgord.patterns.strategy.BaseTest;
import nn.dgord.patterns.strategy.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static nn.dgord.patterns.strategy.utils.Constants.USER_ID_FIRST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDataStorageImplTest extends BaseTest {
    private UserDataStorageImpl userDataStorage;

    @Before
    public void setUp() {
        userDataStorage = new UserDataStorageImpl();
    }

    @Test
    public void when_instanceCreating_thenObjectWasCreated() {
        assertNotNull(userDataStorage);
    }

    @Test
    public void when_getAllUsers_thenResultIsNotNull() {
        assertNotNull(userDataStorage.getAllEntityData());
    }

    @Test
    public void when_getAllUsers_thenResultLengthOfListIsTwo() {
        assertEquals(2, userDataStorage.getAllEntityData().size());
    }

    @Test
    public void when_getUserByIdentifier_thenUserWasFound() {
        assertNotNull(userDataStorage.getEntityData(USER_ID_FIRST));
    }

    @Test(expected = UserNotFoundException.class)
    public void when_getNonexistentUser_thenResultIsUserNotFoundException() {
        userDataStorage.getEntityData(UUID.randomUUID());
    }

    @Test
    public void when_getUsersWithBalance_thenResultIsNotNull() {
        assertNotNull(userDataStorage.getListOfUsersWithBalance());
    }
}