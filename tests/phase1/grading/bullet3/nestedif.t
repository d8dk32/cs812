int main() {

  if (1) if (1) out 42; else out 43; else out 43;
  if (1) if (0) out 43; else out 42; else out 43;
  if (0) if (1) out 43; else out 42; else out 43;
  if (0) if (0) out 43; else out 43; else out 42;

  if (1) out 42; else if (1) out 43; else out 43;
  if (1) out 42; else if (0) out 43; else out 43;
  if (0) out 43; else if (1) out 42; else out 43;
  if (0) out 43; else if (0) out 43; else out 42;
}

