package com.example.back.services;

import com.example.back.config.StatisticConfig;
import com.example.back.entity.AccountEntity;
import com.example.back.models.Account;
import com.example.back.models.Film;
import com.example.back.models.LikesAction;
import com.example.back.repository.AccountRepository;
import com.example.back.repository.FilmRepository;
import com.example.back.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AdminService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LikeRepository likeRepository;

    Account account;

    public List<Film> initialized_films(){
        Film film=new Film();
        return filmRepository.findAll();
    }

    public StatisticConfig initialized_statistic(){
        StatisticConfig statisticConfig=new StatisticConfig();
        statisticConfig.setNumber_block(accountRepository.countByStatus(AccountEntity.accountStatusBlock));
        statisticConfig.setNumber_user(accountRepository.findAll().size());
        statisticConfig.setNumber_film(filmRepository.findAll().size());
        List<LikesAction>list=likeRepository.findAll();
        AtomicInteger j= new AtomicInteger();
        AtomicInteger f= new AtomicInteger();
        AtomicInteger m= new AtomicInteger();
        AtomicInteger a= new AtomicInteger();
        AtomicInteger may= new AtomicInteger();
        AtomicInteger jun= new AtomicInteger();
        AtomicInteger jul= new AtomicInteger();
        AtomicInteger aug= new AtomicInteger();
        AtomicInteger sep= new AtomicInteger();
        AtomicInteger ot= new AtomicInteger();
        AtomicInteger nov= new AtomicInteger();
        AtomicInteger dec= new AtomicInteger();
        list.forEach(likesAction -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDate localDate = LocalDate.parse(likesAction.getDate(), formatter);
            if(localDate.getMonth()== Month.JANUARY){
                j.getAndIncrement();
            }
            if(localDate.getMonth()==Month.FEBRUARY){
                f.getAndIncrement();
            }
            if(localDate.getMonth()==Month.MARCH){
                m.getAndIncrement();
            }
            if(localDate.getMonth()==Month.APRIL){
                a.getAndIncrement();
            }
            if(localDate.getMonth()==Month.MAY){
                may.getAndIncrement();
            }
            if(localDate.getMonth()==Month.JUNE){
                jun.getAndIncrement();
            }
            if(localDate.getMonth()==Month.JULY){
                jul.getAndIncrement();
            }
            if(localDate.getMonth()==Month.AUGUST){
                aug.getAndIncrement();
            }
            if(localDate.getMonth()==Month.SEPTEMBER){
                sep.getAndIncrement();
            }
            if(localDate.getMonth()==Month.OCTOBER){
                ot.getAndIncrement();
            }
            if(localDate.getMonth()==Month.NOVEMBER){
                nov.getAndIncrement();
            }
            if(localDate.getMonth()==Month.DECEMBER){
                dec.getAndIncrement();
            }
        });
        List<Integer>integerList=new ArrayList<>();
        integerList.addAll(Arrays.asList(j.intValue(),f.intValue(),m.intValue(),a.intValue(),may.intValue(),jun.intValue(),jul.intValue(),aug.intValue(),sep.intValue()
                ,ot.intValue(),nov.intValue(),dec.intValue()));
        statisticConfig.setList_like(integerList);
        statisticConfig.setLikesActions(filmRepository.findAll());
        Collections.sort(statisticConfig.getLikesActions(), new Comparator<Film>() {
            @Override
            public int compare(Film o1, Film o2) {
                return o2.getList().size()-o1.getList().size();
            }

        });
        return statisticConfig;
    }

    public Film add_film(Film film) {
        return filmRepository.save(film);
    }

    public List<Film>getFilm(String type){
        if(type.equals("Все фильмы"))return filmRepository.findAll();
        else
            return filmRepository.findAllByGenre(type);
    }

    public List<Account>initialized_users(){
        return accountRepository.findAll();
    }

    public Account initialized_settins(int id){
        Account ac=accountRepository.findById(id).get();
        account=ac;
        return ac;
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

    public Account blockAccount(int id) {
        Account account = accountRepository.findById(id).get();
        account.setStatus(AccountEntity.accountStatusBlock);
        return accountRepository.save(accountRepository.findById(id).get());
    }

    public Account unblockAccount(int id){
        Account account=accountRepository.findById(id).get();
        account.setStatus(AccountEntity.accountStatusActive);
        return accountRepository.save(accountRepository.findById(id).get());
    }

    public Account take_admin(int id) {
        Account account = accountRepository.findById(id).get();
        account.setRole(AccountEntity.accountRoleAdmin);
        return accountRepository.save(account);
    }

    public Account take_user(int id) {
        Account account = accountRepository.findById(id).get();
        account.setRole(AccountEntity.accountRoleUser);
        return accountRepository.save(account);
    }

    public Account removeAccount(int id) {
        Account account = accountRepository.findById(id).get();
        account.setStatus(AccountEntity.accountStatusBlock);
        accountRepository.deleteById(accountRepository.findById(id).get().getId());
        return account;
    }

    }
