package com.demo.schedule.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    private int currentPage;
    private int pageSize;
    private long total;
    private List<T> data;

}
