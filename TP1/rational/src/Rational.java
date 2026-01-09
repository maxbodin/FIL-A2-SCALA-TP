//public class Rational {
//    private final int n;
//    private final int d;
//
//    public Rational(int n, int d) {
//        this.n = n;
//        this.d = d;
//    }
//    public Rational(int n){
//        this(n, 1);
//    }
//    public int getN() {
//        return n;
//    }
//    // public void setN(int i) { n = i; }
//    public int getD() {
//        return d;
//    }
//    // public void setD(int i) { d = i; }
//
//    @Override
//    public String toString() {
//        return n + "/" + d;
//    }
//
//    public Rational add(Rational that){
//        return new Rational(
//                n * that.getD() + that.getN() * d,
//                d * that.getD());
//    }
//}