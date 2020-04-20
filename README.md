# DOCUMENTATIE Hotel Management System
From students to students

# 1.Descrierea problemei
In contextul anului 2020, tehnologizarea business-urilor este vitala, un business fara o tehnologie solida in spate e sortit unui esec iminent. Atfel problema propusa spre rezolvare este facilizarea unui sistem de management pentru un lant de hotel-uri astfel incat toate activitatile sa aiba loc online. Problema presupune implementarea unei aplicatii web-based care sa permita manager-ului hotelului sa desfasoare toate activitatile online, print-un GUI interactiv, care sa usureze exponential necesitatea folosirii pixului si a hartiei pentru a gestiona afacerea sub un control permanent. Aplicatia ofera managerului puterea si flexibilitatea de a controla tot sistemul dintr-un mediu online singular, insa aceasta este destinata si utilizatorilor de rand, oferind un acces limitat la mediu. 

Aplicatia de management al unui lant hotelier ar trebui sa ofere urmatoarele functionalitati:
- rezervari de camere;
- staff management;
- postare de camere libere pentru manager;
- utilizatorii pot sa vizualizeze camerele libere la anumite hoteluri, si sa rezerve;
- administratorul poate modifica, sterge sau face update pentru o rezervare aflata in sistem;
- alte servicii furnizate de hotel;
- utilizatorii sunt notificati cand lantul hotelier nu mai are camere, sau cand sunt camere disponibile.

### Avantajele Sistemului
 - Economiseste din timpul utilizatorului de a cauta o camera;
 - Calculeaza costul exact, incluzand unele oferte in timp real;
 - Salveaza timp si costuri, atat pentru utilizatori cat si pentru manager.
 
 ### Dezavantajele Sistemului
-   Procesul de rezervare presupune existenta unei identitati ale utilizatorului, fapt pe care sistemul nu-l poate detecta automat;
-   Necesita conexiune la internet.


# 2. Tehnologii utilizate

- Spring Boot
- The Java Persistence API (JPA) -este o specificare Java pentru accesarea, persistenta  si gestionarea datelor intre obiectele Java si baza de date relationala;
- Hibernate ORM (or simply Hibernate) este un tool de mapping de la obiect la partea relationala a bazelor de date;
- Mockito framework- librarie Java care e utilizata pentru testarea efectiva a aplicatiilor Java;
- MySql.

# 3. Solutia aleasa.Implementare
Am ales impartirea functionalitatii aplicatiei in 2 mari parti: partea utilizator si partea administrator, aceasta ofera functionalitati diferite in functie de contul si parola cu care se face logarea. Aplicatia permite utiliatorilor comuni sa selecteze perioada rezervarii, hotelul si tipul camerei. Pentru tipul camerei am folosit un design pattern numit FACTORY pentru a putea crea o camera (reprezentata prin clasa de tipul Room) in functie de tipul ei: camera de conferinte,camera premium sau camera regulara. La crearea unei camere, administratorul va introduce doar tipul ei, design pattern-ul Factory, preluand de aici si creand camera curenta. Un alt design pattern folosit este Observer, cand un hotel va ajunge la 0 camere libere un Observer va notifica utilizatorul sau admin-ul cu privire la acest lucru. Pentru legarea la baza de date, am folosit MySql, si JPA pentru pastrarea consistentei intre clasele obiectului si tabelele bazei de date. Administratorul poate sa genereze rapoarte despre hoteluri, poate modifica, sterge sau adauga camere, rezervari, si alte chestii specifice.





## Diagrama bazei de date

