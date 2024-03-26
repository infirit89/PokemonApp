package com.test.app.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Sprites {

    public Sprites(String backDefault, String backFemale, String backShiny, String backShinyFemale,
                   String frontDefault, String frontFemale, String frontShiny, String frontShinyFemale)
    {
        this.backDefault = backDefault;
        this.backFemale = backFemale;
        this.backShiny = backShiny;
        this.backShinyFemale = backShinyFemale;
        this.frontDefault = frontDefault;
        this.frontFemale = frontFemale;
        this.frontShiny = frontShiny;
        this.frontShinyFemale = frontShinyFemale;
    }

    @Nullable
    public String getBackDefault() {
        return backDefault;
    }

    @Nullable
    public String getBackFemale() {
        return backFemale;
    }

    @Nullable
    public String getBackShiny() {
        return backShiny;
    }

    @Nullable
    public String getBackShinyFemale() {
        return backShinyFemale;
    }

    @Nullable
    public String getFrontDefault() {
        return frontDefault;
    }

    @Nullable
    public String getFrontFemale() {
        return frontFemale;
    }

    @Nullable
    public String getFrontShiny() {
        return frontShiny;
    }

    @Nullable
    public String getFrontShinyFemale() {
        return frontShinyFemale;
    }


    @Nullable
    @SerializedName(value="back_default")
    private String backDefault;
    @Nullable
    @SerializedName(value="back_female")
    private String backFemale;
    @Nullable
    @SerializedName(value="back_shiny")
    private String backShiny;
    @Nullable
    @SerializedName(value="back_shiny_female")
    private String backShinyFemale;
    @Nullable
    @SerializedName(value="front_default")
    private String frontDefault;
    @Nullable
    @SerializedName(value="front_female")
    private String frontFemale;
    @Nullable
    @SerializedName(value="front_shiny")
    private String frontShiny;
    @Nullable
    @SerializedName(value="front_shiny_female")
    private String frontShinyFemale;
}
