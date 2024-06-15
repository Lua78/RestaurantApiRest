package com.api.demo.controllers;

import com.api.demo.model.Dinner;
import com.api.demo.model.Menu;
import com.api.demo.repository.MenuRepository;
import com.api.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuRepository menuRepo;
    @Autowired
    public MenuController(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    @GetMapping("dinners/{idMenu}")
    public ResponseEntity<List<Dinner>> getDinners(@PathVariable long idMenu){
        try {
            List<Dinner> dinners = menuRepo.getDinners(idMenu);
            return new ResponseEntity<>(dinners, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        String valString = Utils.validateObject(Menu.class, menu);
        if (!valString.equals("Ok")) {
            return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
        }
        try {
            menuRepo.save(menu);
            return new ResponseEntity<>("Menu creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la menu: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {
        try {
            List<Menu> menus = menuRepo.findAll();
            return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") Long id) {
        Optional<Menu> menuData = menuRepo.findById(id);
        return menuData.map(menu -> new ResponseEntity<>(menu, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMenu(@PathVariable("id") Long id, @RequestBody Menu menu) {
        Optional<Menu> menuData = menuRepo.findById(id);
        if (menuData.isPresent()) {
            String valString = Utils.validateObject(Menu.class, menu);
            if (!valString.equals("Ok")) {
                return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
            }
            Menu _menu = menuData.get();
            _menu.setName(menu.getName());
            _menu.setState(menu.getState());
            _menu.setPrice(menu.getPrice());
            return new ResponseEntity<>(menuRepo.save(_menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Menu> patchMenu(@PathVariable("id") Long id, @RequestBody Menu menu) {
        Optional<Menu> menuData = menuRepo.findById(id);
        if (menuData.isPresent()) {
            Menu _menu = menuData.get();
            if (menu.getName() != null) {
                _menu.setName(menu.getName());
            }
            if (menu.getState()) {
                _menu.setState(menu.getState());
            }
            if (menu.getPrice() > 0) {
                _menu.setPrice(menu.getPrice());
            }
            return new ResponseEntity<>(menuRepo.save(_menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMenu(@PathVariable("id") Long id) {
        try {
            menuRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
