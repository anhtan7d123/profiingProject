package com.example.spofiling.repository.itemRepo;

import com.example.spofiling.entity.itemEntity.ItemRecentLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRecentRepo extends JpaRepository<ItemRecentLocation, Integer> {
}
