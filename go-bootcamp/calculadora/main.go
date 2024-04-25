package main

import "fmt"

func sum(a int, b int) int {
	return a + b
}

func sub(a int, b int) int {
	return a - b
}

func mult(a int, b int) int {
	return a * b
}

func div(a int, b int) int {
	return a / b
}

func main() {
	a := sum(1, 2)
	b := sub(1, 2)
	c := mult(1, 2)
	d := div(1, 2)

	fmt.Println(a, b, c, d)
}
