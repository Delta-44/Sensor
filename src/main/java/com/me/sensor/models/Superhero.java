package com.me.sensor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Superhero {
    private String name;
    private Map<String, String> powerstats;
    private String image; // Aquí guardaremos la URL extraída

    public Superhero() { }

    // Getter y setter para name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter y setter para powerstats
    public Map<String, String> getPowerstats() {
        return powerstats;
    }
    public void setPowerstats(Map<String, String> powerstats) {
        this.powerstats = powerstats;
    }

    // Getter para image
    public String getImage() {
        return image;
    }
    /**
     * Aquí extraemos la URL del objeto que nos devuelve la API.
     * La API envía "image": { "url": "..." }, por lo que si recibimos un Map,
     * tomamos el valor asociado a "url".
     */
    public void setImage(Object image) {
        if (image instanceof Map) {
            Map<?, ?> imageMap = (Map<?, ?>) image;
            Object urlObj = imageMap.get("url");
            this.image = urlObj != null ? urlObj.toString() : null;
        } else {
            this.image = image != null ? image.toString() : null;
        }
    }
}
