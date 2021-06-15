package Comsc075;/*
 * Duy Nguyen
 * Comsc075.CityInfo.java
 * Reads cities from files and calculates statistics from country code
 */

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// Creates individual city object
class City {

    private String country;
    private String name;
    private int population;

    public City(String country, String name, int population) {
        this.country = country;
        this.name = name;
        this.population = population;
    }

    // Getters/Setters
    public String getCountry() {
        return country;
    }
    public String getName() {
        return name;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return  country + "; " + name + "; " + population;
    }

}

// Gets city info from files
public class CityInfo {

    // Reads city file lines that have proper format
    public static ArrayList<City> readCityFile(String fileName) {

        ArrayList<City> cityList = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner fileContent = new Scanner(file);

            while (fileContent.hasNextLine()) {

                String line = fileContent.nextLine();
                String[] properties = line.split(";");

                if (properties.length != 3) {
                    System.out.printf("\"%s\" does not have three entries.\n",
                            line);
                } else {
                    try {
                        cityList.add(new City(
                                properties[0],
                                properties[1],
                                Integer.valueOf(properties[2])));
                    }
                    catch (NumberFormatException ex) {
                        System.out.printf("\"%s\" does not have correct data type(s). \n", line);
                    }
                }
            }

            fileContent.close();
        }

        catch (FileNotFoundException ex) {
            System.out.printf("File, %s, could not be opened or found ...\n", fileName);
        }

        return cityList;
    }

    // Calculates statistics based off country from list
    public static int statistics(String countryCode, ArrayList<City> cityList) {

        countryCode.toUpperCase();
        int cities = 0;
        double sumOfPopulation = 0;
        double avgPopulation = 0;

        for (City city: cityList) {
                if (city.getCountry().equals(countryCode)) {
                    cities++;
                    sumOfPopulation += city.getPopulation();
                }
        }

        if (cities != 0) {
            avgPopulation = sumOfPopulation / cities;
            System.out.printf("Average Population: %.2f\n", avgPopulation);
            System.out.printf("Number of cities: %d\n", cities);
        }
        else {
            System.out.println("No cities found\n");
        }

        return cities;
    }

    // Writes cities' info into file from country
    public static void writeCountryFile(String countryCode,
                                        ArrayList<City> cityList) {

        String fileName = countryCode + ".dat";
        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(file)) {
            for (City city: cityList) {
                if (city.getCountry().equals(countryCode)) {
                    writer.println(city);
                }
            }
            System.out.printf("File saved as %s\n\n", fileName);
        }

        catch (IOException ex) {
            System.out.printf("Error writing %s\n\n", fileName);
            System.out.println(ex.getMessage());
        }

    }

    // Reads city data from file and returns statistics on specified Country.
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Reading city file ...\n");
        ArrayList<City> cityList = readCityFile("citylist.dat");

        System.out.println(); // Formatting

        if (cityList.size() != 0) {

            boolean exit = false;

            while (!exit) {

                Scanner input = new Scanner(System.in);
                System.out.printf("Enter a two-letter country code, or press Enter to quit: ");
                String countryCode = input.nextLine();

                if (countryCode.equals("")) {
                    exit = true;
                } else {
                    if (statistics(countryCode, cityList) > 0) {
                        writeCountryFile(countryCode, cityList);
                    }
                }
            }
        }
        else {
            System.out.println("The file does not contain any cities");
        }
    }
}
