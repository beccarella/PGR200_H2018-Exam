## Gitt tilbakemelding





### Hva var bra?
Pluss for å ha tatt i bruk interfaces.

Det er startet implementasjon av en del funksjonalitet og det meste ser ut til å fungere ut i fra testene som er skrevet.

Alle testene kjører grønt bortsett fra i ConferenceServerTest - dette ble det sagt i fra om på forhånd av vurderingen og de jobber med saken.
Det er på dette tidspunktet heller ikke blitt delt opp i foskjellige moduler, men har blitt fortalt at det også er noe som jobbes med. Det er bra, for det vil gjøre mappestrukturen mer ryddig og oversiktlig.


### Hva kan forbedres?
Kunne med fordel flyttet metodene som tar for seg decoding og parsing av requests og querys inn i en egen klasse, i stedet for at de ligger i ConferenceServer.
Bør ikke bruke testklassene til å teste mot ting (som f.eks server, i dette tilfellet) som ligger eksternt. Bør heller bruke de til å teste at metoder og klasser kjører korrekt (internt).


### Hva forstod vi ikke av koden?
Forstår ikke alt av logikken til alle metodene, men så er ikke oppgaven ferdig når denne vurderingen utføres heller. Sikkert enklere å forstå når koden refaktoreres og får dokumentasjon.