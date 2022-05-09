package com.nttdata.api.typeoperation.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "typeoperation")
public class TypeOperation {

    @Id
    private Integer id;
    private String description;

}
