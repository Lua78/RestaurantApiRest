package com.api.demo.controllers;

import com.api.demo.model.Dinner;
import com.api.demo.model.MenuDinner;
import com.api.demo.repository.MenuDinnerRepository;
import com.api.demo.repository.MenuDinnerRepository;
import com.api.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menuDinners")
public class MenuDinnerController {
    private final MenuDinnerRepository menuDinnerRepo;
    @Autowired
    public MenuDinnerController(MenuDinnerRepository menuDinnerRepo) {
        this.menuDinnerRepo = menuDinnerRepo;
    }

    @PostMapping
    public ResponseEntity<String> createMenuDinner(@RequestBody MenuDinner menu_dinner) {
        String valString = Utils.validateObject(MenuDinner.class, menu_dinner);
        if (!valString.equals("Ok")) {
            return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
        }
        try {
            menuDinnerRepo.save(menu_dinner);
            return new ResponseEntity<>("MenuDinner creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la menu_dinner: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<MenuDinner>> getAllMenuDinners() {
        try {
            List<MenuDinner> menus = menuDinnerRepo.findAll();
            return new ResponseEntity<>(menus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDinner> getMenuDinnerById(@PathVariable("id") Long id) {
        Optional<MenuDinner> menuData = menuDinnerRepo.findById(id);
        return menuData.map(menu_dinner -> new ResponseEntity<>(menu_dinner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMenuDinner(@PathVariable("id") Long id, @RequestBody MenuDinner menu_dinner) {
        Optional<MenuDinner> menuData = menuDinnerRepo.findById(id);
        if (menuData.isPresent()) {
            String valString = Utils.validateObject(MenuDinner.class, menu_dinner);
            if (!valString.equals("Ok")) {
                return new ResponseEntity<>(valString, HttpStatus.BAD_REQUEST);
            }
            MenuDinner _menu = menuData.get();
            _menu.setMenu_id(menu_dinner.getMenu_id());
            _menu.setDinner_id(menu_dinner.getDinner_id());
            return new ResponseEntity<>(menuDinnerRepo.save(_menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuDinner> patchMenuDinner(@PathVariable("id") Long id, @RequestBody MenuDinner menu_dinner) {
        Optional<MenuDinner> menuData = menuDinnerRepo.findById(id);
        if (menuData.isPresent()) {
            MenuDinner _menu = menuData.get();
            if (menu_dinner.getDinner_id()>0) {
                _menu.setDinner_id(menu_dinner.getDinner_id());
            }
            if (menu_dinner.getMenu_id()>0) {
                _menu.setMenu_id(menu_dinner.getMenu_id());
            }
            return new ResponseEntity<>(menuDinnerRepo.save(_menu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMenuDinner(@PathVariable("id") Long id) {
        try {
            menuDinnerRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
