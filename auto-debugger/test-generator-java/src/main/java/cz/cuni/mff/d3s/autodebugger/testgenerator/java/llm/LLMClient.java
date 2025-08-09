package cz.cuni.mff.d3s.autodebugger.testgenerator.java.llm;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import cz.cuni.mff.d3s.autodebugger.testgenerator.common.LLMConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Client for communicating with Large Language Model APIs.
 * Supports multiple providers including OpenAI, Anthropic, and local models.
 */
@Slf4j
public class LLMClient {

    private LLMConfiguration config;
    private HttpClient httpClient;
    private AnthropicClient anthropicClient;

    public void configure(LLMConfiguration config) {
        if (config == null) {
            throw new IllegalArgumentException("LLM configuration cannot be null");
        }
        this.config = config;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(config.getRequestTimeout())
                .build();

        // Initialize Anthropic client if using Anthropic provider
        if ("anthropic".equalsIgnoreCase(config.getProvider())) {
            this.anthropicClient = AnthropicOkHttpClient.builder()
                    .apiKey(config.getApiKey())
                    .build();
        }

        log.info("Configured LLM client for provider: {}", config.getProvider());
    }
    
    /**
     * Generates code using the configured LLM.
     * 
     * @param prompt The prompt to send to the LLM
     * @return Generated code response
     * @throws IOException if the request fails
     */
    public String generateCode(String prompt) throws IOException {
        if (config == null) {
            throw new IllegalStateException("LLM client must be configured before use");
        }

        log.debug("Sending prompt to LLM (length: {} chars)", prompt.length());

        try {
            String response;
            if ("mock".equalsIgnoreCase(config.getProvider())) {
                response = generateMockResponse(prompt);
            } else if ("anthropic".equalsIgnoreCase(config.getProvider()) && anthropicClient != null) {
                response = sendAnthropicRequest(prompt);
            } else {
                response = sendRequest(prompt);
            }
            String extractedCode = extractCodeFromResponse(response);

            log.debug("Received response from LLM (length: {} chars)", extractedCode.length());
            return extractedCode;

        } catch (Exception e) {
            log.error("Failed to generate code using LLM", e);
            throw new IOException("LLM code generation failed", e);
        }
    }
    
    private String sendRequest(String prompt) throws IOException, InterruptedException {
        String requestBody = buildRequestBody(prompt);
        String endpoint = getApiEndpoint();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + config.getApiKey())
                .timeout(config.getRequestTimeout())
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("LLM API request failed with status: " + response.statusCode() + 
                                ", body: " + response.body());
        }
        
        return response.body();
    }

    private String sendAnthropicRequest(String prompt) throws IOException {
        try {
            MessageCreateParams params = MessageCreateParams.builder()
                    .model(Model.of(config.getModelName()))
                    .maxTokens((long) config.getMaxTokens())
                    .temperature(config.getTemperature())
                    .addUserMessage(prompt)
                    .build();

            Message response = anthropicClient.messages().create(params);

            // Extract text content from the response
            if (response.content() != null && !response.content().isEmpty()) {
                // Get the first content block and extract text
                var contentBlock = response.content().getFirst();
                if (contentBlock.text().isPresent()) {
                    return contentBlock.text().get().text();
                }
            }

            throw new IOException("Empty response from Anthropic API");

        } catch (Exception e) {
            log.error("Failed to send request to Anthropic API", e);
            throw new IOException("Anthropic API request failed", e);
        }
    }
    
    private String buildRequestBody(String prompt) {
        // This is a simplified implementation - in practice, you'd want to use a JSON library
        StringBuilder json = new StringBuilder();
        json.append("{");
        
        switch (config.getProvider().toLowerCase()) {
            case "openai":
                json.append("\"model\":\"").append(config.getModelName()).append("\",");
                json.append("\"messages\":[{\"role\":\"user\",\"content\":\"").append(escapeJson(prompt)).append("\"}],");
                json.append("\"max_tokens\":").append(config.getMaxTokens()).append(",");
                json.append("\"temperature\":").append(config.getTemperature());
                break;
                
            case "anthropic":
                json.append("\"model\":\"").append(config.getModelName()).append("\",");
                json.append("\"max_tokens\":").append(config.getMaxTokens()).append(",");
                json.append("\"messages\":[{\"role\":\"user\",\"content\":\"").append(escapeJson(prompt)).append("\"}],");
                json.append("\"temperature\":").append(config.getTemperature());
                break;
                
            default:
                // Generic format
                json.append("\"prompt\":\"").append(escapeJson(prompt)).append("\",");
                json.append("\"max_tokens\":").append(config.getMaxTokens()).append(",");
                json.append("\"temperature\":").append(config.getTemperature());
                break;
        }
        
        json.append("}");
        return json.toString();
    }
    
    private String getApiEndpoint() {
        if (config.getApiEndpoint() != null && !config.getApiEndpoint().isEmpty()) {
            return config.getApiEndpoint();
        }

        return switch (config.getProvider().toLowerCase()) {
            case "openai" -> "https://api.openai.com/v1/chat/completions";
            case "anthropic" -> "https://api.anthropic.com/v1/messages";
            case "mock" -> "http://localhost:8080/mock"; // Mock endpoint for testing
            default -> throw new IllegalArgumentException("Unknown provider: " + config.getProvider());
        };
    }

    private String generateMockResponse(String prompt) {
        // Generate a mock test class for testing purposes
        return """
            ```java
            package com.example;

            import org.junit.jupiter.api.Test;
            import static org.junit.jupiter.api.Assertions.*;

            public class MockGeneratedTest {
                @Test
                void testMockMethod() {
                    // Mock test generated for testing purposes
                    assertTrue(true);
                }
            }
            ```
            """;
    }

    private String extractCodeFromResponse(String response) {
        // This is a simplified implementation - in practice, you'd want to use a JSON library
        // and handle the specific response format for each provider
        
        try {
            // For OpenAI format
            if (response.contains("\"choices\"")) {
                int contentStart = response.indexOf("\"content\":\"") + 11;
                int contentEnd = response.indexOf("\"", contentStart);
                if (contentStart > 10 && contentEnd > contentStart) {
                    return unescapeJson(response.substring(contentStart, contentEnd));
                }
            }
            
            // For Anthropic format
            if (response.contains("\"content\"")) {
                int contentStart = response.indexOf("\"text\":\"") + 8;
                int contentEnd = response.indexOf("\"", contentStart);
                if (contentStart > 7 && contentEnd > contentStart) {
                    return unescapeJson(response.substring(contentStart, contentEnd));
                }
            }
            
            // Fallback: try to extract code blocks
            if (response.contains("```java")) {
                int codeStart = response.indexOf("```java") + 7;
                int codeEnd = response.indexOf("```", codeStart);
                if (codeEnd > codeStart) {
                    return response.substring(codeStart, codeEnd).trim();
                }
            }
            
            // If no specific format found, return the whole response
            log.warn("Could not extract code from LLM response, returning full response");
            return response;
            
        } catch (Exception e) {
            log.error("Error extracting code from LLM response", e);
            return response;
        }
    }
    
    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
    
    private String unescapeJson(String text) {
        return text.replace("\\\"", "\"")
                  .replace("\\n", "\n")
                  .replace("\\r", "\r")
                  .replace("\\t", "\t")
                  .replace("\\\\", "\\");
    }
}
