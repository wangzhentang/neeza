package io.github.hotspacode.neeza.base.service;


import io.github.hotspacode.neeza.base.api.IMockSpyService;
import io.github.hotspacode.neeza.base.common.SpiLoader;

public final class MockSpyServiceProvider {

    private static IMockSpyService instance = null;

    static {
        resolveInstance();
    }

    private static void resolveInstance() {
        instance = SpiLoader.loadHighestPriorityInstance(IMockSpyService.class);
    }

    public static IMockSpyService getInstance() {
        return instance;
    }

    private MockSpyServiceProvider() {
    }
}
