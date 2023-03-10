package com.freestack.persistence.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(name = "xrel_actorMovie",
    joinColumns=@JoinColumn(name="actor_id",  referencedColumnName = "id"),
    inverseJoinColumns=@JoinColumn(name="film_id", referencedColumnName="id")
    )
    private List<Movie> bookOfActor;

    public List<Movie> getBookOfActor() {
        return bookOfActor;
    }

    public void addMovieToActorBook(Movie movie) {
        if(this.bookOfActor == null){
            this.bookOfActor = new ArrayList<>();
        }
        this.bookOfActor.add(movie);
    }

    public void setBookOfActor(List<Movie> book) {
        this.bookOfActor = book;
    }

    public Actor() {
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Actor{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
