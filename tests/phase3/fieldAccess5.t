
int main() {
  B b;
  b = new B();
  b.k = 20;
  b.i = 22;
  C c;
  c = new C();
  c.i = 1;
  out b.i + b.k;
  A a;
  a = c;
  out a.i;
}

class C extends B {
  int i;
}

class B extends A {
  int i;
  int k;
}

class A {
  int i;
  int j;
}

