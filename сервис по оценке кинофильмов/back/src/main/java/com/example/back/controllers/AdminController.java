package com.example.back.controllers;

import com.example.back.models.Account;
import com.example.back.models.Film;
import com.example.back.repository.AccountRepository;
import com.example.back.repository.FilmRepository;
import com.example.back.repository.LikeRepository;
import com.example.back.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin_menu")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LikeRepository likeRepository;

    Account account;

    @GetMapping("/initialized_films")
    public ResponseEntity initialized_films(){
        try {
            return ResponseEntity.ok(adminService.initialized_films());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/initialized_statistic")
    public ResponseEntity initialized_statistic(){
        try {
            return ResponseEntity.ok(adminService.initialized_statistic());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping( value = "/add_film")
    public ResponseEntity add_film(@RequestBody Film film){
        try {
            adminService.add_film(film);
            return ResponseEntity.status(HttpStatus.OK).body("Киноафиша успешно добавлена");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getFilm")
    public ResponseEntity getFilm(@RequestParam(name = "type")String type){
        try {
            return ResponseEntity.ok(adminService.getFilm(type));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/initialized_users")
    public ResponseEntity initialized_users(){
        try {
            return ResponseEntity.ok(adminService.initialized_users());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/initialized_settins")
    public ResponseEntity initialized_settins(@RequestParam(name = "id")int id){
        try {
            return ResponseEntity.ok(adminService.initialized_settins(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/update_settins")
    public ResponseEntity update_settins(@RequestBody Account account){
        try {
            adminService.update_settins(account);
            System.out.println(account);
            return ResponseEntity.status(HttpStatus.OK).body("Настройки успешно сохранены");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/block_account")
    public ResponseEntity blockAccount(@RequestParam (name = "id")int id){
        try {
            adminService.blockAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("Пользователь " + account.getMen().getName() + " успешно заблокирован");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/unblock_account")
    public ResponseEntity<?> unblockAccount(@RequestParam(name = "id")int id){
        try {
            adminService.unblockAccount(id);
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/take_admin")
    public ResponseEntity take_admin(@RequestParam(name = "id")int id){
        try {
            adminService.take_admin(id);
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/take_user")
    public ResponseEntity take_user(@RequestParam(name = "id")int id){
        try {
            adminService.take_user(id);
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/remove_account")
    public ResponseEntity<?> removeAccount(@RequestParam(name="id")int id){
        try {
            adminService.removeAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("Пользователь " + account.getMen().getName() + " успешно удален");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
