# Arkkitehtuurikuvaus

## Rakenne

Pelissä on JavaFX-sovellusluokka TowerDefenseUi, joka käyttää JavaFX Canvas-
sekä AnimationTimer -luokkia pelin tilan piirtämiseen. Tarvittavat tiedot
piirtämistä varten ongitaan TowerDefensen gettereillä ja muiden
domain-pakkauksen luokkien julkisella rajapinnalla. Jokaisella
AnimationTimer.handle() -kutsulla, ennen piirtämistä suoritetaan
TowerDefense-luokan update(double deltaTime) -metodi joka puolestaan edistää
peliä deltaTime (sekuntia) ajan verran.

![Class diagram](pictures/classdiagram.png)

## Hyökkäysaallon vaihtuminen

![Sequence diagram](pictures/wave_sequence.png)
