# TowerDefense

Tämä on Helsingin yliopiston Ohjelmistotekniikka -kurssin työ,
jossa toteutan torninpuolustuspelin.

## Dokumentaatio

[Vaatimusmäärittely](documentation/requirements.md)

[Arkkitehtuurikuvaus](documentation/architecture.md)

[Työaikakirjanpito](documentation/work_journal.md)

## Kääntäminen

Projekti ei toimi Java 8 -versiolla koska Mavenin repositorysta ei saa vanhempia
JavaFX versioita kuin 11.
OpenJDK 8:n kanssa en ole onnistunut saamaan mitään JavaFX:ää toimimaan.
Archin java-openjfx kaatuu johonkin "no toolkit found" exceptioniin,
ja Gentoolla ei edes ole JavaFX -paketteja.
JavaFX 11 vaatii Java 11 -yhteensopivan kehitysympäristön.

Kääntäminen tapahtuu komennolla `mvn compile` ja projektin pääluokan voi
halutessaan suorittaa komennolla `mvn exec:java`.

## Testaus

Testit suoritetaan komennolla `mvn test` ja testikattavuusraportin saa tehtyä
komennolla `mvn jacoco:report` jonka tulossivut luodaan hakemistoon
target/site/jacoco.

## Checkstyle

Lähdekoodin tyylin tarkistukset suoritetaan komennolla
`mvn jxr:jxr checkstyle:checkstyle`.

Tarkistuksen tulokset löytyy sitten tiedostosta target/site/checkstyle.html.
