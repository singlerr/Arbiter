package io.github.singlerr.cob.core.util;

public final class Vector4s {

    private short x;

    private short y;

    private short z;

    private short w;

    public Vector4s(short x, short y, short z, short w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public short getZ() {
        return z;
    }

    public void setZ(short z) {
        this.z = z;
    }

    public short getW() {
        return w;
    }

    public void setW(short w) {
        this.w = w;
    }
}
