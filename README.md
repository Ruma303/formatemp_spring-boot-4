# Esercitazione: Gestione di una Rubrica Contatti

Sviluppare una semplice API RESTful che gestisce una rubrica di contatti dal database.

---

## **Database**

Creazione di un database di esempio:

```sql
CREATE DATABASE IF NOT EXISTS formatemp_rubrica;

USE formatemp_rubrica;

CREATE TABLE IF NOT EXISTS contatti (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    numero VARCHAR(255) NULL,
    note TEXT NULL
);

DESCRIBE contatti;

INSERT INTO contatti (nome, cognome, email, numero, note) VALUES
('Mario', 'Rossi', 'mario.rossi@example.com', '3331234567', 'Amico stretto'),
('Laura', 'Verdi', 'laura.verdi@example.com', '3479876543', 'Collega di lavoro'),
('Giovanni', 'Bianchi', 'giovanni.bianchi@example.com', '3385551212', 'Cliente importante'),
('Anna', 'Neri', 'anna.neri@example.com', '3491112233', 'Fornitore'),
('Luca', 'Gialli', 'luca.gialli@example.com', '3394445566', 'Conoscente'),
('Sofia', 'Marrone', 'sofia.marrone@example.com', '3456667788', 'Amica di infanzia'),
('Marco', 'Blu', 'marco.blu@example.com', '3347778899', 'Contatto professionale'),
('Elena', 'Viola', 'elena.viola@example.com', '3409990011', 'Vicino di casa'),
('Davide', 'Arancio', 'davide.arancio@example.com', '3352223344', 'Compagno di scuola'),
('Giulia', 'Grigio', 'giulia.grigio@example.com', '3483334455', 'Membro associazione');

SELECT * FROM contatti;
```

---

## **`pom.xml`**

Il file `pom.xml` è il cuore della configurazione di un'applicazione basata su Maven, che gestisce le dipendenze, i plugin e le configurazioni del ciclo di vita del progetto. In un progetto Spring Boot, il `pom.xml` è essenziale per definire le librerie e le tecnologie che l'applicazione utilizza. Di seguito, vediamo nel dettaglio cosa succede nel file `pom.xml` fornito, in particolare con l'integrazione di Spring JPA, Hibernate e altre dipendenze utili.

In questa esercitazione useremo anche Spring JPA e Hibernate. Importiamoli nel file `pom.xml`.

```xml
<dependencies>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<dependency>
		<groupId>com.mysql</groupId>
		<artifactId>mysql-connector-j</artifactId>
		<scope>runtime</scope>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>

</dependencies>
```

Le dipendenze sono dichiarate all'interno del tag `<dependencies>`. Ogni dipendenza include il `groupId`, l'`artifactId`, e a volte un `scope` che definisce la visibilità e la durata della dipendenza nel ciclo di vita del progetto.

---

### **Dipendenza `spring-boot-starter-web`**

La dipendenza `spring-boot-starter-web` è una delle dipendenze principali di Spring Boot per la creazione di applicazioni web. Include tutti i componenti necessari per sviluppare applicazioni RESTful, come il supporto per il framework Spring MVC, il motore di template (come Thymeleaf, se configurato), e il supporto per la gestione delle richieste HTTP.

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Questa dipendenza è essenziale per le applicazioni che richiedono un'interfaccia web, come applicazioni con endpoint RESTful. La presenza di questa dipendenza consente l'utilizzo di controller web e la gestione di richieste HTTP.

---

### **Dipendenza `mysql-connector-j`**

- La dipendenza `mysql-connector-j` è il driver JDBC per MySQL, che permette alla tua applicazione Java di connettersi a un database MySQL. Senza questa libreria, l'applicazione non potrebbe interagire con il database MySQL.
- Il tag `<scope>runtime</scope>` indica che questa dipendenza è necessaria solo durante l'esecuzione del programma e non durante la fase di compilazione.

```xml
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>
```

Questa dipendenza permette all'applicazione di comunicare con il database MySQL. È una parte fondamentale per qualsiasi applicazione che si connette a un database MySQL, come nel caso di questa applicazione che utilizza Spring JPA con Hibernate.

---

### **Dipendenza `spring-boot-starter-data-jpa`**

La dipendenza `spring-boot-starter-data-jpa` è un modulo che integra Spring Data JPA in un'applicazione Spring Boot. Spring Data JPA fornisce un framework per l'accesso ai dati basato su JPA (Java Persistence API), semplificando la gestione delle operazioni di persistenza (creazione, lettura, aggiornamento, eliminazione) tramite l'uso di repository.

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Questa dipendenza consente all'applicazione di sfruttare JPA e, quindi, Hibernate, per gestire le entità e la persistenza dei dati in modo dichiarativo, senza bisogno di scrivere manualmente il codice SQL per le operazioni CRUD. È una componente chiave per la persistenza degli oggetti nel database MySQL.

