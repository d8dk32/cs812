
class A {
  int a;
  int meth1() { out 1; }
  int meth2() { out a; }
  int meth3() { out 3; }
}

int main() {
  out 42;
}

class B extends A {
  int b;
  A meth4(int a, int[] b, A c) { out a; }
  B meth5() { out 5; }
}

