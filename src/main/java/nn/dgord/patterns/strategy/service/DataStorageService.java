package nn.dgord.patterns.strategy.service;

import java.util.List;

public interface DataStorageService<T, I> {
    List<T> getAllEntityData();
    T getEntityData(I entityIdentifier);
}
