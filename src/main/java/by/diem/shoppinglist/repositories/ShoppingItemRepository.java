package by.diem.shoppinglist.repositories;

import by.diem.shoppinglist.entities.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
    List<ShoppingItem> findByUserUsername(String username);
}