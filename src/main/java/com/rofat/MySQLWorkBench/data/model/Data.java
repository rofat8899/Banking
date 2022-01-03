package com.rofat.MySQLWorkBench.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Data {
    private Merchant merchant;
    private List<Promotions> promotions;
}
