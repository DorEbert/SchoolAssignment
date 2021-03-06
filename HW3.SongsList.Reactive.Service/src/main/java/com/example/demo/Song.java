package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Songs")
public class Song {
	@Id
	private String id;
	
	private String songId;
	
	private String songListId;

	public Song() {
	}

	public Song(String songId) {
		this.songId = songId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}
	
	public String getSongListId() {
		return songListId;
	}

	public void setSongListId(String songListId) {
		this.songListId = songListId;
	}

	public SongDTO toDTO() {
		return new SongDTO(songId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((songId == null) ? 0 : songId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (songId == null) {
			if (other.songId != null)
				return false;
		} else if (!songId.equals(other.songId))
			return false;
		return true;
	}
}
