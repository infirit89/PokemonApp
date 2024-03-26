package com.test.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pokemon implements Serializable {

    public Pokemon() {}

    public Pokemon(int id, String name, int baseExperience, boolean isDefault,
                   int order, int weight, Sprites sprites)
    {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.isDefault = isDefault;
        this.order = order;
        this.weight = weight;
        this.sprites = sprites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Pokemon:\n");
        builder.append(String.format("Id: %d\n", id));
        builder.append(String.format("Name: %s\n", name));

        return builder.toString();
    }

    private int id;
    private String name;
    @SerializedName(value="base_experience")
    private int baseExperience;
    @SerializedName(value="is_default")
    private boolean isDefault;
    private int order;
    private int weight;
    private Sprites sprites;
}
