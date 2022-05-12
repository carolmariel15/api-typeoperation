package com.nttdata.api.typeoperation.producer;

import com.nttdata.api.typeoperation.document.TypeOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducer.class);

    private final KafkaTemplate<String, TypeOperation> kafkaTemplate;

    public KafkaJsonProducer(@Qualifier("kafkaJsonTemplate") KafkaTemplate<String, TypeOperation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTypeOperation(TypeOperation to) {
        LOGGER.info("Enviando tipo de operacion", to);
        this.kafkaTemplate.send("topic-typeoperation", to);
    }

}