![database](https://user-images.githubusercontent.com/49992235/77261119-def85e00-6c94-11ea-9357-47297dd4271c.png)

# 4. Analiza problemei, modelare, scenarii, cazuri de utilizare

Analiza preliminara a problemei ne imparte sarcina in crearea in primul rand a modelelor de baza pentru aplicatie, si anume clasele care vor corespunde cu tabelele din baza de date(User, Admin, Booking, Hotel, Room), cu ajutorul acestor clase, ale caror campuri corespund neaparat cu numele coloanelor din baza de date, se vor face interogari specifice, datele din baza noastra de date relationala vor fi stocate in aplicatia Java practic in aceste clase, care, cu ajutorul JPA vor vamane mereu consistente cu baza noastra de date.

Tot din analiza problemei reiese faptul ca avem nevoie de o conexiune la baza de date, conexiune realizata cu ajutorul tool-ului Hibernate si a facilitatilor puse la dispozitie de JPA, prin crearea unor REPOSITORY pentru fiecare clasa in parte.

n continuare voi prezenta scenarii si cazuri de utilizare sub forma de diagrame use-case:

![UserUseCase](https://user-images.githubusercontent.com/49992235/77851092-96cec380-71df-11ea-9476-e4610c9dd1a6.png)

-	Use case: Users operations
-	Primary actor:utilizatorul
-	Main succes scenario:
--	Utiliatorul porneste view-ul(in web) pentru oparatiile cu clienti
--	Utilizatorul introduce username si parola pentru logare
--	Utilizatorul apasa butonul corespunzator optiunii dorite(rezervare, vizualizare stiri lant hotelier)
-- Utilizatorul selecteaza orasul, hotelul, si camera dorita
--	Aplicatia verifica daca inputul este corect daca se doreste rezervare si toate datele sunt valide( perioada nu este ocupata, camera este libera, etc)
--	Utilizatorul poate vizualiza istoricul propriilor rezervari


![AdminUseCase](https://user-images.githubusercontent.com/49992235/77851561-8409be00-71e2-11ea-9fd8-d9de7a5e2e3b.png)

-	Use case: Admins operations
-	Primary actor:admin
-	Main succes scenario:
--	Adminul porneste view-ul(in web) asociat administratorului
--	Adminul introduce username si parola pentru logare
--	Adminul apasa butonul corespunzator optiunii dorite(Hotels Detais, User Deteils, Booking details, Statistics)
-- Adminul modifica/sterge/adauga clienti/hoteluri/camere
--	Adminul verifica statistica hoteliera pe o perioada aleasa
-- Adminul adauga oferte, notifica utilizatorii prin aplicatie

#### Modelare camere hoteluri Factory DP
    Pentru modelarea camerelor Hotelului am folosit Design Pattern-ul Factory, pentru o maleabilitate asupra tipului camerelor care sa permita o modelare usoara si flexibila pentru admin, in momentul crearii, modificarii camerelor existente in propriul lant hotelier:
    
![FactoryPattern](https://user-images.githubusercontent.com/49992235/77852096-6d189b00-71e5-11ea-9d82-aca4172b5ff9.png)

Se observa in imaginea de mai sus, faptul ca Controllerul pentru camere are o dependinta fata de Room Factory, pe care il foloseste pentru crearea unei camere propriu zise din cele 3 existente.Folosirea Factory method este folositoare cand se instantiaza una din cele 3 subclase,  nu trebuie schimbat codul cand se doreste schimbarea unei instante a subclasei.

#### Modelare stiri utilizatori Observer DP
    Pentru modelarea stirilor si a modului in care utilizatorii sunt instiintati in momentul in care hotelul are camere libere, sau hotelul nu mai are camere libere, am folosit Design Pattern-ul Observer prezentat mai jos: 
    
    
![ObserverPattern](https://user-images.githubusercontent.com/49992235/77852502-afdb7280-71e7-11ea-9b37-b5b341db9d86.png)

Dupa cum se observa in imagine, observatorii concreti sunt utilizatorii, care implementeaza interfata Observer, oferind o implementare concreta a metodei update(), prin care campul specific fiecarul utilizator se updateaza cu stirile primite de la RoomController. RoomController e un agregat, prin faptul ca mentile o lista dinamica cu utilizatorii sistemului de management al lantului hotelier. Prin metoda addObserver() se mai adauga un observator, removeObserver() face contrariul, iar notifyAllObserves() are un pseudocod prezentat in notita aferenta. Metoda de baza a clasei este checkRooms(), in aceasta se adauga dinamic toti utilizatorii in lista de observatori, se verifica camerele hotelurilor, si in functie de disponibilitatea sau indisponibilitatea acestora sunt notificati utilizatorii.

#### Metode principale si descriere sumara

- ###### metoda findByUsername(String username) - clasa AdminController
     -- aceasta metoda este una utila pentru cazul in care unul dintre adminii sistemului de management al lantului hotelier isi pierde parola, un alt admin avand acces la vechea parola prin intermediul acestei metode;
- ###### metoda findByCity(String city) - clasa HotelController
     -- pe pagina web a aplicatiei este foarte utila o filtrare a hotelurilor cat mai la indemana utilizatorului, pentru aceasta am creat aceasta metoda, care afiseaza doar hotelurile din orasul selectat de utilizator;
- ###### metoda findBySearchString(String searchString) - clasa HotelController
     -- ca pe orice pagina web, aceasta aplicatie trebuie sa ofere un feedback in cazul in care utilizatorul cauta dupa numele unui Hotel. Pentru aceasta metoda de mai sus primeste Stringul introdus de utilizator in textbox-ul corespunzator, si ofera rezultatele care se potrivesc( exemplu : utilizatorul introduce in textfield 2 nume de hoteluri care exista in lantul hotelier, aplicatia va cauta in baza de date, si daca numele sunt prezente, se vor afisa doar acele hoteluri);
- ###### metoda findByCity(String city) - clasa UserController
    -- aceasta este o metoda necesara statisticilor care vor fi afisate catre administrator, spre exemplu cand se doreste prezentarea unei statistici care sa prezinte orasele din care vin cei mai multi clienti, fapt util pentru administrator in vederea actualizarii publicitatii in orasele de unde nu prea vin clienti
- ###### metode create, update, delete - pentru toate clasele proiectului
- ###### metoda findFreeRooms() - clasa RoomController
    -- aceasta metoda ajuta la afisarea doar a camerelor libere, cele ocupate nefiind afisate
- ###### metoda findByHotel(String hotel) - clasa RoomController
    -- aceasta metoda afiseaza doar camerele din hotelul selectat, nu din toate hotelurile
- ###### metoda create(Reservation reservation) - clasa ReservationController
    -- aceasta metoda este una din cele mai folositoare, prin aceasta se creeaza rezervari pentru diferite camere ale hotelurilor. Aceata metoda verifica de asemenea ca perioadele din rezervari sa nu se suprapuna prin urmatorul algoritm:
    Fie Conditia A care inseamna ca perioada 1 e complet dupa perioada 2

                    (True if StartA > EndB)

    Fie Conditia  B care inseamna ca perioada 1 e complet inainte de perioada 2

                    (True if EndA < StartB)
    Suprapunerea exista daca nici A nici B nu e TRUE.
    (Daca o perioada nu e complet dupa alta, nici complet inainte, atunci sigur se suprapun)

   Legea lui DE MORGAN:

                    Not (A Or B) <=> Not A And Not B

    Ceea ce inseamna :(StartA <= EndB)  and  (EndA >= StartB)

#### Testare
- In sectiunea de Unit Teste, sunt prezente teste pentru toate metodele de mai sus. Pentru create, update si delete, fiind repetitive, s-au executat teste doar pe cate o clasa.

