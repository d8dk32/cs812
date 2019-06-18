
int main() {
  int x[];
  x = new int[42];
  out x.length;
  x = new int[12];
  out x.length + 30;
  int x2[][];
  x2 = new int[17][];
  x2[3] = new int[15];
  out x2.length + x2[3].length + 10;
}

