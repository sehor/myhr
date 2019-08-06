package myhr.data.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "me")
public class Author {
	
	private String name;
	private String address;
	private List<String> favorites=new ArrayList<String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<String> favorites) {
		this.favorites = favorites;
	}
	

}
