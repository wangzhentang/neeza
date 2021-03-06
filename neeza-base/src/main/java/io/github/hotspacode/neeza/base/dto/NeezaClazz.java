package io.github.hotspacode.neeza.base.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class NeezaClazz implements Serializable {
    private String name;
    private String genericString;
    private String serviceName;
    private List<NeezaMethod> methods;
    private boolean enableMethodMockPull = false;
    private boolean enableMethodMockPush = false;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenericString() {
        return genericString;
    }

    public void setGenericString(String genericString) {
        this.genericString = genericString;
    }

    public List<NeezaMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<NeezaMethod> methods) {
        this.methods = methods;
    }

    public boolean isEnableMethodMockPull() {
        return enableMethodMockPull;
    }

    public void setEnableMethodMockPull(boolean enableMethodMockPull) {
        this.enableMethodMockPull = enableMethodMockPull;
    }

    public boolean isEnableMethodMockPush() {
        return enableMethodMockPush;
    }

    public void setEnableMethodMockPush(boolean enableMethodMockPush) {
        this.enableMethodMockPush = enableMethodMockPush;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeezaClazz that = (NeezaClazz) o;
        return Objects.equals(genericString, that.genericString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genericString);
    }

    public static class NeezaMethod {
        private String name;
        private String genericString;

        public NeezaMethod() {
        }

        public NeezaMethod(String name, String genericString) {
            this.name = name;
            this.genericString = genericString;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGenericString() {
            return genericString;
        }

        public void setGenericString(String genericString) {
            this.genericString = genericString;
        }
    }
}