---

### **Dipendenza `spring-boot-starter-test`**

- La dipendenza `spring-boot-starter-test` è un modulo che include le librerie necessarie per i test in un'applicazione Spring Boot. Include librerie come JUnit, Hamcrest, Mockito e Spring Test, che consentono di scrivere e eseguire test unitari e di integrazione.
- Il tag `<scope>test</scope>` indica che questa dipendenza è necessaria solo per la fase di test e non viene inclusa nel pacchetto finale dell'applicazione.

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

Questa dipendenza è fondamentale per testare il comportamento dell'applicazione. Permette di scrivere test automatizzati che possono verificare che le diverse parti dell'applicazione (ad esempio, i controller, i servizi o i repository) funzionino correttamente prima che il codice venga distribuito in produzione.

---

### **Funzionamento complessivo delle dipendenze**

Quando Maven esegue il ciclo di costruzione dell'applicazione, queste dipendenze vengono scaricate dai repository e incluse nel classpath del progetto. Ognuna di queste dipendenze contribuisce a una parte specifica dell'applicazione:

- **`spring-boot-starter-web`**: Gestisce la logica web (controller REST, gestione delle richieste HTTP).
- **`mysql-connector-j`**: Fornisce la connettività al database MySQL.
- **`spring-boot-starter-data-jpa`**: Permette di utilizzare JPA per la gestione della persistenza dei dati e integra Hibernate come implementazione JPA.
- **`spring-boot-starter-test`**: Facilita la scrittura di test per l'applicazione, garantendo che il codice sia verificato in modo automatico durante lo sviluppo.

Queste dipendenze sono la base per lo sviluppo di un'applicazione che utilizza un'architettura a tre livelli, con un'interfaccia web per la gestione delle richieste HTTP, una persistenza dei dati tramite JPA/Hibernate, e test automatizzati per garantire la qualità del codice.

---

## **`application.properties`**


Il file `application.properties` in un'applicazione Spring Boot viene utilizzato per configurare il comportamento dell'applicazione, incluse le impostazioni relative alla connessione al database, la gestione delle transazioni e le caratteristiche del framework JPA (Java Persistence API), in particolare con l'uso di Hibernate. Di seguito, vediamo nel dettaglio cosa accade con ogni proprietà definita in questo file.

Configuriamo l'applicazione e la connessione al database nel file `src/main/resources/application.properties`.

```properties
spring.application.name=formatemp_spring-boot-4

# Configurazione Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/formatemp_rubrica?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurazione JPA (Hibernate)
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.generate_statistics=true
```

---

### **1. Configurazione dell'applicazione**

La proprietà `spring.application.name` definisce il nome dell'applicazione che viene utilizzato per identificare il contesto dell'applicazione durante l'esecuzione, ad esempio nei log e nei monitoraggi.

```properties
spring.application.name=formatemp_spring-boot-4
```

In questo caso, il nome dell'applicazione è impostato su `formatemp_spring-boot-4`. Questo nome potrebbe essere utilizzato per etichettare i log, i file di configurazione o nelle configurazioni di monitoraggio come Spring Cloud.

---

### **2. Configurazione del Datasource (Database)**

- **spring.datasource.url**: Questa proprietà definisce l'URL di connessione al database. In questo caso, si sta utilizzando un database MySQL locale (`localhost`) sulla porta `3306` con il nome del database `formatemp_rubrica`. La stringa `?serverTimezone=UTC` è aggiunta per configurare il fuso orario del server in UTC (questo può prevenire errori legati ai fusi orari).
- **spring.datasource.username**: Qui viene definito il nome utente da utilizzare per connettersi al database. In questo esempio, l'utente è `root`.
- **spring.datasource.password**: La password da utilizzare per la connessione al database. In questo caso è vuota (potrebbe essere un errore, se il database richiede una password).
- **spring.datasource.driver-class-name**: Definisce la classe del driver JDBC da utilizzare per MySQL, che è `com.mysql.cj.jdbc.Driver` in questo caso.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/formatemp_rubrica?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Queste proprietà configurano la connessione al database MySQL locale per l'applicazione. Quando l'applicazione Spring Boot si avvia, utilizza queste informazioni per stabilire una connessione al database specificato (`formatemp_rubrica`).

---

### **3. Configurazione JPA (Java Persistence API) e Hibernate**

```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.generate_statistics=true
```

- **spring.jpa.database-platform**: Questa proprietà specifica il dialetto di Hibernate da utilizzare per il database. Un "dialetto" è un adattamento di Hibernate per un particolare database. In questo caso, viene specificato il `MySQL8Dialect`, che è il dialetto per MySQL versione 8. Hibernate si comporterà in modo appropriato per interagire con il database MySQL 8.

