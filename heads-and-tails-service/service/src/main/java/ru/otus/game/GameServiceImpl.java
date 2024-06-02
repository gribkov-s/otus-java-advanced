package ru.otus.game;

import lombok.AllArgsConstructor;
import ru.otus.model.Game;
import ru.otus.repository.GameRepository;
import ru.otus.factgen.*;

@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private GameRepository repository;
    private FactGenerator factGen;

    @Override
    public Game play(int prediction) {
        Game game = new Game(prediction);
        FactGeneratorInt factGen = new FactGeneratorInt(0, 1);
        int fact = factGen.get();
        game.setFact(fact);
        return game;
    }
}
