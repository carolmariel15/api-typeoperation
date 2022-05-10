package com.nttdata.api.typeoperation.router;

import com.nttdata.api.typeoperation.handler.TypeOperationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TypeOperationRouter {

    @Bean
    public RouterFunction<ServerResponse> clientRouterFunc(TypeOperationHandler typeOperationHandler) {
        return RouterFunctions.route(GET("/typeOperation").and(accept(MediaType.TEXT_EVENT_STREAM)), typeOperationHandler::getAllTypeOperation)
                .andRoute(GET("/typeOperation/{id}").and(accept(MediaType.TEXT_EVENT_STREAM)), typeOperationHandler::getTypeOperation)
                .andRoute(POST("/typeOperation").and(accept(MediaType.TEXT_EVENT_STREAM)), typeOperationHandler::create)
                .andRoute(PUT("/typeOperation").and(accept(MediaType.TEXT_EVENT_STREAM)), typeOperationHandler::edit)
                .andRoute(DELETE("/typeOperation/{id}").and(accept(MediaType.TEXT_EVENT_STREAM)), typeOperationHandler::delete);
    }

}
