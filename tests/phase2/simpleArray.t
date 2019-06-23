
int main() {
  int x[];
  int y[];
  x = new int[10];
  y = new int[10];
  x[5] = 5;
  y[4] = 5;
  out x[0];
  out x[5];
  out y[4];
  x = y;
  out x[4];
  x = new int[5];
  x[0] = 42;
  out x[0];
  out x[4];
}

