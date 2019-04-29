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

OpenJFX 11 jota projekti käyttää vaatii Java version 10 tai uudemman.
Voit mahdollisesti poistaa Open JavaFX:n riippuvuuksista pom.xml-tiedostosta
jolloin projekti saattaa kääntyä Java 8 -JDK:lla kunhan OpenJFX 8 on asennettu
tai JDK on Oraclen.

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
