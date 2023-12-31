package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.krasnoposlkyi.simpleauthentication.dao.request.RequestPayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreatingService {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public int createTable(Map<String, Object> payload) {
        //mapping to custom request
        RequestPayload requestPayload = mapToRequestPayload(payload);

        //dynamic creating sql query for creating table
        String query = createDynamicQuery(requestPayload);

        //creating query via entityManager
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.executeUpdate(); // executing creating table

        // Inserting data from JSON payload named "records"
        int countOfInsert = insertDataFromJson(requestPayload);
        return countOfInsert;
    }

    private int insertDataFromJson(RequestPayload requestPayload) {
        String tableName = requestPayload.getTableName();

        int count = 0;
        for (Map<String, String> record : requestPayload.getRecords()) {
            // Create string of columns in snakeCase style
            List<String> columns = record.keySet()
                    .stream()
                    .map(word -> convertCamelToSnake(word))
                    .toList();

            // Create string of comma-separated values
            List<String> values = new ArrayList<>(record.values());

            // Construct the query and insert data into the table
            String query = "INSERT INTO " + tableName + " (" + String.join(",", columns)
                    + ") VALUES ('" + String.join("','", values) + "')";

            Query nativeQuery = entityManager.createNativeQuery(query);
            count += nativeQuery.executeUpdate();
        }
        return count;
    }

    private String createDynamicQuery(RequestPayload requestPayload) {
        //in this case for creating table I use only first records
        Set<String> keySet = requestPayload.getRecords().get(0).keySet();
        keySet = keySet
                .stream()
                .map(column -> convertCamelToSnake(column))
                .collect(Collectors.toSet());

        String start = "CREATE TABLE IF NOT EXISTS ";
        String tableName = requestPayload.getTableName();
        String id = "(id SERIAL PRIMARY KEY, ";
        StringBuilder sb = new StringBuilder();
        for (String key : keySet) {
            //for all columns were used VARCHAR because in the task JSON was passed only as string
            sb.append(key).append(" VARCHAR(255), ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(");");
        return start + tableName + id + sb;
    }

    //converting columnNames to the snake case
    private String convertCamelToSnake(String sourceColumnName) {
        StringBuilder snakeCaseWord = new StringBuilder();

        for (int i = 0; i < sourceColumnName.length(); i++) {
            char character = sourceColumnName.charAt(i);

            if (Character.isUpperCase(character)) {
                if (i > 0) {
                    snakeCaseWord.append('_');
                }
                snakeCaseWord.append(Character.toLowerCase(character));
            } else {
                snakeCaseWord.append(character);
            }
        }

        return snakeCaseWord.toString();
    }

    //mapping from the received object to request
    private RequestPayload mapToRequestPayload(Map<String, Object> payload) {
        String tableName = (String) payload.get("table");
        List<Map<String, String>> records = (List<Map<String, String>>) payload.get("records");
        return new RequestPayload(tableName, records);
    }
}
