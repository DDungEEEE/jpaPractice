package com.example.jpa.controller;

import com.example.jpa.dto.PageDto;
import com.example.jpa.entity.Item;
import com.example.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {


    private final ItemRepository itemRepository;

    @PostMapping("/saveItem")
    public void addItem(@RequestBody Item item){
        itemRepository.save(item);
    }

    @PostMapping("/findByItemNameAndStatus")
            public ResponseEntity<?> find(@RequestBody Item item)
    {
        Optional<Item> findItem = itemRepository.findItemsByItemNameAndItemStatus(item.getItemName(), item.getItemStatus());
        return new ResponseEntity<>(findItem, HttpStatus.OK);
    }

    @GetMapping("/findByName/{page}")
    public ResponseEntity<?> findName(@PathVariable int page){
        Sort sort = Sort.by(Sort.Direction.DESC, "itemName");
        PageRequest pageRequest = PageRequest.of(page, 20, sort);

        Page<Item> itemResult = itemRepository.findByItemNameStartingWith("son", pageRequest);
        List<Item> content = itemResult.getContent();
        int totalPages = itemResult.getTotalPages();

        PageDto pageDto = PageDto.builder()
                .page(totalPages)
                .itemList(content)
                .build();

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
