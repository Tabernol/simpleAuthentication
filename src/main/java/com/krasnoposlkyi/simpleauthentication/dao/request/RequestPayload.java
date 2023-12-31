package com.krasnoposlkyi.simpleauthentication.dao.request;

import lombok.*;

import java.util.List;
import java.util.Map;
@AllArgsConstructor
@Data
@Getter
@Setter
public class RequestPayload {
    private String tableName;
    private List<Map<String, String>> records;
}
