class A {
  A(int i, int j) { 
      a2 = i + j; 
      out a2;
    }
  int a2;
}

class B extends A {
  B(int i) { 
      super(i, i+5); 
      // a2 = 42;
      out a2; 
    }
  B(int i, int j) {
    a2 = i + j +1;
    out a2;
  }
}

int main() {
  B b;
  b = new B(13);
  // b = new B();
  out b.a2;
}

