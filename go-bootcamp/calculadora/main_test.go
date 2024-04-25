package main

import "testing"

func TestSum(t *testing.T) {
	expected := 3
	result := sum(1, 2)

	if result != expected {
		t.Errorf("Expected %d, but got %d", expected, result)
	}
}

func TestSub(t *testing.T) {
	expected := -1
	result := sub(1, 2)

	if result != expected {
		t.Errorf("Expected %d, but got %d", expected, result)
	}
}

func TestMult(t *testing.T) {
	expected := 2
	result := mult(1, 2)

	if result != expected {
		t.Errorf("Expected %d, but got %d", expected, result)
	}
}

func TestDivide(t *testing.T) {
	expected := 0
	result := div(1, 2)

	if result != expected {
		t.Errorf("Expected %d, but got %d", expected, result)
	}
}
