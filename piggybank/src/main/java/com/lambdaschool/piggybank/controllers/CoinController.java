package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//sets up spring framework to recieve requests
@RestController
public class CoinController {

    //dimensions a new CoinRepo as coinRepo and connects it to our repository
    @Autowired
    CoinRepo coinRepo;

    //creates new method that will
    //let us receive requests at the url = "http://localhost:2019/total
    //return a json object
    @GetMapping(value = "total", produces = {"application/json"})
    public ResponseEntity<?> giveTotal(){

        //dimension list
        List<Coin> totalCoins = new ArrayList<>();
        //adds all the coins in coinRepo to totalCoins
        coinRepo.findAll().iterator().forEachRemaining(totalCoins::add);
        //dimension accumulator
        double accu = 0;

        //loop through the totalCoins list
        for (Coin c : totalCoins){
            //add the value of each coin to the accumulator
            accu += c.getValue() * c.getQuantity();
            if (c.getQuantity() > 1){ //if the quantity of the coin is more than one use the plural name
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            }else { //otherwise use the singular name
                System.out.println(c.getQuantity() + " " + c.getName());
            }
        }
        //log the accumulator
        System.out.println("The piggy bank holds " + accu);

        //let the client know it was a successful request
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "money/{amount}", produces = {"application/json"})
    public ResponseEntity<?> removeAmount(@PathVariable double amount){

        //dimension list
        List<Coin> totalCoins = new ArrayList<>();
        //adds all the coins in coinRepo to totalCoins
        coinRepo.findAll().iterator().forEachRemaining(totalCoins::add);

        //dimension accumulator
        double amountAccu = 0;
        double accu = 0;

        //dim the index
        int i = 0;
        //loop through code until amount = the amount accumulator
        while(amountAccu != amount){

            if(i > totalCoins.size()){
                i = 0;
            }
            System.out.println("\niterator " + i);
            System.out.println("\ntotalCoins " + totalCoins.size());
            System.out.println("\namountAccu " + amountAccu);
            //if the sum of the accu and the current coin value is less than the amount
            //to be subtracted add that amount to the accu
            if((Math.round(amountAccu * 100) / 100) + totalCoins.get(i).getValue() <= amount){
                amountAccu = amountAccu + totalCoins.get(i).getValue();

                //if the quantity of the coin is 1 remove it from the array
                if(totalCoins.get(i).getQuantity() == 1){
                    totalCoins.remove(i);
                //otherwise subtract one from the quantity
                }else{
                    totalCoins.get(i).setQuantity(totalCoins.get(i).getQuantity() - 1);
                }

            }

            //increase the index if it is less than the length of totalCoins
            if(totalCoins.size() - 1  > i){
                i++;
            }else{
                i = 0;
            }
        }

        //loop through the totalCoins list
        for (Coin c : totalCoins){
            //add the value of each coin to the accumulator
            accu += c.getValue() * c.getQuantity();
            if(c.getName() == "Dollars"){
                    System.out.println("$" + c.getQuantity());
            }else{
                if (c.getQuantity() > 1){ //if the quantity of the coin is more than one use the plural name
                    System.out.println(c.getQuantity() + " " + c.getNameplural());
                }else { //otherwise use the singular name
                    System.out.println(c.getQuantity() + " " + c.getName());
                }

            }
        }
        //log the total
        System.out.println("The piggy bank holds $" + accu);

        //let the client know it was a successful request
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
