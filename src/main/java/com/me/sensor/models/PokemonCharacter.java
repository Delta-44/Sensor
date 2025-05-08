package com.me.sensor.models;

import lombok.Data;

import java.util.List;

@Data
public class PokemonCharacter {
    private String name;
    private List<TypeWrapper> types; // Lista para mapear los tipos
    private Sprites sprites; // Objeto para mapear las imágenes

    @Data
    public static class TypeWrapper {
        private Type type;

        @Data
        public static class Type {
            private String name;
        }
    }

    @Data
    public static class Sprites {
        private String front_default; // URL de la imagen
    }
}