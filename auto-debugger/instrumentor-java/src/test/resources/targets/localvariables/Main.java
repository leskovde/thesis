public class Main {

  public static int testMul(int x, int y) {
    System.out.println("app: Main.testMul()");
    int a = x;
    int b = y;
    int mul = a * b;
    System.out.println("app: a=" + a);
    System.out.println("app: b=" + b);
    System.out.println("app: mul=" + mul);
    return mul;
  }

  public static void main(String[] args) {
    testMul(3, 5);
  }
}
