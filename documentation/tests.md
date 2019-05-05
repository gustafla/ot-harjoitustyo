# Testausdokumentti

Peliä on testattu automaattisilla JUnit yksikkö- ja integraatiotesteillä, sekä
pelaamalla.

## Sovelluslogiikka

Pelin logiikka, eli domain-pakkauksen luokkia integraatiotestaa
TowerDefenseTest-luokan testit jotka simuloivat vihollisaaltojen liikkumista ja
sijoitettujen tykkien toimintaa. Muille domainin luokille on yksikkö- ja
integraatiotestejä omissa Test-luokissaan.

## DAO-luokat

Ainoalla DAO-luokalla, TowerDefenseFileDao:lla ei ole automaattisia testejä,
mutta sen toiminta pelin tallennukseen aaltojen välissä on testattu
manuaalisesti pelaamalla peliä OpenJDK11 -javalla sekä jarista että mavenista.

## Kattavuus

Käyttöliittymäluokkia lukuunottamatta pelin testit kattavat 90% riveistä ja 90%
haarautumista.

## Laatuongelmat

Peli ei ilmoita milloin tila on tallennettu eikä kerro onnistuiko tallennuksen
lataus vai alkoiko peli tyhjältä pohjalta.
