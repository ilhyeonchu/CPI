#include <stdio.h>

unsigned int stack[5];
int top = -1;
void push(unsigned int x) {
  if (top >= 5-1) {
    printf("Stack is full");
    return;
  };
  stack[++top] = x;
}

unsigned int pop() {
  if (top < 0) {
    printf("Stack is empty");
    return 0;
}
  return stack[top--];
}
int main() {
  unsigned var1, var2, var3, input, output1, output2;

  input = 3;
  push(input);
  scanf("%u", &input);
  push(input);
  output1 = pop();
  output2 = pop();
  input = output1+output2;
  push(input);
  output1 = pop();
  printf("%u", output1);

  return 0;
}