- **spring.jpa.hibernate.ddl-auto**: La proprietà `ddl-auto` è utilizzata per gestire la generazione e l'aggiornamento dello schema del database. Il valore `update` indica a Spring Boot di aggiornare lo schema del database in base alle entità JPA definite nell'applicazione. Quindi, ogni volta che l'applicazione si avvia, Hibernate aggiornerà il database per allinearlo con le modifiche apportate alle entità (ad esempio, aggiungendo nuove colonne o tabelle). Le opzioni comuni per `ddl-auto` sono:

    - `none`: Non esegue nessuna operazione sul database.
    - `update`: Modifica lo schema del database in base alle entità JPA.
    - `create`: Crea lo schema (rimuovendo eventualmente quello esistente).
    - `create-drop`: Crea lo schema e lo elimina quando l'applicazione termina.

- **spring.jpa.show-sql**: La proprietà `show-sql` è impostata su `true` per abilitare la visualizzazione delle query SQL eseguite da Hibernate nei log dell'applicazione. Questo è utile per il debug o per monitorare le query inviate al database.

- **spring.jpa.properties.hibernate.format_sql**: Imposta su `true` la formattazione delle query SQL. Questo rende le query SQL più leggibili, con l'indentazione adeguata, per facilitare la lettura nei log.

- **spring.jpa.properties.hibernate.use_sql_comments**: Abilita l'uso dei commenti nelle query SQL. I commenti sono aggiunti alle query SQL da Hibernate, in modo da rendere il codice SQL generato più comprensibile (ad esempio, includendo il nome dell'entità o del campo che ha generato una query).

- **spring.jpa.properties.hibernate.generate_statistics**: Se impostato su `true`, questa proprietà fa sì che Hibernate generi statistiche sull'esecuzione delle query, come il numero di query eseguite, il tempo impiegato e altre metriche relative al comportamento di Hibernate. Queste informazioni sono utili per analizzare le performance dell'applicazione.

Queste proprietà configurano come Spring Boot e Hibernate gestiscono la persistenza dei dati. In particolare:
- Si usa il dialetto specifico per MySQL 8.
- Lo schema del database viene aggiornato automaticamente all'avvio dell'applicazione per riflettere i cambiamenti nelle entità JPA.
- Le query SQL vengono mostrate nei log per facilitare il debug.
- Viene generato un formato leggibile delle query SQL, con l'uso dei commenti per maggiore chiarezza.
- Le statistiche sulle query eseguite possono essere tracciate per analizzare le performance del sistema.

---

## **Contatto**

La classe `Contatto` che viene mostrata è un'entità JPA (Java Persistence API), che rappresenta una tabella del database. Ogni istanza della classe corrisponde a una riga della tabella `contatti` nel database MySQL. La configurazione di questa classe è fondamentale per la persistenza dei dati tramite JPA e Hibernate, come definito nelle dipendenze del file `pom.xml`.

```java
package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contatti")
public class Contatto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String numero;

    @Column
    private String note;

    public Contatto() {
    }

    public Contatto(Long id, String nome, String cognome, String email, String numero, String note) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero = numero;
        this.note = note;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", numero='" + numero + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
```

---

## **Annotazioni JPA per la configurazione della connessione al database**

### **`@Entity`**

L'annotazione `@Entity` indica che questa classe è una _entità JPA_. Ogni entità è mappata a una tabella del database. In questo caso, la classe `Contatto` sarà mappata alla tabella `contatti` nel database.

```java
@Entity
```

JPA utilizza questa annotazione per identificare la classe come un'entità da persistere nel database. Ogni oggetto `Contatto` creato sarà salvato come una riga nella tabella `contatti`.

---

### **`@Table`**

L'annotazione `@Table` viene utilizzata per specificare il nome della tabella del database a cui l'entità `Contatto` è associata. Se omessa, il nome della tabella è impostato di default come il nome della classe (`Contatto`), ma in questo caso viene esplicitamente definito come `contatti`.

```java
@Table(name = "contatti")
```

Questo è utile se si vuole mappare un'entità a una tabella con un nome diverso da quello della classe. In questo caso, la classe `Contatto` rappresenta la tabella `contatti` nel database.

---

### **`@Id` e `@GeneratedValue`**

- L'annotazione `@Id` è utilizzata per identificare il campo `id` come la chiave primaria della tabella. In altre parole, questo campo servirà per identificare in modo univoco ogni riga della tabella `contatti`.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` specifica che il valore del campo `id` è generato automaticamente dal database al momento dell'inserimento di una nuova riga. La strategia `GenerationType.IDENTITY` indica che MySQL gestisce il valore del `id` utilizzando un contatore automatico per ogni nuova riga inserita (auto-incremento).

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

La combinazione di queste annotazioni permette di generare e gestire automaticamente l'ID univoco per ogni `Contatto` nel database senza che l'utente debba preoccuparsi di farlo manualmente.

---

### **`@Column`**

