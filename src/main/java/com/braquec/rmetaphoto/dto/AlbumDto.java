package com.braquec.rmetaphoto.dto;

import com.braquec.rmetaphoto.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class AlbumDto {
    private Long id;
    private String title;
    private User user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PhotoDto> photos;

    public AlbumDto() {
    }

    public AlbumDto(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public List<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDto> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDto albumDto = (AlbumDto) o;
        return id.equals(albumDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
