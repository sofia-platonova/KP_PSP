package com.example.back.controllers;

import com.example.back.config.DateConfig;
import com.example.back.dto.RatingDto;
import com.example.back.models.Account;
import com.example.back.models.Film;
import com.example.back.models.LikesAction;
import com.example.back.models.Rating;
import com.example.back.repository.AccountRepository;
import com.example.back.repository.FilmRepository;
import com.example.back.repository.LikeRepository;
import com.example.back.repository.RatingRepository;
import com.example.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user_menu")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountRepository accountRepository;

    Account account;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RatingRepository ratingRepository;


    @GetMapping("/add_rating")
    private ResponseEntity add_rating(@RequestParam(name = "id")int id,@RequestParam(name = "id_film")int id_films,@RequestParam(name = "rating")int rat){
        try {
            userService.add_rating(id, id_films, rat);
            return ResponseEntity.status(HttpStatus.OK).body("Оценка успешно добавлена");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/update_settins")
    private ResponseEntity update_settins(@RequestBody Account account){
        try {
            userService.update_settins(account);
            System.out.println(account);
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/initialized_films")
    public ResponseEntity initialized_films(){
        try {
            return ResponseEntity.ok(userService.initialized_films());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/add_like")
    public ResponseEntity add_like(@RequestParam(name = "id_films")int id_films,@RequestParam(name = "id")int id){
        try {
            return ResponseEntity.ok(userService.add_like(id_films, id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/remove_like")
    public String remove_like(@RequestParam(name = "id_films")int id_films,@RequestParam(name = "id")int id){
        account=accountRepository.findById((id)).get();
        likeRepository.deleteByAccountIdAndFilmId(id,id_films);
        return null;
    }

    @GetMapping("/get_history")
    public ResponseEntity historyRating(@RequestParam(name = "id")int id_account){
        try {
            return ResponseEntity.ok(userService.historyRating(id_account));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/getFilm")
    public ResponseEntity getFilm(@RequestParam(name = "type")String type){
        try {
            return ResponseEntity.ok(userService.getFilm(type));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
