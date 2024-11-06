package com.PPOOII.Laboratorio.APIs.GoogleMaps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Geocoder {

    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";

    //private static final String API_KEY = "5HF6xeOMJ0bUoslSr2XuJDHo5LoQvPs8BxNvKRn1KdM";
    private static final String API_KEY = "1YldpTvrSEkj5pGAqLnOwNm7h_af1vakN9xagy1Y6Aw";

    public Geocoder() {

    }

    public String GeocodeSync(String query) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return (String) geocodingResponse.body();
    }

    public String getLatLng(String query) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String response = this.GeocodeSync(query);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        if (items != null && items.size() > 0) {
            JsonNode position = items.get(0).get("position");
            String latitud = position.get("lat").asText();
            String longitud = position.get("lng").asText();
            return latitud + ", " + longitud;
        } else {
            throw new RuntimeException("No se encontraron candidatos para la ubicacion proporcionada.");
        }
    }

}
