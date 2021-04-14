package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("leader"), is("Petr Arsentev"));
        assertThat(config.value("mentor"), is("Rail Shamsemuhametov"));
        assertThat(config.value("student"), is("Ruslan Davydov"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("leader"), is("Petr Arsentev"));
        assertThat(config.value("mentor"), is("Rail Shamsemuhametov"));
        assertThat(config.value("student"), is("Ruslan Davydov"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithWrong() {
        String path = "./data/pair_with_wrong.properties";
        Config config = new Config(path);
        config.load();
    }
}
