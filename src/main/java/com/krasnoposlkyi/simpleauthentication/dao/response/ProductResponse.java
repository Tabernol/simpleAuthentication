package com.krasnoposlkyi.simpleauthentication.dao.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Setter
public class ProductResponse {

    private String tableName;
    private boolean isCreated;
    private Integer rowInserted;

}
