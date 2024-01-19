package poney.fs.util;

public class MathFS{
    public static float easeOutQuad(double t, float b, float c, double d) {
        float z = (float) t / (float) d;
        return -c * z * (z - 2.0F) + b;
    }
}
