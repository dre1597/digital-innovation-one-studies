package main

import (
	"fmt"
	"time"
)

func ping(channel chan string) {
	for i := 0; ; i++ {
		channel <- "ping"
	}
}

func pong(channel chan string) {
	for i := 0; ; i++ {
		channel <- "pong"
	}
}

func printMessage(channel chan string) {
	for {
		message := <-channel
		fmt.Println(message)
		time.Sleep(1 * time.Second)
	}
}

func main() {
	channel := make(chan string)

	go ping(channel)
	go printMessage(channel)
	go pong(channel)

	time.Sleep(10 * time.Second)
}
