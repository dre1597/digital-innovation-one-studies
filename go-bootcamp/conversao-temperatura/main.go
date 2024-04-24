package conversao_temperatura

import "fmt"

const EbulicaoKelvin = 373.0

func main() {
	temperaturaKelvin := EbulicaoKelvin
	temperaturaCelsius := temperaturaKelvin - 273.0

	fmt.Printf("A temperatura de ebulicão da água em Kelvin é de %g e em Celcius é de %g\n", temperaturaKelvin, temperaturaCelsius)
}
