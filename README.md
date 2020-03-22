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
- alte servicii furnizate de hotel.

### Avantajele Sistemului
 - Economiseste din timpul utilizatorului de a cauta o camera;
 - Calculeaza costul exact, incluzand unele oferte in timp real;
 - Salveaza timp si costuri, atat pentru utilizatori cat si pentru manager.
 
 ### Dezavantajele Sistemului
-   Procesul de rezervare presupune existenta unei identitati ale utilizatorului, fapt pe care sistemul nu-l poate detecta automat;
-   Necesita conexiune la internet.


# 2. Tehnologii utilizate
-Spring Boot
-The Java Persistence API (JPA) -este o specificare Java pentru accesarea, persistenta  si gestionarea datelor intre obiectele Java si baza de date relationala;
-Hibernate ORM (or simply Hibernate) este un tool de mapping de la obiect la partea relationala a bazelor de date;
-Mockito framework- librarie Java care e utilizata pentru testarea efectiva a aplicatiilor Java;
-MySql.

# 3. Solutia aleasa.Implementare
Am ales impartirea functionalitatii aplicatiei in 2 mari parti: partea utilizator si partea administrator, aceasta ofera functionalitati diferite in functie de contul si parola cu care se face logarea. Aplicatia permite utiliatorilor comuni sa selecteze perioada rezervarii, hotelul si tipul camerei. Pentru tipul camerei am folosit un design pattern numit FACTORY pentru a putea crea o camera (reprezentata prin clasa de tipul Room) in functie de tipul ei: camera de conferinte,camera premium sau camera regulara. La crearea unei camere, administratorul va introduce doar tipul ei, design pattern-ul Factory, preluand de aici si creand camera curenta. Un alt design pattern folosit este Observer, cand un hotel va ajunge la 0 camere libere un Observer va notifica utilizatorul sau admin-ul cu privire la acest lucru. Pentru legarea la baza de date, am folosit MySql, si JPA pentru pastrarea consistentei intre clasele obiectului si tabelele bazei de date. Administratorul poate sa genereze rapoarte despre hoteluri, poate modifica, sterge sau adauga camere, rezervari, si alte chestii specifice.





## Diagrama bazei de date


