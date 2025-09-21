package org.example.notes_app.Routes;

import org.aspectj.weaver.ast.Not;
import java.util.Map;
import org.example.notes_app.Service.NoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.security.SecureRandom;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class NoteRoutes
{

    private final NoteService service;

    public  NoteRoutes(NoteService service)
    {
        this.service = service;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/notes", req -> ServerResponse.ok().body(service.all()))
                .POST("/notes", req -> {
                    Map body = req.body(Map.class);
                    String note = (String) body.get("note");
                    String tags = (String) body.get("tags");
                    boolean important = Boolean.parseBoolean(String.valueOf(body.getOrDefault("important", "false")));
                    return ServerResponse.ok().body(service.save(note, tags, important));
                })
                .GET("/notes/filter/tag/{tag}", req -> {
                    String tag = req.pathVariable("tag");
                    return ServerResponse.ok().body(service.filterByTag(tag));
                })
                .GET("/notes/filter/important/{flag}", req -> {
                    boolean flag = Boolean.parseBoolean(req.pathVariable("flag"));
                    return ServerResponse.ok().body(service.filterByImportance(flag));
                })
                .POST("/notes/{id}/like", req -> {
                    Long id = Long.parseLong(req.pathVariable("id"));
                    return ServerResponse.ok().body(service.like(id));
                })
                .GET("/notes/trending", req -> ServerResponse.ok().body(service.trending()))
                .build();
    }
}