package com.test.app.callbacks;

import com.test.app.models.Pokemon;

public interface PokemonRequestSuccessCallback {

    void call(Pokemon pokemon);
}
