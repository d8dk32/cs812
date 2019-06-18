

int main() {
  int x;
  if (x) out 0; else out 1;
  x = 1;
  if (x) out 0; else out 1;
  x = 0;
  if (x) out 2; else ;
  x = 1;
  if (x) out 3; else ;
  if (x) if (x) out 4; else out 5; else out 6;
  if (x) out 7; else if (x) out 8; else out 9;
  x = 0;
  if (x) out 10; else if (x) out 11; else out 12;
  if (x) {
    out 13; out 14;
  }
  else {
    out 15; out 16;
  }
  x = 1;
  if (x) { } else {}
  if (x) { out 17; } else {}
}

