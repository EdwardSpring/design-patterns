package com.algomeri.designpatterns.creational;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import com.algomeri.designpatterns.creational.builder.User;
import com.algomeri.designpatterns.creational.singleton.Configuration;

public class CreationalTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testSingleton() {
        Configuration configuration = new Configuration.Builder()
                .username("user")
                .password("pass")
                .build();

        Configuration configuration2 = new Configuration.Builder()
                .username("user_user")
                .password("pass_pass")
                .build();

        assertThat(configuration2.getUsername()).isEqualTo(configuration.getUsername());
        assertThat(configuration2.getPassword()).isEqualTo(configuration.getPassword());
        assertThat(configuration).isEqualTo(configuration2);
    }

    @Test
    public void testBuilder() {
        final String FIRST_NAME = "MikeWill";
        final String LAST_NAME = "MadeIt";
        User user = new User.Builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
        
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
    }
}
