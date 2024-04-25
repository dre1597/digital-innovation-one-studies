package main

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

type Customer struct {
	ID      string `json:"id,omitempty"`
	Name    string `json:"name,omitempty"`
	Address string `json:"address,omitempty"`
}

var customers []Customer

func GetAll(w http.ResponseWriter, _ *http.Request) {
	log.Printf("GetAll")
	err := json.NewEncoder(w).Encode(customers)
	if err != nil {
		return
	}
}

func GetOne(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	for _, item := range customers {
		if item.ID == params["id"] {
			err := json.NewEncoder(w).Encode(item)
			if err != nil {
				return
			}
			return
		}
	}

	err := json.NewEncoder(w).Encode(&Customer{})
	if err != nil {
		return
	}
}

func Create(w http.ResponseWriter, r *http.Request) {
	var customer Customer
	err := json.NewDecoder(r.Body).Decode(&customer)
	if err != nil {
		return
	}

	customers = append(customers, customer)
	err = json.NewEncoder(w).Encode(&customer)
	if err != nil {
		return
	}
}

func Update(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	for index, item := range customers {
		if item.ID == params["id"] {
			customers = append(customers[:index], customers[index+1:]...)
			var customer Customer
			err := json.NewDecoder(r.Body).Decode(&customer)
			if err != nil {
				return
			}
			customer.ID = params["id"]
			customers = append(customers, customer)
			err = json.NewEncoder(w).Encode(&customer)
			if err != nil {
				return
			}
			return
		}
	}

	err := json.NewEncoder(w).Encode(&Customer{})
	if err != nil {
		return
	}
}

func Delete(_ http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	for index, item := range customers {
		if item.ID == params["id"] {
			customers = append(customers[:index], customers[index+1:]...)
			break
		}
	}
}

func main() {
	r := mux.NewRouter()

	r.HandleFunc("/customers", GetAll).Methods("GET")
	r.HandleFunc("/customers/{id}", GetOne).Methods("GET")
	r.HandleFunc("/customers", Create).Methods("POST")
	r.HandleFunc("/customers/{id}", Update).Methods("PUT")
	r.HandleFunc("/customers/{id}", Delete).Methods("DELETE")
	err := http.ListenAndServe(":8080", r)
	if err != nil {
		return
	}
}
