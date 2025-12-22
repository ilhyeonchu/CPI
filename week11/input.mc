int fac(int i) {
  if (i <= 1)
    return 1;

  return fac(i-1) * i;
}

void main() {
  x = 3;
  print(fac(x));
}
