# Poker Game

## Endpoints
CREATE_GAME : ```/game```

DELETE_GAME : ```/game/{gameId}```

CREATE_PLAYER : ```/player```

ADD_PLAYER_TO_GAME : ```/game/{gameId}/player/{playerId}```

CREATE_GAME_DECK : ```game_deck/{gameId}/{noOfDeck}```

DEAL_CARDS_TO_PLAYER : ```deal_cards/{gameId}/{playerId}```

SHUFFLE_GAME_DECK : ```shuffle/{gameId}```

GET_CARDS_OF_PLAYER : ```cards/{gameId}/{playerId}```

GET_COUNT_OF_CARDS_IN_DECK : ```cards/count/{gameId}/{playerId}```

GET_CARDS_IN_DECK_IN_SORTED_ORDER : ```cards/sorted/{gameId}```

