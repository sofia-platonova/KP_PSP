package com.example.back.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    AccountRepository accountRepository;

    Account account;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RatingRepository ratingRepository;

    public List<Film> getFilm(String type){
        if(type.equals("Все фильмы"))return filmRepository.findAll();
        else
            return filmRepository.findAllByGenre(type);
    }

    public String add_like(int id_films,int id){
        account=accountRepository.findById((id)).get();
        LikesAction like=new LikesAction();
        like.setAccount(account);
        like.setDate(DateConfig.getDateConfigure().nowDate());
        like.setFilm(filmRepository.findById(id_films).get());
        likeRepository.save(like);
        return null;
    }

    public List<RatingDto> historyRating(int id_account){
        List<RatingDto>ratingDtos=new ArrayList<>();
        for (Rating rating:ratingRepository.findByAccountId(id_account)){
            RatingDto ratingDto=new RatingDto();
            ratingDto.setFilm(rating.getFilm());
            ratingDto.setDate(rating.getDate());
            ratingDto.setId(rating.getId());
            ratingDto.setRating(rating.getRating());
            ratingDtos.add(ratingDto);
        }
        return ratingDtos;
    }

    public List<Film> initialized_films(){
        Film film=new Film();
        return filmRepository.findAll();
    }

    public Rating add_rating(int id, int id_films, int rat) {
        Rating rating = new Rating();
        rating.setDate(DateConfig.getDateConfigure().nowDate());
        rating.setFilm(filmRepository.findById(id_films).get());
        rating.setAccount(accountRepository.findById(id).get());
        rating.setRating(rat);

        return ratingRepository.save(rating);
    }

    public Account update_settins(Account account) {
        this.account.setAvatar(account.getAvatar());
        this.account.setLogin(account.getLogin());
        this.account.setPassword(account.getPassword());
        this.account.getMen().setName(account.getMen().getName());
        this.account.getMen().setLast_name(account.getMen().getLast_name());
        this.account.getMen().setPatronymic(account.getMen().getPatronymic());
        return accountRepository.save(this.account);
    }
}
