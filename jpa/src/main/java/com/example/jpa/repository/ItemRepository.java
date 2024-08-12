package com.example.jpa.repository;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
        Optional<Item> findItemsByItemNameAndItemStatus(String itemName, Status status);
    Page<Item> findByItemNameStartingWith(String itemName, Pageable pageable);
}
