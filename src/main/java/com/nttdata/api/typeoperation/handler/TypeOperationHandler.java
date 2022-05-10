package com.nttdata.api.typeoperation.handler;

import com.nttdata.api.typeoperation.document.TypeOperation;
import com.nttdata.api.typeoperation.repository.TypeOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TypeOperationHandler {

    private final TypeOperationRepository typeOperationRepository;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getAllTypeOperation(ServerRequest serverRequest) {
        return  ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(typeOperationRepository.findAll().log(), TypeOperation.class);
    }

    public Mono<ServerResponse> getTypeOperation(ServerRequest serverRequest) {
        var id = Integer.parseInt(serverRequest.pathVariable("id"));
        var typeOperation = typeOperationRepository.findById(id);
        return typeOperation.flatMap(t -> ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(typeOperation, TypeOperation.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        var typeOperationMono = serverRequest.bodyToMono(TypeOperation.class);
        return  typeOperationMono.flatMap(t -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(typeOperationRepository.save(t), TypeOperation.class));
    }

    public Mono<ServerResponse> edit(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TypeOperation.class).flatMap(v -> {
            return typeOperationRepository.findById(v.getId()).flatMap(t -> {
                t.setDescription(v.getDescription());
                return ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(typeOperationRepository.save(t), TypeOperation.class);
            }).switchIfEmpty(notFound);
        });
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        var id = Integer.parseInt(serverRequest.pathVariable("id"));
        return typeOperationRepository.findById(id)
                .flatMap(t -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(typeOperationRepository.delete(t), Void.class))
                .switchIfEmpty(notFound);
    }

}
