package main

func divisiveisPorTres(n int) bool {
	return n%3 == 0
}

func divisiveisPorCinco(n int) bool {
	return n%5 == 0
}

func main() {
	for i := 1; i <= 100; i++ {
		if divisiveisPorTres(i) {
			println(i)
		}
	}

	for i := 1; i <= 100; i++ {
		if divisiveisPorTres(i) {
			println("Pin")
		}

		if divisiveisPorCinco(i) {
			println("Pan")
		}
	}
}
