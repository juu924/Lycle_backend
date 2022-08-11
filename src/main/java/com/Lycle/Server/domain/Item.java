package com.Lycle.Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String store;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Builder
    public Item(Long id, String store,String name, Long price){
        this.id = id;
        this.store = store;
        this.name = name;
        this.price = price;
    }

}
