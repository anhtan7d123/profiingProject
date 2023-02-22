package com.example.profiling.repository.itemRepo;

import com.example.profiling.entity.itemEntity.ItemInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInforRepo extends JpaRepository<ItemInfor, Integer> {
    ItemInfor findByItemId(Integer itemId);

    ItemInfor findByItemName(String itemName);

    @Query("select i from ItemInfor i where " +
            "i.itemName = ?1 or " +
            "i.itemLabel = ?1 or " +
            "i.itemNumber = ?1 or " +
            "i.itemSize = ?1 or " +
            "i.itemColor = ?1")
    List<ItemInfor> findByAllProperties(String keyword);
}
