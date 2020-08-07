package com.lambdaschool.piggybank.repositories;

import com.lambdaschool.piggybank.models.Coin;
import org.springframework.data.repository.CrudRepository;

//helps us access our repository in the rest of the app
public interface CoinRepo extends CrudRepository<Coin, Long> {

}
