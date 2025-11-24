int fac(int i) {
  if (x <= 1)
    return 1;

  return fac(i-1) * i;
}

void main() {
  x = 3;
  fac(x);
}
