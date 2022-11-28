package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {
  }

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;


  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude;

  public double getLatitude() {
    return latitude;
  }

  private double longitude;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  public static List<Place> search(final List<Place> places, final String search) {
    if (places == null || search == null) {
      throw new IllegalArgumentException();
    }
    String s = search.trim().toLowerCase();
    if (places.isEmpty() || search.equals("")) {
      return places;
    }
    List<Place> ans = new ArrayList<>();
    for (Place p : places) {
      String str = "";
      for (char current : p.description.toLowerCase().toCharArray()) {
        if (Character.isAlphabetic(current)
            || Character.isDigit(current)
            || Character.isWhitespace(current)) {
          str += current;
        } else if (current == '.'
            || current == '!'
            || current == '!'
            || current == '?'
            || current == ','
            || current == ';'
            || current == '/') {
          str += " ";
        }
      }
      String[] result = str.split(" ");
      for (int i = 0; i < result.length; i++) {
        if (result[i].equals(s)) {
          System.out.println(result[i]);
          ans.add(p);
          break;
        }
      }
    }
    return ans;
  }
}



