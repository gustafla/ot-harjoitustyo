# TowerDefense

Tämä on Helsingin yliopiston Ohjelmistotekniikka -kurssin työ,
jossa toteutan torninpuolustuspelin.

## Dokumentaatio

[Käyttöohje](documentation/manual.md)

[Vaatimusmäärittely](documentation/requirements.md)

[Arkkitehtuurikuvaus](documentation/architecture.md)

[Työaikakirjanpito](documentation/work_journal.md)

## Julkaisut

[1.1](../../releases/tag/v1.1)

[1.0](../../releases/tag/v1.0)

[Viikko 6 \(0.2\)](../../releases/tag/v0.2)

[Viikko 5 \(0.1\)](../../releases/tag/v0.1)

## Kääntäminen ja paketointi

Projekti ei toimi Java 8 -versiolla koska Mavenin repositorysta ei saa vanhempia
JavaFX versioita kuin 11.
OpenJDK 8:n kanssa en ole onnistunut saamaan mitään JavaFX:ää toimimaan.
Archin java-openjfx kaatuu johonkin "no toolkit found" exceptioniin,
ja Gentoolla ei edes ole JavaFX -paketteja.
JavaFX 11 vaatii Java 11 -yhteensopivan kehitysympäristön.

Kääntäminen tapahtuu komennolla `mvn compile` ja projektin pääluokan voi
halutessaan suorittaa komennolla `mvn exec:java`.

Suoritettavan Java Archiven (.jar -tiedoston) voi generoida komennolla
`mvn package`. JAR-tiedosto luodaan target-hakemistoon.

## Testaus

Testit suoritetaan komennolla `mvn test` ja testikattavuusraportin saa tehtyä
komennolla `mvn jacoco:report` jonka tulossivut luodaan hakemistoon
target/site/jacoco.

## JavaDoc

JavaDoc -dokumentaatiosivuston voi luoda komennolla `mvn javadoc:javadoc`.
JavaDoc-sivut löytyvät hakemistosta target/site/apidocs.

## Checkstyle

Lähdekoodin tyylin tarkistukset suoritetaan komennolla
`mvn jxr:jxr checkstyle:checkstyle`.

Tarkistuksen tulokset löytyy sitten tiedostosta target/site/checkstyle.html.
