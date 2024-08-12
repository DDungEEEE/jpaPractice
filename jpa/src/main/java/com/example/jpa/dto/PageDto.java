package com.example.jpa.dto;

import com.example.jpa.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageDto {
    private List<Item> itemList;
    private int page;
}
