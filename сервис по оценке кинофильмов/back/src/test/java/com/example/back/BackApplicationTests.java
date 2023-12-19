package com.example.back;

import com.example.back.config.DateConfig;
import com.example.back.models.Film;
import com.example.back.services.AdminService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BackApplicationTests {

    @Test
    void contextLoads() {

    }
}
