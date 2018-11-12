# PGR200 Hovedinnlevering

Rebecca Urquhart(urqreb17) og Tharin Chobkaphol (clotha17)


## Hvordan kjøre programmet


#### Eksempel kjøring

```bash
> mvn test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] PGR200 Mappeinnlevering                                            [pom]
[INFO] database                                                           [jar]
[INFO] http                                                               [jar]
[INFO] commandline                                                        [jar]
[INFO] 
[INFO] ----------< no.kristiania.pgr200:pgr200-innlevering-starter >-----------
[INFO] Building PGR200 Mappeinnlevering 0.0.1-SNAPSHOT                    [1/4]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.2:prepare-agent (default) @ pgr200-innlevering-starter ---
[INFO] argLine set to -javaagent:/Users/rebeccaurquhart/.m2/repository/org/jacoco/org.jacoco.agent/0.8.2/org.jacoco.agent-0.8.2-runtime.jar=destfile=/Users/rebeccaurquhart/Desktop/pgr200-eksamen-beccarella/target/jacoco.exec
....
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
....


> mvn install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Building conference-server 0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
...
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ conference-server ---
[INFO] Building jar: e:\Profiles\jbrodwal\workspaces\demo\conference-server\target/conference-server-0.1-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.1.1:shade (default) @ conference-server ---
[INFO] Including org.postgresql:postgresql:jar:42.2.2 in the shaded jar.
[INFO] Replacing original artifact with shaded artifact.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.565 s
[INFO] Finished at: 2018-07-08T17:18:12+02:00
[INFO] Final Memory: 21M/211M
[INFO] ------------------------------------------------------------------------

Stå i hovedpakka(hovedmodulen)
> psql --username postgres --command="create database ... with owner .."'
> java -jar database/target/database-0.0.1-SNAPSHOT.jar insert 
> java -jar database/target/database-0.0.1-SNAPSHOT.jar list
> java -jar database/target/database-0.0.1-SNAPSHOT.jar resetdb
```


## Vurdering av eget arbeid

Dette har vært et adskillig tøffere prosjekt å jobbe med enn arbeidskrav 2. Stressnivået har vært skyhøyt, vi har blitt pushet til randen og har ikke alltid hatt den beste tonen oss i mellom. Men vi har kjempet igjennom det og partnerskapet er fortsatt inntakt. Vi har blitt kjent med hverandre på godt og vondt, og har lært mye mer om det å jobbe i et partnerskap på dette prosjektet enn vi har gjort på tidligere prosjekter. 

Vi har klart å komme frem til en løsning, som kanskje ikke er like god som vi skulle ønske, men som vi begge har lagt absolutt alt vi har i. Hadde vi blitt vurdert ut i fra hvor mye innsats vi har lagt i arbeidet, så hadde vi definitivt fått en A. Rent faglig sett derimot, så mener vi at vi har gjort nok til å få bestått (vi øsnker oss selvfølgelig en C, men tror på en D). 
Planen vår var å lage en langt større database og joine flere av tabellene sammen, men vi endte til slutt opp med å lage en liten og enkel database, slik at vi kunne fokusere på skikkelig funksjonalitet fremfor en grandios database arkitektur som ikke hadde fungert like bra.

Vi har slitt veldig med å få til koblingen mellom server og database. Her har vi endelig sett lyset (og det viste seg å egentlig være ganske enkelt), men når det kommer til å skrive metodene / klassene som sender tar i mot og videresender input til de riktige instansene i riktig format - der fomler vi fortsatt i mørket.

Vi skulle veldig gjerne tatt i bruk fremmednøklene vi har lagt inn i databasen, men det har vi rett og slett ikke rukket. Dette er noe vi vil jobbe videre med og bygge ut ved en senere anledning. 

Vi har heller ikke rukket å lage ny video.

### Arkitektur

![Architecture Overview](doc/conference-server.png)

### Programflyt

![Programflyt](doc/conference-server-flow.png)

### Forslag til datamodell

![Datamodell](doc/conference-data-model.png)

## Link til video

https://www.youtube.com/watch?v=laElnEZqslE


##

I tilbakemeldingen er det lurt å stille spørsmålene: 1. Hva lærte jeg av denne koden? 2. Hva forsto jeg ikke av denne koden? 3. Hva tror jeg forfatterne av koden kunne ha nyttig av å lære?

## Sjekkliste for innleveringen

