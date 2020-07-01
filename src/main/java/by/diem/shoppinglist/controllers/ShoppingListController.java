package by.diem.shoppinglist.controllers;

import by.diem.shoppinglist.entities.ShoppingItem;
import by.diem.shoppinglist.entities.User;
import by.diem.shoppinglist.repositories.ShoppingItemRepository;
import by.diem.shoppinglist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShoppingListController {
    private final ShoppingItemRepository repository;
    private UserRepository userRepository;

    @Autowired
    public ShoppingListController(ShoppingItemRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        model.addAttribute("items", repository.findByUserUsername(principal.getName()));
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    @PostMapping
    public String newShoppingItem(ShoppingItem shoppingItem, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        shoppingItem.setUser(user);
        repository.save(shoppingItem);
        return "redirect:/";
    }

    @DeleteMapping("{id}")
    public String deleteShoppingItem(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
