package com.test.app.models;

import com.google.gson.annotations.SerializedName;

public class Pokemon {

    private int id;
    private String name;
    @SerializedName(value="base_experience")
    private int baseExperience;
    @SerializedName(value="is_default")
    private boolean isDefault;
    private int order;
    private int weight;
    private Sprites[] sprites;
}
