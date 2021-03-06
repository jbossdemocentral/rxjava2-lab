package me.escoffier.lab.chapter5;

import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;

public class Code2 {

    public static void main(String[] args) {
        load()
            .map(array -> array.encodePrettily())
            .subscribe(System.out::println, Throwable::printStackTrace);
    }

    static Single<JsonArray> load() {
        Vertx vertx = Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        return fileSystem.rxReadFile("src/main/resources/characters.json")
            .map(buffer -> buffer.toString())
            .map(content -> new JsonArray(content));
    }
}
