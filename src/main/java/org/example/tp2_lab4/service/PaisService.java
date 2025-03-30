package org.example.tp2_lab4.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.example.tp2_lab4.model.entity.Pais;
import org.example.tp2_lab4.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void cargarPaises() {
        for (int codigo = 1; codigo <= 999; codigo++) {

            String url = "https://restcountries.com/v3.1/alpha/" + String.format("%03d", codigo);
            try {
                ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    JsonNode jsonData = response.getBody().get(0);

                    Pais pais = new Pais();
                    pais.setCodigoPais(jsonData.get("ccn3").asInt());
                    pais.setNombrePais(jsonData.get("name").get("common").asText());
                    pais.setCapitalPais(jsonData.get("capital").get(0).asText());
                    pais.setRegion(jsonData.get("region").asText());
                    pais.setSubregion(jsonData.get("subregion").asText());
                    pais.setPoblacion(jsonData.get("population").asLong());
                    pais.setLatitud(jsonData.get("latlng").get(0).asDouble());
                    pais.setLongitud(jsonData.get("latlng").get(1).asDouble());

                    paisRepository.save(pais);
                    System.out.println("Pais: " + pais.getNombrePais() + ", codigo: " + pais.getCodigoPais() + " guardado");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public List<Pais> obtenerTodosLosPaises() {
        return paisRepository.findAll();
    }
}
