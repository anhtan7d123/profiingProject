package com.example.spofiling.repository.itemRepo;

import com.example.spofiling.entity.itemEntity.ItemRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecentRepo extends JpaRepository<ItemRecentLocation, Integer> {
    ItemRecentLocation findByRecentItemName(String recentItemName);

    ItemRecentLocation findByRecentItemId(Integer itemLocationId);
}
