package eng.vitor.prcamel.model.entities;

public class Segment {

    private float m;
    private float q;

    /*
     * y = mx + q
     */
    public Segment(Point p1, Point p2) {
        m = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
        q = p1.getY() - m * p1.getX();
    }

    public boolean isOnSegment(Point p) {
        return p.getY() == m * p.getX() + q;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(m);
        result = prime * result + Float.floatToIntBits(q);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (Float.floatToIntBits(m) != Float.floatToIntBits(other.m))
            return false;
        if (Float.floatToIntBits(q) != Float.floatToIntBits(other.q))
            return false;
        return true;
    }

    
}
