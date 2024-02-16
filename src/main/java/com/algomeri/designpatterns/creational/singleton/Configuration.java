package com.algomeri.designpatterns.creational.singleton;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

/**
 * This class shows how to use the singleton pattern. It also demonstrates that you can combine other patterns as well as it uses the builder pattern to initialize this with properties.
 */
@ToString
@Getter
public class Configuration {
    private Optional<String> username = Optional.empty();
    private Optional<String> password = Optional.empty();

    @Getter(value = AccessLevel.NONE)
    private static volatile Optional<Configuration> instance = Optional.empty();

    /**
     * Used to lazily initialize the singleton 
     */
    private Configuration() {

    }

    /**
     * Used to initialize this class using a builder
     * @param builder
     */
    private Configuration(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
    }

    /**
     * Used by the builder to build a single instance
     * @param builder
     * @return
     */
    private static synchronized Configuration buildInstance(Builder builder) {
        if (instance.isEmpty()) {
            instance = Optional.of(new Configuration(builder));
        }
        return instance.get();
    }

    /**
     * Used to get an instance of this class
     * @return {@link Configuration}
     */
    public static synchronized Configuration getInstance() {
        if (instance.isEmpty()) {
            instance = Optional.of(new Configuration());
        }
        return instance.get();
    }

    public synchronized void setPassword(String password) {
        System.out.println(this.password.isEmpty());
        if (this.password.isEmpty()) {
            System.out.println("password is empty");
            this.password = Optional.ofNullable(password);
        }
    }

    public synchronized void setUsername(String username) {
        if (this.username.isEmpty()) {
            this.username = Optional.ofNullable(username);
        }
    }

    /**
     * This is used to initialize the singleton with a set of properties.
     * Note: these properties can only be set once;
     */
    public static class Builder {

        private Optional<String> username = Optional.empty();
        private Optional<String> password = Optional.empty();

        public synchronized Builder password(String password) {
            if (this.password.isEmpty()) {
                this.password = Optional.ofNullable(password);
            }
            return this;
        }

        public synchronized Builder username(String username) {
            if (this.username.isEmpty()) {
                this.username = Optional.ofNullable(username);
            }
            return this;
        }

        public synchronized Configuration build() {
            return buildInstance(this);
        }

    }
}
