package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krasnoposlkyi.simpleauthentication.dao.entity.Products;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ParseJsonServiceImpl {
    private final ObjectMapper objectMapper;

    public ParseJsonServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }


    public List<Products> parseProductJson(String json) {
        List<Products> result = new ArrayList<>();
        try {
            List<JsonNode> records = getJsonNodes(json);

            //mapping to object Java and saving to List
            for (JsonNode record : records) {
                Products products = objectMapper.readValue(record.toString(), Products.class);
                result.add(products);
            }
        } catch (JsonMappingException e) {
            log.info("problem with mapping JSON ", e);
        } catch (JsonProcessingException e) {
            log.info("problem with parsing JSON ", e);
        }
        return result;
    }

    public Optional<String> getTableName(String json) {
        String tableName = null;
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            tableName = rootNode.get("table").asText();
        } catch (JsonProcessingException e) {
            log.info("problem with parsing JSON ", e);
        }
        return Optional.of(tableName);
    }


    public Set<String> getColumnsName(String json) {
        Set<String> columnNames = new HashSet<>();
        // Extract keys from "records"
        List<JsonNode> records = getJsonNodes(json);
        JsonNode jsonNode;
        if (!records.isEmpty()) {
            //For creating table use only first record
            //to do validation of all JSON
            jsonNode = records.get(0);
            Iterator<String> stringIterator = jsonNode.fieldNames();
            while (stringIterator.hasNext()) {
                //save column names to Set
                columnNames.add(stringIterator.next());
            }
        }
        log.info(columnNames.toString());
        return columnNames;
    }

    private List<JsonNode> getJsonNodes(String json) {
        List<JsonNode> jsonNodes = new ArrayList<>();
        try {
            // Creating root node
            JsonNode rootNode = objectMapper.readTree(json);

            // Get JSON nodes array from JSON "records"
            jsonNodes = objectMapper.readValue(
                    rootNode.get("records").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class)
            );

        } catch (JsonMappingException e) {
            log.info("problem with mapping JSON ", e);
        } catch (JsonProcessingException e) {
            log.info("problem with parsing JSON ", e);
        }
        return jsonNodes;
    }


}
