package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Company> companies = getCompanies();


        //groupingBy + mapping = Map<String, List<Company>> - компания и сколько раз встретилась и верни в виде списка
        //groupingBy + counting = Map<String, Integer> - компания, сколько раз встретилась в списке

        //companyName, moneyTotal
        //


        System.out.println("Company - Total Raised");
        companies.stream()
                .collect(Collectors.toMap(
                        key -> key.getCompany(),                                    //google
                        value -> value.getRaisedAmount(),                           //1,000,000 + 200,000 | 2,000,000,000
                        (raisedAmount1, raisedAmount2) -> raisedAmount1 + raisedAmount2
                ))
                .forEach((key, value) -> System.out.println(key + " - " + value));

        System.out.println();
        System.out.println("Company - Times raised");
        companies.stream()

                //Map<String, Long>
                .collect(Collectors.groupingBy(
                        x -> x.getCompany(),
                        Collectors.counting()
                ))
                .forEach((key, value) -> System.out.println(key + " - " + value));

        System.out.println();

        System.out.println("Company Aggregation");

        //                 Map<String           ,       Integer>
        //List<Company> -> Map<название компании, кол-во раундов>

        //                 Map<String           ,       Integer>
        //List<Company> -> Map<название компании,       общая сумма>

        //List<Компания, Сколько раз получали деньги, Общая сумма>
        //CompanyAggregation

        System.out.println("Raised");
        companies.stream()
                .map(x -> {
                    CompanyAggregation companyAggregation = new CompanyAggregation();
                    companyAggregation.setCompany(x);
                    companyAggregation.setTimesRaisedAmount(1);
                    companyAggregation.setTotalRaisedAmount(x.getRaisedAmount());

                    //google, 1, 1.000.000
                    //facebook, 1
                    //google, 1, 200.000
                    //facebook, 1
                    //facebook, 1
                    //google, 1, 300.000

                    //Map<google, 3>


                    return companyAggregation;
                })
                //Map<String, CompanyAggregation>
                .collect(Collectors.toMap(
                        //
                        companyAggregation -> companyAggregation.getCompany().getCompany(),
                        value -> value, //CompanyAggregation
                        (a, b) -> {
                            Integer firstTimes = a.getTimesRaisedAmount();
                            Integer secondTimes = b.getTimesRaisedAmount();
                            a.setTimesRaisedAmount(firstTimes + secondTimes); //1

                            Integer firstAmount = a.getTotalRaisedAmount();
                            Integer secondAmount = b.getTotalRaisedAmount();

                            a.setTotalRaisedAmount(firstAmount + secondAmount);
                            return a;
                        }
                ))
                //Map<String, CompanyAggregation> -> Stream<T>
                //Map<String, Integer> -> Set<Entry<String, Integer>>

                .entrySet()//Set<Entry>
                .stream() //Stream<Entry>

                //sorting by money amount
                //default sorting - 1..99
                //reverseOrder    - 99..1
                .sorted(Comparator.comparing(x -> x.getValue().getTotalRaisedAmount(), Comparator.reverseOrder()))
                .limit(5)
                //reverse order + limit(5) = show top 5 best
                //default order + limit(5) = show top 5 last


                //Map<String, Integer>
//                .forEach((companyNameKey,companyAggregation) -> System.out.println(companyNameKey + " - " + companyAggregation.getTimesRaisedAmount() + " - " + companyAggregation.getTotalRaisedAmount() + " " + companyAggregation.getCompany().getRaisedCurrency()));
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue().getTimesRaisedAmount() + " - " + entry.getValue().getTotalRaisedAmount() + " " + entry.getValue().getCompany().getRaisedCurrency()));

        //


        //CompanyAggregation: company, rounds count


        //filter non 'web'
        long nonWebCompanies = companies.stream()
                .filter(x -> x.getCategory() != null && !x.getCategory().equals("web"))
                .count();

        System.out.println("'Non-Web' companies count " + nonWebCompanies);

        Map<String, Long> categoriesMap = companies.stream()
                .collect(
                        Collectors.groupingBy(
                                x -> {
                                    boolean hasNoCategory = x.getCategory() == null || x.getCategory().isEmpty();
                                    if (hasNoCategory) {
                                        return "other";
                                    } else {
                                        return x.getCategory();
                                    }
                                },
                                Collectors.counting()
                        )
                );

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Total companies: " + companies.size());
        categoriesMap
//                .entrySet()
//                .stream()
//                .sorted(Comparator.comparing(x -> x.getValue(), Comparator.reverseOrder()))
//                .collect(
//                        Collectors.toMap(
//                                x -> x.getKey(),
//                                y -> y.getValue(),
//                                (a, b) -> a, //not important
//                                () -> new TreeMap<>(Comparator.comparing(z -> z))
//                        )
//                )
                .forEach((category, count) -> {

                    double percentPerMarket = (double) count / companies.size();
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    System.out.println(category + " - " + count + " - " + decimalFormat.format(percentPerMarket * 100) + "%");
                });

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("categories sorted");
        categoriesMap.entrySet()
                .stream()
                .map(x -> new Pair<>(x.getValue(), x.getKey())) //<key, value> -> <value, key>
                .sorted(Comparator.comparing(x -> x.getKey(), Comparator.reverseOrder()))
                .forEach(x -> {
                    double percentPerMarket = (double) x.getKey() / companies.size();
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    System.out.println(x.getValue() + " - " + x.getKey() + " - " + decimalFormat.format(percentPerMarket * 100) + "%");
                });


    }

    private static List<Company> getCompanies() throws IOException {
        List<Company> companies =
                Files
                        .lines(Paths.get("TechCrunchcontinentalUSA.csv")) //Stream<String>
                        .skip(1)
                        .map(x -> { //String -> Company
                            String[] elements = x.split(",");
                            Company company = new Company(
                                    elements[0],
                                    elements[1],
                                    elements[2] == null || elements[2].length() == 0 ? null : Integer.parseInt(elements[2]),
                                    elements[3],
                                    elements[4],
                                    elements[5],
                                    elements[6],
                                    elements[7] == null || elements[7].isEmpty() ? null : Integer.parseInt(elements[7]),
                                    elements[8],
                                    elements[9]
                            );

                            return company;
                        }) //Stream<Company>
                        .collect(Collectors.toList()); //ArrayList<Company>

        return companies;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}


