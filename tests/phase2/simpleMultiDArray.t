
int main() {
  int x[][];
  int y[];
  x = new int[5][];
  y = new int[3];
  y[0] = 42;
  out y[0];
  x[4] = y;
  out y[0];
  y[1] = 42;
  x[4][2] = 42;
  out x[3][2];

}

