package com.mall.lenovoMall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class searchData implements Serializable {
    public String search;

    public String currentPage;

    public String pageSize;
}
