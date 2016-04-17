package me.bcfh.myboard.item.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import me.bcfh.myboard.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
