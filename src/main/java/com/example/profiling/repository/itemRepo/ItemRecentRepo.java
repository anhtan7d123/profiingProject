package com.example.profiling.repository.itemRepo;

import com.example.profiling.entity.itemEntity.ItemRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecentRepo extends JpaRepository<ItemRecentLocation, Integer> {
    ItemRecentLocation findByRecentItemName(String recentItemName);

    ItemRecentLocation findByRecentItemId(Integer itemLocationId);
}
