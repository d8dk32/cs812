class A {
}

class B extends A {
}

int main() {
  A a;
  B b;
  a = new A();
  b = new B();
  out a.equals(a);
  out a.equals(b);
  a = b;
  out a.equals(b);
  a = new B();
  out a.equals(b);
}

