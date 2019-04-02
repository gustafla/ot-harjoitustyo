# TowerDefense

Tämä on Helsingin yliopiston Ohjelmistotekniikka -kurssin työ,
jossa toteutan torninpuolustuspelin.

## Dokumentaatio

[Vaatimusmäärittely](dokumentointi/requirements.md)

[Työaikakirjanpito](dokumentointi/work_journal.md)

## Kääntäminen

Projekti ei toimi Java 8 -versiolla koska Mavenin repositorysta ei saa vanhempia
JavaFX versioita kuin 11.
OpenJDK 8:n kanssa en ole onnistunut saamaan mitään JavaFX:ää toimimaan.
Archin java-openjfx kaatuu johonkin "no toolkit found" exceptioniin,
ja Gentoolla ei edes ole JavaFX -paketteja.
JavaFX 11 vaatii periaatteessa Java 11 -kehitysympäristön, mutta omalla
koneellani se on tähän mennessä toiminut OpenJDK 10:llä.

Kääntäminen tapahtuu komennolla `mvn compile` ja projektin pääluokan voi
halutessaan suorittaa komennolla `mvn exec:java`.

## Testaus

Testit suoritetaan komennolla `mvn test` ja testikattavuusraportin saa tehtyä
komennolla `mvn jacoco:report` jonka tulossivut luodaan hakemistoon
target/site/jacoco.
