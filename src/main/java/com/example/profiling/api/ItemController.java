package com.example.profiling.api;

import com.example.profiling.entity.itemEntity.ItemInfor;
import com.example.profiling.entity.itemEntity.ItemRecentLocation;
import com.example.profiling.repository.itemRepo.ItemInforRepo;
import com.example.profiling.repository.itemRepo.ItemRecentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/item")
public class ItemController {
    private final ItemInforRepo itemInforRepo;

    private final ItemRecentRepo itemRecentRepo;

    @PostMapping("/create-item")
    public ResponseEntity<?> createItemInfor(@RequestBody ItemInfor itemInfor){
        ItemInfor checkItemInfor = itemInforRepo.findByItemName(itemInfor.getItemName());
        if (checkItemInfor != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            List<ItemRecentLocation> itemRecentLocations = itemInfor.getItemRecentLocations();
            List<ItemInfor> itemInfors = new ArrayList<>();
            itemInfors.add(itemInforRepo.save(itemInfor));
            Map<String, String> newBody = new LinkedHashMap<>();
            if (itemRecentLocations != null){
                for (ItemRecentLocation itemRecentLocation : itemRecentLocations){
                    ItemRecentLocation checkItemLocation = itemRecentRepo.
                            findByRecentItemId(itemRecentLocation.getRecentItemId());
                    if (checkItemLocation != null){
                        checkItemLocation.setItemInfors(itemInfors);
                        itemRecentRepo.save(checkItemLocation);
                    }else {
                        newBody.put("Error: ", "item recent location id: " + itemRecentLocation.getRecentItemId() + " does not exist");
                    }
                }
            }
            if (newBody.isEmpty()){
                ItemInfor newItemInfor = itemInforRepo.findByItemId(itemInfor.getItemId());
                return new ResponseEntity<>(newItemInfor, HttpStatus.CREATED);
            }
            else {
                itemInforRepo.deleteById(itemInfor.getItemId());
                return new ResponseEntity<>(newBody, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/create-item-location")
    public ResponseEntity<?> createItemRecentLocation(@RequestBody ItemRecentLocation itemRecentLocation){
        ItemRecentLocation checkItemLocation = itemRecentRepo.findByRecentItemName(itemRecentLocation.getRecentItemName());
        if (checkItemLocation != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            itemRecentRepo.save(itemRecentLocation);
            return new ResponseEntity<>(itemRecentLocation, HttpStatus.CREATED);
        }
    }

    @GetMapping("/get-by-keyword/{keyword}")
    public ResponseEntity<?> getItemByAllPro(@PathVariable String keyword){
        List<ItemInfor> itemInfors = new ArrayList<>();
        itemInforRepo.findByAllProperties(keyword).forEach(itemInfors::add);
        return new ResponseEntity<>(itemInfors, HttpStatus.OK);
    }

}