- [ ] Kodekvalitet
  - [x] Koden er klonet fra GitHub classrom
  - [ ] Produserer `mvn package` en executable jar? (tips: Bruk `maven-shade-plugin`)
  - [ ] Bruker koden Java 8 og UTF-8
  - [ ] Bygger prosjektet på [https://travis-ci.com](https://travis-ci.com)?
  - [ ] Har du god test-dekning? (tips: Sett opp jacoco-maven-plugin til å kreve at minst 65% av linjene har testdekning)
  - [ ] Er koden delt inn i flere Maven `<modules>`?
  - [ ] Bruker kommunikasjon mellom klient og server HTTP korrekt?
  - [ ] Kobler serveren seg opp mot PostgreSQL ved hjelp av konfigurasjon i fila `innlevering.properties` i *current working directory* med `dataSource.url`, `dataSource.username`, `dataSource.password`?
- [ ] Funksjonalitet
  - [ ] add: Legg til et foredrag i databasen med title, description og topic (valgfritt)
  - [ ] list: List opp alle foredrag i basen med et valgfritt topic
  - [ ] show: Vis detaljer for et foredrag
  - [ ] update: Endre title, description eller topic for et foredrag
  - [ ] Valgfri tillegg: Kommandoer for å sette opp hvor mange dager og timer konferansen skal vare og hvor mange parallelle spor den skal inneholde.
- [ ] Dokumentasjon i form av README.md
  - [ ] Navn og Feide-ID på dere de som var på teamet
  - [ ] Inkluderer dokumentasjonen hvordan man tester ut funksjonaliteten programmet manuelt? (Inkludert eventuell ekstra funksjonalitet dere har tatt med)
  - [ ] Inkluderer dokumentasjonen en evaluering av hvordan man jobbet sammen?
  - [ ] Inkluderer dokumentasjonen en screencast av en parprogrammeringsesjon?
  - [ ] Inkluderer dokumentasjonen en evaluering *fra* en annen gruppe og en evaluering *til* en annen gruppe?
  - [ ] Inkluderer dokumentasjonen en UML diagram med datamodellen?
  - [ ] Inkluderer dokumentasjonen en link til screencast av programmeringsesjon?
  - [ ] Inkluderer dokumentasjonen en egenevaluering med hvilken karakter gruppen mener de fortjener?

### Forberedelse

- [ ] Finn endelig grupperpartner innen 1. november
- [ ] Finn en gruppe for gjensidig evaluering innen 1. november

### Innlevering

- [ ] Gi veilederne `hakonschutt` og `mudasar187` tilgang til repository
- [ ] Tag koden med `innlevering` i GitHub
- [ ] Ta en zip-eksport fra GitHub
- [ ] Last opp zip-fil i WiseFlow
- [ ] Dersom innlevering #1 eller innlevering #2 ikke ble godkjent *i WiseFlow*, last opp zip-fil med hver av disse innleveringene

## Retningslinjer for vurdering

### Minimum krav for bestått

- Kompilerende kode som er sjekket inn i GitHub
- Tester som gjør noe ikke totalt ufornuftig (eksempel på ufornuftlig `assertTrue(true)` eller `assertEquals(4, 2+2)`)
- Kjørbart program som legger inn data i databasen

### Minimum krav for C

- Skriv og les programmet fra databasen i Java i henhold til deres egen dokumentasjon
- Les og skriv data over socket
- Kode lagret på GitHub, kompilerer og utfører en oppgave uten å krasje

### Minimum krav for B

De fleste av følgende må være oppfyllt:

- Et rimelig nivå med enhetstester som kjører på Travis CI
- Kode uten større skrivefeil, feil innrykk, slukte exceptions eller advarsler fra Eclipse
- Readme som beskriver 4-10 steg for å demonstrere programmet
- God kode: Enkel, konsis, uttrykksfull, velformattert kode uten vestlige feil eller mangler
- Ingen alvorlige feil, SQL injection hull, krasj ved uventet input

### Krav for A

Løsningen må oppfylle alle krav til B og ha 2-3 områder som hever den ytterligere:

- Velskrevet (men ikke nødvendigvis omfattende) dokumentasjon
- At videoen får fram kvalitetene i designet
- Uttrykksfulle enhetstester som er effektive på å fange feil og som kjører på Travis CI
- En velbegrunnet datamodell med 4-8 klasser
- En lettfattelig og utvidbar http-server
- Spennende generisk kode som egentlig er unødvendig kompleks for å løse problemet
- Enkel kode som løser problemet presist og konsist (i konflikt med forrige)

Grupper på 3 må ha flere av disse enn grupper på 2 for å få en A.
