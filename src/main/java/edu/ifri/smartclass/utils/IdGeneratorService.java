package edu.ifri.smartclass.utils;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {
    public String generateUniqueId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 10).toUpperCase();
    }
}
