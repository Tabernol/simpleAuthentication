package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class CreatingTableService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ParseJsonServiceImpl parseJsonService;

    public CreatingTableService(ParseJsonServiceImpl parseJsonService) {
        this.parseJsonService = parseJsonService;
    }


    @Transactional
    public boolean create(String json) {
        // Get table name from parser
        Optional<String> tableName = parseJsonService.getTableName(json);

        // Get column names from parser
        Set<String> columnsName = parseJsonService.getColumnsName(json);


        // creating query manually
        if (tableName.isPresent() && !isTableExist(tableName.get())) {
            String dynamicQuery = createDynamicQuery(tableName.get(), columnsName);
            log.info(dynamicQuery);

            // Create table dynamically (Assuming id as primary key)
            entityManager.createNativeQuery(dynamicQuery).executeUpdate();
            return true;
        } else {
            return false;
        }
    }

    private String createDynamicQuery(String tableName, Set<String> columns) {
        //in this case for creating table I use only first records
        String start = "CREATE TABLE IF NOT EXISTS ";
        String id = "(id SERIAL PRIMARY KEY, ";
        StringBuilder sb = new StringBuilder();
        for (String key : columns) {
            //for all columns were used VARCHAR because in the task JSON was passed only as string
            key = convertCamelToSnake(key);
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

    private boolean isTableExist(String tableName) {
        try {
            String sqlQuery = "SHOW TABLES LIKE '" + tableName + "';";

            Query query = entityManager.createNativeQuery(sqlQuery);
            Object result = query.getSingleResult();

            return result != null;
        } catch (Exception e) {
            return false;
        }
    }

}
