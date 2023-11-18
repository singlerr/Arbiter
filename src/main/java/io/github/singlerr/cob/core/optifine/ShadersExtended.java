package io.github.singlerr.cob.core.optifine;

public final class ShadersExtended {

    public static final AttribInfo EXTRA_ATTRIB = new AttribInfo("extra_data", 13);


    public static class AttribInfo {
        private final String name;

        private final int index;

        private boolean enabled;

        public AttribInfo(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