- L'annotazione `@Column` è utilizzata per mappare i campi della classe Java alle colonne della tabella del database.
- Il parametro `nullable = false` indica che il campo non può essere `null` nel database, quindi è obbligatorio fornire un valore per quei campi quando viene creato un nuovo `Contatto`.
- Il parametro `unique = true` nella colonna `email` indica che il valore dell'email deve essere univoco nella tabella, evitando duplicati.

```java
@Column(nullable = false)
private String nome;

@Column(nullable = false)
private String cognome;

@Column(nullable = false, unique = true)
private String email;

@Column
private String numero;

@Column
private String note;
```

Ogni campo della classe `Contatto` viene mappato a una colonna della tabella `contatti` nel database. Queste annotazioni consentono di definire le caratteristiche delle colonne, come la non nullabilità o l'unicità dei valori.

---

### **Funzionamento nel contesto della connessione al database**

1. **Persistenza degli oggetti:**
    - La classe `Contatto` rappresenta un'entità che può essere salvata, aggiornata e recuperata dal database tramite l'uso di JPA (con l'implementazione di Hibernate).
    - Quando si crea un oggetto di tipo `Contatto`, ad esempio `new Contatto()`, e si invia questo oggetto tramite un repository JPA, Hibernate si occupa della traduzione tra l'oggetto Java e la riga del database. La configurazione `@Entity`, `@Id`, e `@Column` definisce come ogni attributo della classe deve essere mappato nel database.

2. **Generazione della tabella e delle colonne:**
    - Se l'applicazione viene configurata per eseguire il _DDL automatico_ (ad esempio tramite `spring.jpa.hibernate.ddl-auto=update`), Hibernate può creare la tabella `contatti` e le colonne corrispondenti nel database automaticamente all'avvio, utilizzando le informazioni fornite dalle annotazioni JPA.
    - In questo caso, Hibernate mapperà i campi `nome`, `cognome`, `email`, `numero`, e `note` alle colonne della tabella, applicando anche i vincoli di `nullable` e `unique`.

3. **Operazioni CRUD:**
    - Una volta che l'entità è definita con queste annotazioni, l'applicazione può eseguire operazioni CRUD (Create, Read, Update, Delete) sul database. Queste operazioni vengono eseguite tramite i repository JPA, che sono in grado di interagire con la tabella `contatti` grazie alla configurazione delle entità e delle colonne.

4. **Sincronizzazione con il database:**
    - L'integrazione tra Hibernate e il database MySQL è garantita dalla configurazione del `DataSource` nel file `application.properties`. La connessione al database viene stabilita automaticamente all'avvio dell'applicazione, e ogni volta che si effettua un'operazione di persistenza (come il salvataggio o il recupero di un contatto), JPA/Hibernate esegue la query appropriata sul database MySQL.

---

## **ContattoRepository**

`ContattoRepository` è un'interfaccia che si occupa di interagire con il database per eseguire operazioni di persistenza sui dati della classe `Contatto`. In particolare, questa interfaccia estende `JpaRepository`, che fornisce già tutte le funzionalità di base per il CRUD (Create, Read, Update, Delete) sul database.

```java
package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Contatto;

public interface ContattoRepository extends JpaRepository<Contatto, Long> {
    // Metodi personalizzati in futuro
}
```

- `JpaRepository` è un'interfaccia di Spring Data JPA che estende `PagingAndSortingRepository`, il quale a sua volta estende `CrudRepository`. In pratica, `JpaRepository` offre un set di operazioni di base per la gestione delle entità JPA, come il salvataggio, la ricerca, l'aggiornamento e la rimozione degli oggetti dal database, senza la necessità di scrivere codice SQL o HQL (Hibernate Query Language).

- Il tipo generico `Contatto` indica che questa repository gestisce entità di tipo `Contatto`. Il tipo `Long` rappresenta il tipo della chiave primaria della classe `Contatto` (in questo caso, il campo `id` di tipo `Long`).

- Estendendo `JpaRepository`, l'interfaccia `ContattoRepository` acquisisce automaticamente vari metodi per operazioni CRUD, come ad esempio:
    - `save(S entity)` - Salva un'entità nel database.
    - `findById(ID id)` - Recupera un'entità dato l'ID.
    - `findAll()` - Recupera tutte le entità della tabella.
    - `deleteById(ID id)` - Elimina un'entità in base all'ID.
    - `count()` - Conta il numero di entità nel database.

Questi metodi sono già implementati da Spring Data JPA, così non è necessario scrivere manualmente alcuna logica per interagire con il database.

---

### **Metodi personalizzati**

Sebbene `JpaRepository` fornisca già metodi generici per le operazioni CRUD, è possibile estendere il repository con metodi personalizzati. Ad esempio, supponiamo che si voglia trovare un contatto in base al nome o all'email. In tal caso, basta definire un nuovo metodo nell'interfaccia, e Spring Data JPA genererà automaticamente la query per noi.

Esempio di metodo personalizzato:

```java
public interface ContattoRepository extends JpaRepository<Contatto, Long> {
    List<Contatto> findByNome(String nome);
    Contatto findByEmail(String email);
}
```

Spring Data JPA riconosce automaticamente che `findByNome` è una query che deve cercare nella colonna `nome` della tabella `contatti`, mentre `findByEmail` cercherà nella colonna `email`. Non è necessario scrivere il codice SQL corrispondente: Spring Data JPA genera la query in modo dinamico basato sul nome del metodo.

---

### **Operazioni di persistenza tramite Repository**

1. **Salvataggio dei dati:**
    - Utilizzando il metodo `save(Contatto contatto)`, è possibile salvare un oggetto `Contatto` nel database. Se l'oggetto ha già un ID (ad esempio, un oggetto esistente con un ID non nullo), Spring JPA eseguirà un'operazione di aggiornamento; altrimenti, eseguirà un'operazione di inserimento.

2. **Ricerca dei dati:**
    - Con il metodo `findById(Long id)`, si può recuperare un singolo contatto dato l'ID. Se esiste un contatto con quell'ID, il metodo restituirà un `Optional<Contatto>`, altrimenti restituirà un `Optional.empty()`.
    - Il metodo `findAll()` restituirà una lista di tutti i contatti salvati nella tabella `contatti`.

3. **Eliminazione dei dati:**
    - Il metodo `deleteById(Long id)` elimina il contatto con l'ID specificato. Se l'entità non esiste, non farà nulla.

4. **Contare i dati:**
    - Il metodo `count()` restituirà il numero totale di contatti presenti nel database.

---

### Query Personalizzate con `@Query`

Se desiderassimo scrivere query personalizzate che non possono essere generate tramite la convenzione del nome del metodo, potremmo utilizzare l'annotazione `@Query`. Ecco un esempio:

```java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContattoRepository extends JpaRepository<Contatto, Long> {

    @Query("SELECT c FROM Contatto c WHERE c.nome = :nome")
    List<Contatto> findContattiByNome(@Param("nome") String nome);
}
```

In questo caso, la query personalizzata utilizza JPQL (Java Persistence Query Language) per cercare i contatti con un nome specifico.

---

## **ContattoService**

La classe `ContattoService` si occupa di gestire la logica di business relativa agli oggetti `Contatto`. Essa interagisce con il repository `ContattoRepository` per ottenere, manipolare e restituire i dati. È un esempio tipico di un service layer in un'applicazione Spring, che separa la logica di business dall'accesso diretto ai dati (gestito dal repository).

```java
package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contatto;
import com.example.demo.repositories.ContattoRepository;

@Service
public class ContattoService {

    private final ContattoRepository contattoRepository;

    @Autowired
    public ContattoService(ContattoRepository contattoRepository) {
        this.contattoRepository = contattoRepository;
    }

    public List<Contatto> all() {
        List<Contatto> contatti = contattoRepository.findAll();
        return contatti;
    }
}
```

---

### **`@Service`**

L'annotazione `@Service` è una specializzazione di `@Component` di Spring, che indica che la classe è un componente del service layer. Questa annotazione è usata per segnalare che la classe fornisce servizi di business, come la gestione dei contatti in questo caso.

```java
@Service
public class ContattoService {
```

Quando Spring esegue la scansione dei componenti durante l'avvio dell'applicazione, la classe annotata con `@Service` viene automaticamente registrata nel contesto dell'applicazione come bean gestito da Spring. Questo significa che è possibile iniettarla in altre classi tramite dependency injection.

---

### **`private final ContattoRepository contattoRepository;`**

`ContattoRepository` è il repository che gestisce la persistenza dei dati relativi agli oggetti `Contatto` nel database. La classe `ContattoService` dipende da questo repository per eseguire operazioni di lettura e scrittura sui dati.

```java
private final ContattoRepository contattoRepository;
```

La dipendenza da `ContattoRepository` è iniettata tramite il costruttore, come mostrato nel codice successivo. La variabile `contattoRepository` è dichiarata come `final`, il che significa che può essere inizializzata solo una volta, per garantire che la dipendenza venga configurata correttamente al momento della creazione della classe `ContattoService`.

---

### **`@Autowired` e Costruttore**

L'annotazione `@Autowired` indica a Spring di iniettare il bean di tipo `ContattoRepository` nel costruttore della classe `ContattoService` quando un'istanza di questa classe viene creata.

`@Autowired` è una delle modalità più comuni per fare **Dependency Injection** in Spring. In questo caso, Spring riconosce che il costruttore richiede un'istanza di `ContattoRepository` e provvede a fornirla automaticamente.

```java
@Autowired
public ContattoService(ContattoRepository contattoRepository) {
    this.contattoRepository = contattoRepository;
}
```

Il costruttore assicura che il repository venga passato all'istanza di `ContattoService` in fase di inizializzazione, il che consente alla classe di accedere ai metodi del repository per interagire con il database.

---

### **`findAll()`**

Il metodo personalizzato `all()` è responsabile di recuperare tutti i contatti dal database. Utilizza il metodo `findAll()` del `ContattoRepository` per ottenere tutti i contatti dalla tabella `contatti`. `findAll()` restituisce una lista di oggetti `Contatto`.

```java
public List<Contatto> all() {
    List<Contatto> contatti = contattoRepository.findAll();
    return contatti;
}
```

- Il metodo esegue una chiamata al repository per ottenere tutte le entità `Contatto` salvate nel database e le restituisce come una lista (`List<Contatto>`). La lista viene quindi inviata al chiamante del metodo, che può utilizzarla come preferisce (per esempio, per visualizzare i contatti in un'interfaccia utente).

- Si noti che il metodo `all()` è un esempio di un'operazione di lettura (Read) nel contesto del pattern CRUD. Sebbene in questo caso il metodo sia molto semplice, in scenari reali il service layer potrebbe contenere logiche più complesse, come la gestione di transazioni o la manipolazione dei dati prima di restituirli.

---

## **ContactController**

La classe `ContactController` è il controller di tipo REST che gestisce le richieste HTTP relative ai contatti. Utilizzando il framework Spring, il controller espone endpoint API che possono essere chiamati da client (ad esempio, un'applicazione frontend o un'altra parte dell'applicazione) per interagire con i dati dei contatti.

```java
package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contatto;
import com.example.demo.services.ContattoService;

@RestController
@RequestMapping("/api/contatti")
public class ContactController {

    private final ContattoService contattoService;

    @Autowired
    public ContactController(ContattoService contattoService) {
        this.contattoService = contattoService;
    }

    @GetMapping("/")
    public List<Contatto> all() {
        List<Contatto> contatti = contattoService.all();
        return contatti;
    }
}
```

---

### **`@RestController`**

- L'annotazione `@RestController` è una combinazione di due annotazioni: `@Controller` e `@ResponseBody`.
- `@Controller` indica che la classe è un controller di Spring, mentre `@ResponseBody` indica che i metodi della classe restituiscono direttamente i dati nel corpo della risposta HTTP (ad esempio, un oggetto JSON) anziché un nome di vista.

```java
@RestController
public class ContactController {
```

- In pratica, `@RestController` è una specializzazione di `@Controller` che è ideale per creare API RESTful, dove i dati vengono restituiti direttamente al client, solitamente in formato JSON o XML.
- Quando un client effettua una richiesta HTTP, la classe `ContactController` gestisce tale richiesta e restituisce una risposta. L'annotazione `@RestController` rende la classe idonea per lavorare con API RESTful.

---

### **`@RequestMapping("/api/contatti")`**

- `@RequestMapping` è un'annotazione che definisce il percorso (URL) di base per i metodi all'interno del controller. In questo caso, l'annotazione indica che tutte le rotte gestite da `ContactController` inizieranno con `/api/contatti`.

```java
@RequestMapping("/api/contatti")
public class ContactController {
```

- La combinazione di `@RequestMapping` con i metodi `@GetMapping` o altri metodi HTTP consente di mappare i percorsi delle richieste in modo chiaro. Ad esempio, tutte le richieste HTTP relative ai contatti partiranno dall'URL `/api/contatti`.

---

### **Iniezione del Service nel Controller con `@Autowired`**

- Il controller dipende dal `ContattoService`, che è iniettato nel costruttore della classe grazie all'annotazione `@Autowired`.
- L'annotazione `@Autowired` indica a Spring di risolvere e iniettare automaticamente l'istanza di `ContattoService` nel controller.

```java
private final ContattoService contattoService;

@Autowired
public ContactController(ContattoService contattoService) {
    this.contattoService = contattoService;
}
```

- Il costruttore crea un'istanza di `ContactController`, passando al costruttore il bean `ContattoService`. Questo approccio promuove la separazione delle preoccupazioni, dove il controller gestisce solo le richieste HTTP, mentre la logica di business è delegata al service.

---

### **`@GetMapping("/")`**

- Il metodo `all()` è mappato alla rotta `/api/contatti/` e restituisce una lista di oggetti `Contatto`.
- Utilizza il service `ContattoService` per ottenere tutti i contatti dal database chiamando il metodo `all()` del service.

```java
@GetMapping("/")
public List<Contatto> all() {
    List<Contatto> contatti = contattoService.all();
    return contatti;
}
```

- Quando un client invia una richiesta GET a `/api/contatti/`, il metodo `all()` viene eseguito. Questo metodo chiama il `ContattoService`, che interroga il repository `ContattoRepository` per ottenere tutti i contatti salvati nel database.
- Il risultato è una lista di oggetti `Contatto`, che viene serializzata automaticamente da Spring in formato JSON e restituita come risposta alla richiesta HTTP.

---

### **Flusso di Esecuzione**

1. Un client invia una richiesta GET all'URL `/api/contatti/`.
2. Spring indirizza la richiesta al metodo `all()` di `ContactController`.
3. Il metodo `all()` chiama il service `ContattoService` per recuperare tutti i contatti.
4. `ContattoService` interagisce con il repository `ContattoRepository`, che accede al database e restituisce tutti i contatti.
5. La lista di contatti viene restituita come risposta in formato JSON, grazie a `@RestController`.

---

# **Altre operazioni CRUD**

## **ContattiController**

```java
package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contatto;
import com.example.demo.services.ContattoService;

@RestController
@RequestMapping("/api/contatti")
public class ContactController {

	private final ContattoService contattoService;

	@Autowired
	public ContactController(ContattoService contattoService) {
		this.contattoService = contattoService;
	}

	@GetMapping("/")
	public List<Contatto> all() {
		return contattoService.all();
	}

	@GetMapping("/{id}")
	public Contatto get(@PathVariable Long id) {
		return contattoService.get(id);
	}

	@PostMapping("/")
	public Contatto create(@RequestBody Contatto contatto) {
		return contattoService.create(contatto);
	}

	@PutMapping("/{id}")
	public Contatto update(@RequestBody Contatto contatto, @PathVariable Long id) {
		return contattoService.update(contatto, id);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		contattoService.delete(id);
		return "Contatto con id " + id + " eliminato correttamente.";
	}
}
```

---

## **ContattoService**

La classe `ContattoService` rappresenta il livello di servizio dell'applicazione. Il suo scopo è gestire la logica di business relativa ai contatti e interfacciarsi con il repository `ContattoRepository` per interagire con il database.

```java
package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contatto;
import com.example.demo.repositories.ContattoRepository;

@Service
public class ContattoService {

    private final ContattoRepository contattoRepository;

    @Autowired
    public ContattoService(ContattoRepository contattoRepository) {
        this.contattoRepository = contattoRepository;
    }

    public List<Contatto> all() {
        List<Contatto> contatti = contattoRepository.findAll();
        return contatti;
    }

    public Contatto get(Long id) {
        return contattoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contatto non trovato"));
    }

    public Contatto create(Contatto contatto) {
        return contattoRepository.save(contatto);
    }

    public Contatto update(Contatto contatto, Long id) {
        Contatto contattoEsistente = contattoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
        contattoEsistente.setNome(contatto.getNome());
        contattoEsistente.setCognome(contatto.getCognome());
        contattoEsistente.setEmail(contatto.getEmail());
        contattoEsistente.setNumero(contatto.getNumero());
        contattoEsistente.setNote(contatto.getNote());

        return contattoRepository.save(contattoEsistente);
    }

    public void delete(Long id) {
        Contatto contatto = contattoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
        contattoRepository.delete(contatto);
    }
}
```

---

### **1. Dipendenza da `ContattoRepository`**

```java
private final ContattoRepository contattoRepository;

@Autowired
public ContattoService(ContattoRepository contattoRepository) {
    this.contattoRepository = contattoRepository;
}
```

- Il repository `ContattoRepository` viene iniettato nel servizio tramite **dependency injection** nel costruttore.
- `@Autowired` permette a Spring di creare automaticamente un'istanza del repository e di passarla a `ContattoService`.
- Il repository funge da interfaccia per accedere alla tabella `contatti` nel database.

---

### **2. Metodo `all()`: Recupera tutti i contatti**

```java
public List<Contatto> all() {
    List<Contatto> contatti = contattoRepository.findAll();
    return contatti;
}
```

- Chiama `contattoRepository.findAll()`, che esegue una **query SELECT** per ottenere tutti i record dalla tabella `contatti`.
- Restituisce una **lista di oggetti `Contatto`**.

---

### **3. Metodo `get(Long id)`: Recupera un contatto per ID**

```java
public Contatto get(Long id) {
    return contattoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
}
```

- Utilizza `findById(id)`, che esegue una **query SELECT con WHERE id = ?**.
- Se il contatto non esiste, **lancia un'eccezione** con messaggio `"Contatto non trovato"`.

---

### **4. Metodo `create(Contatto contatto)`: Crea un nuovo contatto**

```java
public Contatto create(Contatto contatto) {
    return contattoRepository.save(contatto);
}
```

- Chiama `save(contatto)`, che:
    - Se l'ID è `null`, esegue una **query INSERT** per creare un nuovo record.
    - Se l'ID è valorizzato, esegue una **query UPDATE**.
- Restituisce l'oggetto `Contatto` salvato con l'ID generato.

---

### **5. Metodo `update(Contatto contatto, Long id)`: Aggiorna un contatto**

```java
public Contatto update(Contatto contatto, Long id) {
    Contatto contattoEsistente = contattoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
    contattoEsistente.setNome(contatto.getNome());
    contattoEsistente.setCognome(contatto.getCognome());
    contattoEsistente.setEmail(contatto.getEmail());
    contattoEsistente.setNumero(contatto.getNumero());
    contattoEsistente.setNote(contatto.getNote());

    return contattoRepository.save(contattoEsistente);
}
```

- Recupera il contatto dal database usando `findById(id)`. Se non esiste, lancia un'eccezione.
- Aggiorna i campi del contatto esistente con i nuovi valori.
- Chiama `save(contattoEsistente)`, che esegue una **query UPDATE**.

---

### **6. Metodo `delete(Long id)`: Elimina un contatto**

```java
public void delete(Long id) {
    Contatto contatto = contattoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
    contattoRepository.delete(contatto);
}
```

- Recupera il contatto per ID. Se non esiste, lancia un'eccezione.
- Chiama `delete(contatto)`, che esegue una **query DELETE** nel database.

---

## **ContattoRepository**

Non sono necessarie modifiche al repository, poiché `JpaRepository` fornisce già metodi come `save()`, `findAll()`, `findById()`, e `delete()` che soddisfano le esigenze delle operazioni CRUD.

Per completare le operazioni CRUD (Create, Read, Update, Delete) nel controller `ContactController`, è necessario apportare delle modifiche anche al service e al repository. Ecco i passaggi da seguire per implementare ciascuna operazione CRUD:

---

## **Testare l'API**

Non ci resta che testare i vari endpoint dell'API utilizzando un'applicazione come **Postman** oppure **Insomnia**. Vediamo degli esempi di test:

### **Recuperare tutti i contatti**

```http
GET http://localhost:8080/api/contatti/
```

Dovrebbe risponderci con un JSON con tutti i contatti nel database:

```json
[
    {
        "id": 1,
        "nome": "Mario",
        "cognome": "Rossi",
        "email": "mario.rossi@example.com",
        "numero": "3331234567",
        "note": "Amico stretto"
    },
    {
        "id": 2,
        "nome": "Laura",
        "cognome": "Verdi",
        "email": "laura.verdi@example.com",
        "numero": "3479876543",
        "note": "Collega di lavoro"
    },
    //...
]
```

---

### **Recuperare un singolo contatto**

```http
GET http://localhost:8080/api/contatti/2
```

Dovrebbe risponderci con un JSON con il singolo contatto dal database filtrato dall'id dinamico:

```json
{
    "id": 2,
    "nome": "Laura",
    "cognome": "Verdi",
    "email": "laura.verdi@example.com",
    "numero": "3479876543",
    "note": "Collega di lavoro"
}
```

---

### **Creare un singolo contatto**

```http
POST http://localhost:8080/api/contatti/
```

Nella richiesta HTTP di tipo POST dobbiamo anche passare un oggetto di dati, ovvero le informazioni per creare il contatto. Ad esempio, in Postman troveremo la sezione **Body** > **raw** > **JSON** ed inviamo un oggetto JSON con i dati corrispondenti ai campi della tabella `contatti`. Non scriviamo l'`id` in quanto verrà creato automaticamente nel database.

```json
{
    "nome": "Tizio",
    "cognome": "Cognome Tizio",
    "email": "tizio@email.com",
    "numero": "12401285107235",
    "note": "Mangia la pizza con l'ananas"
}
```

---

### **Aggiornare un singolo contatto**

```http
PUT http://localhost:8080/api/contatti/11
```

Sulla stessa linea dell'operazione precedente, testiamo anche la richiesta HTTP di tipo PUT (oppure PATCH) dobbiamo anche passare un oggetto di dati, ovvero le informazioni per aggiornare un contatto esistente.

Nell'esempio aggiorniamo il contatto appena creato, che avrà quindi id = 11. In Postman inviamo l'oggetto JSON come prima ma con i dati aggiornati, sempre con i dati corrispondenti ai campi della tabella `contatti`.

```json
{
    "nome": "Caio",
    "cognome": "Cognome Caio",
    "email": "caio@email.com",
    "numero": "12401285107235",
    "note": "Mangia la pizza con l'ananas"
}
```

---

### **Eliminare un singolo contatto**

```http
DELETE http://localhost:8080/api/contatti/11
```

Infine, testiamo anche la richiesta HTTP di tipo DELETE per eliminare un contatto. Dobbiamo solo specificare l'id di un contatto esistente nel database.

---

# Conclusioni

Abbiamo creato una prima applicazione backend in Spring Boot connettendoci con il database MySQL che si collega all'esterno esponendo un'API. Su questa REST API possiamo, ad esempio, collegare un framework front end per creare interfacce grafiche che comunicano con l'utente finale.