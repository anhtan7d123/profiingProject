package com.example.spofiling.repository.itemRepo;

import com.example.spofiling.entity.itemEntity.ItemInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInforRepo extends JpaRepository<ItemInfor, Integer> {
    ItemInfor findByItemId(Integer itemId);
}
