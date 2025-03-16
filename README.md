## Kuidas tööle panna:

1. **Järgi järgmisi samme, et käivitada rakendus:**
    - **Folderis `release`** on JAR-fail, mis tuleb käivitada järgmise käsuga:
      ```bash
      java -jar failnimi
      ```
    - Kui see on tehtud, saad tulemusi vaadata, minnes oma brauseris aadressile `http://localhost:8080`.
    - On võimalik, et tuleb oodata mõned sekundid, kuna rakenduse käivitamisel täidetakse andmete sisestamine andmebaasi.

---

## Kasutatud tehnoloogiad:
- **SSR**: frontendi jaoks tegin serveri poolset renderdamist.
- **Thymeleaf**: Kasutatud HTML-failides dünaamiliselt andmete kuvamiseks.
- **htmx**: Kasutatud kliendi poolt tehtavateks requestideks, mis ei põhjusta lehe uuesti laadimist.
- **HSQLDB**: Kasutatud andmebaas, kuid on võimalik hõlpsasti vahetada päris andmebaasi vastu.
- **Tailwind CSS**: Kasutatud rakenduse stiilimiseks.

---

## Projekti struktuur:

- **`CgiPraktikaApplication`**: Põhiklasse, mis käivitab rakenduse.
- **`Controller`** package: Siin on määratud kõik route'id.
- **`DataGeneration`** package: Klass, mis genereerib võltsandmed.
- **`DataObjects`**: package, kus on defineeritud andmebaasi tabelid ning nende klassid.
- **`Repository`** package: Interface andmebaasi suhtlemiseks.
- **`Service`** package: Klassid, mis saadavad andmeid Controller klassi ja andmebaasi vahel.
- **`resources/templates`**: Kaust, kus asuvad HTML leheküljed ja fragmendid, mida saadetakse kliendile.

---

## Mida oleks tahtnud veel lisada?

- **Testide kirjutamine**: Soovisin luua teste, et leida võimalikke vigu rakenduses.
- **Kasutaja loomise funktsioon**
- **Administraatori õigustega andmete lisamine**: Admin kasutajad oleksid saanud lisada andmeid.
- **Istekohtade soovituste täiendamine**: Soovitusi oleks saanud põhjalikumalt teha, praegu saab valida ainult, kas soovitakse olla akna all või mitte.

---

## Mis jäi halvasti?

- **Filtreerimine ja sorteerimine**: Iga filtreerimise ja sorteerimise järel viiakse kasutaja uuele lehele.
- **Andmete päringud**: Hetkel võetakse andmebaasis kõik andmed, mis vastavad filtritele, ning seejärel filtreeritakse need õigeks leheks. Andmete filtreerimist oleks saanud teha juba andmebaasis, et parandada jõudlust.

---

## Millega läks palju aega?

- **Andmebaasi lisamine**: Alguses kulges andmebaasi lisamine kiiresti, kuid hiljem tekkis mitmeid vigu, mille parandamiseks kulus palju aega.
- **Frontend**: Kulus palju aega, et õppida, kuidas teha serveripoolset renderdamist, kuna üldiselt olen frameworke kasutanud.

---

## Mis läks hästi?

- **Õppimine**: Üldiselt õppisin palju ja ehkki lõpptulemus ei ole täpselt see, mida ootasin, olen ikkagi enam-vähem rahul. Enamik asju toimib, ja rakendus töötab enam-vähem hästi.

---

## ChatGPT

Kasutasin **AI**-d, et arendada projekti visuaalset stiili. Kui tekkis probleem, mida ei suutnud ise lahendada, küsisin abi **ChatGPT**-lt või otsisin internetist lahendusi.

---

## Ajakulu

Kogu projekti tegemiseks kulus umbes **20 tundi** kolme nädala jooksul.
