package com.test.app.callbacks;

import com.test.app.web.PKResponseResult;

public interface PokemonsRequestSuccessCallback {

    public void call(PKResponseResult[] pokemon);
}
